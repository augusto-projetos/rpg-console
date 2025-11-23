import java.util.Scanner;

public class Batalha {
    private Scanner scanner;
    private Personagem heroi;
    private Personagem monstro;
    private java.util.Random random;

    public Batalha() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {

        this.random = new java.util.Random();
        int xpRecompensa = 0;
        int xpPenalidade = 0;

        System.out.println("=== BEM-VINDO À BATALHA ===");
        if (heroi == null) {
        
            System.out.println("Digite o nome do seu Herói: ");
            String nomeHeroi = scanner.nextLine();

            System.out.println("Escolha sua Classe:" +
                               "\n1. Guerreiro (Vida Alta, Defesa Alta)" +
                               "\n2. Mago (Dano Alto, Vida Baixa)" +
                               "\n3. Arqueiro (Crítico Alto, Equilibrado)");
            int escolhaClasse = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch(escolhaClasse) {
                case 1: // Guerreiro: Tanque
                    heroi = new Personagem(nomeHeroi, 120, 12, 15, "Guerreiro");
                    break;

                case 2: // Mago: Canhão de vidro
                    heroi = new Personagem(nomeHeroi, 70, 25, 5, "Mago");
                    break;

                case 3: // Arqueiro: Crítico
                    heroi = new Personagem(nomeHeroi, 90, 18, 8, "Arqueiro");
                    break;

                default: // Classe genérica
                    System.out.println("Classe inválida, você virou um Camponês!");
                    heroi = new Personagem(nomeHeroi, 80, 10, 5, "Camponês");
            }

        } else {

            System.out.println("O herói " + heroi.getNome() + " (Nível " + heroi.getNivel() + ") da classe " + heroi.getClasse() + " retorna para a arena!\n");
            
            // Recupera Vida e Mana
            heroi.setVida(heroi.getVidaMaxima());
            heroi.setMana(heroi.getManaMaxima());
            
            System.out.println("Vida e Mana recuperadas totalmente!");
        }

        System.out.println("\nEscolha seu Oponente:" +
                           "\n1. Slime Gosmento (Tutorial)" + 
                           "\n2. Esqueleto Arqueiro (Frágil mas Dano Alto)" +
                           "\n3. Goblin Furioso (Equilibrado)" +
                           "\n4. Necromante Sombrio (Dano Explosivo)" +
                           "\n5. Orc Blindado (Muita Defesa)" +
                           "\n6. Aranha Rainha (Muita Vida)" +
                           "\n7. Golem de Pedra (O Tanque)" +
                           "\n8. Dragão Ancião (Chefe Final)" +
                           "\n9. ??? (Secreto)"); // O Easter Egg
        int modo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        switch (modo) {
            case 1: // Tutorial
                monstro = new Personagem("Slime Gosmento", 30, 8, 0, "Fera");
                monstro.setEMonstro(true);
                xpRecompensa = 20;
                break; // Não tem penalidade no tutorial

            case 2: // Glass Cannon (Canhão de Vidro)
                // Pouca vida, mas bate forte. Bom para testar quem tem reflexo.
                monstro = new Personagem("Esqueleto Arqueiro", 50, 18, 2, "Arqueiro");
                monstro.setEMonstro(true);
                xpRecompensa = 40;
                xpPenalidade = 5;
                break;
        
            case 3: // O Padrão
                monstro = new Personagem("Goblin Furioso", 70, 15, 5, "Guerreiro");
                monstro.setEMonstro(true);
                xpRecompensa = 60;
                xpPenalidade = 10; 
                break;

            case 4: // O Mago Inimigo
                monstro = new Personagem("Necromante Sombrio", 80, 25, 3, "Mago");
                monstro.setEMonstro(true);
                xpRecompensa = 90;
                xpPenalidade = 15;
                break;

            case 5: // O Teste de Dano
                // Defesa 12 é alta! Quem bater fraco vai tirar 0 ou 1 de dano.
                monstro = new Personagem("Orc Blindado", 100, 22, 12, "Guerreiro");
                monstro.setEMonstro(true);
                xpRecompensa = 120;
                xpPenalidade = 20; 
                break;

            case 6: // O Teste de Resistência
                // Muita vida, a luta vai ser longa.
                monstro = new Personagem("Aranha Rainha", 150, 20, 8, "Fera");
                monstro.setEMonstro(true);
                xpRecompensa = 180;
                xpPenalidade = 25; 
                break;

            case 7: // O Porteiro do Chefe
                // Golem é quase imune a ataques fracos (Defesa 18).
                monstro = new Personagem("Golem de Pedra", 180, 25, 18, "Guerreiro");
                monstro.setEMonstro(true);
                xpRecompensa = 300;
                xpPenalidade = 40; 
                break;

            case 8: // O Chefe Final
                monstro = new Personagem("Dragão Ancião", 250, 35, 15, "Fera");
                monstro.setEMonstro(true);
                xpRecompensa = 1000; // Vale muito a pena
                xpPenalidade = 100; // Tudo ou nada
                break;

            case 9: // O Easter Egg (Impossível)
                // Vida 999 e Defesa absurda. Só ganha com nível muito alto.
                monstro = new Personagem("Augusto, o Criador", 999, 50, 50, "Dev");
                monstro.setEMonstro(true);
                xpRecompensa = 9999;
                xpPenalidade = 100;
                break;

            default:
                System.out.println("Modo inválido! Escolha novamente.");
                return;
        }

        System.out.println("\nA batalha vai começar entre " + heroi.getNome() + " (Força: " + heroi.getForca() + " | Defesa: " + heroi.getDefesa() + ") vs " + 
                            monstro.getNome() + " (Força: " + monstro.getForca() + " | Defesa: " + monstro.getDefesa() + ")");

        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            System.out.println("\n-------------------------------------------------");
            System.out.println(heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " HP | " +
                               heroi.getMana() + "/" + heroi.getManaMaxima() + " MP");
            System.out.println(monstro.getNome() + ": " + monstro.getVida() + "/" + monstro.getVidaMaxima() + " HP");
            System.out.println("-------------------------------------------------");

            System.out.println("\nEscolha uma ação:" + 
                               "\n1. Atacar" + 
                               "\n2. Habilidade: " + heroi.getHabilidade().getNome() + " (" + heroi.getHabilidade().getCustoMana() + " MP)" + 
                               "\n3. Defender (+10 HP)" +
                               "\n4. Fugir");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            System.out.println("\n=============================");

            switch (escolha) {
                case 1:
                    int danoDoHeroi = heroi.atacar(monstro);
                    System.out.println("Você atacou com força " + danoDoHeroi + "!");
                    monstro.receberDano(danoDoHeroi);

                    break;

                case 2:
                    boolean funcionou = heroi.getHabilidade().usar(heroi, monstro);
                    
                    if (!funcionou) {
                        System.out.println("Você se atrapalhou na conjuração e perdeu a vez!");
                    }
                    break;
            
                case 3:
                    System.out.println("Você assume uma postura defensiva e recupera 10 de vida.");
                    
                    int vidaAtual = heroi.getVida();
                    int cura = 10;
                    int vidaMaxima = heroi.getVidaMaxima();

                    // Se a cura for fazer a vida ultrapassar o máximo...
                    if (vidaAtual + cura > vidaMaxima) {
                        // ... a vida vira exatamente o máximo (enche o tanque e para)
                        heroi.setVida(vidaMaxima);
                    } else {
                        // Se não encher tudo, cura normal
                        heroi.setVida(vidaAtual + cura);
                    }
                    
                    System.out.println("Vida atual: " + heroi.getVida() + "/" + heroi.getVidaMaxima());

                    // Recupera um pouquinho de mana também ao defender
                    int manaAtual = heroi.getMana();
                    if (manaAtual + 5 <= heroi.getManaMaxima()) {
                        heroi.setMana(manaAtual + 5);
                        System.out.println("Recuperou 5 MP descansando.");
                    }

                    break;

                case 4:
                    if (random.nextInt(10) < 3) { // 30% de chance de fuga bem-sucedida
                        System.out.println("Você conseguiu fugir da batalha!");
                        System.out.println("Porém não ganhou XP.\n");
                        return;
                    } else {
                        System.out.println("Você tentou fugir mas tropeçou!");
                    }

                    break;

                default:
                    System.out.println("Opção inválida! Perdeu a vez.");
            }

            if (monstro.getVida() <= 0) {

                System.out.println("O monstro foi derrotado!");
                break;

            } else {

                try {
                    System.out.println("\nO monstro está se preparando...");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int danoDoMonstro = monstro.atacar(heroi);
                System.out.println("\nO " + monstro.getNome() + " te atacou com força " + danoDoMonstro + "!");
                heroi.receberDano(danoDoMonstro);
            }

            System.out.println("=============================\n");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if (heroi.getVida() != 0) {
            System.out.println("=============================\n");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fim da Batalha: Verifica quem ganhou
        if (heroi.getVida() > 0) {

            System.out.println("VITÓRIA! O " + monstro.getNome() + " caiu!");
            heroi.ganharXp(xpRecompensa);

        } else {

            System.out.println("GAME OVER... " + heroi.getNome() + " caiu em combate.");
            heroi.perderXp(xpPenalidade);
        }
    }
}