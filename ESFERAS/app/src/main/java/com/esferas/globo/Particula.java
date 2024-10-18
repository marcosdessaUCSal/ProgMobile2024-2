package com.esferas.globo;

public class Particula {

    public PontoPolar pontoPolar;
    public Cor cor;
    public double h;
    public double tamanho;

    public Particula() {
        this.pontoPolar = new PontoPolar();
        this.cor = new Cor();
        this.h = 0;
        this.tamanho = 5; // default, apenas para constar
    }
}
