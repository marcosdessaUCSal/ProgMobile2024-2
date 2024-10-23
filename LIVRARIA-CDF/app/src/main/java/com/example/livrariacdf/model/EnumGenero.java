package com.example.livrariacdf.model;

public enum EnumGenero {

    DEV("algo"),
    SCI_FI("ficção científica"),
    HQ("HQ");


    public final String genero;

    EnumGenero(String genero) {
        this.genero = genero;
    }
}
