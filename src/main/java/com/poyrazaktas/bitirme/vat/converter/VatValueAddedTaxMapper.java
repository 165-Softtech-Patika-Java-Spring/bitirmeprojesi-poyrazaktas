package com.poyrazaktas.bitirme.vat.converter;

import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxSaveReqDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxUpdateReqDto;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VatValueAddedTaxMapper {
    VatValueAddedTaxMapper INSTANCE = Mappers.getMapper(VatValueAddedTaxMapper.class);

    VatValueAddedTax convertToValueAddedTax(VatValueAddedTaxSaveReqDto saveReqDto);

    VatValueAddedTax convertToValueAddedTax(VatValueAddedTaxUpdateReqDto updateReqDto);

    VatValueAddedTaxDto convertToValueAddedTaxDto(VatValueAddedTax valueAddedTax);

    List<VatValueAddedTaxDto> convertToValueAddedTaxDtoList(List<VatValueAddedTax> valueAddedTaxList);
}
