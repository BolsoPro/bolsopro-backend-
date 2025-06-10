package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.Notificacao;
import org.bolsopro.blsopro.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    private final NotificacaoRepository notificacaoRepository;

    @Autowired
    public NotificacaoController(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @GetMapping
    public List<Notificacao> listarTodas() {
        return notificacaoRepository.findAll();
    }

    @PostMapping
    public Notificacao criarNotificacao(@RequestBody Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    @GetMapping("/{id}")
    public Notificacao buscarPorId(@PathVariable Long id) {
        return notificacaoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Notificacao atualizarNotificacao(@PathVariable Long id, @RequestBody Notificacao nova) {
        return notificacaoRepository.findById(id).map(notificacao -> {
            notificacao.setMensagem(nova.getMensagem());
            notificacao.setTipo(nova.getTipo());
            notificacao.setData(nova.getData());
            notificacao.setUsuario(nova.getUsuario());
            return notificacaoRepository.save(notificacao);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarNotificacao(@PathVariable Long id) {
        notificacaoRepository.deleteById(id);
    }
}
