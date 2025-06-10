package org.bolsopro.blsopro.service;

import org.bolsopro.blsopro.Notificacao;
import org.bolsopro.blsopro.MetaFinanceira;
import org.bolsopro.blsopro.Despesa;
import org.bolsopro.blsopro.Usuario;
import org.bolsopro.blsopro.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GerenciadorNotificacoes {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    // Corrigido para receber o objeto Usuario, e não o ID
    public void criarNotificacao(String mensagem, String tipo, Usuario usuario) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(mensagem);
        notificacao.setTipo(tipo);
        notificacao.setData(LocalDateTime.now());
        notificacao.setUsuario(usuario); // ❗ define o objeto, não o ID

        notificacaoRepository.save(notificacao);
    }

    public void enviarNotificacaoParaUsuario(Usuario usuario) {
        List<Notificacao> notificacoes = notificacaoRepository.findByUsuarioId(usuario.getId());
        for (Notificacao n : notificacoes) {
            System.out.println(n.enviar());
        }
    }

    public void verificarDespesasComMetas(List<Despesa> despesas, List<MetaFinanceira> metas) {
        for (MetaFinanceira meta : metas) {
            for (Despesa despesa : despesas) {
                if (meta.verificarExcedente(despesa.getValor())) {
                    // ❗ Aqui também corrigido para usar o objeto do usuário da despesa
                    criarNotificacao(
                            "Despesa excedeu a meta: " + meta.getDescricao(),
                            "Alerta",
                            despesa.getUsuario()
                    );
                }
            }
        }
    }
}
