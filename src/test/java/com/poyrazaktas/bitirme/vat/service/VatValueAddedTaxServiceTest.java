package com.poyrazaktas.bitirme.vat.service;

import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.gen.exception.ItemNotFoundException;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import com.poyrazaktas.bitirme.usr.entity.UsrUser;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxSaveReqDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxUpdateReqDto;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import com.poyrazaktas.bitirme.vat.service.entityservice.VatValueAddedTaxEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VatValueAddedTaxServiceTest {

    @Mock
    private VatValueAddedTaxEntityService valueAddedTaxEntityService;

    @Mock
    private PrdProductEntityService productEntityService;

    @InjectMocks
    private VatValueAddedTaxService valueAddedTaxService;

    @Test
    void whenFindAll_calledWithVatList_thenItShouldReturnVatDtoList() {
        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);

        when(valueAddedTaxEntityService.findAll()).thenReturn(Collections.singletonList(valueAddedTax));

        List<VatValueAddedTaxDto> result = valueAddedTaxService.findAll();

        assertEquals(1, result.size());

    }

    @Test
    void whenFindAll_calledWithEmptyVatList_thenItShouldReturnEmptyVatDtoList() {

        when(valueAddedTaxEntityService.findAll()).thenReturn(Collections.emptyList());

        List<VatValueAddedTaxDto> result = valueAddedTaxService.findAll();

        assertEquals(0, result.size());

    }

    @Test
    void whenGet_calledWithValidId_thenItShouldReturnValidVatDto() {

        Long id = 2L;

        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);
        when(valueAddedTax.getId()).thenReturn(id);

        when(valueAddedTaxEntityService.findById(anyLong())).thenReturn(Optional.of(valueAddedTax));

        VatValueAddedTaxDto vatValueAddedTaxDto = valueAddedTaxService.get(anyLong());

        assertEquals(id, vatValueAddedTaxDto.getId());

    }

    @Test
    void whenGet_calledWithNotMatchingId_thenItShouldThrowItemNotFoundException() {

        when(valueAddedTaxEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> valueAddedTaxService.get(anyLong()));
    }

    @Test
    void whenSave_calledWithValidRequestBody_thenItShouldReturnValidResponse() {

        int vatRate = 50;

        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);
        VatValueAddedTaxSaveReqDto saveReqDto = mock(VatValueAddedTaxSaveReqDto.class);

        when(valueAddedTax.getVatRate()).thenReturn(vatRate);

        when(valueAddedTaxEntityService.save(any())).thenReturn(valueAddedTax);

        VatValueAddedTaxDto result = valueAddedTaxService.save(saveReqDto);

        assertEquals(vatRate, result.getVatRate());

    }

    @Test
    void whenUpdate_calledWithValidRequestBody_thenItShouldReturnValidResponse() {

        Long id = 2L;
        int oldVatRate = 20;
        int newVatRate = 30;

        ProductType productType = ProductType.CLEANING;


        VatValueAddedTaxUpdateReqDto updateReqDto = mock(VatValueAddedTaxUpdateReqDto.class);
        when(updateReqDto.getId()).thenReturn(id);
        when(updateReqDto.getVatRate()).thenReturn(50);
        when(updateReqDto.getProductType()).thenReturn(productType);


        VatValueAddedTax oldValueAddedTax = new VatValueAddedTax();
        oldValueAddedTax.setId(id);
        oldValueAddedTax.setVatRate(oldVatRate);
        oldValueAddedTax.setProductType(productType);

        VatValueAddedTax newValueAddedTax = new VatValueAddedTax();
        newValueAddedTax.setId(id);
        newValueAddedTax.setVatRate(newVatRate);
        newValueAddedTax.setProductType(productType);

        PrdProduct product = mock(PrdProduct.class);
        when(product.getPriceRaw()).thenReturn(BigDecimal.valueOf(100));

        PrdProduct updatedProduct = mock(PrdProduct.class);

        when(valueAddedTaxEntityService.findById(id)).thenReturn(Optional.of(oldValueAddedTax));

        when(valueAddedTaxEntityService.save(any())).thenReturn(newValueAddedTax);

        when(productEntityService.findAllByProductType(any())).thenReturn(Collections.singletonList(product));

        when(productEntityService.save(any())).thenReturn(updatedProduct);

        VatValueAddedTaxDto result = valueAddedTaxService.update(updateReqDto);

        assertEquals(id, result.getId());

    }

    @Test
    void whenUpdate_calledWithNotMatchingId_thenItShouldThrowItemNotFoundException() {

        VatValueAddedTaxUpdateReqDto updateReqDto = mock(VatValueAddedTaxUpdateReqDto.class);
        when(updateReqDto.getId()).thenReturn(2L);

        when(valueAddedTaxEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> valueAddedTaxService.update(updateReqDto));

    }

    @Test
    void whenDelete_calledWithValidId_thenItShouldDelete() {

        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);

        when(valueAddedTaxEntityService.findById(anyLong())).thenReturn(Optional.of(valueAddedTax));
        valueAddedTaxService.delete(anyLong());

        verify(valueAddedTaxEntityService).findById(anyLong());
        verify(valueAddedTaxEntityService).delete(any());
    }

    @Test
    void whenDelete_calledWithNotMatchingId_thenItShouldThrowItemNotFoundException() {

        when(valueAddedTaxEntityService.findById(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> valueAddedTaxService.delete(anyLong()));

        verify(valueAddedTaxEntityService).findById(anyLong());
    }
}