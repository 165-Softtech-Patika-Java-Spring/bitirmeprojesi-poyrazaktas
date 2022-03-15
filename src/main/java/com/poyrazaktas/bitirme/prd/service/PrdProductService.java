package com.poyrazaktas.bitirme.prd.service;

import com.poyrazaktas.bitirme.prd.converter.PrdProductMapper;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrdProductService {
    private final PrdProductEntityService productEntityService;

    List<PrdProductDto> findAll(){
        List<PrdProduct> productList = productEntityService.findAll();
        return PrdProductMapper.INSTANCE.convertToProductDtoList(productList);
    }

    PrdProductDto getById(Long id){
        PrdProduct product = productEntityService.getByIdWithControl(id);
        return PrdProductMapper.INSTANCE.convertToProductDto(product);
    }

    PrdProductDto save(PrdProductSaveReqDto saveReqDto){
        PrdProduct product = PrdProductMapper.INSTANCE.convertToProduct(saveReqDto);

        // get value added tax rate by product type by id
        Long productTypeId = product.getProductTypeId();

        // calculate price with tax with vat rate

        // save product

        // convert product to product dto

        return new PrdProductDto();
    }
}
