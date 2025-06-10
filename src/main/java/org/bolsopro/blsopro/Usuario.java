package org.bolsopro.blsopro;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private PerfilComportamental perfilComportamental;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receitas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Despesa> despesas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MetaFinanceira> metasFinanceiras = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacao> notificacoes = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void cadastrarReceita(Receita receita) {
        receita.setUsuario(this);
        receitas.add(receita);
    }

    public void cadastrarDespesa(Despesa despesa) {
        despesa.setUsuario(this);
        despesas.add(despesa);
    }

    public void definirMeta(MetaFinanceira meta) {
        meta.setUsuario(this);
        metasFinanceiras.add(meta);
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilComportamental getPerfilComportamental() {
        return perfilComportamental;
    }

    public void setPerfilComportamental(PerfilComportamental perfilComportamental) {
        this.perfilComportamental = perfilComportamental;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public List<MetaFinanceira> getMetasFinanceiras() {
        return metasFinanceiras;
    }

    public void setMetasFinanceiras(List<MetaFinanceira> metasFinanceiras) {
        this.metasFinanceiras = metasFinanceiras;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }
}
