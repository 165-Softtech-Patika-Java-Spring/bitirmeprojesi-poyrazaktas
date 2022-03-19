package com.poyrazaktas.bitirme.prd.dto;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PrdProductUpdateReqDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private ProductType productType;

    @Positive
    @NotNull
    private BigDecimal priceRaw;

}
