package org.bolsopro.blsopro;

import jakarta.persistence.*;

@Entity
public class MetaFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private float valorLimite;
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public MetaFinanceira() {
    }

    public MetaFinanceira(String descricao, float valorLimite, String periodo, Usuario usuario) {
        this.descricao = descricao;
        this.valorLimite = valorLimite;
        this.periodo = periodo;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(float valorLimite) {
        this.valorLimite = valorLimite;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean verificarExcedente(float valorDespesa) {
        return valorDespesa > valorLimite;
    }
}
