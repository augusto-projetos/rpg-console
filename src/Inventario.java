package src;
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>(); // Inicializa a lista vazia
    }

    // Adiciona um item na mochila
    public void adicionar(Item item) {
        itens.add(item);
        System.out.println(Cores.GREEN + item.getNome() + " foi adicionado ao inventário." + Cores.RESET);
    }

    // Mostra o que tem dentro (Listar)
    public void exibir() {
        if (itens.isEmpty()) {
            System.out.println(Cores.YELLOW + "Sua mochila está vazia." + Cores.RESET);
        } else {
            System.out.println(Cores.YELLOW_BOLD + "\n=== MOCHILA ===" + Cores.RESET);
            // Loop "For-Each": Para cada 'item' dentro de 'itens'
            for (int i = 0; i < itens.size(); i++) {
                Item item = itens.get(i);
                System.out.println((i + 1) + ". " + Cores.WHITE_BOLD + item.getNome() + Cores.RESET + 
                                   " (Recupera " + item.getValorEfeito() + " " + item.getTipo() + ")");
            }
            System.out.println(Cores.YELLOW_BOLD + "===============\n" + Cores.RESET);
        }
    }

    // Usa um item e remove ele da lista
    public void usarItem(int indice, Personagem dono) {
        // O índice vem do menu (1, 2, 3), mas o array começa em 0. Então subtraímos 1.
        int indiceReal = indice - 1;

        if (indiceReal >= 0 && indiceReal < itens.size()) {
            Item item = itens.get(indiceReal);

            // Aplica o efeito
            if (item.getTipo().equals("Vida")) {
                int novaVida = dono.getVida() + item.getValorEfeito();
                if (novaVida > dono.getVidaMaxima()) novaVida = dono.getVidaMaxima();
                dono.setVida(novaVida);
                System.out.println(Cores.GREEN + "Você usou " + item.getNome() + " e recuperou vida!" + Cores.RESET);
            
            } else if (item.getTipo().equals("Mana")) {
                int novaMana = dono.getMana() + item.getValorEfeito();
                if (novaMana > dono.getManaMaxima()) novaMana = dono.getManaMaxima();
                dono.setMana(novaMana);
                System.out.println(Cores.BLUE + "Você usou " + item.getNome() + " e recuperou mana!" + Cores.RESET);
            }

            // Remove da lista (Gasta o item)
            itens.remove(indiceReal);
        } else {
            System.out.println(Cores.RED + "Item inválido!" + Cores.RESET);
        }
    }
    
    public int getTotalItens() {
        return itens.size();
    }
}