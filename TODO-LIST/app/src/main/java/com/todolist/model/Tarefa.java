package com.todolist.model;

public class Tarefa {

    public Integer id;
    public String texto;
    public boolean marcado;

    public Tarefa() {

    }

    public Tarefa(String texto) {
        this.id = null;
        this.texto = texto;
        this.marcado = false;
    }

    public Tarefa(Integer id, String texto, boolean marcado) {
        this.id = id;
        this.texto = texto;
        this.marcado = marcado;
    }
}
