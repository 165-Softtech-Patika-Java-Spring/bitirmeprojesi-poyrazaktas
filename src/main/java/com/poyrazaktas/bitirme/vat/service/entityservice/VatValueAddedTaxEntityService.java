package com.poyrazaktas.bitirme.vat.service.entityservice;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.gen.service.BaseEntityService;
import com.poyrazaktas.bitirme.vat.dao.VatValueAddedTaxDao;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import org.springframework.stereotype.Service;

@Service
public class VatValueAddedTaxEntityService extends BaseEntityService<VatValueAddedTax, VatValueAddedTaxDao> {
    public VatValueAddedTaxEntityService(VatValueAddedTaxDao dao) {
        super(dao);
    }

    public VatValueAddedTax getVatValueAddedTaxByProductType(ProductType productType) {
        return this.getDao().getVatValueAddedTaxByProductType(productType);
    }
}
