package com.poyrazaktas.bitirme.prd.dao;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.prd.dto.PrdProductTypeDetailDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct, Long> {
    List<PrdProduct> findAllByProductType(ProductType productType);

    List<PrdProduct> findAllByPriceWithTaxBetween(BigDecimal lowestPrice, BigDecimal highestPrice);

    @Query(
            "select new com.poyrazaktas.bitirme.prd.dto.PrdProductTypeDetailDto(" +
                    "product.productType," +
                    "valueAddedTax.vatRate," +
                    "min(product.priceWithTax), " +
                    "max(product.priceWithTax), " +
                    "avg(product.priceWithTax), " +
                    "count(product.id)" +
                    ") " +
                    "from PrdProduct product " +
                    "inner join VatValueAddedTax valueAddedTax " +
                    "on product.productType = valueAddedTax.productType " +
                    "group by product.productType, valueAddedTax.vatRate"

    )
    List<PrdProductTypeDetailDto> getAllProductTypeDetails();

}
