package com.poyrazaktas.bitirme.vat.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.gen.enums.ProductType;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxSaveReqDto;
import com.poyrazaktas.bitirme.vat.dto.VatValueAddedTaxUpdateReqDto;
import com.poyrazaktas.bitirme.vat.service.VatValueAddedTaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            tags = "Value Added Tax Controller",
            description = "Find all value added taxes",
            summary = "Find All Value Added Taxes"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of value added taxes",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = VatValueAddedTaxDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content = @Content()
                    )
            }
    )
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        List<VatValueAddedTaxDto> valueAddedTaxDtoList = valueAddedTaxService.findAll();
        RestResponse<List<VatValueAddedTaxDto>> response = RestResponse.of(valueAddedTaxDtoList);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Value Added Tax Controller",
            description = "Get value added tax by id",
            summary = "Get Value Added Tax by Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found a Value Added Tax",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content = @Content()
                    )
            }
    )
    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        VatValueAddedTaxDto valueAddedTaxDto = valueAddedTaxService.get(id);
        RestResponse<VatValueAddedTaxDto> response = RestResponse.of(valueAddedTaxDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Value Added Tax Controller",
            description = "Initialize Value Added Tax Rates with the given table at the readme document.",
            summary = "Initialize Value Added Tax Rates"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Initialized VAT rates successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while initializing VAT rates",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content = @Content()
                    )
            }
    )
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

    @Operation(
            tags = "Value Added Tax Controller",
            description = "Save a value added tax entity considering the product type would be unique",
            summary = "Save Value Added Tax Entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Value Added Tax",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = VatValueAddedTaxSaveReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "New Value Added Tax",
                                                    summary = "An example of value added tax save request",
                                                    description = "An example of value added tax save request " +
                                                            "which product type is \"NUTRITION\", and " +
                                                            "vat rate is 5%.",
                                                    value = "{\n" +
                                                            "   \"productType\" : \"NUTRITION\",\n" +
                                                            "   \"vatRate\" : 5\n" +
                                                            "}"

                                            )
                                    }
                            )
                    }
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "VAT saved successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while saving VAT",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content = @Content()
                    )
            }
    )
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody VatValueAddedTaxSaveReqDto saveReqDto) {
        VatValueAddedTaxDto valueAddedTaxDto = valueAddedTaxService.save(saveReqDto);
        RestResponse<VatValueAddedTaxDto> response = RestResponse.of(valueAddedTaxDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Value Added Tax Controller",
            description = "Update a value added tax entity considering the product type would be unique",
            summary = "Update Value Added Tax Entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Value Added Tax",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = VatValueAddedTaxSaveReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "New Value Added Tax",
                                                    summary = "An example of value added tax update request",
                                                    description = "An example of value added tax update request " +
                                                            "which id is 1, product type is \"NUTRITION\", and " +
                                                            "vat rate is 5%.",
                                                    value = "{\n" +
                                                            "   \"id\" : 1,\n" +
                                                            "   \"productType\" : \"NUTRITION\",\n" +
                                                            "   \"vatRate\" : 5\n" +
                                                            "}"

                                            )
                                    }
                            )
                    }
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "VAT updated successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while updating VAT",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content = @Content()
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody VatValueAddedTaxUpdateReqDto updateReqDto) {
        VatValueAddedTaxDto valueAddedTaxDto = valueAddedTaxService.update(updateReqDto);
        RestResponse<VatValueAddedTaxDto> response = RestResponse.of(valueAddedTaxDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Value Added Tax Controller",
            description = "Delete value added tax by id",
            summary = "Delete Value Added Tax by Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Value Added Tax deleted successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found a Value Added Tax to delete",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized.",
                            content = @Content()
                    )
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        valueAddedTaxService.delete(id);
        RestResponse<Object> response = RestResponse.empty();
        return ResponseEntity.ok(response);
    }
}
