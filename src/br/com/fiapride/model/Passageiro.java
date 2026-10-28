package br.com.fiapride.model;

/**
 * Representa um passageiro do FiapRide.
 * Os métodos concentram as regras de negócio do saldo em um único lugar,
 * em vez de espalhar "saldo = saldo + x" pelo código.
 */
public class Passageiro {
    public String nome;
    public String email;
    public double saldo;
    public String tipoConta; // "COMUM" ou "PREMIUM"

    /** Adiciona crédito. Recusa valores zerados ou negativos. */
    public boolean adicionarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: valor de recarga deve ser positivo.");
            return false;
        }
        saldo += valor;
        return true;
    }

    /** Debita uma corrida. Só paga se houver saldo suficiente. */
    public boolean pagar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: valor de pagamento inválido.");
            return false;
        }
        if (valor > saldo) {
            System.out.println("Erro: saldo insuficiente para " + nome + ".");
            return false;
        }
        saldo -= valor;
        return true;
    }

    public String exibirResumo() {
        return String.format("%s (%s) | Saldo: R$ %.2f", nome, tipoConta, saldo);
    }
}
