package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.Despesa;
import org.bolsopro.blsopro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @GetMapping
    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    @PostMapping
    public Despesa criarDespesa(@RequestBody Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    @GetMapping("/{id}")
    public Despesa buscarPorId(@PathVariable Long id) {
        return despesaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Despesa atualizarDespesa(@PathVariable Long id, @RequestBody Despesa despesaAtualizada) {
        return despesaRepository.findById(id).map(despesa -> {
            despesa.setDescricao(despesaAtualizada.getDescricao());
            despesa.setValor(despesaAtualizada.getValor());
            despesa.setData(despesaAtualizada.getData());
            despesa.setCategoria(despesaAtualizada.getCategoria());
            despesa.setUsuario(despesaAtualizada.getUsuario());
            return despesaRepository.save(despesa);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarDespesa(@PathVariable Long id) {
        despesaRepository.deleteById(id);
    }
}
