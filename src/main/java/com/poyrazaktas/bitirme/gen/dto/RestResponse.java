package com.poyrazaktas.bitirme.gen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> implements Serializable {
    private T data;
    private boolean success;

    public static <T> RestResponse<T> of(T t){
        return new RestResponse<>(t,true);
    }

    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>(t,false);
    }

    public static <T> RestResponse<T> empty(){
        return new RestResponse<>(null,true);
    }
}
