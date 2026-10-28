package br.com.biblioteca.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Empréstimo TEM-UM Usuario e TEM-UM ItemAcervo (associação). */
public class Emprestimo {
    private final Usuario usuario;
    private final ItemAcervo item;
    private final LocalDate dataRetirada;
    private final LocalDate dataPrevista;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, ItemAcervo item, LocalDate dataRetirada) {
        if (!usuario.podeEmprestar()) {
            throw new IllegalStateException(usuario.getNome()
                    + " atingiu o limite ou tem multa pendente.");
        }
        item.marcarEmprestado(); // lança exceção se já estiver emprestado
        usuario.registrarEmprestimo();
        this.usuario = usuario;
        this.item = item;
        this.dataRetirada = dataRetirada;
        this.dataPrevista = dataRetirada.plusDays(item.getPrazoEmDias()); // polimórfico
    }

    /** Devolve o item e calcula multa por atraso. Retorna a multa gerada. */
    public double devolver(LocalDate dataDevolucao) {
        if (this.dataDevolucao != null) {
            throw new IllegalStateException("Empréstimo já devolvido.");
        }
        this.dataDevolucao = dataDevolucao;
        long diasAtraso = Math.max(0, ChronoUnit.DAYS.between(dataPrevista, dataDevolucao));
        double multa = diasAtraso * Emprestavel.MULTA_POR_DIA;
        item.marcarDevolvido();
        usuario.registrarDevolucao(multa);
        return multa;
    }

    public LocalDate getDataPrevista() { return dataPrevista; }

    public String exibirResumo() {
        return String.format("%s -> %s | retirada %s | devolver até %s%s",
                item.getTitulo(), usuario.getNome(), dataRetirada, dataPrevista,
                dataDevolucao == null ? "" : " | devolvido " + dataDevolucao);
    }
}
