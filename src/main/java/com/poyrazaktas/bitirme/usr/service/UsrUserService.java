package com.poyrazaktas.bitirme.usr.service;

import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.usr.converter.UsrUserMapper;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserUpdateReqDto;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import com.poyrazaktas.bitirme.usr.enums.UsrUserErrorMessage;
import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsrUserService {
    private final UsrUserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;

    public List<UsrUserDto> findAll() {
        List<UsrUser> userList = userEntityService.findAll();
        return UsrUserMapper.INSTANCE.convertToUserDtoList(userList);
    }

    public UsrUserDto get(Long id) {
        UsrUser user = userEntityService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(UsrUserErrorMessage.ITEM_NOT_FOUND));
        return UsrUserMapper.INSTANCE.convertToUserDto(user);
    }

    public UsrUserDto save(UsrUserSaveReqDto saveReqDto) {
        UsrUser user = UsrUserMapper.INSTANCE.convertToUser(saveReqDto);

        encodeUserPassword(user);

        user = userEntityService.save(user);
        return UsrUserMapper.INSTANCE.convertToUserDto(user);
    }

    public UsrUserDto update(UsrUserUpdateReqDto updateReqDto) {
        UsrUser user = UsrUserMapper.INSTANCE.convertToUser(updateReqDto);

        encodeUserPassword(user);

        user = userEntityService.save(user);
        return UsrUserMapper.INSTANCE.convertToUserDto(user);
    }

    private void encodeUserPassword(UsrUser user) {
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
    }

    public void delete(Long id) {
        UsrUser user = userEntityService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(UsrUserErrorMessage.ITEM_NOT_FOUND));
        userEntityService.delete(user);
    }
}
