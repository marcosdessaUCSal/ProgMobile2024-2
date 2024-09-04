package com.ciclodevida.model;

import java.util.ArrayList;

public class CicloDeVida {
    private static CicloDeVida instance = null;

    private ArrayList<String> estados;
    private int count = 0;

    private CicloDeVida() {
        estados = new ArrayList<>();
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < estados.size(); i++) {
            sb.append(estados.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static boolean podeDizerValor() {
        return instance == null;
    }

    public static int digaValor() {
        return 2024;
    }

    public void registraEstado(String msg) {
        count++;
        estados.add(String.valueOf(count) + " - " + msg);
    }

    public static CicloDeVida getInstance() {
        if (instance == null) {
            instance = new CicloDeVida();
        }
        return instance;
    }


}
