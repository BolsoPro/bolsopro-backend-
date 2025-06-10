package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.ResumoFinanceiro;
import org.bolsopro.blsopro.Receita;
import org.bolsopro.blsopro.Despesa;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumo")
public class ResumoFinanceiroController {

    @PostMapping
    public ResumoFinanceiro gerarResumo(@RequestBody ResumoRequest request) {
        ResumoFinanceiro resumo = new ResumoFinanceiro(0, 0, 0, 0, request.getUsuarioId());
        resumo.calcularTotalReceitas(request.getReceitas());
        resumo.calcularTotalDespesas(request.getDespesas());
        resumo.calcularSaldo();
        return resumo;
    }

    // Classe auxiliar interna para receber receitas e despesas no JSON
    public static class ResumoRequest {
        private List<Receita> receitas;
        private List<Despesa> despesas;
        private int usuarioId;

        // Construtor padrão necessário para desserialização
        public ResumoRequest() {}

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

        public int getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(int usuarioId) {
            this.usuarioId = usuarioId;
        }
    }
}