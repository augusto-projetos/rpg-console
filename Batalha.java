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
        monstro = new Personagem("Goblin", 60, 15, 2);
        this.random = new java.util.Random();

        System.out.println("=== BEM-VINDO À BATALHA ===");
        System.out.println("Digite o nome do seu Herói: ");
        String nomeHeroi = scanner.nextLine();
        heroi = new Personagem(nomeHeroi, 100, 15, 8);

        System.out.println("\nA batalha vai começar entre " + heroi.getNome() + " vs " + monstro.getNome());

        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            System.out.println(heroi.getNome() + ": " + heroi.getVida() + " HP | " + heroi.getForca() + " Força | " + heroi.getDefesa() + " Defesa\n" +
                               monstro.getNome() + ": " + monstro.getVida() + " HP | " + monstro.getForca() + " Força | " + monstro.getDefesa() + " Defesa");

            System.out.println("\nEscolha uma ação:" + 
                               "\n1. Atacar" + 
                               "\n2. Defender (+5 HP)" +
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
                    System.out.println("Você assume uma postura defensiva e recupera 5 de vida.");
                    heroi.setVida(heroi.getVida() + 5);

                    break;

                case 3:
                    if (random.nextInt(10) < 3) {
                        System.out.println("Você conseguiu fugir da batalha!");
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

                int danoDoMonstro = monstro.atacar();
                System.out.println("\nO " + monstro.getNome() + " te atacou com força " + danoDoMonstro + "!");
                heroi.receberDano(danoDoMonstro);
            }

            System.out.println("=============================\n");
        }
        
        System.out.println("=============================\n");
        if (heroi.getVida() > 0) {

            System.out.println("VITÓRIA! O " + monstro.getNome() + " caiu!");

        } else {

            System.out.println("GAME OVER... " + heroi.getNome() + " caiu em combate.");
        }
    }
}