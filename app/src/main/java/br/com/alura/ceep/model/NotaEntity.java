package br.com.alura.ceep.model;

import java.io.Serializable;

public class NotaEntity implements Serializable {

    private int id;
    private String titulo;
    private String descricao;
    private int cor;
    private int posicao;

    public NotaEntity(int id, String titulo, String descricao, int cor, int posicao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cor = cor;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}