package com.poyrazaktas.bitirme.vat.dao;

import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VatValueAddedTaxDao extends JpaRepository<VatValueAddedTax,Long> {
}
