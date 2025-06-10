package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.Receita;
import org.bolsopro.blsopro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping
    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }

    @PostMapping
    public Receita criarReceita(@RequestBody Receita receita) {
        return receitaRepository.save(receita);
    }

    @GetMapping("/{id}")
    public Receita buscarPorId(@PathVariable Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Receita atualizarReceita(@PathVariable Long id, @RequestBody Receita receitaAtualizada) {
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
