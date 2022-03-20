package com.poyrazaktas.bitirme.vat.enums;

import com.poyrazaktas.bitirme.gen.exception.BaseErrorMessage;

public enum VatValueAddedTaxErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("Value Added Tax not found!");

    private String message;

    VatValueAddedTaxErrorMessage(String message) {
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
