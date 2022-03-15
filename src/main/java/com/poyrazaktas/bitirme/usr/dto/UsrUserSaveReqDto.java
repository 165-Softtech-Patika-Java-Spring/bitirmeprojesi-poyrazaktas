package com.poyrazaktas.bitirme.usr.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsrUserSaveReqDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
