package org.bolsopro;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        // ✅ 1. Criar usuário
        Usuario usuario = new Usuario(1, "Maria", "maria@email.com", "senha123");

        // ✅ 2. Criar e adicionar uma receita
        Receita receita = new Receita(1, "Salário", 3000.0f, LocalDate.now(), usuario.getId());
        usuario.cadastrarReceita(receita);

        // ✅ 3. Criar e adicionar uma despesa
        Despesa despesa = new Despesa(1, "Aluguel", 1200.0f, LocalDate.now(), "Moradia", usuario.getId());
        usuario.cadastrarDespesa(despesa);

        // ✅ 4. Definir meta financeira
        MetaFinanceira meta = new MetaFinanceira(1, "Limite mensal", 2000.0f, "Mensal", usuario.getId());
        usuario.definirMeta(meta);

        // ✅ 5. Criar perfil comportamental e avaliar
        PerfilComportamental perfil = new PerfilComportamental(1, "", usuario.getId());
        perfil.avaliar(usuario.getReceitas(), usuario.getDespesas());
        System.out.println("Perfil financeiro: " + perfil.getTipoPerfil());

        // ✅ 6. Gerar sugestão de investimento
        SugestaoInvestimento sugestao = SugestaoInvestimento.gerarSugestoes(perfil.getTipoPerfil());
        System.out.println("Sugestão de Investimento: " + sugestao.getDescricao());

        // ✅ 7. Criar e calcular resumo financeiro
        ResumoFinanceiro resumo = new ResumoFinanceiro(1, 0, 0, 0, usuario.getId());
        resumo.calcularTotalReceitas(usuario.getReceitas());
        resumo.calcularTotalDespesas(usuario.getDespesas());
        resumo.calcularSaldo();
        resumo.gerarGraficoResumo();

        // ✅ 8. Gerenciador de notificações
        GerenciadorNotificacoes gerenciador = new GerenciadorNotificacoes(1);
        gerenciador.verificarDespesasComMetas(usuario.getDespesas(), usuario.getMetasFinanceiras());
        gerenciador.enviarNotificacaoParaUsuario(usuario);
    }
}
