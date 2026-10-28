package br.com.biblioteca.model;

public class Livro extends ItemAcervo {
    private final String autor;
    private final int paginas;

    public Livro(String codigo, String titulo, int anoPublicacao, String autor, int paginas) {
        super(codigo, titulo, anoPublicacao);
        if (paginas <= 0) {
            throw new IllegalArgumentException("Número de páginas inválido.");
        }
        this.autor = autor;
        this.paginas = paginas;
    }

    @Override
    public String getTipo() { return "Livro"; }

    /** Livros grandes ganham mais tempo de leitura. */
    @Override
    public int getPrazoEmDias() {
        return paginas > 400 ? 21 : 14;
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + " | " + autor + ", " + paginas + " págs";
    }

    public String getAutor() { return autor; }
}
