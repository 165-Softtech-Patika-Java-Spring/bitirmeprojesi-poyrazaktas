package com.poyrazaktas.bitirme.prd.service;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.gen.util.CalculationUtil;
import com.poyrazaktas.bitirme.prd.converter.PrdProductMapper;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductUpdateReqDto;
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

    public List<PrdProductDto> findAllByProductType(ProductType productType) {
        List<PrdProduct> productList = productEntityService.findAllByProductType(productType);
        return PrdProductMapper.INSTANCE.convertToProductDtoList(productList);
    }

    public List<PrdProductDto> findAllByPriceWithTaxBetween(BigDecimal lowestPrice, BigDecimal highestPrice) {
        List<PrdProduct> productList = productEntityService.findAllByPriceWithTaxBetween(lowestPrice, highestPrice);
        return PrdProductMapper.INSTANCE.convertToProductDtoList(productList);
    }

    public PrdProductDto getById(Long id) {
        PrdProduct product = productEntityService.getByIdWithControl(id);
        return PrdProductMapper.INSTANCE.convertToProductDto(product);
    }

    public PrdProductDto save(PrdProductSaveReqDto saveReqDto) {
        PrdProduct product = PrdProductMapper.INSTANCE.convertToProduct(saveReqDto);

        // get value added tax rate by product type
        ProductType productType = product.getProductType();
        VatValueAddedTax tax = valueAddedTaxEntityService.getVatValueAddedTaxByProductType(productType);
        int vatRate = tax.getVatRate();

        // calculate price with tax with vat rate
        BigDecimal priceRaw = product.getPriceRaw();
        BigDecimal priceWithTax = CalculationUtil.calculatePriceWithTax(vatRate, priceRaw);
        product.setPriceWithTax(priceWithTax);

        product = productEntityService.save(product);

        return PrdProductMapper.INSTANCE.convertToProductDto(product);
    }


    public PrdProductDto update(PrdProductUpdateReqDto updateReqDto) {
        PrdProduct oldProduct = productEntityService.getByIdWithControl(updateReqDto.getId());
        PrdProduct newProduct = PrdProductMapper.INSTANCE.convertToProduct(updateReqDto);

        // unless product type or priceRaw change, don't calculate the price with tax
        if (oldProduct.getProductType() != newProduct.getProductType() ||
                oldProduct.getPriceRaw() != newProduct.getPriceRaw()) {
            // get product type
            ProductType productType = newProduct.getProductType();
            BigDecimal priceRaw = newProduct.getPriceRaw();
            VatValueAddedTax valueAddedTax = valueAddedTaxEntityService.getVatValueAddedTaxByProductType(productType);
            int vatRate = valueAddedTax.getVatRate();
            BigDecimal priceWithTax = CalculationUtil.calculatePriceWithTax(vatRate, priceRaw);
            newProduct.setPriceWithTax(priceWithTax);
        }

        PrdProduct updatedProduct = productEntityService.save(newProduct);

        return PrdProductMapper.INSTANCE.convertToProductDto(updatedProduct);

    }

    public void delete(Long id) {
        PrdProduct product = productEntityService.getByIdWithControl(id);
        productEntityService.delete(product);
    }

}
