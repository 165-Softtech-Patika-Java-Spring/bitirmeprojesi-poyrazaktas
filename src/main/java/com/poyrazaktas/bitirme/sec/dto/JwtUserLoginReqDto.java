package com.poyrazaktas.bitirme.sec.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JwtUserLoginReqDto {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
