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
        String[] partes = linha.split(",");
        
        // Se tiver 7 partes, é um EQUIPAMENTO (Arma/Armadura)
        if (partes.length == 7) {
            String nome = partes[0];
            String tipo = partes[1];
            // partes[2] é o valorEfeito (0 para equips), pulamos ou usamos
            int preco = Integer.parseInt(partes[3]);
            int aumentoStatus = Integer.parseInt(partes[4]);
            String slot = partes[5];
            String classeIdeal = partes[6];
            
            return new Equipamento(nome, tipo, aumentoStatus, preco, slot, classeIdeal);
        } 
        
        // Se tiver 4 partes, é um ITEM COMUM (Poção)
        else {
            return new Item(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
        }
    }
}