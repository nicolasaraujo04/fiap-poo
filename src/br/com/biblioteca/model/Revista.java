package br.com.biblioteca.model;

public class Revista extends ItemAcervo {
    private final int edicao;

    public Revista(String codigo, String titulo, int anoPublicacao, int edicao) {
        super(codigo, titulo, anoPublicacao);
        if (edicao <= 0) {
            throw new IllegalArgumentException("Edição inválida.");
        }
        this.edicao = edicao;
    }

    @Override
    public String getTipo() { return "Revista"; }

    /** Revistas circulam rápido: prazo menor. */
    @Override
    public int getPrazoEmDias() {
        return 7;
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + " | edição " + edicao;
    }
}
