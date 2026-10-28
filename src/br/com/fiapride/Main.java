package br.com.fiapride;

import br.com.fiapride.model.Avaliavel;
import br.com.fiapride.model.Carro;
import br.com.fiapride.model.CarroEletrico;
import br.com.fiapride.model.Moto;
import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Recarregavel;
import br.com.fiapride.model.Veiculo;
import br.com.fiapride.model.Viagem;

import java.util.List;

/**
 * Demonstração final do FiapRide: reúne os conceitos das aulas 1 a 9.
 */
public class Main {
    public static void main(String[] args) {
        // ---------- Aulas 1-4: objetos, métodos, encapsulamento, construtores ----------
        Passageiro ana = new Passageiro("Ana Silva", "ana@email.com");
        Passageiro bruno = new Passageiro("Bruno Costa", "bruno@email.com", "PREMIUM");
        ana.adicionarSaldo(100);
        bruno.adicionarSaldo(60);

        // ---------- Aulas 6-9: herança, polimorfismo, abstrata, interfaces ----------
        CarroEletrico dolphin = new CarroEletrico("ELT2A45", "BYD Dolphin", 2024, 44.9, 6.5);
        List<Veiculo> frota = List.of(
                new Carro("ABC1D23", "Chevrolet Onix", 2022, 44, 4, 13.5),
                new Moto("MOT0A12", "Honda CG 160", 2023, 16, 160, true, 40),
                dolphin
        );

        System.out.println("=== Frota FiapRide ===");
        for (Veiculo veiculo : frota) {
            System.out.printf("%s | Autonomia: %.0f km%n",
                    veiculo.exibirDetalhes(), veiculo.calcularAutonomia());
        }

        // ---------- Aula 5: associação ----------
        System.out.println("\n=== Viagens ===");
        Viagem v1 = new Viagem(ana, dolphin, "FIAP Paulista", "Aeroporto de Guarulhos", 30);
        v1.finalizar();
        dolphin.consumirBateria(30);
        System.out.println(v1.exibirResumo());

        Viagem v2 = new Viagem(bruno, frota.get(1), "Pinheiros", "Moema", 9);
        v2.finalizar();
        System.out.println(v2.exibirResumo());

        // ---------- Aula 9: interfaces e constantes ----------
        System.out.println("\n=== Recarga (interface Recarregavel) ===");
        System.out.println("Carga mínima para corrida: " + Recarregavel.CARGA_MINIMA_PARA_CORRIDA + "%");
        for (Veiculo veiculo : frota) {
            if (veiculo instanceof Recarregavel recarregavel) {
                System.out.println(veiculo.getModelo() + " está com " + recarregavel.getNivelBateria() + "%");
                recarregavel.recarregar(15);
            }
        }

        System.out.println("\n=== Avaliações (interface Avaliavel) ===");
        // a mesma interface une classes de hierarquias diferentes
        List<Avaliavel> avaliaveis = List.of(ana, bruno, dolphin, frota.get(1));
        ana.avaliar(5);
        ana.avaliar(4);
        bruno.avaliar(5);
        dolphin.avaliar(5);
        dolphin.avaliar(3);
        frota.get(1).avaliar(4);
        frota.get(1).avaliar(Avaliavel.NOTA_MAXIMA + 1); // inválida
        for (Avaliavel a : avaliaveis) {
            String nome = (a instanceof Passageiro p) ? p.getNome() : ((Veiculo) a).getModelo();
            System.out.printf("%-15s média %.1f%n", nome, a.getMediaAvaliacoes());
        }

        System.out.println("\n=== Saldos finais ===");
        System.out.println(ana.exibirResumo());
        System.out.println(bruno.exibirResumo());
    }
}
