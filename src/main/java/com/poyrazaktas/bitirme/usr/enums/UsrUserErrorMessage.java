package com.poyrazaktas.bitirme.usr.enums;

import com.poyrazaktas.bitirme.gen.exception.BaseErrorMessage;

public enum UsrUserErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("User not found!");

    private String message;

    UsrUserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
