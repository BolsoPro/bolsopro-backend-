package org.bolsopro;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private PerfilComportamental perfilComportamental;
    private List<Receita> receitas;
    private List<Despesa> despesas;
    private List<MetaFinanceira> metasFinanceiras;
    private List<Notificacao> notificacoes;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.metasFinanceiras = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
    }

    public void cadastrarReceita(Receita receita) {
        receitas.add(receita);
    }

    public void cadastrarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public void definirMeta(MetaFinanceira meta) {
        metasFinanceiras.add(meta);
    }

    // Outros métodos conforme dicionário de dados...
}
