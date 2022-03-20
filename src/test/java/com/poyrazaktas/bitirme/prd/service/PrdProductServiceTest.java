package com.poyrazaktas.bitirme.prd.service;

import com.poyrazaktas.bitirme.prd.converter.PrdProductMapper;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrdProductServiceTest {
    @Mock
    private PrdProductEntityService productEntityService;

    @InjectMocks
    private PrdProductService productService;


    @Test
    void shouldFindAll() {
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
    void findAllByProductType() {
    }

    @Test
    void findAllByPriceWithTaxBetween() {
    }

    @Test
    void getAllProductTypeDetails() {
    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}