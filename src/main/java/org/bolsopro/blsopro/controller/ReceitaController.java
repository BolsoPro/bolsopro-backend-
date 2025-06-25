package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.Receita;
import org.bolsopro.blsopro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping
    public List<Receita> listarReceitasDoUsuario(@PathVariable Long usuarioId) {
        return receitaRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Receita criarReceita(@PathVariable Long usuarioId, @RequestBody Receita receita) {
        receita.getUsuario().setId(usuarioId); 
        return receitaRepository.save(receita);
    }

    @PutMapping("/{id}")
    public Receita atualizarReceita(@PathVariable Long usuarioId, @PathVariable Long id, @RequestBody Receita receitaAtualizada) {
        return receitaRepository.findById(id).map(receita -> {
            receita.setDescricao(receitaAtualizada.getDescricao());
            receita.setValor(receitaAtualizada.getValor());
            receita.setData(receitaAtualizada.getData());
            receita.setUsuario(receitaAtualizada.getUsuario());
            return receitaRepository.save(receita);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarReceita(@PathVariable Long id) {
        receitaRepository.deleteById(id);
    }
}
