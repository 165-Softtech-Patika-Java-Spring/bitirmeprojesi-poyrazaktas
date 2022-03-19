package com.poyrazaktas.bitirme.prd.dto;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PrdProductTypeDetailDto {
    private ProductType productType;
    private int vatRate;
    private BigDecimal minimumPrice;
    private BigDecimal maximumPrice;
    private double averagePrice;
    private Long numberOfProducts;
}
