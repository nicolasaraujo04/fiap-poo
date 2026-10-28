package br.com.fiapride.model;

/**
 * Representa um passageiro do FiapRide.
 * Atributos privados: ninguém de fora altera o estado diretamente.
 * O saldo NÃO tem setter de propósito: ele só muda por operações
 * de negócio (adicionarSaldo / pagar), que têm regras.
 */
public class Passageiro {
    private String nome;
    private String email;
    private double saldo;
    private String tipoConta; // "COMUM" ou "PREMIUM"

    // ---------- Getters ----------
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public double getSaldo() { return saldo; }
    public String getTipoConta() { return tipoConta; }

    // ---------- Setters com validação ----------
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("E-mail inválido: " + email);
        }
        this.email = email.trim().toLowerCase();
    }

    public void setTipoConta(String tipoConta) {
        if (!"COMUM".equals(tipoConta) && !"PREMIUM".equals(tipoConta)) {
            throw new IllegalArgumentException("Tipo de conta deve ser COMUM ou PREMIUM.");
        }
        this.tipoConta = tipoConta;
    }

    // ---------- Regras de negócio ----------
    public boolean adicionarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: valor de recarga deve ser positivo.");
            return false;
        }
        saldo += valor;
        return true;
    }

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
