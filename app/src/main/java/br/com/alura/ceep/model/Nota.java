package br.com.alura.ceep.model;

import java.io.Serializable;

public class Nota implements Serializable {

    private final String titulo;
    private final String descricao;
    private int cor;

    public Nota(String titulo, String descricao, int cor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.cor = cor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
}