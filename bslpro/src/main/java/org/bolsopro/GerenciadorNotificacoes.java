package org.bolsopro;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorNotificacoes {
    private int id;
    private List<Notificacao> notificacoes;

    public GerenciadorNotificacoes(int id) {
        this.id = id;
        this.notificacoes = new ArrayList<>();
    }

    public void criarNotificacao(String mensagem, String tipo, int usuarioId) {
        Notificacao notificacao = new Notificacao(
                notificacoes.size() + 1,
                mensagem,
                tipo,
                java.time.LocalDateTime.now(),
                usuarioId
        );
        notificacoes.add(notificacao);
    }

    public void enviarNotificacaoParaUsuario(Usuario usuario) {
        for (Notificacao n : notificacoes) {
            if (n.getUsuarioId() == usuario.getId()) {
                System.out.println(n.enviar());
            }
        }
    }

    public void verificarDespesasComMetas(List<Despesa> despesas, List<MetaFinanceira> metas) {
        for (MetaFinanceira meta : metas) {
            for (Despesa despesa : despesas) {
                if (meta.verificarExcedente(despesa.getValor())) {
                    criarNotificacao(
                            "Despesa excedeu a meta: " + meta.getDescricao(),
                            "Alerta",
                            despesa.getUsuarioId()
                    );
                }
            }
        }
    }
}
