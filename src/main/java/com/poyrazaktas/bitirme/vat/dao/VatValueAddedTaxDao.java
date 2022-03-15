package com.poyrazaktas.bitirme.vat.dao;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VatValueAddedTaxDao extends JpaRepository<VatValueAddedTax,Long> {
    VatValueAddedTax getVatValueAddedTaxByProductType(ProductType productType);
}
