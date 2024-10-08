package models;

public class Filme {
    private String nome_filme;
    private int preco;

    public Filme(String nome_filme, int preco) {
        this.nome_filme = nome_filme;
        this.preco = preco;
    }
    public String getNome_filme() {
        return this.nome_filme;
    }
    public void setNome_filme(String nome_filme) {
        this.nome_filme = nome_filme;
    }
    public int getPreco() {
        return this.preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
}
