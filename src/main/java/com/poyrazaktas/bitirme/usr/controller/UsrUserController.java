package com.poyrazaktas.bitirme.usr.controller;

import com.poyrazaktas.bitirme.gen.dto.RestResponse;
import com.poyrazaktas.bitirme.usr.dto.UsrUserDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserSaveReqDto;
import com.poyrazaktas.bitirme.usr.dto.UsrUserUpdateReqDto;
import com.poyrazaktas.bitirme.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsrUserController {
    private final UsrUserService userService;

    @GetMapping
    public ResponseEntity findAll() {
        List<UsrUserDto> userDtoList = userService.findAll();
        RestResponse<List<UsrUserDto>> response = RestResponse.of(userDtoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        UsrUserDto usrUserDto = userService.get(id);
        RestResponse<UsrUserDto> response = RestResponse.of(usrUserDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody UsrUserSaveReqDto saveReqDto) {
        UsrUserDto userDto = userService.save(saveReqDto);
        RestResponse<UsrUserDto> response = RestResponse.of(userDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UsrUserUpdateReqDto updateReqDto) {
        UsrUserDto userDto = userService.update(updateReqDto);
        RestResponse<UsrUserDto> response = RestResponse.of(userDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        RestResponse<Object> response = RestResponse.empty();
        return ResponseEntity.ok(response);
    }
}
