package com.poyrazaktas.bitirme.prd.service;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.prd.converter.PrdProductMapper;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import com.poyrazaktas.bitirme.vat.service.entityservice.VatValueAddedTaxEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrdProductService {
    private final PrdProductEntityService productEntityService;
    private final VatValueAddedTaxEntityService valueAddedTaxEntityService;

    public List<PrdProductDto> findAll() {
        List<PrdProduct> productList = productEntityService.findAll();
        return PrdProductMapper.INSTANCE.convertToProductDtoList(productList);
    }

    public PrdProductDto getById(Long id) {
        PrdProduct product = productEntityService.getByIdWithControl(id);
        return PrdProductMapper.INSTANCE.convertToProductDto(product);
    }

    public PrdProductDto save(PrdProductSaveReqDto saveReqDto) {
        PrdProduct product = PrdProductMapper.INSTANCE.convertToProduct(saveReqDto);

        // get value added tax rate by product type by id
        ProductType productType = product.getProductType();
        VatValueAddedTax tax = valueAddedTaxEntityService.getVatValueAddedTaxByProductType(productType);
        int vatRate = tax.getVatRate();

        // calculate price with tax with vat rate
        BigDecimal priceRaw = product.getPriceRaw();
        BigDecimal priceWithTax = calculatePriceWithTax(vatRate, priceRaw);
        product.setPriceWithTax(priceWithTax);

        // save product
        product = productEntityService.save(product);

        // convert product to product dto

        return PrdProductMapper.INSTANCE.convertToProductDto(product);
    }

    private BigDecimal calculatePriceWithTax(int vatRate, BigDecimal priceRaw) {
        return priceRaw.add(priceRaw.multiply(BigDecimal.valueOf(vatRate).divide(BigDecimal.valueOf(100))));
    }
}
