package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.MetaFinanceira;
import org.bolsopro.blsopro.Usuario;
import org.bolsopro.blsopro.repository.MetaFinanceiraRepository;
import org.bolsopro.blsopro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaFinanceiraController {

    @Autowired
    private MetaFinanceiraRepository metaFinanceiraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<MetaFinanceira> listarTodas() {
        return metaFinanceiraRepository.findAll();
    }

    @PostMapping
    public MetaFinanceira criarMeta(@RequestBody MetaFinanceira metaFinanceira) {
        return metaFinanceiraRepository.save(metaFinanceira);
    }

    @GetMapping("/{id}")
    public MetaFinanceira buscarPorId(@PathVariable Long id) {
        return metaFinanceiraRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public MetaFinanceira atualizarMeta(@PathVariable Long id, @RequestBody MetaFinanceira atualizada) {
        return metaFinanceiraRepository.findById(id).map(meta -> {
            meta.setDescricao(atualizada.getDescricao());
            meta.setValorLimite(atualizada.getValorLimite());
            meta.setPeriodo(atualizada.getPeriodo());
            meta.setUsuario(atualizada.getUsuario());
            return metaFinanceiraRepository.save(meta);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarMeta(@PathVariable Long id) {
        metaFinanceiraRepository.deleteById(id);
    }

    @PostMapping("/usuarios/{id}/metas")
    public MetaFinanceira adicionarMetaParaUsuario(@PathVariable Long id, @RequestBody MetaFinanceira meta) {
        return usuarioRepository.findById(id).map(usuario -> {
            meta.setUsuario(usuario);
            return metaFinanceiraRepository.save(meta);
        }).orElse(null);
    }
}
