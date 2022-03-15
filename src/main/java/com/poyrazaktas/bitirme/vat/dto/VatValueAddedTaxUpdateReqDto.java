package com.poyrazaktas.bitirme.vat.dto;


import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class VatValueAddedTaxUpdateReqDto {

    @NotNull
    private ProductType productType;

    @PositiveOrZero
    private int vatRate;
}
