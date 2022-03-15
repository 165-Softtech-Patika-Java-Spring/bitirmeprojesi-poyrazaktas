package com.poyrazaktas.bitirme.gen.enums;

public enum ProductType {
    NUTRITION("NUTRITION"),
    STATIONARY("STATIONARY"),
    CLOTHING("CLOTHING"),
    TECHNOLOGY("TECHNOLOGY"),
    CLEANING("CLEANING"),
    OTHER("OTHER")
    ;

    private String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
