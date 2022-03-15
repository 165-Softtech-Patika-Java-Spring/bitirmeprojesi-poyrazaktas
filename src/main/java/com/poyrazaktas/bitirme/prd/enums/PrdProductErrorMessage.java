package com.poyrazaktas.bitirme.prd.enums;

public enum PrdProductErrorMessage {
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
