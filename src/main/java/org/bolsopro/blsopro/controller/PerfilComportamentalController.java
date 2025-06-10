package org.bolsopro.blsopro.controller;

import org.bolsopro.blsopro.PerfilComportamental;

import org.bolsopro.blsopro.Receita;
import org.bolsopro.blsopro.Despesa;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfil")
public class PerfilComportamentalController {

    @PostMapping("/avaliar")
    public PerfilComportamental avaliarPerfil(@RequestBody PerfilRequest request) {
        PerfilComportamental perfil = new PerfilComportamental();
        perfil.avaliar(request.getReceitas(), request.getDespesas());
        return perfil;
    }


    // Classe auxiliar interna para receber receitas e despesas no JSON
    public static class PerfilRequest {
        private List<Receita> receitas;
        private List<Despesa> despesas;

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
    }
}
