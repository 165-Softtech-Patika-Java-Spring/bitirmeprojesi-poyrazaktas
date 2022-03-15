package com.poyrazaktas.bitirme.typ.service;

import com.poyrazaktas.bitirme.typ.converter.TypProductTypeMapper;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeDto;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeSaveReqDto;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeUpdateReqDto;
import com.poyrazaktas.bitirme.typ.entity.TypProductType;
import com.poyrazaktas.bitirme.typ.service.entityservice.TypProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypProductTypeService {
    private final TypProductTypeEntityService productTypeEntityService;

    public List<TypProductTypeDto> findAll() {
        List<TypProductType> productTypeList = productTypeEntityService.findAll();
        return TypProductTypeMapper.INSTANCE.convertToProductTypeDtoList(productTypeList);
    }

    public TypProductTypeDto get(Long id) {
        TypProductType productType = productTypeEntityService.getByIdWithControl(id);
        return TypProductTypeMapper.INSTANCE.convertToProductTypeDto(productType);
    }

    public TypProductTypeDto save(TypProductTypeSaveReqDto saveReqDto) {
        TypProductType productType = TypProductTypeMapper.INSTANCE.convertToProductType(saveReqDto);
        productType = productTypeEntityService.save(productType);
        return TypProductTypeMapper.INSTANCE.convertToProductTypeDto(productType);
    }

    public TypProductTypeDto update(TypProductTypeUpdateReqDto updateReqDtoReqDto) {
        TypProductType productType = TypProductTypeMapper.INSTANCE.convertToProductType(updateReqDtoReqDto);
        productType = productTypeEntityService.save(productType);
        return TypProductTypeMapper.INSTANCE.convertToProductTypeDto(productType);
    }

    public void delete(Long id){
        TypProductType productType = productTypeEntityService.getByIdWithControl(id);
        productTypeEntityService.delete(productType);
    }
}