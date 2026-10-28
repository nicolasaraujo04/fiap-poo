package br.com.fiapride;

import br.com.fiapride.model.Passageiro;

public class Main {

    static final double TAXA_BASE = 5.0;
    static final double PRECO_POR_KM = 2.5;

    /** Calcula o valor estimado de uma corrida. Retorna -1 para distância inválida. */
    static double calcularValorCorrida(double distanciaKm) {
        if (distanciaKm <= 0) {
            System.out.println("Erro: distância deve ser maior que zero.");
            return -1;
        }
        return TAXA_BASE + distanciaKm * PRECO_POR_KM;
    }

    /** Aplica desconto de 10% para contas PREMIUM. */
    static double aplicarDesconto(double valor, String tipoConta) {
        if ("PREMIUM".equals(tipoConta)) {
            return valor * 0.9;
        }
        return valor;
    }

    public static void main(String[] args) {
        Passageiro ana = new Passageiro();
        ana.nome = "Ana Silva";
        ana.email = "ana@email.com";
        ana.saldo = 50.0;
        ana.tipoConta = "COMUM";

        Passageiro bruno = new Passageiro();
        bruno.nome = "Bruno Costa";
        bruno.email = "bruno@email.com";
        bruno.saldo = 120.0;
        bruno.tipoConta = "PREMIUM";

        System.out.println("=== Teste de métodos ===");
        ana.adicionarSaldo(30);
        ana.adicionarSaldo(-10); // deve ser recusado
        System.out.println(ana.exibirResumo());

        double corrida = calcularValorCorrida(12);
        double corridaBruno = aplicarDesconto(corrida, bruno.tipoConta);
        System.out.printf("Corrida de 12 km: R$ %.2f (Bruno paga R$ %.2f)%n", corrida, corridaBruno);

        bruno.pagar(corridaBruno);
        ana.pagar(500); // saldo insuficiente
        calcularValorCorrida(0); // distância inválida

        System.out.println(ana.exibirResumo());
        System.out.println(bruno.exibirResumo());
    }
}
