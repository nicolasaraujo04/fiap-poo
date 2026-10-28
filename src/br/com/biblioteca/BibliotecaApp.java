package br.com.biblioteca;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.ItemAcervo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Revista;
import br.com.biblioteca.model.Usuario;

import java.time.LocalDate;
import java.util.List;

/** Projeto pessoal: sistema de empréstimos de uma biblioteca. */
public class BibliotecaApp {
    public static void main(String[] args) {
        Livro domCasmurro = new Livro("L001", "Dom Casmurro", 1899, "Machado de Assis", 256);
        Livro grandeSertao = new Livro("L002", "Grande Sertão: Veredas", 1956, "Guimarães Rosa", 624);
        Revista superInteressante = new Revista("R001", "Superinteressante", 2025, 470);

        List<ItemAcervo> acervo = List.of(domCasmurro, grandeSertao, superInteressante);

        System.out.println("=== Acervo (polimorfismo) ===");
        for (ItemAcervo item : acervo) {
            System.out.println(item.exibirDetalhes() + " | prazo " + item.getPrazoEmDias() + " dias");
        }

        Usuario carla = new Usuario("Carla Mendes", "558812");
        LocalDate hoje = LocalDate.of(2026, 10, 1);

        System.out.println("\n=== Empréstimos (associação) ===");
        Emprestimo e1 = new Emprestimo(carla, grandeSertao, hoje);
        Emprestimo e2 = new Emprestimo(carla, superInteressante, hoje);
        System.out.println(e1.exibirResumo());
        System.out.println(e2.exibirResumo());

        try {
            new Emprestimo(new Usuario("Diego Lima", "771203"), grandeSertao, hoje);
        } catch (IllegalStateException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }

        System.out.println("\n=== Devoluções ===");
        double multa1 = e1.devolver(LocalDate.of(2026, 10, 20));  // dentro do prazo
        double multa2 = e2.devolver(LocalDate.of(2026, 10, 12));  // 4 dias de atraso
        System.out.printf("Multa Grande Sertão: R$ %.2f | Multa revista: R$ %.2f%n", multa1, multa2);
        System.out.printf("%s deve R$ %.2f e pode emprestar? %b%n",
                carla.getNome(), carla.getMultaPendente(), carla.podeEmprestar());

        carla.pagarMulta(carla.getMultaPendente());
        System.out.println("Após pagar, pode emprestar? " + carla.podeEmprestar());

        System.out.println("\n=== Acervo final ===");
        acervo.forEach(item -> System.out.println(item.exibirDetalhes()));
    }
}
