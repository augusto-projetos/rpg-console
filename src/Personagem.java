package src;
public class Personagem {
    private java.util.Random random;
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int forca;
    private int defesa;
    private int nivel;
    private int xp;
    private boolean eMonstro;
    private String classe;
    private int mana;
    private int manaMaxima;
    private Habilidade habilidade;
    private int ouro;
    private Inventario inventario;
    private int destreza;  // Influencia a chance de acertar
    private int agilidade; // Influencia a chance de esquivar

    // Construtor
    public Personagem(String nome, int vida, int forca, int defesa, String classe) {
        this.random = new java.util.Random();
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.nivel = 1;
        this.xp = 0;
        this.eMonstro = false;
        this.classe = classe;
        
        // Configura Mana e Skill baseado na classe
        switch (classe) {
            case "Mago":
                this.manaMaxima = 50;
                this.habilidade = new Habilidade("Bola de Fogo", 20, 30, "Dano");
                this.destreza = 10; // Mago não tem mira física muito boa
                this.agilidade = 12; // Mas é ligeiro (tecido leve)
                break;

            case "Guerreiro":
                this.manaMaxima = 20;
                this.habilidade = new Habilidade("Golpe Pesado", 10, 15, "Dano");
                this.destreza = 12; // Razoável
                this.agilidade = 5; // Lento (Armadura pesada)
                break;

            case "Arqueiro":
                this.manaMaxima = 30;
                this.habilidade = new Habilidade("Flecha Explosiva", 15, 20, "Dano");
                this.destreza = 20; // Excelente mira
                this.agilidade = 18; // Muito rápido
                break;

            default: // Camponês
                this.manaMaxima = 10;
                this.habilidade = new Habilidade("Ataque Básico", 0, 5, "Dano");
                this.destreza = 12;
                this.agilidade = 8;
        }

        this.mana = this.manaMaxima; // Começa cheio
        this.ouro = 0; // Começa sem ouro
        this.inventario = new Inventario(); // Cria a mochila vazia
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

    public int getMana() {
        return mana; 
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }
    public void setManaMaxima(int manaMaxima) {
        this.manaMaxima = manaMaxima;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }
    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    public int getOuro() {
        return ouro;
    }
    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public int getDestreza() {
        return destreza;
    }
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getAgilidade() {
        return agilidade;
    }
    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    // Métodos de combate
    public int atacar(Personagem alvo) {
        int dado = random.nextInt(10);
        int danoTotal = this.forca + dado;

        // --- 1. CRÍTICO E FÚRIA ---
        if (this.eMonstro && (this.vida * 100) / this.vidaMaxima <= 40) {
            if (dado >= 6) {
                System.out.println("\n>>> O MONSTRO FICOU FURIOSO! CRÍTICO! <<<");
                danoTotal = danoTotal * 2;
            }
        } else if (this.eMonstro) {
            if (dado == 9) {
                System.out.println("\n>>> O MONSTRO ACERTOU UM CRÍTICO! <<<");
                danoTotal = danoTotal * 2;
            }
        } else {
            // Herói
            if (dado == 9) {
                System.out.println(">>> CRÍTICO! <<<");
                danoTotal = danoTotal * 2;
            }
        }

        // --- 2. VANTAGEM DO HERÓI ---
        if (!this.eMonstro) {
            
            // GUERREIRO bate em Arqueiro/Fera
            if (this.classe.equalsIgnoreCase("Guerreiro")) {
                if (alvo.getClasse().equalsIgnoreCase("Arqueiro") || alvo.getClasse().equalsIgnoreCase("Fera")) {
                    System.out.println("VANTAGEM: Sua lâmina corta fundo em " + alvo.getNome() + "!");
                    danoTotal += 5;
                }
            }

            // MAGO bate em Guerreiro
            else if (this.classe.equalsIgnoreCase("Mago")) {
                if (alvo.getClasse().equalsIgnoreCase("Guerreiro")) {
                    System.out.println("VANTAGEM: Sua magia derrete a armadura de " + alvo.getNome() + "!");
                    danoTotal += 8;
                }
            }

            // ARQUEIRO bate em Fera/Mago
            else if (this.classe.equalsIgnoreCase("Arqueiro")) {
                if (alvo.getClasse().equalsIgnoreCase("Fera") || alvo.getClasse().equalsIgnoreCase("Mago")) {
                    System.out.println("VANTAGEM: Tiro preciso no ponto fraco de " + alvo.getNome() + "!");
                    danoTotal += 6;
                }
            }
        }

        // --- 3. FRAQUEZA DO HERÓI ---
        else { // O "else" aqui garante que se entrou no de cima, não entra aqui
            
            // Monstro Guerreiro bate em Mago Herói
            if (this.classe.equalsIgnoreCase("Guerreiro") && alvo.getClasse().equalsIgnoreCase("Mago")) {
                System.out.println("FRAQUEZA: O " + alvo.getNome() + " não tem armadura para aguentar a pancada!");
                danoTotal += 5;
            }

            // Monstro Mago bate em Guerreiro Herói
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

        System.out.println(Cores.RED + this.nome + " recebeu " + danoReal + " de dano." + Cores.RESET + 
                           " Vida restante: " + Cores.GREEN + this.vida + Cores.RESET);
    }

    // Exibe a barra de XP
    private void exibirBarraXp() {
        int xpNecessario = this.nivel * 100;
        
        // Calcula a porcentagem real (pode passar de 100%)
        int porcentagemXP = (int) ((double) this.xp / xpNecessario * 100);
        
        // Trava em 100% o desenho da barra
        int porcentagemParaDesenho = porcentagemXP;
        if (porcentagemParaDesenho > 100) {
            porcentagemParaDesenho = 100;
        }

        // Calculamos os blocos usando a porcentagem travada
        int blocosPreenchidos = porcentagemParaDesenho / 5; 
        int blocosVazios = 20 - blocosPreenchidos;

        System.out.print(Cores.CYAN + "XP: [");
        for (int i = 0; i < blocosPreenchidos; i++) System.out.print("█");
        for (int i = 0; i < blocosVazios; i++) System.out.print("-");
        
        // No texto final, mantemos o porcentagemXP real para o jogador entender que sobrou XP
        System.out.println("] " + porcentagemXP + "% (" + this.xp + "/" + xpNecessario + ")\n" + Cores.RESET);
    }

    // Ganha XP e verifica se sobe de nível
    public void ganharXp(int xpGanho) {
        this.xp += xpGanho;
        int xpNecessario = this.nivel * 100; // Meta para o próximo nível
        
        System.out.println(Cores.GREEN + this.nome + " ganhou " + xpGanho + " XP." + Cores.RESET);

        exibirBarraXp();

        // Lógica de subir de nível (While)
        while (this.xp >= xpNecessario) {
            
            this.xp -= xpNecessario; // Tira o XP gasto
            this.nivel++;
            this.forca += 3;
            this.defesa += 2;
            this.destreza += 1; 
            this.agilidade += 1;
            
            // Atualiza a meta para o novo nível
            xpNecessario = this.nivel * 100; 
            
            this.vidaMaxima += 20; 
            this.vida = this.vidaMaxima; 

            System.out.println(Cores.YELLOW_BOLD + "\n---------------- LEVEL UP! ----------------");
            System.out.println("PARABÉNS! Você subiu para o nível " + this.nivel + "!");
            System.out.println("Vida aumentada para: " + this.vidaMaxima);
            System.out.println("Força +3 | Defesa +2 | Des/Agi +1");
            System.out.println("------------------------------------------\n" + Cores.RESET);
        }
    }

    // Perde uma porcentagem do XP atual
    public void perderXp(int porcentagem) {
        int perda = (this.xp * porcentagem) / 100;
        this.xp -= perda;
        
        // Garante que o XP não fique negativo
        if (this.xp < 0) this.xp = 0; 
        
        System.out.println(Cores.RED + "PENALIDADE: Você perdeu " + perda + " XP por ter sido derrotado." + Cores.RESET);

        exibirBarraXp();
    }

    // Ganha Ouro
    public void ganharOuro(int quantidade) {
        this.ouro += quantidade;
        System.out.println(Cores.YELLOW + this.nome + " encontrou " + quantidade + " moedas de ouro!" + Cores.RESET);
    }

    // Perde uma porcentagem de Ouro (cai do bolso na derrota)
    public void perderOuro(int porcentagem) {
        int perda = (this.ouro * porcentagem) / 100;
        this.ouro -= perda;
        
        if (this.ouro < 0) this.ouro = 0;
        
        System.out.println(Cores.RED + "BOLSO FURADO: Você deixou cair " + perda + " moedas de ouro na fuga." + Cores.RESET);
        System.out.println(Cores.YELLOW + "Ouro Restante: " + this.ouro + Cores.RESET);
    }

    // Compra um item se tiver ouro suficiente
    public void comprarItem(Item item) {
        if (this.ouro >= item.getPreco()) {
            this.ouro -= item.getPreco();
            this.inventario.adicionar(item); // Guarda na mochila
            System.out.println(Cores.GREEN + "Compra realizada! Saldo atual: " + this.ouro + Cores.RESET);
        } else {
            System.out.println(Cores.RED + "Ouro insuficiente! (" + this.ouro + "/" + item.getPreco() + ")" + Cores.RESET);
        }
    }

    // Usa uma poção do inventário
    public void usarPocao(int indice) {
        this.inventario.usarItem(indice, this);
    }
    
    // Expoe o inventário para o menu saber o que tem dentro
    public Inventario getInventario() {
        return this.inventario;
    }

    // Retorna TRUE se esquivou, FALSE se foi atingido
    public boolean tentarEsquivar(Personagem atacante) {
        // Fórmula: 75% base + (Destreza do Atacante - Minha Agilidade)
        int chanceAcerto = 75 + atacante.getDestreza() - this.agilidade;
        
        // Trava a chance entre 20% e 100% (para não ficar impossível acertar ou errar)
        if (chanceAcerto > 100) chanceAcerto = 100;
        if (chanceAcerto < 20) chanceAcerto = 20;

        int dado = random.nextInt(100) + 1; // Roda um dado de 1 a 100

        System.out.println(Cores.CYAN + "(Chance de Acerto: " + chanceAcerto + "%)" + Cores.RESET);

        // Se o dado for MAIOR que a chance, o ataque ERROU (Esquiva)
        if (dado > chanceAcerto) {
            return true; // Esquivou!
        }
        return false; // Foi atingido
    }
}