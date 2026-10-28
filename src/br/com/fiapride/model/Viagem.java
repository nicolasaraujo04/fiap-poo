package br.com.fiapride.model;

/**
 * Uma Viagem TEM-UM Passageiro e TEM-UM Veiculo (associação).
 * Guardamos o objeto inteiro, e não só o nome, porque ao finalizar
 * a viagem precisamos AGIR sobre o passageiro (debitar o saldo).
 */
public class Viagem {
    private static final double TAXA_BASE = 5.0;
    private static final double PRECO_POR_KM = 2.5;

    private final Passageiro solicitante;
    private final Veiculo veiculo;
    private final String origem;
    private final String destino;
    private final double distanciaKm;
    private String status; // SOLICITADA, FINALIZADA, CANCELADA

    public Viagem(Passageiro solicitante, Veiculo veiculo,
                  String origem, String destino, double distanciaKm) {
        if (solicitante == null || veiculo == null) {
            throw new IllegalArgumentException("Viagem precisa de passageiro e veículo.");
        }
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("Distância deve ser positiva.");
        }
        this.solicitante = solicitante;
        this.veiculo = veiculo;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.status = "SOLICITADA";
    }

    public double calcularValor() {
        double valor = TAXA_BASE + distanciaKm * PRECO_POR_KM;
        if ("PREMIUM".equals(solicitante.getTipoConta())) {
            valor *= 0.9;
        }
        return valor;
    }

    /** Encerra a corrida e cobra do passageiro associado. */
    public boolean finalizar() {
        if (!"SOLICITADA".equals(status)) {
            System.out.println("Viagem já encerrada.");
            return false;
        }
        if (solicitante.pagar(calcularValor())) {
            status = "FINALIZADA";
            return true;
        }
        status = "CANCELADA";
        return false;
    }

    public Passageiro getSolicitante() { return solicitante; }
    public Veiculo getVeiculo() { return veiculo; }
    public String getStatus() { return status; }

    public String exibirResumo() {
        return String.format("%s -> %s (%.1f km) | %s | %s | R$ %.2f | %s",
                origem, destino, distanciaKm, solicitante.getNome(),
                veiculo.exibirDetalhes(), calcularValor(), status);
    }
}
