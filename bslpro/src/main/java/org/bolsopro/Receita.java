package org.bolsopro;

import java.time.LocalDate;

public class Receita {
    private int id;
    private String descricao;
    private float valor;
    private LocalDate data;
    private int usuarioId;

    // Construtor
    public Receita(int id, String descricao, float valor, LocalDate data, int usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.usuarioId = usuarioId;
    }

    // getters
    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    // setters
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
