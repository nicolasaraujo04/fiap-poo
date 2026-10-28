package br.com.fiapride;

import br.com.fiapride.model.Carro;
import br.com.fiapride.model.Moto;
import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Veiculo;
import br.com.fiapride.model.Viagem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Teste polimórfico ===");

        List<Veiculo> frota = List.of(
                new Carro("ABC1D23", "Chevrolet Onix", 2022, 44, 4, 13.5),
                new Moto("MOT0A12", "Honda CG 160", 2023, 16, 160, true, 40),
                new Carro("XYZ9876", "Fiat Mobi", 2021, 47, 4, 14.2)
        );

        // a variável é do tipo genérico Veiculo, mas cada objeto executa SUA versão
        for (Veiculo veiculo : frota) {
            System.out.printf("%s | Autonomia: %.0f km | Tarifa: R$ %.2f/km%n",
                    veiculo.exibirDetalhes(), veiculo.calcularAutonomia(), veiculo.getTarifaPorKm());
        }

        // a mesma viagem custa diferente dependendo do veículo
        Passageiro ana = new Passageiro("Ana Silva", "ana@email.com");
        ana.adicionarSaldo(100);
        System.out.println("\nCotação de 10 km para " + ana.getNome() + ":");
        for (Veiculo veiculo : frota) {
            Viagem cotacao = new Viagem(ana, veiculo, "FIAP Paulista", "Ibirapuera", 10);
            System.out.printf("  %-16s R$ %.2f%n", veiculo.getModelo(), cotacao.calcularValor());
        }
    }
}
