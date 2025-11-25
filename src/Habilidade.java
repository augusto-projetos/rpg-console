package src;
public class Habilidade {
    private String nome;
    private int custoMana;
    private int poder;
    private String tipo;
    private String efeitoStatus; // Ex: "Veneno", "Stun", "Normal"
    private int turnosStatus; // Quantos turnos dura
    private int poderStatus; // Dano por turno (DOT)

    public Habilidade(String nome, int custoMana, int poder, String tipo, String efeitoStatus, int turnosStatus, int poderStatus) {
        this.nome = nome;
        this.custoMana = custoMana;
        this.poder = poder;
        this.tipo = tipo;
        this.efeitoStatus = efeitoStatus;
        this.turnosStatus = turnosStatus;
        this.poderStatus = poderStatus;
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
            System.out.println(Cores.RED + "Mana insuficiente! (" + atacante.getMana() + "/" + this.custoMana + ")" + Cores.RESET);
            return false;
        }

        // Gasta a Mana do atacante
        atacante.setMana(atacante.getMana() - this.custoMana);
        System.out.println(Cores.CYAN + atacante.getNome() + " conjurou " + this.nome + "!" + Cores.RESET);

        // Aplica o efeito
        if (this.tipo.equals("Dano")) {
            // A magia soma a força do mago + o poder da magia
            int danoTotal = atacante.getForca() + this.poder;
            
            System.out.println(Cores.PURPLE + "A habilidade causou " + danoTotal + " de impacto!" + Cores.RESET);
            alvo.receberDano(danoTotal);
        }

        if (!this.efeitoStatus.equals("Normal")) {
            alvo.receberStatus(this.efeitoStatus, this.turnosStatus, this.poderStatus);
        }

        return true;
    }
}