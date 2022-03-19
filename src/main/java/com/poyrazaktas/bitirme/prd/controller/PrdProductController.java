package com.poyrazaktas.bitirme.prd.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.prd.dto.PrdProductDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductSaveReqDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductTypeDetailDto;
import com.poyrazaktas.bitirme.prd.dto.PrdProductUpdateReqDto;
import com.poyrazaktas.bitirme.prd.service.PrdProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class PrdProductController {
    private final PrdProductService productService;

    @Operation(
            tags = "Product Controller",
            description = "Find all products without any filter.",
            summary = "Find All Products"
    )
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        List<PrdProductDto> productDtoList = productService.findAll();
        RestResponse<List<PrdProductDto>> response = RestResponse.of(productDtoList);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "Find all products by product type.",
            summary = "Find All Products by Product Type"
    )
    @GetMapping("/findAllByProductType")
    public ResponseEntity findAllByProductType(@RequestParam("type") ProductType productType) {
        List<PrdProductDto> productDtoList = productService.findAllByProductType(productType);
        RestResponse<List<PrdProductDto>> response = RestResponse.of(productDtoList);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "Find all products between user specified lowest price and highest price. " +
                    "Both of the lowest and highest price required to perform this request.",
            summary = "Find All Products Between Lowest and Highest Price"
    )
    @GetMapping("/findAllByPriceBetween")
    public ResponseEntity findAllByPriceBetween(@RequestParam BigDecimal lowestPrice, @RequestParam BigDecimal highestPrice) {
        List<PrdProductDto> productDtoList = productService.findAllByPriceWithTaxBetween(lowestPrice, highestPrice);
        RestResponse<List<PrdProductDto>> response = RestResponse.of(productDtoList);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "For every product type get vat rate, minimum price, maximum price, " +
                    "average price and number of products. ",
            summary = "Get Details About Product Types."
    )
    @GetMapping("/getAllProductTypeDetails")
    public ResponseEntity getAllProductTypeDetails() {
        List<PrdProductTypeDetailDto> productTypeDetailDtoList = productService.getAllProductTypeDetails();
        RestResponse<List<PrdProductTypeDetailDto>> response = RestResponse.of(productTypeDetailDtoList);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "Get product by id.",
            summary = "Get Product By Id"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        PrdProductDto productDto = productService.getById(id);
        RestResponse<PrdProductDto> response = RestResponse.of(productDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "Save product",
            summary = "Save Product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PrdProductSaveReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "New Nutrition Product",
                                                    summary = "An example of product save request",
                                                    description = "An example of new product save request " +
                                                            "which name is \"pizza\", product type is \"NUTRITION\", and " +
                                                            "raw price(price without value added tax) is 20$.",
                                                    value = "{\n" +
                                                            "   \"name\" : \"pizza\",\n" +
                                                            "   \"productType\" : \"NUTRITION\",\n" +
                                                            "   \"priceRaw\" : 20\n" +
                                                            "}"

                                            ),
                                            @ExampleObject(
                                                    name = "New Clothing Product",
                                                    summary = "An example of product save request",
                                                    description = "An example of new product save request " +
                                                            "which name is \"jean\", product type is \"CLOTHING\", and " +
                                                            "raw price(price without value added tax) is 100$.",
                                                    value = "{\n" +
                                                            "   \"name\" : \"jean\",\n" +
                                                            "   \"productType\" : \"CLOTHING\",\n" +
                                                            "   \"priceRaw\" : 100\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PrdProductSaveReqDto saveReqDto) {
        PrdProductDto productDto = productService.save(saveReqDto);
        RestResponse<PrdProductDto> response = RestResponse.of(productDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "Update product",
            summary = "Update Product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PrdProductUpdateReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Update a product",
                                                    summary = "An example of product update request",
                                                    description = "An example of product update request " +
                                                            "which id is 1  name is \"pizza\", product type is \"NUTRITION\"," +
                                                            " and raw price(price without value added tax) is 20$.",
                                                    value = "{\n" +
                                                            "   \"id\" : 1,\n" +
                                                            "   \"name\" : \"pizza\",\n" +
                                                            "   \"productType\" : \"NUTRITION\",\n" +
                                                            "   \"priceRaw\" : 20\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody PrdProductUpdateReqDto updateReqDto) {
        PrdProductDto productDto = productService.update(updateReqDto);
        RestResponse<PrdProductDto> response = RestResponse.of(productDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Product Controller",
            description = "Delete product by product id.",
            summary = "Delete Product by Product Id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        RestResponse<Object> emptyResponse = RestResponse.empty();
        return ResponseEntity.ok(emptyResponse);
    }
}
