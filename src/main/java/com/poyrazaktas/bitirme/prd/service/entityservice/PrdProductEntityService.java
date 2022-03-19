package com.poyrazaktas.bitirme.prd.service.entityservice;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.gen.service.BaseEntityService;
import com.poyrazaktas.bitirme.prd.dao.PrdProductDao;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PrdProductEntityService extends BaseEntityService<PrdProduct, PrdProductDao> {
    public PrdProductEntityService(PrdProductDao dao) {
        super(dao);
    }

    public List<PrdProduct> findAllByProductType(ProductType productType) {
        return this.getDao().findAllByProductType(productType);
    }

    public List<PrdProduct> findAllByPriceWithTaxBetween(BigDecimal lowestPrice, BigDecimal highestPrice) {
        return this.getDao().findAllByPriceWithTaxBetween(lowestPrice, highestPrice);
    }
}
