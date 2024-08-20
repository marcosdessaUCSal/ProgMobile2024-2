package com.contatos1.Model;

import java.util.ArrayList;

public class ListaContatos {

    private ArrayList<Contato> listaContatos;

    public ListaContatos() {
        listaContatos = new ArrayList<>();
        this.geraLista();
    }

    public ArrayList<Contato> getListaContatos() {
        return listaContatos;
    }



    // GERA A LISTA DE CONTATOS
    private void geraLista() {
        // contato 1
        Contato contato = new Contato();
        contato.setNome("Fulano de Tal");
        contato.setEmail("fulanodetal@email.com");
        contato.setTel("(71) 99999 1234");
        listaContatos.add(contato);

        // contato 1
        contato = new Contato();
        contato.setNome("Sicrano");
        contato.setEmail("siucrano@email.com");
        contato.setTel("(71) 98888 4321");
        listaContatos.add(contato);

        // contato 2
        contato = new Contato();
        contato.setNome("Beltrano Jr");
        contato.setEmail("beljr@email.com");
        contato.setTel("(71) 98786 1645");
        listaContatos.add(contato);

        // contato 3
        contato = new Contato();
        contato.setNome("Senhor Dr Tiririca");
        contato.setEmail("srtiririca@email.com");
        contato.setTel("(71) 99123 1345");
        listaContatos.add(contato);

        // contato 4
        contato = new Contato();
        contato.setNome("Excelentíssimo Cidadão");
        contato.setEmail("cidadao@email.com");
        contato.setTel("(71) 90012 1287");
        listaContatos.add(contato);

        // contato 5
        contato = new Contato();
        contato.setNome("Um Amigo");
        contato.setEmail("amigo@email.com");
        contato.setTel("(71) 9987 9978");
        listaContatos.add(contato);
    }
}
