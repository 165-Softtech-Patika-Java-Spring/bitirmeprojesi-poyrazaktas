package com.poyrazaktas.bitirme.sec.service;

import com.poyrazaktas.bitirme.sec.dto.JwtUserLoginReqDto;
import com.poyrazaktas.bitirme.sec.enums.JwtConstant;
import com.poyrazaktas.bitirme.sec.security.JwtTokenGenerator;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.service.UsrUserService;
import com.poyrazaktas.bitirme.usr.service.entityservice.UsrUserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UsrUserService userService;

    @Mock
    private UsrUserEntityService userEntityService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenGenerator jwtTokenGenerator;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void whenRegister_calledWithValidRequestBody_thenItShouldReturnValidResponse() {
        String userName = "test";

        UsrUserSaveReqDto saveReqDto = mock(UsrUserSaveReqDto.class);

        UsrUserDto userDto = mock(UsrUserDto.class);
        when(userDto.getUserName()).thenReturn(userName);

        when(userService.save(saveReqDto)).thenReturn(userDto);

        UsrUserDto result = authenticationService.register(saveReqDto);

        assertEquals(userName, result.getUserName());

    }

    @Test
    void whenRegister_calledWithNull_thenItShouldThrowNullPointerException() {

        UsrUserSaveReqDto saveReqDto = mock(UsrUserSaveReqDto.class);

        when(userService.save(null)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class,()->authenticationService.register(null));

    }

    @Test
    void whenLogin_calledWithValidRequestBody_thenItShouldReturnValidResponse() {
        String userName = "username";
        String password = "password";
        String token = "token";
        String bearerToken = JwtConstant.BEARER.getConstant() + token;

        JwtUserLoginReqDto loginReqDto = mock(JwtUserLoginReqDto.class);
        when(loginReqDto.getUserName()).thenReturn(userName);
        when(loginReqDto.getPassword()).thenReturn(password);

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtTokenGenerator.generateJwtToken(authentication)).thenReturn(token);

        String result = authenticationService.login(loginReqDto);

        assertThat(result.equalsIgnoreCase(bearerToken));


    }

    @Test
    void whenLogin_calledWithNull_thenItShouldReturnNullPointerException() {

        JwtUserLoginReqDto loginReqDto = mock(JwtUserLoginReqDto.class);

        assertThrows(NullPointerException.class, ()->authenticationService.login(loginReqDto));

    }

    @Test
    void getCurrentUserId() {
    }

    @Test
    void getCurrentUserName() {
    }

    @Test
    void getCurrentUser() {
    }
}