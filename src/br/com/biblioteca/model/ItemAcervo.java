package br.com.biblioteca.model;

/**
 * Classe mãe abstrata: não existe "um item genérico" na estante,
 * só livros, revistas etc.
 */
public abstract class ItemAcervo implements Emprestavel {
    private final String codigo;
    private final String titulo;
    private final int anoPublicacao;
    private boolean disponivel;

    protected ItemAcervo(String codigo, String titulo, int anoPublicacao) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código é obrigatório.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        if (anoPublicacao < 1450 || anoPublicacao > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Ano de publicação inválido.");
        }
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public int getAnoPublicacao() { return anoPublicacao; }

    @Override
    public boolean isDisponivel() { return disponivel; }

    @Override
    public void marcarEmprestado() {
        if (!disponivel) {
            throw new IllegalStateException(titulo + " já está emprestado.");
        }
        disponivel = false;
    }

    @Override
    public void marcarDevolvido() {
        disponivel = true;
    }

    /** Cada filha descreve a si mesma. */
    public abstract String getTipo();

    public String exibirDetalhes() {
        return String.format("[%s] %s (%d) - %s", getTipo(), titulo, anoPublicacao,
                disponivel ? "disponível" : "emprestado");
    }
}
