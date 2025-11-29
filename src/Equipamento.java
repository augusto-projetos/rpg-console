package src;

public class Equipamento extends Item {
    private int aumentoStatus; // Pode ser Dano (arma) ou Defesa (armadura)
    private String slot; // "Arma" ou "Armadura"

    // Construtor
    public Equipamento(String nome, String tipo, int aumentoStatus, int preco, String slot) {
        // Passamos '0' no valorEfeito porque equipamento não cura vida/mana instantaneamente
        super(nome, tipo, 0, preco);
        
        this.aumentoStatus = aumentoStatus;
        this.slot = slot;
    }

    // Getters específicos
    public int getAumentoStatus() { return aumentoStatus; }

    public String getSlot() { return slot; }

    // Sobrescrita (Polimorfismo)
    @Override
    public String toSaveString() {
        // Formato: Nome,Tipo,ValorEfeito,Preco,AumentoStatus,Slot
        return getNome() + "," + getTipo() + "," + getValorEfeito() + "," + getPreco() + "," + aumentoStatus + "," + slot;
    }
}