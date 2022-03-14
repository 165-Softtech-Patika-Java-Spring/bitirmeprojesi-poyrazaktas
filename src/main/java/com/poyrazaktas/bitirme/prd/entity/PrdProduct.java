package com.poyrazaktas.bitirme.prd.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="PRD_PRODUCT")
@Getter
@Setter
public class PrdProduct {

    @Id
    @SequenceGenerator(name = "PrdProduct", sequenceName = "SEQ_PRD_PRODUCT_ID")
    @GeneratedValue(generator = "PrdProduct")
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    // ürün tipine karar verilecek

    @Column(name = "PRICE_RAW", precision = 19, scale = 2)
    private BigDecimal priceRaw;

    @Column(name = "PRICE_WITH_TAX",precision = 19, scale = 2)
    private BigDecimal priceWithTax;

}
