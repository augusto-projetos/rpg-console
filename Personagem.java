public class Personagem {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int forca;
    private int defesa;
    private int nivel;
    private int xp;
    private boolean eMonstro;
    private String classe;
    private java.util.Random random;

    // Construtor
    public Personagem(String nome, int vida, int forca, int defesa, String classe) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.nivel = 1;
        this.xp = 0;
        this.eMonstro = false;
        this.classe = classe;
        this.random = new java.util.Random();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getForca() {
        return forca;
    }
    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }

    public boolean getEMonstro() {
        return eMonstro;
    }
    public void setEMonstro(boolean eMonstro) {
        this.eMonstro = eMonstro;
    }

    public String getClasse() {
        return classe;
    }
    public void setClasse(String classe) {
        this.classe = classe;
    }

    // Métodos de combate
    public int atacar(Personagem alvo) {
        int dado = random.nextInt(10);
        int danoTotal = this.forca + dado;

        // Lógica para o Monstro
        if (this.eMonstro && (this.vida * 100) / this.vidaMaxima <= 40) { // A chance do monstro sobe para 40% se a vida chegar a 40%
            if (dado >= 6) { // 40% de chance de crítico
                System.out.println("\n>>> O MONSTRO FICOU FURIOSO! CRÍTICO! <<<");
                danoTotal = danoTotal * 2;
            }

        } else if (this.eMonstro) {
            if (dado == 9) { // 10% de chance de crítico
                System.out.println("\n>>> O MONSTRO ACERTOU UM CRÍTICO! <<<");
                danoTotal = danoTotal * 2;
            }
        }

        // Lógica para o Herói
        else {
            if (dado == 9) { // 10% de chance de crítico
                System.out.println(">>> CRÍTICO! <<<");
                danoTotal = danoTotal * 2;
            }
        }

        // Sistema de Vantagem de Classe
        // Verificamos se existe vantagem do ATACANTE sobre o ALVO

        // GUERREIRO: Forte contra Arqueiros (Esqueleto) e Feras (Slime)
        if (this.classe.equalsIgnoreCase("Guerreiro")) {
            if (alvo.getClasse().equalsIgnoreCase("Arqueiro") || alvo.getClasse().equalsIgnoreCase("Fera")) {
                System.out.println("VANTAGEM: Sua lâmina corta fundo em " + alvo.getNome() + "!");
                danoTotal += 5; // Dano Bônus
            }
        } 
        
        // MAGO: Forte contra Guerreiros (Orcs e Golems têm armadura pesada, magia ignora)
        else if (this.classe.equalsIgnoreCase("Mago")) {
            if (alvo.getClasse().equalsIgnoreCase("Guerreiro")) {
                System.out.println("VANTAGEM: Sua magia derrete a armadura de " + alvo.getNome() + "!");
                danoTotal += 8; // Mago bate muito forte na vantagem
            }
        }
        
        // ARQUEIRO: Forte contra Feras (Dragão/Aranha) e Magos (Necromante)
        else if (this.classe.equalsIgnoreCase("Arqueiro")) {
            if (alvo.getClasse().equalsIgnoreCase("Fera") || alvo.getClasse().equalsIgnoreCase("Mago")) {
                System.out.println("VANTAGEM: Tiro preciso no ponto fraco de " + alvo.getNome() + "!");
                danoTotal += 6;
            }
        }

        // Sistema de Fraqueza (O Monstro batendo no Herói)
        // Se o monstro for o atacante, verificamos se o herói (alvo) é fraco
        
        if (this.eMonstro) {
            // Monstros Guerreiros (Orc) esmagam Magos (que usam roupão)
            if (this.classe.equalsIgnoreCase("Guerreiro") && alvo.getClasse().equalsIgnoreCase("Mago")) {
                System.out.println("FRAQUEZA: O " + alvo.getNome() + " não tem armadura para aguentar a pancada!");
                danoTotal += 5;
            }

            // Monstros Magos derretem Guerreiros
            else if (this.classe.equalsIgnoreCase("Mago") && alvo.getClasse().equalsIgnoreCase("Guerreiro")) {
                System.out.println("FRAQUEZA: A magia do " + this.nome + " ignorou a armadura pesada do Guerreiro!");
                danoTotal += 6;
            }
        }
        
        return danoTotal;
    }

    // Recebe dano considerando a defesa
    public void receberDano(int danoRecebido) {
        int danoReal = danoRecebido - this.defesa;

        if (danoReal < 0) {
            danoReal = 0;
        }

        this.vida -= danoReal;
        if (this.vida < 0) {
            this.vida = 0;
        }

        System.out.println(this.nome + " recebeu " + danoReal + " de dano. Vida restante: " + this.vida);
    }

    // Ganha XP e verifica se sobe de nível
        public void ganharXp(int xpGanho) {
        this.xp += xpGanho;
        int xpNecessario = this.nivel * 100; // Meta para o próximo nível
        
        System.out.println(this.nome + " ganhou " + xpGanho + " XP.");

        // Exibe a barra de progresso de XP
        // O (double) serve para forçar a conta a ser decimal antes de virar inteira
        int porcentagemXP = (int) ((double) this.xp / xpNecessario * 100);
        
        int blocosPreenchidos = porcentagemXP / 5; // Cada bloco vale 5% (100% / 20 blocos)
        int blocosVazios = 20 - blocosPreenchidos;

        System.out.print("\nXP: [");
        // Imprime os quadrados cheios (█)
        for (int i = 0; i < blocosPreenchidos; i++) {
            System.out.print("█");
        }
        // Imprime os traços vazios (-)
        for (int i = 0; i < blocosVazios; i++) {
            System.out.print("-");
        }
        System.out.println("] " + porcentagemXP + "% (" + this.xp + "/" + xpNecessario + ")\n");

        // Lógica de subir de nível (While)
        while (this.xp >= xpNecessario) {
            
            this.xp -= xpNecessario; // Tira o XP gasto
            this.nivel++;
            this.forca += 3;
            this.defesa += 2;
            
            // Atualiza a meta para o novo nível
            xpNecessario = this.nivel * 100; 
            
            this.vidaMaxima += 20; 
            this.vida = this.vidaMaxima; 

            System.out.println("\n---------------- LEVEL UP! ----------------");
            System.out.println("PARABÉNS! Você subiu para o nível " + this.nivel + "!");
            System.out.println("Vida aumentada para: " + this.vidaMaxima);
            System.out.println("Força +3 | Defesa +2");
            System.out.println("------------------------------------------\n");
        }
    }

    // Perde uma porcentagem do XP atual
    public void perderXp(int porcentagem) {
        int perda = (this.xp * porcentagem) / 100;
        this.xp -= perda;
        
        System.out.println("PENALIDADE: Você perdeu " + perda + " XP por ter sido derrotado.");
        System.out.println("XP Atual: " + this.xp + "/" + (this.nivel * 100));
    }
}