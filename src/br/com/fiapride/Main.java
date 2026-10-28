package br.com.fiapride;

import br.com.fiapride.model.Carro;
import br.com.fiapride.model.Moto;
import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Viagem;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Teste de herança ===");

        Carro onix = new Carro("ABC1D23", "Chevrolet Onix", 2022, 44, 4, 13.5);
        Moto cg = new Moto("MOT0A12", "Honda CG 160", 2023, 16, 160, true, 40);

        // métodos herdados da mãe Veiculo
        System.out.println(onix.exibirDetalhes() + " | " + onix.getNumeroPortas() + " portas");
        System.out.println(cg.exibirDetalhes() + " | " + cg.getCilindradas() + "cc"
                + (cg.isPossuiBau() ? " com baú" : ""));
        cg.atualizarPlaca("MOT1B23", "DET-778899");

        // a validação de placa da mãe vale para as filhas
        try {
            new Moto("XX", "Yamaha Factor", 2021, 15, 150, false, 38);
        } catch (IllegalArgumentException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }

        // Viagem aceita Carro e Moto porque ambos SÃO Veiculo
        Passageiro ana = new Passageiro("Ana Silva", "ana@email.com");
        ana.adicionarSaldo(100);
        Viagem v = new Viagem(ana, cg, "Consolação", "Vila Madalena", 4);
        v.finalizar();
        System.out.println(v.exibirResumo());
    }
}
