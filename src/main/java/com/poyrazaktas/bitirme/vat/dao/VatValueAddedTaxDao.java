package com.poyrazaktas.bitirme.vat.dao;

import com.poyrazaktas.bitirme.typ.entity.TypProductType;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VatValueAddedTaxDao extends JpaRepository<VatValueAddedTax,Long> {
    VatValueAddedTax getVatValueAddedTaxByProductTypeId(Long productTypeId);
}
