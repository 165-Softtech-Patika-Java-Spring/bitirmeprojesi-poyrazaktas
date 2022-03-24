package com.poyrazaktas.bitirme.sec.enums;

import com.poyrazaktas.bitirme.gen.exception.BaseErrorMessage;

public enum SecSecurityErrorMessage implements BaseErrorMessage {
    REQUEST_BODY_IS_EMPTY("Request body is empty!");

    private String message;
    SecSecurityErrorMessage(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String toString() {
        return message;
    }
}
