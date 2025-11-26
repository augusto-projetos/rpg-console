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
                System.out.println(Cores.YELLOW_BOLD + "\n--- PRÓLOGO: O DESPERTAR ---" + Cores.RESET);
                narrar("Você acorda com a cabeça latejando em uma floresta desconhecida.");
                narrar(Cores.RED + "Antes que possa se levantar, algo gosmento agarra sua perna!" + Cores.RESET);
                
                monstro = new Personagem("Slime Gosmento", 30, 8, 0, "Fera");
                monstro.setEMonstro(true);
                monstro.setAgilidade(2); monstro.setDestreza(5);
                monstro.setXpReward(20); monstro.setOuroReward(10);
                break;

            case 1: // A ESCOLHA (Único com bifurcação)
                System.out.println(Cores.YELLOW_BOLD + "\n--- CAPÍTULO 1: A ENCRUZILHADA ---" + Cores.RESET);
                narrar("Vencendo a gosma, você encontra uma placa velha apontando dois caminhos.");
                
                System.out.println("\nEscolha seu destino:");
                System.out.println("1. " + Cores.PURPLE + "Cemitério Profano" + Cores.RESET + " (Inimigo Ágil)");
                System.out.println("2. " + Cores.RED + "Acampamento de Saqueadores" + Cores.RESET + " (Inimigo Guerreiro)");
                
                int esc = 0;
                try { esc = scanner.nextInt(); scanner.nextLine(); } catch(Exception e) { scanner.nextLine(); }
                while (esc != 1 && esc != 2) {
                    System.out.print(Cores.RED + "Opção inválida! Escolha 1 ou 2: " + Cores.RESET);
                    try { esc = scanner.nextInt(); scanner.nextLine(); } catch(Exception e) { scanner.nextLine(); }
                };
                
                if (esc == 1) { // ESQUELETO
                    narrar(Cores.PURPLE + "O ar esfria... Mãos esqueléticas surgem da terra!" + Cores.RESET);
                    
                    monstro = new Personagem("Esqueleto Arqueiro", 50, 18, 2, "Arqueiro");
                    monstro.setEMonstro(true);
                    monstro.setAgilidade(18); monstro.setDestreza(18);
                    monstro.setXpReward(40); monstro.setOuroReward(25);
                    monstro.setXpLose(5); monstro.setOuroLose(5);

                } else { // GOBLIN
                    narrar(Cores.RED + "Você ouve risadas cruéis e vê fogueiras ao longe..." + Cores.RESET);
                    
                    monstro = new Personagem("Goblin Furioso", 70, 15, 5, "Guerreiro"); 
                    monstro.setEMonstro(true);
                    monstro.setAgilidade(10); monstro.setDestreza(12);
                    monstro.setXpReward(60); monstro.setOuroReward(35);
                    monstro.setXpLose(10); monstro.setOuroLose(10);
                }
                break;

            case 2: // NECROMANTE
                System.out.println(Cores.YELLOW_BOLD + "\n--- CAPÍTULO 2: A TORRE DE OBSIDIANA ---" + Cores.RESET);
                narrar("O caminho leva a uma torre cercada por chamas verdes.");
                narrar("Um feiticeiro flutua na entrada, segurando um livro proibido.");
                narrar(Cores.PURPLE + "Necromante: 'Sua alma será um excelente combustível!'" + Cores.RESET);
                
                monstro = new Personagem("Necromante Sombrio", 80, 25, 3, "Mago");
                monstro.setEMonstro(true);
                monstro.setAgilidade(12); monstro.setDestreza(15);
                monstro.setXpReward(90); monstro.setOuroReward(50);
                monstro.setXpLose(15); monstro.setOuroLose(15);
                break;

            case 3: // ORC
                System.out.println(Cores.YELLOW_BOLD + "\n--- CAPÍTULO 3: A PONTE DO GUARDIÃO ---" + Cores.RESET);
                narrar("Para prosseguir, você deve cruzar uma ponte estreita de pedra.");
                narrar("Um guerreiro colossal, coberto de aço, bloqueia a passagem.");
                narrar(Cores.RED_BOLD + "Orc: 'Ninguém passa aqui sem pagar o pedágio de sangue!'" + Cores.RESET);
                
                monstro = new Personagem("Orc Blindado", 100, 22, 12, "Guerreiro"); 
                monstro.setEMonstro(true);
                monstro.setAgilidade(8); monstro.setDestreza(12);
                monstro.setXpReward(120); monstro.setOuroReward(60);
                monstro.setXpLose(20); monstro.setOuroLose(20);
                break;

            case 4: // ARANHA
                System.out.println(Cores.YELLOW_BOLD + "\n--- CAPÍTULO 4: O NINHO DA ESCURIDÃO ---" + Cores.RESET);
                narrar("Você entra em uma caverna úmida. Teias grossas cobrem as paredes.");
                narrar(Cores.RED + "Você sente que está sendo observado por muitos olhos..." + Cores.RESET);
                narrar("A Rainha desce do teto, faminta por carne fresca.");
                
                monstro = new Personagem("Aranha Rainha", 150, 20, 8, "Fera");
                monstro.setEMonstro(true);
                monstro.setAgilidade(22); monstro.setDestreza(18);
                monstro.setXpReward(180); monstro.setOuroReward(80);
                monstro.setXpLose(25); monstro.setOuroLose(25);
                break;

            case 5: // GOLEM
                System.out.println(Cores.YELLOW_BOLD + "\n--- CAPÍTULO 5: AS RUÍNAS ANTIGAS ---" + Cores.RESET);
                narrar("Você chega às ruínas de uma civilização esquecida.");
                narrar("O chão treme violentamente a cada passo que você dá.");
                System.out.println(Cores.YELLOW + "Uma pilha de rochas antigas se levanta. O Guardião acordou!" + Cores.RESET);
                
                monstro = new Personagem("Golem de Pedra", 180, 25, 18, "Guerreiro");
                monstro.setEMonstro(true);
                monstro.setAgilidade(0); monstro.setDestreza(8);
                monstro.setXpReward(300); monstro.setOuroReward(150);
                monstro.setXpLose(40); monstro.setOuroLose(30);
                break;

            case 6: // DRAGÃO
                System.out.println(Cores.RED_BOLD + "\n--- CAPÍTULO FINAL: O PICO DA MONTANHA ---" + Cores.RESET);
                narrar("Você escalou até o ponto mais alto do mundo.");
                narrar(Cores.RED + "O céu fica vermelho sangue. O calor é insuportável." + Cores.RESET);
                narrar("Com um rugido que abala os céus, a Lenda pousa na sua frente.");
                System.out.println(Cores.RED_BOLD + "DRAGÃO ANCIÃO: 'VOCÊ OUSA DESAFIAR O REI?'" + Cores.RESET);
                
                monstro = new Personagem("Dragão Ancião", 250, 35, 15, "Fera");
                monstro.setEMonstro(true);
                monstro.setAgilidade(15); monstro.setDestreza(25);
                monstro.setXpReward(1000); monstro.setOuroReward(500);
                monstro.setXpLose(100); monstro.setOuroLose(50);
                break;

            case 7: // SECRETO
                System.out.println(Cores.PURPLE + "\n--- ??? : A ANOMALIA ---" + Cores.RESET);
                narrar("Você venceu o Dragão, mas o jogo não acabou...");
                narrar(Cores.GREEN + "O mundo ao seu redor começa a falhar e bugar." + Cores.RESET);
                System.out.println(Cores.CYAN + "Criador: 'Você achou que podia zerar o meu sistema?'" + Cores.RESET);
                
                monstro = new Personagem("Augusto, o Criador", 999, 50, 50, "Dev");
                monstro.setEMonstro(true);
                monstro.setAgilidade(50); monstro.setDestreza(50);
                monstro.setXpReward(9999); monstro.setOuroReward(999);
                monstro.setXpLose(100); monstro.setOuroLose(100);
                break;

        default:
                System.out.println(Cores.YELLOW + "\n--- FIM DA JORNADA DISPONÍVEL ---" + Cores.RESET);
                return null;
        }

        return monstro;
    }
}