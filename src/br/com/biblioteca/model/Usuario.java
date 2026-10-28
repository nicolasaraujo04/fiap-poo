package br.com.biblioteca.model;

/** Leitor cadastrado. Multas só mudam por métodos de negócio. */
public class Usuario {
    public static final int LIMITE_EMPRESTIMOS = 3;

    private final String nome;
    private final String matricula;
    private int emprestimosAtivos;
    private double multaPendente;

    public Usuario(String nome, String matricula) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (matricula == null || !matricula.matches("\\d{5,6}")) {
            throw new IllegalArgumentException("Matrícula deve ter 5 ou 6 dígitos.");
        }
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public int getEmprestimosAtivos() { return emprestimosAtivos; }
    public double getMultaPendente() { return multaPendente; }

    public boolean podeEmprestar() {
        return emprestimosAtivos < LIMITE_EMPRESTIMOS && multaPendente == 0;
    }

    void registrarEmprestimo() { emprestimosAtivos++; }

    void registrarDevolucao(double multa) {
        emprestimosAtivos--;
        multaPendente += multa;
    }

    public boolean pagarMulta(double valor) {
        if (valor <= 0 || valor > multaPendente) {
            System.out.println("Erro: valor de pagamento inválido.");
            return false;
        }
        multaPendente -= valor;
        return true;
    }
}
