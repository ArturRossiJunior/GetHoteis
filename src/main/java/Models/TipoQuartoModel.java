package Models;

public class TipoQuartoModel extends PadraoModel {
    
    private String nome;
    private int quantidadeCamas;
    private double valorDiaria;
    private String descricao;

    public TipoQuartoModel(String nome, int quantidadeCamas, double valorDiaria, String descricao) {
        this.nome = nome;
        this.quantidadeCamas = quantidadeCamas;
        this.valorDiaria = valorDiaria;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeCamas() {
        return quantidadeCamas;
    }

    public void setQuantidadeCamas(int quantidadeCamas) {
        this.quantidadeCamas = quantidadeCamas;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}