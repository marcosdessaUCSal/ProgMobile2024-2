package com.esferas.globo;

import java.util.ArrayList;

public class Globo {

    private static Globo instance = null;

    // número de partículas por círculo horizontal;
    private static final int NUM_PART = 12;

    //número de círculos horizontais sem contar o equador
    private static final int NUM_CIRC = 5;

    private static final double RAIO_MAX = 400;

    private ArrayList<Circulo> arrayCirculos;

    private double omega;

    private double escalaX;
    private double escalaY;

    private Globo() {
        this.arrayCirculos = new ArrayList<>();
        this.inicializa();
    }

    public static Globo getInstance() {
        if (instance == null) {
            instance = new Globo();
        }
        return instance;
    }


    public void variaOmega(float delta) {
        this.omega += delta;
    }


    // Criação de toda a estrutura do globo
    private void inicializa() {
        this.escalaX = 1.0F;
        this.escalaY = 1.0F;
        this.omega = 0;
        this.criaTodosOsCirculos();
    }

    public void setEscalaX(float s) {
        this.escalaX = s;
    }

    public void setEscalaY(float s) {
        this.escalaY = s;
    }

    public double getEscalaX() {
        return this.escalaX;
    }

    public double getEscalaY() {
        return this.escalaY;
    }

    private Circulo criaUmCirculo(double r, double h) {
        Circulo c = new Circulo();
        c.r = r;
        c.h = h;
        // Enche o círculo de pontos polares
        double teta = 0;
        for (int i = 0; i < NUM_PART; i++) {
            teta = (2 * Math.PI / NUM_PART) * i;
            Particula p = new Particula();
            p.pontoPolar = new PontoPolar(r, teta);
            p.cor = new Cor(100, 100, 100);
            p.h = h;
            c.arrParticulas.add(p);
        }
        return c;
    }

    private void criaTodosOsCirculos() {
        double ang = (Math.PI / 2) / NUM_CIRC;
        double r;
        double h;
        ArrayList<Circulo> array = new ArrayList<>();
        for (int i = 1; i <= NUM_CIRC; i++) {
            r = (float) (RAIO_MAX * Math.cos(ang * i));
            h = (float) (RAIO_MAX * Math.sin(ang * i));
            array.add(criaUmCirculo(r, h));
            array.add(criaUmCirculo(r, -h));
        }
        // círculo do equador
        h = 0;
        r = RAIO_MAX;
        array.add(criaUmCirculo(r, h));
        this.arrayCirculos = array;
    }

    public ArrayList<Particula> getParcitulasAtras() {
        ArrayList<Particula> arrayResposta = new ArrayList<>();
        for (Circulo c : arrayCirculos) {
            for (Particula p : c.arrParticulas) {
                if (Math.sin(p.pontoPolar.teta + omega) >= 0) {
                    Particula novaParticula = new Particula();
                    novaParticula.pontoPolar = new PontoPolar(p.pontoPolar.r, p.pontoPolar.teta + omega);
                    novaParticula.h = p.h;
                    novaParticula.cor = determinaCor(novaParticula.pontoPolar.teta, p.h);
                    novaParticula.tamanho = calctamanho(novaParticula.pontoPolar.teta, p.h);
                    arrayResposta.add(novaParticula);
                }
            }
        }
        return arrayResposta;
    }

    public ArrayList<Particula> getParticulasFrente() {
        ArrayList<Particula> arrayResposta = new ArrayList<>();
        for (Circulo c : arrayCirculos) {
            for (Particula p : c.arrParticulas) {
                if (Math.sin(p.pontoPolar.teta + omega) < 0) {
                    Particula novaParticula = new Particula();
                    novaParticula.pontoPolar = new PontoPolar(p.pontoPolar.r, p.pontoPolar.teta + omega);
                    novaParticula.h = p.h;
                    novaParticula.cor = determinaCor(novaParticula.pontoPolar.teta, p.h);
                    novaParticula.tamanho = calctamanho(novaParticula.pontoPolar.teta, p.h);
                    arrayResposta.add(novaParticula);
                }
            }
        }
        return arrayResposta;
    }

    // Calcula o tamanho (raio) da partícula, com base no ângulo
    private double calctamanho(double angulo, double h) {
        double th = (400 - Math.abs(h)) / 400;
        double resultado = 6 * (1 - 0.6 * Math.sin(angulo) * th);
        return resultado;
    }

    // Determina cor
    private Cor determinaCor(double angulo, double h) {
        double nivel = 0;
        double nivel2 = 0;
        nivel = 1 - Math.sin(angulo + Math.PI / 3) * 50;
        nivel2 = (RAIO_MAX - h) / RAIO_MAX / 6 + 0.6;
        Cor cor = new Cor((int) ((200 + nivel) * nivel2), (int) ((100 + nivel) * nivel2), (int) ((200 + nivel) * nivel2));
        return cor;
    }


}
