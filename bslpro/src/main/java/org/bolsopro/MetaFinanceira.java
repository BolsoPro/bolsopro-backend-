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

    //comparar a despesa com o limite da meta financeira, retornando true se a despesa exceder o valor limite.
    public boolean verificarExcedente(float valorDespesa) {
        return  valorDespesa > valorLimite;
    }


}
