package com.poyrazaktas.bitirme.prd.entity;

import com.poyrazaktas.bitirme.gen.entity.BaseEntity;
import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "PRD_PRODUCT")
@Getter
@Setter
public class PrdProduct extends BaseEntity {

    @Id
    @SequenceGenerator(name = "PrdProduct", sequenceName = "SEQ_PRD_PRODUCT_ID")
    @GeneratedValue(generator = "PrdProduct")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE",length = 30,nullable = false)
    private ProductType productType;

    @Positive
    @Column(name = "PRICE_RAW", precision = 19, scale = 2, nullable = false)
    private BigDecimal priceRaw;

    @Column(name = "PRICE_WITH_TAX", precision = 19, scale = 2)
    private BigDecimal priceWithTax;

}
