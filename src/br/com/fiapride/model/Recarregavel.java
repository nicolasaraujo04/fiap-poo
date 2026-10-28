package br.com.fiapride.model;

/**
 * Contrato para veículos que funcionam com bateria.
 * É uma interface (e não uma classe mãe) porque um CarroEletrico já
 * herda de Veiculo, e Java só permite UMA classe mãe.
 */
public interface Recarregavel {
    int CARGA_MAXIMA = 100;          // %
    int CARGA_MINIMA_PARA_CORRIDA = 20; // %
    int VOLTAGEM_PADRAO = 220;       // volts

    void recarregar(int minutos);

    int getNivelBateria();
}
