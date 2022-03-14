package com.poyrazaktas.bitirme.typ.enums;

public enum TypType {
    NUTRITION("NUTRITION"),
    STATIONARY("STATIONARY"),
    CLOTHING("CLOTHING"),
    TECHNOLOGY("TECHNOLOGY"),
    CLEANING("CLEANING"),
    OTHER("OTHER")
    ;

    private String name;

    TypType(String name) {
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
