package com.poyrazaktas.bitirme.typ.entity;

import com.poyrazaktas.bitirme.typ.enums.TypType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TYP_PRODUCT_TYPE")
@Getter
@Setter
public class TypProductType {

    @Id
    @SequenceGenerator(name = "TypProductType", sequenceName = "SEQ_TYP_PRODUCT_TYPE_ID")
    @GeneratedValue(generator = "TypProductType")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE",length = 30,nullable = false)
    private TypType type;

}
