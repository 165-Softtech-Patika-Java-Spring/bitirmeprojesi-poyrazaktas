package com.poyrazaktas.bitirme.typ.dto;

import com.poyrazaktas.bitirme.typ.enums.TypType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TypProductTypeUpdateReqDto {
    @NotNull
    private Long id;
    @NotNull
    private TypType type;
}
