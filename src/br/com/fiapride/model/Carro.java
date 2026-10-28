package br.com.fiapride.model;

/** Carro É-UM Veiculo: herda placa, modelo, ano e as regras de validação. */
public class Carro extends Veiculo {
    private int numeroPortas;
    private double consumoKmPorLitro;

    public Carro(String placa, String modelo, int ano, double capacidadeTanqueLitros,
                 int numeroPortas, double consumoKmPorLitro) {
        // a mãe valida e inicializa os próprios atributos privados
        super(placa, modelo, ano, capacidadeTanqueLitros);
        if (numeroPortas != 2 && numeroPortas != 4) {
            throw new IllegalArgumentException("Carro deve ter 2 ou 4 portas.");
        }
        if (consumoKmPorLitro <= 0) {
            throw new IllegalArgumentException("Consumo deve ser positivo.");
        }
        this.numeroPortas = numeroPortas;
        this.consumoKmPorLitro = consumoKmPorLitro;
    }

    public int getNumeroPortas() { return numeroPortas; }
    public double getConsumoKmPorLitro() { return consumoKmPorLitro; }
}
