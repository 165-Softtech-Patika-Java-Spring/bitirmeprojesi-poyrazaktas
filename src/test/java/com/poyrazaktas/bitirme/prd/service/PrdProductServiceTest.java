package com.poyrazaktas.bitirme.prd.service;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductTypeDetailDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductUpdateReqDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import com.poyrazaktas.bitirme.vat.service.entityservice.VatValueAddedTaxEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrdProductServiceTest {
    @Mock
    private PrdProductEntityService productEntityService;

    @Mock
    private VatValueAddedTaxEntityService valueAddedTaxEntityService;

    @InjectMocks
    private PrdProductService productService;


    @Test
    void whenFindAll_calledWithProductList_thenItShouldReturnProductDtoList() {
        PrdProduct product = mock(PrdProduct.class);
        List<PrdProduct> productList = new ArrayList<>();
        productList.add(product);

        PrdProductDto productDto = mock(PrdProductDto.class);
        List<PrdProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);

        when(productEntityService.findAll()).thenReturn(productList);

        List<PrdProductDto> result = productService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void whenFindAll_calledWithEmptyProductList_thenItShouldReturnEmptyProductDtoList() {
        List<PrdProduct> productList = new ArrayList<>();

        when(productEntityService.findAll()).thenReturn(productList);

        List<PrdProductDto> result = productService.findAll();
        assertEquals(0, result.size());
    }

    @Test
    void whenFindAllByProductType_calledWithValidRequest_thenItShouldReturnValidProductDtoList() {
        ProductType productType = ProductType.OTHER;

        PrdProduct product = mock(PrdProduct.class);

        when(product.getProductType()).thenReturn(productType);

        when(productEntityService.findAllByProductType(productType)).thenReturn(Collections.singletonList(product));

        List<PrdProductDto> result = productService.findAllByProductType(productType);

        assertEquals(1, result.size());
        assertEquals(productType, result.get(0).getProductType());

    }

    @Test
    void whenFindAllByProductType_calledWithAnotherProductType_thenItShouldReturnValidEmptyProductDtoList() {
        ProductType productType = ProductType.OTHER;
        ProductType anotherProductType = ProductType.CLEANING;

        when(productEntityService.findAllByProductType(any())).thenReturn(Collections.emptyList());

        List<PrdProductDto> result1 = productService.findAllByProductType(productType);
        List<PrdProductDto> result2 = productService.findAllByProductType(anotherProductType);

        assertEquals(0, result1.size());
        assertEquals(0, result2.size());

    }

    @Test
    void whenFindAllByProductType_calledWithNull_thenItShouldReturnEmptyList() {
        when(productEntityService.findAllByProductType(null)).thenReturn(Collections.emptyList());
        List<PrdProductDto> result = productService.findAllByProductType(null);

        assertEquals(0, result.size());
    }

    @Test
    void whenFindAllByPriceWithTaxBetween_calledWithValidParams_thenItShouldReturnValidResponse() {
        BigDecimal lowestPrice = BigDecimal.ZERO;
        BigDecimal highestPrice = BigDecimal.TEN;

        PrdProduct product = mock(PrdProduct.class);

        when(productEntityService.findAllByPriceWithTaxBetween(any(), any())).thenReturn(Collections.singletonList(product));

        List<PrdProductDto> response = productService.findAllByPriceWithTaxBetween(lowestPrice, highestPrice);

        assertEquals(1, response.size());

    }

    @Test
    void whenFindAllByPriceWithTaxBetween_calledWithNullParams_thenItShouldReturnEmptyList() {

        when(productEntityService.findAllByPriceWithTaxBetween(any(), any())).thenReturn(Collections.emptyList());

        List<PrdProductDto> response = productService.findAllByPriceWithTaxBetween(null, null);

        assertEquals(0, response.size());

    }

    @Test
    void whenGetAllProductTypeDetails_calledWithProductTypeDetailList_thenItShouldReturnProductTypeDetailList() {
        PrdProductTypeDetailDto productTypeDetailDto = mock(PrdProductTypeDetailDto.class);

        when(productEntityService.getAllProductTypeDetails()).thenReturn(Collections.singletonList(productTypeDetailDto));

        List<PrdProductTypeDetailDto> result = productService.getAllProductTypeDetails();

        assertEquals(1, result.size());
    }

    @Test
    void whenGetAllProductTypeDetails_calledWithEmptyList_thenItShouldReturnEmptyList() {

        when(productEntityService.getAllProductTypeDetails()).thenReturn(Collections.emptyList());

        List<PrdProductTypeDetailDto> result = productService.getAllProductTypeDetails();

        assertEquals(0, result.size());
    }

    @Test
    void whenGetById_calledWithValidId_thenItShouldReturnProductDto() {

        Long productId = 2L;

        PrdProduct product = mock(PrdProduct.class);

        when(product.getId()).thenReturn(productId);

        when(productEntityService.findById(any())).thenReturn(Optional.of(product));

        PrdProductDto productDto = productService.getById(productId);

        assertEquals(productId, productDto.getId());

    }

    @Test
    void whenGetById_calledWithNotMatchingId_thenItShouldThrowItemNotFoundException() {
        when(productEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> productService.getById(anyLong()));

        verify(productEntityService).findById(anyLong());
    }

    @Test
    void whenSave_calledWithValidRequestBody_thenItShouldReturnValidProductDto() {
        Long id = 2L;
        int vatRate = 10;
        BigDecimal priceRaw = BigDecimal.TEN;

        PrdProductSaveReqDto saveReqDto = mock(PrdProductSaveReqDto.class);
        when(saveReqDto.getPriceRaw()).thenReturn(priceRaw);

        PrdProduct product = new PrdProduct();
        product.setId(id);
        product.setPriceRaw(priceRaw);
        product.setPriceWithTax(BigDecimal.valueOf(11));

        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);
        when(valueAddedTax.getVatRate()).thenReturn(vatRate);

        when(valueAddedTaxEntityService.getVatValueAddedTaxByProductType(any())).thenReturn(valueAddedTax);

        when(productEntityService.save(any())).thenReturn(product);

        PrdProductDto result = productService.save(saveReqDto);

        assertEquals(id,result.getId());
        assertEquals(BigDecimal.valueOf(11),result.getFinalPrice());

    }

    @Test
    void whenSave_calledWithEmptyRequestBody_thenItShouldThrowNullPointerException() {

        assertThrows(NullPointerException.class,()->productService.save(null));

    }

    @Test
    void whenUpdate_calledWithNewRawPrice_thenItShouldReturnValidProductDto() {
        Long id = 2L;
        int vatRate = 50;
        BigDecimal oldRawPrice = BigDecimal.TEN;
        BigDecimal newRawPrice = BigDecimal.valueOf(20);

        BigDecimal oldPriceWithTax = BigDecimal.valueOf(15);
        BigDecimal newPriceWithTax = BigDecimal.valueOf(30);


        PrdProductUpdateReqDto updateReqDto = mock(PrdProductUpdateReqDto.class);
        when(updateReqDto.getId()).thenReturn(id);
        when(updateReqDto.getPriceRaw()).thenReturn(newRawPrice);


        PrdProduct oldProduct = new PrdProduct();
        oldProduct.setId(id);
        oldProduct.setPriceRaw(oldRawPrice);
        oldProduct.setPriceWithTax(oldPriceWithTax);

        PrdProduct newProduct = new PrdProduct();
        newProduct.setId(id);
        newProduct.setPriceRaw(newRawPrice);
        newProduct.setPriceWithTax(newPriceWithTax);

        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);
        when(valueAddedTax.getVatRate()).thenReturn(vatRate);

        when(valueAddedTaxEntityService.getVatValueAddedTaxByProductType(any())).thenReturn(valueAddedTax);

        when(productEntityService.findById(id)).thenReturn(Optional.of(oldProduct));

        when(productEntityService.save(any())).thenReturn(newProduct);

        PrdProductDto result = productService.update(updateReqDto);

        assertEquals(id,result.getId());
        assertEquals(newPriceWithTax,result.getFinalPrice());

    }

    @Test
    void whenUpdate_calledWithNewProductType_thenItShouldReturnValidProductDto() {
        Long id = 2L;
        int vatRate = 50;
        BigDecimal oldRawPrice = BigDecimal.TEN;


        BigDecimal oldPriceWithTax = BigDecimal.valueOf(15);
        BigDecimal newPriceWithTax = BigDecimal.valueOf(30);

        ProductType oldProductType = ProductType.TECHNOLOGY;
        ProductType newProductType = ProductType.OTHER;

        PrdProductUpdateReqDto updateReqDto = mock(PrdProductUpdateReqDto.class);
        when(updateReqDto.getId()).thenReturn(id);
        when(updateReqDto.getPriceRaw()).thenReturn(oldRawPrice);
        when(updateReqDto.getProductType()).thenReturn(newProductType);


        PrdProduct oldProduct = new PrdProduct();
        oldProduct.setId(id);
        oldProduct.setPriceRaw(oldRawPrice);
        oldProduct.setPriceWithTax(oldPriceWithTax);
        oldProduct.setProductType(oldProductType);

        PrdProduct newProduct = new PrdProduct();
        newProduct.setId(id);
        newProduct.setPriceRaw(oldRawPrice);
        newProduct.setPriceWithTax(newPriceWithTax);
        newProduct.setProductType(newProductType);


        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);
        when(valueAddedTax.getVatRate()).thenReturn(vatRate);

        when(valueAddedTaxEntityService.getVatValueAddedTaxByProductType(any())).thenReturn(valueAddedTax);

        when(productEntityService.findById(id)).thenReturn(Optional.of(oldProduct));

        when(productEntityService.save(any())).thenReturn(newProduct);

        PrdProductDto result = productService.update(updateReqDto);

        assertEquals(id,result.getId());
        assertEquals(newPriceWithTax,result.getFinalPrice());
        assertEquals(newProductType,result.getProductType());

    }

    @Test
    void whenUpdate_calledWithNotMatchingId_thenItShouldThrowItemNotFoundException(){

        PrdProductUpdateReqDto updateReqDto = mock(PrdProductUpdateReqDto.class);

        when(productEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> productService.update(updateReqDto));

        verify(productEntityService).findById(anyLong());
    }

    @Test
    void whenDelete_calledWithValidId_thenItShouldDeleteProduct() {
        PrdProduct product = mock(PrdProduct.class);

        when(productEntityService.findById(anyLong())).thenReturn(Optional.of(product));

        productService.delete(anyLong());

        verify(productEntityService).findById(anyLong());
        verify(productEntityService).delete(any());
    }

    @Test
    void whenDelete_calledWithNull_thenItShouldThrowItemNotFoundException() {

        when(productEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> productService.delete(anyLong()));

        verify(productEntityService).findById(anyLong());
    }
}