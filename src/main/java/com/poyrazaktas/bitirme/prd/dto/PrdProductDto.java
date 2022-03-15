package com.poyrazaktas.bitirme.prd.dto;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PrdProductDto {

    private String name;

    private ProductType productType;

    private BigDecimal finalPrice;

}
