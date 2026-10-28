package br.com.fiapride.model;

/**
 * Veículo cadastrado no FiapRide.
 *
 * Decisões de design:
 *  - NÃO existe setModelo(): o modelo de um carro não muda fisicamente.
 *  - setPlaca() é PRIVADO: trocar placa no mundo real é um processo no Detran,
 *    não um simples "alterar campo". Quem quiser trocar usa atualizarPlaca(),
 *    que exige um protocolo e registra a operação.
 */
public class Veiculo {
    private String placa;
    private String modelo;
    private int ano;

    /** Construtor sem ano: assume o ano corrente e delega com this(). */
    public Veiculo(String placa, String modelo) {
        this(placa, modelo, java.time.Year.now().getValue());
    }

    /** Construtor completo. */
    public Veiculo(String placa, String modelo, int ano) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo é obrigatório.");
        }
        if (ano < 2010) {
            throw new IllegalArgumentException("FiapRide só aceita veículos a partir de 2010.");
        }
        this.setPlaca(placa);
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }

    /** Valida formato Mercosul (ABC1D23) ou antigo (ABC1234). Privado de propósito. */
    private void setPlaca(String placa) {
        if (placa == null) {
            throw new IllegalArgumentException("Placa é obrigatória.");
        }
        String normalizada = placa.toUpperCase().replace("-", "").trim();
        if (!normalizada.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
            throw new IllegalArgumentException("Placa inválida: " + placa);
        }
        this.placa = normalizada;
    }

    /**
     * Processo oficial de troca de placa: exige protocolo do Detran.
     * Retorna false se o processo for recusado.
     */
    public boolean atualizarPlaca(String novaPlaca, String protocoloDetran) {
        if (protocoloDetran == null || !protocoloDetran.matches("DET-\\d{6}")) {
            System.out.println("Troca recusada: protocolo do Detran inválido.");
            return false;
        }
        String antiga = this.placa;
        this.setPlaca(novaPlaca);
        System.out.println("Placa atualizada de " + antiga + " para " + this.placa
                + " (protocolo " + protocoloDetran + ")");
        return true;
    }

    public String exibirDetalhes() {
        return String.format("%s %d - Placa %s", modelo, ano, placa);
    }
}
