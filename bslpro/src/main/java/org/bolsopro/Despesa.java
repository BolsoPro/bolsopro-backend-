package org.bolsopro;

import java.time.LocalDate;

public class Despesa {
    private int id;
    private String descricao;
    private float valor;
    private LocalDate data;
    private String categoria;
    private int usuarioId;

    public Despesa(int id, String descricao, float valor, LocalDate data, String categoria, int usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.usuarioId = usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public int getUsuarioId() {
        return usuarioId;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
