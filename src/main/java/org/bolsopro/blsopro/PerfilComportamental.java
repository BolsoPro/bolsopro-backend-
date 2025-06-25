package org.bolsopro.blsopro;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PerfilComportamental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPerfil tipoPerfil;

    public enum TipoPerfil {
        CONSERVADOR("Conservador"),
        MODERADO("Moderado"),
        AGRESSIVO("Agressivo");

        private final String descricao;

        TipoPerfil(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public PerfilComportamental() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public void avaliarPerfil(float totalReceitas, float totalDespesas) {
        float proporcao = totalDespesas / (totalReceitas + 1); // evita divisão por zero

        if (proporcao < 0.3) {
            this.tipoPerfil = TipoPerfil.CONSERVADOR;
        } else if (proporcao < 0.7) {
            this.tipoPerfil = TipoPerfil.MODERADO;
        } else {
            this.tipoPerfil = TipoPerfil.AGRESSIVO;
        }
    }

    public void avaliar(List<Receita> receitas, List<Despesa> despesas) {
        float totalReceitas = receitas.stream()
                .map(Receita::getValor)
                .reduce(0f, Float::sum);

        float totalDespesas = despesas.stream()
                .map(Despesa::getValor)
                .reduce(0f, Float::sum);

        this.avaliarPerfil(totalReceitas, totalDespesas);
    }
}
