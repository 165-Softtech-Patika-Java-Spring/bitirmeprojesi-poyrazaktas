package com.poyrazaktas.bitirme.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomExceptionResponse {
    private Date date;
    private String message;
    private String description;
}
