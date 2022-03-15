package com.poyrazaktas.bitirme.vat.service;

import com.poyrazaktas.bitirme.vat.converter.VatValueAddedTaxMapper;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxSaveReqDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxUpdateReqDto;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import com.poyrazaktas.bitirme.vat.service.entityservice.VatValueAddedTaxEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatValueAddedTaxService {
    private final VatValueAddedTaxEntityService valueAddedTaxEntityService;

    public List<VatValueAddedTaxDto> findAll() {
        List<VatValueAddedTax> valueAddedTaxList = valueAddedTaxEntityService.findAll();
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDtoList(valueAddedTaxList);
    }

    public VatValueAddedTaxDto get(Long id) {
        VatValueAddedTax vatValueAddedTax = valueAddedTaxEntityService.getByIdWithControl(id);
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDto(vatValueAddedTax);
    }

    public VatValueAddedTaxDto save(VatValueAddedTaxSaveReqDto saveReqDto) {
        VatValueAddedTax valueAddedTax = VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTax(saveReqDto);
        valueAddedTax = valueAddedTaxEntityService.save(valueAddedTax);
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDto(valueAddedTax);
    }


    public VatValueAddedTaxDto update(VatValueAddedTaxUpdateReqDto updateReqDto) {
        VatValueAddedTax valueAddedTax = VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTax(updateReqDto);
        valueAddedTax = valueAddedTaxEntityService.save(valueAddedTax);
        return VatValueAddedTaxMapper.INSTANCE.convertToValueAddedTaxDto(valueAddedTax);
    }

    public void delete(Long id) {
        VatValueAddedTax valueAddedTax = valueAddedTaxEntityService.getByIdWithControl(id);
        valueAddedTaxEntityService.delete(valueAddedTax);
    }

}
