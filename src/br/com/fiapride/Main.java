package br.com.fiapride;

import br.com.fiapride.model.Passageiro;

public class Main {

    static final double TAXA_BASE = 5.0;
    static final double PRECO_POR_KM = 2.5;

    static double calcularValorCorrida(double distanciaKm) {
        if (distanciaKm <= 0) {
            System.out.println("Erro: distância deve ser maior que zero.");
            return -1;
        }
        return TAXA_BASE + distanciaKm * PRECO_POR_KM;
    }

    static double aplicarDesconto(double valor, String tipoConta) {
        return "PREMIUM".equals(tipoConta) ? valor * 0.9 : valor;
    }

    public static void main(String[] args) {
        Passageiro ana = new Passageiro();
        ana.setNome("Ana Silva");
        ana.setEmail("ana@email.com");
        ana.setTipoConta("COMUM");
        ana.adicionarSaldo(50);

        System.out.println("=== Teste de encapsulamento ===");
        System.out.println(ana.exibirResumo());

        // ana.saldo = 1_000_000;  -> não compila mais: saldo é private

        try {
            ana.setEmail("email-invalido");
        } catch (IllegalArgumentException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }
        try {
            ana.setTipoConta("VIP");
        } catch (IllegalArgumentException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }

        double corrida = aplicarDesconto(calcularValorCorrida(8), ana.getTipoConta());
        ana.pagar(corrida);
        System.out.printf("Saldo após corrida (getSaldo): R$ %.2f%n", ana.getSaldo());
    }
}
