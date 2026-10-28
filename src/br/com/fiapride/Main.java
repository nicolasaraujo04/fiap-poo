package br.com.fiapride;

import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Veiculo;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Teste de construtores ===");

        Passageiro convidado = new Passageiro();                       // construtor padrão
        Passageiro ana = new Passageiro("Ana Silva", "ana@email.com"); // 2 parâmetros
        Passageiro bruno = new Passageiro("Bruno Costa", "bruno@email.com", "PREMIUM");
        ana.adicionarSaldo(50);

        System.out.println(convidado.exibirResumo());
        System.out.println(ana.exibirResumo());
        System.out.println(bruno.exibirResumo());

        Veiculo onix = new Veiculo("ABC1D23", "Chevrolet Onix", 2022);
        Veiculo hb20 = new Veiculo("XYZ-9876", "Hyundai HB20"); // ano corrente via this()
        System.out.println(onix.exibirDetalhes());
        System.out.println(hb20.exibirDetalhes());

        // onix.setModelo("Ferrari");  -> não existe
        // onix.setPlaca("AAA0000");   -> privado, não compila
        onix.atualizarPlaca("BRA2E19", "123");        // recusado
        onix.atualizarPlaca("BRA2E19", "DET-004521"); // aceito

        try {
            new Veiculo("123", "Fusca", 1980);
        } catch (IllegalArgumentException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }
    }
}
