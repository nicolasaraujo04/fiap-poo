package br.com.fiapride.model;

/**
 * Representa um passageiro do FiapRide.
 * Uma classe é o "molde": cada passageiro criado com new é um objeto
 * com os mesmos atributos, mas com valores próprios.
 */
public class Passageiro {
    public String nome;
    public String email;
    public double saldo;
    public String tipoConta; // "COMUM" ou "PREMIUM"
}
