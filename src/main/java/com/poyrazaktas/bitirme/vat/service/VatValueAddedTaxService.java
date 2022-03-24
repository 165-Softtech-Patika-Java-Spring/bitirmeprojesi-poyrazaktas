package com.poyrazaktas.bitirme.vat.service;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.gen.util.CalculationUtil;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import com.poyrazaktas.bitirme.vat.converter.VatValueAddedTaxMapper;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxSaveReqDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxUpdateReqDto;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import com.poyrazaktas.bitirme.vat.enums.VatValueAddedTaxErrorMessage;
import com.poyrazaktas.bitirme.vat.service.entityservice.VatValueAddedTaxEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VatValueAddedTaxService {
    private final VatValueAddedTaxEntityService valueAddedTaxEntityService;
    private final PrdProductEntityService productEntityService;

    public List<VatValueAddedTaxDto> findAll() {
        List<VatValueAddedTax> valueAddedTaxList = valueAddedTaxEntityService.findAll();
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDtoList(valueAddedTaxList);
    }

    public VatValueAddedTaxDto get(Long id) {
        VatValueAddedTax vatValueAddedTax = valueAddedTaxEntityService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(VatValueAddedTaxErrorMessage.ITEM_NOT_FOUND));
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDto(vatValueAddedTax);
    }

    public VatValueAddedTaxDto save(VatValueAddedTaxSaveReqDto saveReqDto) {
        VatValueAddedTax valueAddedTax = VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTax(saveReqDto);
        valueAddedTax = valueAddedTaxEntityService.save(valueAddedTax);
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDto(valueAddedTax);
    }

    @Transactional
    public VatValueAddedTaxDto update(VatValueAddedTaxUpdateReqDto updateReqDto) {

        VatValueAddedTax oldValueAddedTax = valueAddedTaxEntityService.findById(updateReqDto.getId())
                .orElseThrow(() -> new ItemNotFoundException(VatValueAddedTaxErrorMessage.ITEM_NOT_FOUND));

        VatValueAddedTax newValueAddedTax = VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTax(updateReqDto);

        newValueAddedTax = updateValueAddedTax(oldValueAddedTax, newValueAddedTax);


        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDto(newValueAddedTax);
    }

    private VatValueAddedTax updateValueAddedTax(VatValueAddedTax oldValueAddedTax, VatValueAddedTax newValueAddedTax) {
        if (isTaxRateOrProductTypeUpdated(oldValueAddedTax, newValueAddedTax)) {
            newValueAddedTax = valueAddedTaxEntityService.save(newValueAddedTax);
            int vatRate = newValueAddedTax.getVatRate();
            ProductType productType = newValueAddedTax.getProductType();
            updateProductPriceWithTaxesWhichVatRateIsUpdated(vatRate, productType);
        } // otherwise, we don't need to perform persistence actions
        return newValueAddedTax;
    }


    public void delete(Long id) {
        VatValueAddedTax valueAddedTax = valueAddedTaxEntityService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(VatValueAddedTaxErrorMessage.ITEM_NOT_FOUND));
        valueAddedTaxEntityService.delete(valueAddedTax);
    }

    private boolean isTaxRateOrProductTypeUpdated(VatValueAddedTax oldValueAddedTax, VatValueAddedTax newValueAddedTax) {
        return (oldValueAddedTax.getVatRate() != newValueAddedTax.getVatRate()) || (oldValueAddedTax.getProductType() != newValueAddedTax.getProductType());
    }

    private void updateProductPriceWithTaxesWhichVatRateIsUpdated(int vatRate, ProductType productType) {
        List<PrdProduct> productList = productEntityService.findAllByProductType(productType);
        for (PrdProduct product :
                productList) {
            BigDecimal priceRaw = product.getPriceRaw();
            BigDecimal priceWithTax = CalculationUtil.calculatePriceWithTax(vatRate, priceRaw);
            product.setPriceWithTax(priceWithTax);
            productEntityService.save(product);
        }
    }
}
