package com.poyrazaktas.bitirme.sec.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.sec.dto.JwtUserLoginReqDto;
import com.poyrazaktas.bitirme.sec.service.AuthenticationService;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtUserLoginReqDto jwtUserLoginReqDto) {
        String token = authenticationService.login(jwtUserLoginReqDto);
        RestResponse<String> response = RestResponse.of(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsrUserSaveReqDto userSaveReqDto) {
        UsrUserDto user = authenticationService.register(userSaveReqDto);
        RestResponse<UsrUserDto> response = RestResponse.of(user);
        return ResponseEntity.ok(response);
    }
}
