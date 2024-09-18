package com.telalogin.Model;

import java.io.Serializable;

public class InfoLogin implements Serializable {

    private String login;
    private String senha;
    private boolean lembrar;
    private int erros;

    public InfoLogin(String login, String senha, boolean lembrar) {
        this.lembrar = lembrar;
        this.login = login;
        this.senha = senha;
        this.erros = 0;
    }

    public int getErros() {
        return this.erros;
    }

    public void acrescentarErro() {
        this.erros++;
    }

    public boolean isLembrar() {
        return lembrar;
    }

    public void setLembrar(boolean lembrar) {
        this.lembrar = lembrar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
