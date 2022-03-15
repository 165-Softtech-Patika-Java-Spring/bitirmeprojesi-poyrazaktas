package com.poyrazaktas.bitirme.typ.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeDto;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeSaveReqDto;
import com.poyrazaktas.bitirme.typ.dto.TypProductTypeUpdateReqDto;
import com.poyrazaktas.bitirme.typ.service.TypProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productTypes")
public class TypProductTypeController {
    private final TypProductTypeService productTypeService;

    @GetMapping("/getAll")
    public ResponseEntity findAll() {
        List<TypProductTypeDto> productTypeDtoList = productTypeService.findAll();
        RestResponse<List<TypProductTypeDto>> response = RestResponse.of(productTypeDtoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        TypProductTypeDto typProductTypeDto = productTypeService.get(id);
        RestResponse<TypProductTypeDto> response = RestResponse.of(typProductTypeDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody TypProductTypeSaveReqDto saveReqDto) {
        TypProductTypeDto typProductTypeDto = productTypeService.save(saveReqDto);
        RestResponse<TypProductTypeDto> response = RestResponse.of(typProductTypeDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody TypProductTypeUpdateReqDto updateReqDto) {
        TypProductTypeDto typProductTypeDto = productTypeService.update(updateReqDto);
        RestResponse<TypProductTypeDto> response = RestResponse.of(typProductTypeDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productTypeService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
