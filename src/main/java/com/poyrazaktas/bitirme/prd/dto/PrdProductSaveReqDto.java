package com.poyrazaktas.bitirme.prd.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class PrdProductSaveReqDto {

    @NotNull
    private Long productTypeId;

    @NotNull
    private String name;

    @Positive
    @NotNull
    private BigDecimal priceRaw;

}
