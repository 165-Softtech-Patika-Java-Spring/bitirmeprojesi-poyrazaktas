package com.poyrazaktas.bitirme.vat.entity;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name="VAT_VALUE_ADDED_TAX")
@Getter
@Setter
public class VatValueAddedTax {
    @Id
    @SequenceGenerator(name = "VatValueAddedTax", sequenceName = "SEQ_VAT_VALUE_ADDED_TAX_ID")
    @GeneratedValue(generator = "VatValueAddedTax")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE",length = 30,nullable = false)
    private ProductType productType;

    @PositiveOrZero
    @Column(name="VAT_RATE",nullable = false)
    private int vatRate;
}
