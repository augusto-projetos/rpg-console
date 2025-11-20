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
            heroi = new Personagem(nomeHeroi, 100, 15, 10);

        } else {

            System.out.println("O herói " + heroi.getNome() + " (Nível " + heroi.getNivel() + ") retorna para a arena!\n");
            heroi.setVida(100 + (heroi.getNivel() * 10));
        }

        System.out.println("Escolha o modo de jogo:" +
                           "\n1. Fácil (Slime)" + 
                           "\n2. Médio (Padrão)" +
                           "\n3. Difícil (Dark Souls)" +
                           "\n4. Impossível (Lenda)");
        int modo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        switch (modo) {
            case 1:
                monstro = new Personagem("Slime Gosmento", 30, 5, 0); 
                monstro.setEMonstro(true);
                xpRecompensa = 20;
                break;
        
            case 2:
                monstro = new Personagem("Goblin Furioso", 60, 15, 2);
                monstro.setEMonstro(true);
                xpRecompensa = 50;
                xpPenalidade = 10; // Perde 10% do XP ao ser derrotado
                break;

            case 3:
                monstro = new Personagem("Orc da Guerra", 100, 20, 8);
                monstro.setEMonstro(true);
                xpRecompensa = 100;
                xpPenalidade = 30; // Perde 30% do XP ao ser derrotado
                break;

            case 4:
                monstro = new Personagem("Dragão Ancião", 200, 30, 15);
                monstro.setEMonstro(true);
                xpRecompensa = 500;
                xpPenalidade = 100; // Perde 100% do XP ao ser derrotado
                break;

            default:
                System.out.println("Modo inválido! Escolha novamente.");
                return;
        }

        System.out.println("\nA batalha vai começar entre " + heroi.getNome() + " vs " + monstro.getNome());

        System.out.println(heroi.getNome() + ": " + heroi.getVida() + " HP" + " |  Força: " + heroi.getForca() + " | Defesa: " + heroi.getDefesa() + "\n" +
                           monstro.getNome() + ": " + monstro.getVida() + " HP" + " |  Força: " + monstro.getForca() + " | Defesa: " + monstro.getDefesa());

        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            System.out.println("\nEscolha uma ação:" + 
                               "\n1. Atacar" + 
                               "\n2. Defender (+15 HP)" +
                               "\n3. Fugir");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            System.out.println("\n=============================");

            switch (escolha) {
                case 1:
                    int danoDoHeroi = heroi.atacar();
                    System.out.println("Você atacou com força " + danoDoHeroi + "!");
                    monstro.receberDano(danoDoHeroi);

                    break;
            
                case 2:
                    System.out.println("Você assume uma postura defensiva e recupera 15 de vida.");
                    heroi.setVida(heroi.getVida() + 15);

                    break;

                case 3:
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

                int danoDoMonstro = monstro.atacar();
                System.out.println("\nO " + monstro.getNome() + " te atacou com força " + danoDoMonstro + "!");
                heroi.receberDano(danoDoMonstro);
            }

            System.out.println("=============================\n");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(heroi.getNome() + ": " + heroi.getVida() + " HP" + "\n" +
                               monstro.getNome() + ": " + monstro.getVida() + " HP");

        }
        
        System.out.println("=============================\n");
        if (heroi.getVida() > 0) {

            System.out.println("VITÓRIA! O " + monstro.getNome() + " caiu!");

            heroi.ganharXp(xpRecompensa);
            System.out.println("\nVocê ganhou " + xpRecompensa + " de XP pela vitória.\n");

        } else {

            System.out.println("GAME OVER... " + heroi.getNome() + " caiu em combate.");
            heroi.perderXp(xpPenalidade);
            System.out.println("\nVocê perdeu " + xpPenalidade + " de XP pela derrota.\n");
        }
    }
}