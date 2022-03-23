package com.poyrazaktas.bitirme.prd.service.entityservice;

import com.poyrazaktas.bitirme.prd.dao.PrdProductDao;
import com.poyrazaktas.bitirme.prd.dto.PrdProductTypeDetailDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrdProductEntityServiceTest {

    @Mock
    private PrdProductDao productDao;

    @InjectMocks
    private PrdProductEntityService productEntityService;

    @Test
    void whenFindAllByProductType_calledWithValidParams_thenItShouldReturnValidResponse() {
        PrdProduct product = mock(PrdProduct.class);

        when(productDao.findAllByProductType(any())).thenReturn(Collections.singletonList(product));

        List<PrdProduct> result = productEntityService.findAllByProductType(any());

        assertEquals(1, result.size());

    }

    @Test
    void whenFindAllByProductType_calledWithNull_thenItShouldReturnEmptyList() {

        when(productDao.findAllByProductType(any())).thenReturn(Collections.emptyList());

        List<PrdProduct> result = productEntityService.findAllByProductType(null);

        assertEquals(0, result.size());

    }


    @Test
    void whenFindAllByPriceWithTaxBetween_calledWithValidParams_thenItShouldReturnValid() {
        PrdProduct product = mock(PrdProduct.class);

        when(productDao.findAllByPriceWithTaxBetween(any(), any())).thenReturn(Collections.singletonList(product));

        List<PrdProduct> result = productEntityService.findAllByPriceWithTaxBetween(any(), any());

        assertEquals(1, result.size());
    }

    @Test
    void whenFindAllByPriceWithTaxBetween_calledWithNullParams_thenItShouldReturnEmptyList() {

        when(productDao.findAllByPriceWithTaxBetween(any(), any())).thenReturn(Collections.emptyList());

        List<PrdProduct> result = productEntityService.findAllByPriceWithTaxBetween(null, null);

        assertEquals(0, result.size());
    }


    @Test
    void whenGetAllProductTypeDetails_calledWithProductTypeDetailList_thenItShouldProductTypeDetailList() {
        PrdProductTypeDetailDto productTypeDetailDto = mock(PrdProductTypeDetailDto.class);

        when(productDao.getAllProductTypeDetails()).thenReturn(Collections.singletonList(productTypeDetailDto));

        List<PrdProductTypeDetailDto> result = productEntityService.getAllProductTypeDetails();

        assertEquals(1, result.size());

    }

    @Test
    void whenGetAllProductTypeDetails_calledWithEmptyProductTypeDetailList_thenItShouldEmptyProductTypeDetailList() {

        when(productDao.getAllProductTypeDetails()).thenReturn(Collections.emptyList());

        List<PrdProductTypeDetailDto> result = productEntityService.getAllProductTypeDetails();

        assertEquals(0, result.size());

    }
}