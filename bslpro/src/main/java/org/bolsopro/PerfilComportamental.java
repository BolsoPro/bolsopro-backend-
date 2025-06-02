package org.bolsopro;

import java.util.List;

public class PerfilComportamental {
    private int id;
    private String tipoPerfil;
    private int usuarioId;

    public PerfilComportamental(int id, String tipoPerfil, int usuarioId) {
        this.id = id;
        this.tipoPerfil = tipoPerfil;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    // avaliar o perfil do usuário
    public void avaliar(List<Receita> receitas, List<Despesa> despesas) {
        float totalReceitas = 0;
        for (Receita r : receitas) {
            totalReceitas += r.getValor();
        }

        float totalDespesas = 0;
        for (Despesa d : despesas) {
            totalDespesas += d.getValor();
        }

        float proporcao = totalDespesas / (totalReceitas + 1); // evitar divisão por zero

        if (proporcao < 0.3) {
            this.tipoPerfil = "Conservador";
        } else if (proporcao < 0.7) {
            this.tipoPerfil = "Moderado";
        } else {
            this.tipoPerfil = "Agressivo";
        }
    }
}
