public class Item {
    private String nome;
    private String tipo; // Ex: "Cura", "Buff", etc.
    private int valorEfeito; // Quanto cura
    private int preco;

    public Item(String nome, String tipo, int valorEfeito, int preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.valorEfeito = valorEfeito;
        this.preco = preco;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public int getValorEfeito() {
        return valorEfeito;
    }
    public int getPreco() {
        return preco;
    }
}