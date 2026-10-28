package br.com.fiapride.model;

/**
 * CarroEletrico É-UM Veiculo (herança simples) e CUMPRE o contrato
 * Recarregavel (interface). Avaliavel já vem herdado de Veiculo.
 */
public class CarroEletrico extends Veiculo implements Recarregavel {
    private static final int PORCENTAGEM_POR_MINUTO = 1;

    private final double kmPorKwh;
    private int nivelBateria; // 0..100 %

    /** Para elétricos, a "capacidade" da mãe representa a bateria em kWh. */
    public CarroEletrico(String placa, String modelo, int ano,
                         double bateriaKwh, double kmPorKwh) {
        super(placa, modelo, ano, bateriaKwh);
        if (kmPorKwh <= 0) {
            throw new IllegalArgumentException("Eficiência deve ser positiva.");
        }
        this.kmPorKwh = kmPorKwh;
        this.nivelBateria = CARGA_MAXIMA;
    }

    @Override
    public String getCategoria() {
        return "Elétrico";
    }

    /** Autonomia considera só a carga ATUAL da bateria. */
    @Override
    public double calcularAutonomia() {
        return capacidadeTanqueLitros * kmPorKwh * nivelBateria / CARGA_MAXIMA;
    }

    @Override
    public double getTarifaPorKm() {
        return 2.2;
    }

    @Override
    public void recarregar(int minutos) {
        if (minutos <= 0) {
            System.out.println("Erro: tempo de recarga inválido.");
            return;
        }
        nivelBateria = Math.min(CARGA_MAXIMA, nivelBateria + minutos * PORCENTAGEM_POR_MINUTO);
        System.out.printf("%s recarregado em %dV: %d%%%n", getModelo(), VOLTAGEM_PADRAO, nivelBateria);
    }

    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }

    /** Simula o gasto de bateria após uma corrida. */
    public void consumirBateria(double distanciaKm) {
        double kwhGasto = distanciaKm / kmPorKwh;
        int pctGasto = (int) Math.ceil(kwhGasto / capacidadeTanqueLitros * CARGA_MAXIMA);
        nivelBateria = Math.max(0, nivelBateria - pctGasto);
    }

    public boolean podeAceitarCorrida() {
        return nivelBateria >= CARGA_MINIMA_PARA_CORRIDA;
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + " | Bateria " + nivelBateria + "%";
    }
}
