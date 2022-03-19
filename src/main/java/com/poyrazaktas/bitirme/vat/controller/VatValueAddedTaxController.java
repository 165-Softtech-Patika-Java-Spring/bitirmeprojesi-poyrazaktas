package com.poyrazaktas.bitirme.vat.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxSaveReqDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxUpdateReqDto;
import com.poyrazaktas.bitirme.vat.service.VatValueAddedTaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/value-added-taxes")
public class VatValueAddedTaxController {
    private final VatValueAddedTaxService valueAddedTaxService;

    @GetMapping
    public ResponseEntity findAll() {
        List<VatValueAddedTaxDto> valueAddedTaxDtoList = valueAddedTaxService.findAll();
        RestResponse<List<VatValueAddedTaxDto>> response = RestResponse.of(valueAddedTaxDtoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        VatValueAddedTaxDto valueAddedTaxDto = valueAddedTaxService.get(id);
        RestResponse<VatValueAddedTaxDto> response = RestResponse.of(valueAddedTaxDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/init-vat-rates")
    public ResponseEntity initVatRates() {
        List<VatValueAddedTaxSaveReqDto> valueAddedTaxSaveReqDtoList = getInitialValueAddedTaxSaveReqDtoList();

        saveAllValueAddedTaxList(valueAddedTaxSaveReqDtoList);

        List<VatValueAddedTaxDto> vatValueAddedTaxDtoList = valueAddedTaxService.findAll();

        RestResponse<List<VatValueAddedTaxDto>> response = RestResponse.of(vatValueAddedTaxDtoList);

        return ResponseEntity.ok(response);

    }

    private void saveAllValueAddedTaxList(List<VatValueAddedTaxSaveReqDto> valueAddedTaxSaveReqDtoList) {
        for (VatValueAddedTaxSaveReqDto valueAddedTaxSaveReqDto :
                valueAddedTaxSaveReqDtoList) {
            valueAddedTaxService.save(valueAddedTaxSaveReqDto);
        }
    }

    private List<VatValueAddedTaxSaveReqDto> getInitialValueAddedTaxSaveReqDtoList() {
        List<VatValueAddedTaxSaveReqDto> valueAddedTaxSaveReqDtoList = new ArrayList<>();
        valueAddedTaxSaveReqDtoList.add(new VatValueAddedTaxSaveReqDto(ProductType.NUTRITION, 1));
        valueAddedTaxSaveReqDtoList.add(new VatValueAddedTaxSaveReqDto(ProductType.STATIONARY, 8));
        valueAddedTaxSaveReqDtoList.add(new VatValueAddedTaxSaveReqDto(ProductType.CLOTHING, 8));
        valueAddedTaxSaveReqDtoList.add(new VatValueAddedTaxSaveReqDto(ProductType.TECHNOLOGY, 18));
        valueAddedTaxSaveReqDtoList.add(new VatValueAddedTaxSaveReqDto(ProductType.CLEANING, 18));
        valueAddedTaxSaveReqDtoList.add(new VatValueAddedTaxSaveReqDto(ProductType.OTHER, 35));
        return valueAddedTaxSaveReqDtoList;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody VatValueAddedTaxSaveReqDto saveReqDto) {
        VatValueAddedTaxDto valueAddedTaxDto = valueAddedTaxService.save(saveReqDto);
        RestResponse<VatValueAddedTaxDto> response = RestResponse.of(valueAddedTaxDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody VatValueAddedTaxUpdateReqDto updateReqDto) {
        VatValueAddedTaxDto valueAddedTaxDto = valueAddedTaxService.update(updateReqDto);
        RestResponse<VatValueAddedTaxDto> response = RestResponse.of(valueAddedTaxDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        valueAddedTaxService.delete(id);
        RestResponse<Object> response = RestResponse.empty();
        return ResponseEntity.ok(response);
    }
}
