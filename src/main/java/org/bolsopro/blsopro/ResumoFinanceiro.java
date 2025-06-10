package org.bolsopro.blsopro;


import java.util.List;

public class ResumoFinanceiro {
    private int id;
    private float totalReceitas;
    private float totalDespesas;
    private float saldo;
    private int usuarioId;

    public ResumoFinanceiro(int id, float totalReceitas, float totalDespesas, float saldo, int usuarioId) {
        this.id = id;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.saldo = saldo;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalReceitas() {
        return totalReceitas;
    }

    public void setTotalReceitas(float totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public float getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(float totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void calcularTotalReceitas(List<Receita> receitas) {
        totalReceitas = 0;
        for (Receita r : receitas){
            totalReceitas += r.getValor();
        }
    }

    public void calcularTotalDespesas(List<Despesa> despesas) {
        totalDespesas = 0;
        for (Despesa d : despesas){
            totalDespesas += d.getValor();
        }
    }

    public void calcularSaldo() {
        saldo = totalReceitas - totalDespesas;
    }

    public void gerarGraficoResumo(){
        System.out.println("Resumo Financeiro:");
        System.out.println("Total de Receitas: " + totalReceitas);
        System.out.println("Total de Despesas: " + totalDespesas);
        System.out.println("Saldo:" + saldo);
    }

}
