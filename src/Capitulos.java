package src;
import java.util.Scanner;

public class Capitulos {

    public static void narrar(String mensagem) {
        System.out.print(Cores.CYAN); 
        for (char c : mensagem.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(30); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Cores.RESET); 
    }

    public static Personagem executarCapitulo(int capituloEscolhido, Scanner scanner) {
        
        Personagem monstro = null;

        switch (capituloEscolhido) {
            case 0: // TUTORIAL
                narrar("\n--- PRÓLOGO: O DESPERTAR ---");
                narrar("Você acorda em uma floresta. Um barulho gosmento surge...");
                
                monstro = new Personagem("Slime Gosmento", 30, 8, 0, "Fera");
                monstro.setAgilidade(2); monstro.setDestreza(5);
                monstro.setXpReward(20); monstro.setOuroReward(10);
                break;

            case 1: // ESCOLHA
                narrar("\n--- CAPÍTULO 1: A ENCRUZILHADA ---");
                System.out.println("1. Cemitério (Ágil) | 2. Acampamento (Guerreiro)");
                int esc = 0;
                try { esc = scanner.nextInt(); scanner.nextLine(); } catch(Exception e) { scanner.nextLine(); }
                
                if (esc == 1) {
                    monstro = new Personagem("Esqueleto Arqueiro", 50, 18, 2, "Arqueiro");
                    monstro.setAgilidade(18); monstro.setDestreza(18);
                    monstro.setXpReward(40); monstro.setOuroReward(25);
                } else {
                    monstro = new Personagem("Goblin Furioso", 70, 15, 5, "Guerreiro");
                    monstro.setAgilidade(10); monstro.setDestreza(12);
                    monstro.setXpReward(40); monstro.setOuroReward(25);
                }
                break;

            case 2: // NECROMANTE
                narrar("\n--- CAPÍTULO 2: A TORRE NEGRA ---");
                narrar("Um feiticeiro bloqueia o caminho.");
                monstro = new Personagem("Necromante Sombrio", 80, 25, 3, "Mago");
                monstro.setAgilidade(12); monstro.setDestreza(15);
                monstro.setXpReward(90); monstro.setOuroReward(50);
                break;

            case 3: // ORC
                narrar("\n--- CAPÍTULO 3: O PORTÃO ---");
                monstro = new Personagem("Orc Blindado", 100, 22, 12, "Guerreiro");
                monstro.setAgilidade(4); monstro.setDestreza(12);
                monstro.setXpReward(120); monstro.setOuroReward(60);
                break;

            case 4: // ARANHA
                narrar("\n--- CAPÍTULO 4: O NINHO ---");
                monstro = new Personagem("Aranha Rainha", 150, 20, 8, "Fera");
                monstro.setAgilidade(22); monstro.setDestreza(18);
                monstro.setXpReward(180); monstro.setOuroReward(80);
                break;

            case 5: // GOLEM
                narrar("\n--- CAPÍTULO 5: O CORAÇÃO DE PEDRA ---");
                monstro = new Personagem("Golem de Pedra", 180, 25, 18, "Guerreiro");
                monstro.setAgilidade(0); monstro.setDestreza(8);
                monstro.setXpReward(300); monstro.setOuroReward(150);
                break;

            case 6: // DRAGÃO
                narrar("\n--- CAPÍTULO FINAL: A LENDA ---");
                monstro = new Personagem("Dragão Ancião", 250, 35, 15, "Fera");
                monstro.setAgilidade(15); monstro.setDestreza(25);
                monstro.setXpReward(1000); monstro.setOuroReward(500);
                break;

            default:
                narrar("\n--- FIM DA JORNADA ---");
                return null;
        }

        if (monstro != null) monstro.setEMonstro(true);
        return monstro;
    }
    
    // Método novo para TREINAR (Farmar)
    public static Personagem treinar(int nivelHeroi) {
        System.out.println(Cores.CYAN + "\nVocê decide treinar na floresta próxima..." + Cores.RESET);
        
        // Gera um monstro baseado no nível do herói para não ficar fácil nem impossível
        Personagem monstroTreino;
        
        if (nivelHeroi <= 2) {
            monstroTreino = new Personagem("Slime de Treino", 30, 8, 0, "Fera");
            monstroTreino.setXpReward(10); monstroTreino.setOuroReward(5);
        } else if (nivelHeroi <= 4) {
            monstroTreino = new Personagem("Goblin Saqueador", 60, 12, 2, "Guerreiro");
            monstroTreino.setXpReward(30); monstroTreino.setOuroReward(15);
        } else {
            monstroTreino = new Personagem("Orc Exilado", 90, 18, 5, "Guerreiro");
            monstroTreino.setXpReward(70); monstroTreino.setOuroReward(40);
        }
        
        monstroTreino.setEMonstro(true);
        // Define atributos padrão para treino
        monstroTreino.setAgilidade(5); monstroTreino.setDestreza(10);
        
        return monstroTreino;
    }
}