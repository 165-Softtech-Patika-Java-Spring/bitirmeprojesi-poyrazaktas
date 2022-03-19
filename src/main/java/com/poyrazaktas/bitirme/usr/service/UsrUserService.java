package com.poyrazaktas.bitirme.usr.service;

import com.poyrazaktas.bitirme.usr.converter.UsrUserMapper;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserUpdateReqDto;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
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
    public List<UsrUserDto> findAll(){
        List<UsrUser> userList = userEntityService.findAll();
        return UsrUserMapper.INSTANCE.convertToUserDtoList(userList);
    }

    public UsrUserDto get(Long id){
        UsrUser user = userEntityService.getByIdWithControl(id);
        return UsrUserMapper.INSTANCE.convertToUserDto(user);
    }

    public UsrUserDto save(UsrUserSaveReqDto saveReqDto){
        UsrUser user = UsrUserMapper.INSTANCE.convertToUser(saveReqDto);

        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);

        user = userEntityService.save(user);
        return UsrUserMapper.INSTANCE.convertToUserDto(user);
    }

    // TODO user şifresi update edildiyse hashleyerek koy
    public UsrUserDto update(UsrUserUpdateReqDto updateReqDto){
        UsrUser user = UsrUserMapper.INSTANCE.convertToUser(updateReqDto);
        user = userEntityService.save(user);
        return UsrUserMapper.INSTANCE.convertToUserDto(user);
    }

    public void delete(Long id){
        UsrUser user = userEntityService.getByIdWithControl(id);
        userEntityService.delete(user);
    }
}
