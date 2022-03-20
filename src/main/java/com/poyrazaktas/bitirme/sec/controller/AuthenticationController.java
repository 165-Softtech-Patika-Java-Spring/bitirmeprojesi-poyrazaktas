package com.poyrazaktas.bitirme.sec.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.sec.dto.JwtUserLoginReqDto;
import com.poyrazaktas.bitirme.sec.service.AuthenticationService;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
            tags = "Authentication Controller",
            description = "You can login to the system," +
                    " and get your authentication token with this request.",
            summary = "Login",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = JwtUserLoginReqDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Login Request",
                                                    summary = "An example of user login request",
                                                    description = "An example of user login request " +
                                                            "which username is \"michael\", and password is \"12345678\"",
                                                    value = "{\n" +
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
                            description = "User logged in successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unsuccessful Login Response. " +
                                    "It happens when your credentials don't match or you didn't register yet.",
                            content = @Content()
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtUserLoginReqDto jwtUserLoginReqDto) {
        String token = authenticationService.login(jwtUserLoginReqDto);
        RestResponse<String> response = RestResponse.of(token);

        return ResponseEntity.ok(response);
    }

    @Operation(
            tags = "Authentication Controller",
            description = "You can register to the system with this request.",
            summary = "Register",
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
                                                    name = "Register Request",
                                                    summary = "An example of user register request",
                                                    description = "An example of user register request " +
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
                            description = "User registered successfully",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unsuccessful Register Response. " +
                                    "It happens when you register a username which is already in use.",
                            content = @Content()
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsrUserSaveReqDto userSaveReqDto) {
        UsrUserDto user = authenticationService.register(userSaveReqDto);
        RestResponse<UsrUserDto> response = RestResponse.of(user);
        return ResponseEntity.ok(response);
    }
}
