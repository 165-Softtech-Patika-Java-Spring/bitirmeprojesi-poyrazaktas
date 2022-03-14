package com.poyrazaktas.bitirme.gen.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
@RequiredArgsConstructor
public class BusinessException extends  RuntimeException{
    private final BaseErrorMessage errorMessage;
}
