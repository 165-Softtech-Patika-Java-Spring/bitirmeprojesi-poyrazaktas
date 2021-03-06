package com.poyrazaktas.bitirme.prd.enums;

import com.poyrazaktas.bitirme.gen.exception.BaseErrorMessage;

public enum PrdProductErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("Product not found!");

    private String message;

    PrdProductErrorMessage(String message) {
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
