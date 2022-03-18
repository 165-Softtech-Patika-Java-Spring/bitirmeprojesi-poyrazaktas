package com.poyrazaktas.bitirme.sec.service;

import com.poyrazaktas.bitirme.sec.dto.JwtUserLoginReqDto;
import com.poyrazaktas.bitirme.sec.enums.JwtConstant;
import com.poyrazaktas.bitirme.sec.security.JwtTokenGenerator;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.service.UsrUserService;
import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginReqDto.getUserName(), userLoginReqDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = JwtConstant.BEARER.getConstant();

        return bearer + token;
    }
}
