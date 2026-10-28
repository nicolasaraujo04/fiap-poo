package br.com.fiapride;

import br.com.fiapride.model.Passageiro;

public class Main {
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

        System.out.println("=== Passageiros FiapRide ===");
        System.out.println(ana.nome + " | " + ana.tipoConta + " | Saldo: R$ " + ana.saldo);
        System.out.println(bruno.nome + " | " + bruno.tipoConta + " | Saldo: R$ " + bruno.saldo);
    }
}
