package com.poyrazaktas.bitirme.gen.enums;

import com.poyrazaktas.bitirme.gen.exception.BaseErrorMessage;

public enum GenErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("Item not found!"),;

    private String message;

    GenErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
