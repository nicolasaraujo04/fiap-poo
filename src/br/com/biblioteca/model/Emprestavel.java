package br.com.biblioteca.model;

/** Contrato para itens do acervo que podem sair da biblioteca. */
public interface Emprestavel {
    double MULTA_POR_DIA = 1.50;

    /** Cada tipo de item define seu próprio prazo. */
    int getPrazoEmDias();

    boolean isDisponivel();

    void marcarEmprestado();

    void marcarDevolvido();
}
