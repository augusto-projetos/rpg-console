public class Habilidade {
    private String nome;
    private int custoMana;
    private int poder;
    private String tipo;

    public Habilidade(String nome, int custoMana, int poder, String tipo) {
        this.nome = nome;
        this.custoMana = custoMana;
        this.poder = poder;
        this.tipo = tipo;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public int getCustoMana() {
        return custoMana;
    }

    // Retorna TRUE se funcionou, FALSE se falhou (sem mana)
    public boolean usar(Personagem atacante, Personagem alvo) {
        
        // Verifica se o dono tem mana
        if (atacante.getMana() < this.custoMana) {
            System.out.println("Mana insuficiente! (" + atacante.getMana() + "/" + this.custoMana + ")");
            return false;
        }

        // Gasta a Mana do atacante
        atacante.setMana(atacante.getMana() - this.custoMana);
        System.out.println(atacante.getNome() + " conjurou " + this.nome + "!");

        // Aplica o efeito
        if (this.tipo.equals("Dano")) {
            // A magia soma a força do mago + o poder da magia
            int danoTotal = atacante.getForca() + this.poder;
            
            System.out.println("A habilidade causou " + danoTotal + " de impacto!");
            alvo.receberDano(danoTotal);
        }

        return true;
    }
}