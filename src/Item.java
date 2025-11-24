package src;
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

    // MÉTODOS PARA O SISTEMA DE SAVE

    // 1. Transforma o Item em uma linha de texto
    // Exemplo de retorno: "Poção de Vida,Vida,50,20"
    public String toSaveString() {
        return nome + "," + tipo + "," + valorEfeito + "," + preco;
    }

    // 2. Cria um Item a partir de uma linha de texto (Método Estático "Fábrica")
    public static Item fromSaveString(String linha) {
        // Corta o texto onde tem vírgula
        String[] partes = linha.split(",");
        
        // partes[0] = Nome
        // partes[1] = Tipo
        // partes[2] = Valor (precisa converter pra int)
        // partes[3] = Preço (precisa converter pra int)
        
        return new Item(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
    }
}