package com.poyrazaktas.bitirme.vat.dto;


import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VatValueAddedTaxDto {

    private Long id;

    private ProductType productType;

    private int vatRate;
}
