package br.com.fiapride.model;

/** Moto É-UM Veiculo, com características próprias (cilindradas, baú). */
public class Moto extends Veiculo {
    private int cilindradas;
    private boolean possuiBau;
    private double consumoKmPorLitro;

    public Moto(String placa, String modelo, int ano, double capacidadeTanqueLitros,
                int cilindradas, boolean possuiBau, double consumoKmPorLitro) {
        super(placa, modelo, ano, capacidadeTanqueLitros);
        if (cilindradas < 50) {
            throw new IllegalArgumentException("Cilindradas inválidas.");
        }
        if (consumoKmPorLitro <= 0) {
            throw new IllegalArgumentException("Consumo deve ser positivo.");
        }
        this.cilindradas = cilindradas;
        this.possuiBau = possuiBau;
        this.consumoKmPorLitro = consumoKmPorLitro;
    }

    @Override
    public double calcularAutonomia() {
        return capacidadeTanqueLitros * consumoKmPorLitro;
    }

    @Override
    public double getTarifaPorKm() {
        return 1.6; // moto é mais barata por km
    }

    @Override
    public String exibirDetalhes() {
        return "[Moto] " + super.exibirDetalhes() + " | " + cilindradas + "cc"
                + (possuiBau ? " com baú" : "");
    }

    public int getCilindradas() { return cilindradas; }
    public boolean isPossuiBau() { return possuiBau; }
    public double getConsumoKmPorLitro() { return consumoKmPorLitro; }
}
