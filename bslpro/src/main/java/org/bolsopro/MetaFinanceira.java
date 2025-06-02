package org.bolsopro;

public class MetaFinanceira {
    private int id;
    private String descricao;
    private float valorLimite;
    private String periodo;
    private int usuarioId;

    public MetaFinanceira(int id, String descricao, float valorLimite, String periodo, int usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valorLimite = valorLimite;
        this.periodo = periodo;
        this.usuarioId = usuarioId;
    }

    public float getValorLimite() {
        return valorLimite;
    }

    public String getPeriodo() {
        return periodo;
    }

    public boolean verificarExcedente(float valorDespesa) {
        return valorDespesa > valorLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
