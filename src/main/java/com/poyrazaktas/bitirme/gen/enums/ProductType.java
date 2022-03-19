package com.poyrazaktas.bitirme.gen.enums;

public enum ProductType {
    NUTRITION("NUTRITION"),
    STATIONARY("STATIONARY"),
    CLOTHING("CLOTHING"),
    TECHNOLOGY("TECHNOLOGY"),
    CLEANING("CLEANING"),
    OTHER("OTHER")
    ;

    private String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
