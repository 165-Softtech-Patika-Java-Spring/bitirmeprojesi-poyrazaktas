package com.poyrazaktas.bitirme.typ.converter;

import com.poyrazaktas.bitirme.typ.dto.TypProductTypeDto;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeSaveReqDto;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeUpdateReqDto;
import com.poyrazaktas.bitirme.typ.entity.TypProductType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypProductTypeMapper {
    TypProductTypeMapper INSTANCE = Mappers.getMapper(TypProductTypeMapper.class);

    TypProductType convertToProductType(TypProductTypeSaveReqDto saveReqDto);

    TypProductType convertToProductType(TypProductTypeUpdateReqDto updateReqDto);

    TypProductTypeDto convertToProductTypeDto(TypProductType productType);

    List<TypProductTypeDto> convertToProductTypeDtoList(List<TypProductType> productTypeList);

}
