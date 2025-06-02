package org.bolsopro;

public class SugestaoInvestimento {
    private int id;
    private String tipo;
    private String descricao;
    private String nivelRisco;

    public SugestaoInvestimento(int id, String tipo, String descricao, String nivelRisco) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.nivelRisco = nivelRisco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    public static SugestaoInvestimento gerarSugestoes(String tipoPerfil) {
        if (tipoPerfil.equalsIgnoreCase("Conservador")) {
            return new SugestaoInvestimento(1, "CDB", "Investimento de baixo risco", "Baixo");
        } else if (tipoPerfil.equalsIgnoreCase("Moderado")) {
            return new SugestaoInvestimento(2, "Fundos Imobiliários", "Renda variável com risco moderado", "Médio");
        } else if (tipoPerfil.equalsIgnoreCase("Agressivo")) {
            return new SugestaoInvestimento(3, "Criptomoedas", "Investimento de alto risco", "Alto");
        } else {
            return new SugestaoInvestimento(0, "Poupança", "Investimento padrão", "Baixo");
        }
    }
}
