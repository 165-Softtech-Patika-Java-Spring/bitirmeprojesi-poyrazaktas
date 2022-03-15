package com.poyrazaktas.bitirme.prd.converter;

import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductUpdateReqDto;
import com.poyrazaktas.bitirme.prd.entity.PrdProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdProductMapper {
    PrdProductMapper INSTANCE = Mappers.getMapper(PrdProductMapper.class);

    PrdProduct convertToProduct(PrdProductSaveReqDto saveReqDto);

    PrdProduct convertToProduct(PrdProductUpdateReqDto updateReqDto);

    @Mapping(source = "priceWithTax", target = "finalPrice")
    PrdProductDto convertToProductDto(PrdProduct product);

    @Mapping(source = "priceWithTax", target = "finalPrice")
    List<PrdProductDto> convertToProductDtoList(List<PrdProduct> productList);
}
