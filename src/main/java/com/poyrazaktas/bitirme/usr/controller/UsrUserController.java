package com.poyrazaktas.bitirme.usr.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserUpdateReqDto;
import com.poyrazaktas.bitirme.usr.service.UsrUserService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsrUserController {
    private final UsrUserService userService;

    @Operation(
            tags = "User Controller",
            description = "Find all users",
            summary = "Find All Users"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of users",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UsrUserDto.class))
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
        List<UsrUserDto> userDtoList = userService.findAll();
        RestResponse<List<UsrUserDto>> response = RestResponse.of(userDtoList);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "User Controller",
            description = "Get user by id.",
            summary = "Get User by Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found a user",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
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
        UsrUserDto usrUserDto = userService.get(id);
        RestResponse<UsrUserDto> response = RestResponse.of(usrUserDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "User Controller",
            description = "Save user",
            summary = "Save User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsrUserSaveReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "User save request",
                                                    summary = "An example of user save request",
                                                    description = "An example of user save request " +
                                                            "which first name is \"Steve\", last name is \"Carell\", " +
                                                            "username is \"michael\", and password is \"12345678\"",
                                                    value = "{\n" +
                                                            "   \"firstName\" : \"Steve\",\n" +
                                                            "   \"lastName\" : \"Carell\",\n" +
                                                            "   \"userName\" : \"michael\",\n" +
                                                            "   \"password\" : \"12345678\"\n" +
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
                            description = "User saved successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while saving user",
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
    public ResponseEntity save(@RequestBody UsrUserSaveReqDto saveReqDto) {
        UsrUserDto userDto = userService.save(saveReqDto);
        RestResponse<UsrUserDto> response = RestResponse.of(userDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "User Controller",
            description = "Update user",
            summary = "Update User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsrUserUpdateReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "User update request",
                                                    summary = "An example of user update request",
                                                    description = "An example of user update request " +
                                                            "which id is 1, first name is \"Steve\", last name is \"Carell\", " +
                                                            "username is \"michael\", and password is \"12345678\"",
                                                    value = "{\n" +
                                                            "   \"id\" : 1,\n" +
                                                            "   \"firstName\" : \"Steve\",\n" +
                                                            "   \"lastName\" : \"Carell\",\n" +
                                                            "   \"userName\" : \"michael\",\n" +
                                                            "   \"password\" : \"12345678\"\n" +
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
                            description = "User updated successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while updating the user",
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
    public ResponseEntity update(@RequestBody UsrUserUpdateReqDto updateReqDto) {
        UsrUserDto userDto = userService.update(updateReqDto);
        RestResponse<UsrUserDto> response = RestResponse.of(userDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "User Controller",
            description = "Delete user",
            summary = "Delete User"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User deleted successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error while deleting the user",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found a user to delete",
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
        userService.delete(id);
        RestResponse<Object> response = RestResponse.empty();
        return ResponseEntity.ok(response);
    }
}
