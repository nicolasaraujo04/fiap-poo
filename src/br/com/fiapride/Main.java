package br.com.fiapride;

import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Veiculo;
import br.com.fiapride.model.Viagem;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Teste de associação ===");

        Passageiro ana = new Passageiro("Ana Silva", "ana@email.com");
        ana.adicionarSaldo(50);
        Passageiro bruno = new Passageiro("Bruno Costa", "bruno@email.com", "PREMIUM");
        bruno.adicionarSaldo(10);

        Veiculo onix = new Veiculo("ABC1D23", "Chevrolet Onix", 2022);

        Viagem v1 = new Viagem(ana, onix, "FIAP Paulista", "Av. Faria Lima", 6.4);
        System.out.println(v1.exibirResumo());
        v1.finalizar();
        System.out.println(v1.exibirResumo());
        System.out.printf("Saldo da Ana após a viagem: R$ %.2f%n", ana.getSaldo());

        // A viagem acessa o objeto real: a mudança aparece no próprio passageiro
        System.out.println("Mesmo objeto? " + (v1.getSolicitante() == ana));

        Viagem v2 = new Viagem(bruno, onix, "Pinheiros", "Aeroporto de Congonhas", 15);
        v2.finalizar(); // Bruno não tem saldo suficiente
        System.out.println(v2.exibirResumo());
    }
}
