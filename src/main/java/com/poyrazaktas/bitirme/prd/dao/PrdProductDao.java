package com.poyrazaktas.bitirme.prd.dao;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct, Long> {
    List<PrdProduct> findAllByProductType(ProductType productType);

    List<PrdProduct> findAllByPriceWithTaxBetween(BigDecimal lowestPrice, BigDecimal highestPrice);

}
