package com.poyrazaktas.bitirme.sec.service;

import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.sec.dto.JwtUserLoginReqDto;
import com.poyrazaktas.bitirme.sec.enums.JwtConstant;
import com.poyrazaktas.bitirme.sec.enums.SecSecurityErrorMessage;
import com.poyrazaktas.bitirme.sec.security.JwtTokenGenerator;
import com.poyrazaktas.bitirme.sec.security.JwtUserDetails;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import com.poyrazaktas.bitirme.usr.enums.UsrUserErrorMessage;
import com.poyrazaktas.bitirme.usr.service.UsrUserService;
import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsrUserService userService;
    private final UsrUserEntityService userEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UsrUserDto register(UsrUserSaveReqDto saveReqDto) {
        UsrUserDto userDto = userService.save(saveReqDto);
        return userDto;
    }

    public String login(JwtUserLoginReqDto userLoginReqDto) {

        validateLoginRequestDto(userLoginReqDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginReqDto.getUserName(), userLoginReqDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = JwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    private void validateLoginRequestDto(JwtUserLoginReqDto userLoginReqDto) {
        if (!StringUtils.hasText(userLoginReqDto.getUserName()) || !StringUtils.hasText(userLoginReqDto.getPassword())){
            throw new NullPointerException(SecSecurityErrorMessage.REQUEST_BODY_IS_EMPTY.getMessage());
        }
    }

    public Long getCurrentUserId() {
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long currentUserId = null;
        if (jwtUserDetails != null) {
            currentUserId = jwtUserDetails.getId();
        }

        return currentUserId;
    }

    public String getCurrentUserName() {
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        String currentUserName = null;
        if (jwtUserDetails != null) {
            currentUserName = jwtUserDetails.getUsername();
        }
        return currentUserName;
    }


    public UsrUser getCurrentUser() {
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        UsrUser user = null;
        if (jwtUserDetails != null) {
            user = userEntityService.findById(jwtUserDetails.getId()).orElseThrow(() -> new ItemNotFoundException(UsrUserErrorMessage.ITEM_NOT_FOUND));
        }

        return user;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;

        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }

        return jwtUserDetails;
    }

}
