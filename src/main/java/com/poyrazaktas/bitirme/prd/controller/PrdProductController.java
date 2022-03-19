package com.poyrazaktas.bitirme.prd.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductUpdateReqDto;
import com.poyrazaktas.bitirme.prd.service.PrdProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class PrdProductController {
    private final PrdProductService productService;

    //TODO Swagger Config
    @GetMapping
    public ResponseEntity findAll() {
        List<PrdProductDto> productDtoList = productService.findAll();
        RestResponse<List<PrdProductDto>> response = RestResponse.of(productDtoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        PrdProductDto productDto = productService.getById(id);
        RestResponse<PrdProductDto> response = RestResponse.of(productDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PrdProductSaveReqDto saveReqDto) {
        PrdProductDto productDto = productService.save(saveReqDto);
        RestResponse<PrdProductDto> response = RestResponse.of(productDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody PrdProductUpdateReqDto updateReqDto) {
        PrdProductDto productDto = productService.update(updateReqDto);
        RestResponse<PrdProductDto> response = RestResponse.of(productDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        RestResponse<Object> emptyResponse = RestResponse.empty();
        return ResponseEntity.ok(emptyResponse);
    }
}
