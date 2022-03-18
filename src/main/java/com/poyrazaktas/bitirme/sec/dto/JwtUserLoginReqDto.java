package com.poyrazaktas.bitirme.sec.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserLoginReqDto {
    private String userName;
    private String password;
}
