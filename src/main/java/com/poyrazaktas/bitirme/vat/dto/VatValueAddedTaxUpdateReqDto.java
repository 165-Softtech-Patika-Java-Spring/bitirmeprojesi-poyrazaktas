package com.poyrazaktas.bitirme.vat.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class VatValueAddedTaxUpdateReqDto {

    @NotNull
    private Long productTypeId;

    @PositiveOrZero
    private int vatRate;
}
