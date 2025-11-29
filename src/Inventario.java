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
            
            for (int i = 0; i < itens.size(); i++) {
                Item item = itens.get(i);
                
                // VERIFICA SE É EQUIPAMENTO PARA MOSTRAR OS STATUS CERTOS
                if (item instanceof Equipamento) {
                    Equipamento equip = (Equipamento) item; // Transforma item em equip para ler os dados
                    System.out.println((i + 1) + ". " + Cores.CYAN + equip.getNome() + Cores.RESET + 
                                       " (+" + equip.getAumentoStatus() + " " + equip.getTipo() + " | Slot: " + equip.getSlot() + ")");
                } 
                // SE NÃO, É POÇÃO
                else {
                    System.out.println((i + 1) + ". " + Cores.WHITE_BOLD + item.getNome() + Cores.RESET + 
                                       " (Recupera " + item.getValorEfeito() + " " + item.getTipo() + ")");
                }
            }
            System.out.println(Cores.YELLOW_BOLD + "===============\n" + Cores.RESET);
        }
    }

    // Usa um item e remove ele da lista
    public void usarItem(int indice, Personagem dono) {
        int indiceReal = indice - 1;

        if (indiceReal >= 0 && indiceReal < itens.size()) {
            Item item = itens.get(indiceReal);

            // 1. É POÇÃO?
            if (item.getTipo().equals("Vida")) {
                int novaVida = dono.getVida() + item.getValorEfeito();
                if (novaVida > dono.getVidaMaxima()) novaVida = dono.getVidaMaxima();
                dono.setVida(novaVida);
                System.out.println(Cores.GREEN + "Você usou " + item.getNome() + " e recuperou vida!" + Cores.RESET);
                itens.remove(indiceReal); // Gasta a poção
            
            } else if (item.getTipo().equals("Mana")) {
                int novaMana = dono.getMana() + item.getValorEfeito();
                if (novaMana > dono.getManaMaxima()) novaMana = dono.getManaMaxima();
                dono.setMana(novaMana);
                System.out.println(Cores.BLUE + "Você usou " + item.getNome() + " e recuperou mana!" + Cores.RESET);
                itens.remove(indiceReal); // Gasta a poção
            }

            // 2. É EQUIPAMENTO?
            else if (item instanceof Equipamento) {
                Equipamento equipParaUsar = (Equipamento) item;
                
                // Chama o método do personagem que faz a troca
                dono.equiparItem(equipParaUsar);
                
                // Remove da mochila (pois agora está no corpo)
                itens.remove(indiceReal);
            }

        } else {
            System.out.println(Cores.RED + "Item inválido!" + Cores.RESET);
        }
    }
    
    public int getTotalItens() {
        return itens.size();
    }

    // Verifica se já existe um item com esse nome na lista
    public boolean possuiItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equals(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    // MÉTODOS DE SAVE/LOAD

    // 1. Transforma a mochila inteira numa String única
    // Exemplo: "PoçãoVida,Vida,50,20###ElixirMana,Mana,30,25"
    public String toSaveString() {
        if (itens.isEmpty()) {
            return "VAZIO";
        }

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            // Adiciona o código do item
            sb.append(item.toSaveString());
            
            // Se NÃO for o último item, adiciona o separador ###
            if (i < itens.size() - 1) {
                sb.append("###");
            }
        }
        return sb.toString();
    }

    // 2. Recebe a String gigante e enche a mochila
    public void carregarDoSave(String dados) {
        // Limpa a mochila atual antes de carregar
        this.itens.clear();

        if (dados.equals("VAZIO") || dados.isEmpty()) {
            return; // Mochila fica vazia mesmo
        }

        // Corta a string onde tem ###
        String[] itensTexto = dados.split("###");

        for (String itemString : itensTexto) {
            // Pede para a classe Item criar o objeto a partir do texto
            Item item = Item.fromSaveString(itemString);
            this.itens.add(item);
        }
    }
}