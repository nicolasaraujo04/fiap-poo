package br.com.fiapride.model;

/**
 * Contrato para tudo que pode receber nota no app (passageiros e veículos).
 * Campos de interface são automaticamente public static final (constantes).
 */
public interface Avaliavel {
    int NOTA_MINIMA = 1;
    int NOTA_MAXIMA = 5;

    void avaliar(int nota);

    double getMediaAvaliacoes();
}
