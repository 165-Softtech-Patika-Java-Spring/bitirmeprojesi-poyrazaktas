package com.poyrazaktas.bitirme.prd.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PrdProductDto {

    private Long productTypeId;

    private String name;

    private BigDecimal finalPrice;

}
