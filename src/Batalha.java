package src;
import java.util.Scanner;

public class Batalha {
    private Scanner scanner;
    private Personagem heroi;
    private Personagem monstro;
    private java.util.Random random;

    public Batalha() {
        this.scanner = new Scanner(System.in);
    }

    // Permite que o Main defina quem é o herói (usado no Load Game)
    public void setHeroi(Personagem heroiCarregado) {
        this.heroi = heroiCarregado;
    }

    // Permite que o Main pegue o herói para salvar
    public Personagem getHeroi() {
        return this.heroi;
    }

    public void iniciar() {

        this.random = new java.util.Random();
        int xpRecompensa = 0; int xpPenalidade = 0;
        int ouroRecompensa = 0; int ouroPenalidade = 0;

        System.out.println(Cores.YELLOW_BOLD + "\n=== BEM-VINDO À BATALHA ===" + Cores.RESET);
        if (heroi == null) {
        
            System.out.println("Digite o nome do seu Herói: ");
            String nomeHeroi = scanner.nextLine();

            System.out.println("\nEscolha sua Classe:" +
                               "\n1. " + Cores.GREEN + "Guerreiro" + Cores.RESET + " (Vida Alta, Defesa Alta)" +
                               "\n2. " + Cores.BLUE + "Mago" + Cores.RESET + " (Dano Alto, Vida Baixa)" +
                               "\n3. " + Cores.YELLOW + "Arqueiro" + Cores.RESET + " (Crítico Alto, Equilibrado)");
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

            System.out.println("O herói " + Cores.GREEN + heroi.getNome() + Cores.RESET + " (Nível " + heroi.getNivel() + ") da classe " + heroi.getClasse() + " retorna para a arena!\n");
            
            // Recupera Vida e Mana
            heroi.setVida(heroi.getVidaMaxima());
            heroi.setMana(heroi.getManaMaxima());
            
            System.out.println(Cores.CYAN + "Vida e Mana recuperadas totalmente!" + Cores.RESET);
        }

        // Pergunta se quer ir na loja antes da batalha
        System.out.println(Cores.YELLOW + "\nDeseja visitar o Mercador antes da batalha? (s/n)" + Cores.RESET);
        String irLoja = scanner.nextLine().toLowerCase();

        while (!irLoja.equals("s") && !irLoja.equals("n")) {
            System.out.println("Opção inválida! Digite 's' para sim ou 'n' para não: ");
            irLoja = scanner.nextLine().toLowerCase();
        }

        if (irLoja.equals("s")) {
            loja();
        }

        // Escolhe o monstro
        System.out.println(Cores.RED_BOLD + "\nEscolha seu Oponente:" + Cores.RESET +
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
                ouroRecompensa = 10;
                monstro.setAgilidade(2); monstro.setDestreza(5);
                break; // Não tem penalidade no tutorial

            case 2: // Glass Cannon (Canhão de Vidro)
                // Pouca vida, mas bate forte. Bom para testar quem tem reflexo.
                monstro = new Personagem("Esqueleto Arqueiro", 50, 18, 2, "Arqueiro");
                monstro.setEMonstro(true);
                xpRecompensa = 40; xpPenalidade = 5;
                ouroRecompensa = 25; ouroPenalidade = 5;
                monstro.setAgilidade(18); monstro.setDestreza(18);
                break;
        
            case 3: // O Padrão
                monstro = new Personagem("Goblin Furioso", 70, 15, 5, "Guerreiro");
                monstro.setEMonstro(true);
                xpRecompensa = 60; xpPenalidade = 10; 
                ouroRecompensa = 35; ouroPenalidade = 10;
                monstro.setAgilidade(10); monstro.setDestreza(12);
                break;

            case 4: // O Mago Inimigo
                monstro = new Personagem("Necromante Sombrio", 80, 25, 3, "Mago");
                monstro.setEMonstro(true);
                xpRecompensa = 90; xpPenalidade = 15;
                ouroRecompensa = 50; ouroPenalidade = 15;
                monstro.setAgilidade(12); monstro.setDestreza(15);
                break;

            case 5: // O Teste de Dano
                // Defesa 12 é alta! Quem bater fraco vai tirar 0 ou 1 de dano.
                monstro = new Personagem("Orc Blindado", 100, 22, 12, "Guerreiro");
                monstro.setEMonstro(true);
                xpRecompensa = 120; xpPenalidade = 20;
                ouroRecompensa = 60; ouroPenalidade = 20;
                monstro.setAgilidade(4); monstro.setDestreza(12);
                break;

            case 6: // O Teste de Resistência
                // Muita vida, a luta vai ser longa.
                monstro = new Personagem("Aranha Rainha", 150, 20, 8, "Fera");
                monstro.setEMonstro(true);
                xpRecompensa = 180; xpPenalidade = 25;
                ouroRecompensa = 80; ouroPenalidade = 25;
                monstro.setAgilidade(22); monstro.setDestreza(18);
                break;

            case 7: // O Porteiro do Chefe
                // Golem é quase imune a ataques fracos (Defesa 18).
                monstro = new Personagem("Golem de Pedra", 180, 25, 18, "Guerreiro");
                monstro.setEMonstro(true);
                xpRecompensa = 300; xpPenalidade = 40;
                ouroRecompensa = 150; ouroPenalidade = 30;
                monstro.setAgilidade(0); monstro.setDestreza(8);
                break;

            case 8: // O Chefe Final
                monstro = new Personagem("Dragão Ancião", 250, 35, 15, "Fera");
                monstro.setEMonstro(true);
                xpRecompensa = 1000; xpPenalidade = 100; // Tudo ou nada
                ouroRecompensa = 500; ouroPenalidade = 50;
                monstro.setAgilidade(15); monstro.setDestreza(25);
                break;

            case 9: // O Easter Egg (Impossível)
                // Vida 999 e Defesa absurda. Só ganha com nível muito alto.
                monstro = new Personagem("Augusto, o Criador", 999, 50, 50, "Dev");
                monstro.setEMonstro(true);
                xpRecompensa = 9999; xpPenalidade = 100;
                ouroRecompensa = 999; ouroPenalidade = 100;
                monstro.setAgilidade(50); monstro.setDestreza(50);
                break;

            default:
                System.out.println("Modo inválido! Escolha novamente.");
                return;
        }

        System.out.println(Cores.RED + "\nBatalha Iniciada: " + Cores.GREEN + heroi.getNome() + " (Força: " + heroi.getForca() + " | Defesa: " + heroi.getDefesa() + ")" + Cores.RESET + " vs " + 
                           Cores.RED_BOLD + monstro.getNome() + " (Força: " + monstro.getForca() + " | Defesa: " + monstro.getDefesa() + ")\n" + Cores.RESET);

        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            // Processa Status do Herói
            boolean heroiPodeAgir = heroi.processarStatus();

            System.out.println("-------------------------------------------------");
            System.out.println(Cores.GREEN + heroi.getNome() + ": " + heroi.getVida() + "/" + heroi.getVidaMaxima() + " HP" + Cores.RESET + " | " +
                               Cores.BLUE + heroi.getMana() + "/" + heroi.getManaMaxima() + " MP" + Cores.RESET);
            System.out.println(Cores.RED + monstro.getNome() + ": " + monstro.getVida() + "/" + monstro.getVidaMaxima() + " HP" + Cores.RESET);
            System.out.println("-------------------------------------------------");

            // Se o herói não estiver atordoado, ele joga normal
            if (heroiPodeAgir) {

                System.out.println("\nEscolha uma ação:" + 
                                "\n1. Atacar" + 
                                "\n2. Habilidade: " + Cores.CYAN + heroi.getHabilidade().getNome() + " (" + heroi.getHabilidade().getCustoMana() + " MP)" + Cores.RESET + 
                                "\n3. Defender (+10 HP)" +
                                "\n4. Usar Item" +
                                "\n5. Fugir");
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha
                System.out.println("\n=============================");

                switch (escolha) {
                    case 1: // ATACAR
                        // Pergunta ao monstro: "Você desviou?"
                        if (monstro.tentarEsquivar(heroi)) {
                            System.out.println(Cores.WHITE_BOLD + "MISS! O " + monstro.getNome() + " desviou do seu ataque!" + Cores.RESET);
                        } else {
                            // Se NÃO desviou, o pau quebra normal
                            int danoDoHeroi = heroi.atacar(monstro);
                            System.out.println("Você atacou com força " + danoDoHeroi + "!");
                            monstro.receberDano(danoDoHeroi);
                        }

                        break;

                    case 2: // HABILIDADE
                        boolean funcionou = heroi.getHabilidade().usar(heroi, monstro);
                        
                        if (!funcionou) {
                            System.out.println("Você se atrapalhou na conjuração e perdeu a vez!");
                        }
                        break;
                
                    case 3: // DEFENDER
                        System.out.println("Você assume uma postura defensiva e " + Cores.GREEN + "recupera 10 de vida." + Cores.RESET);
                        
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
                            System.out.println(Cores.BLUE + "Recuperou 5 MP descansando." + Cores.RESET);
                        }

                        break;

                    case 4: // USAR ITEM
                        System.out.println("Escolha o item para usar:");
                        heroi.getInventario().exibir(); // Mostra a lista
                        
                        if (heroi.getInventario().getTotalItens() > 0) {
                            System.out.println("Digite o número do item (ou 0 para voltar):");
                            int indiceItem = scanner.nextInt();
                            scanner.nextLine();
                            
                            if (indiceItem > 0) {
                                heroi.usarPocao(indiceItem);
                            } else {
                                System.out.println("Cancelado.");
                            }
                        }
                        break;

                    case 5: // FUGIR
                        if (random.nextInt(10) < 3) { // 30% de chance de fuga bem-sucedida
                            System.out.println(Cores.GREEN + "Você fugiu com sucesso!" + Cores.RESET);
                            System.out.println("Porém não ganhou XP.\n");
                            return;
                        } else {
                            System.out.println(Cores.RED + "Você tropeçou e não conseguiu fugir!" + Cores.RESET);
                        }

                        break;

                    default:
                        System.out.println("Opção inválida! Perdeu a vez.");
                }
            } else {
                System.out.println(Cores.YELLOW + heroi.getNome() + " está atordoado e não pode agir neste turno!" + Cores.RESET);
            }

            if (monstro.getVida() <= 0) {

                System.out.println(Cores.GREEN_BOLD + "\nO monstro foi derrotado!" + Cores.RESET);
                break;

            } else {

                // Verifica se o monstro pode agir
                boolean monstroPodeAgir = monstro.processarStatus();

                if (monstroPodeAgir) {
                    try {
                        System.out.println("\nO monstro está se preparando...");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 25% de chance de usar habilidade especial (se tiver mana)
                    boolean usouEspecial = false;
                    if (random.nextInt(100) < 25) { 
                        // Tenta usar a skill. Se tiver mana, retorna true.
                        usouEspecial = monstro.getHabilidade().usar(monstro, heroi);
                    }

                    // Se não usou especial (por azar ou falta de mana), ataca normal
                    if (!usouEspecial) {
                        // Verifica esquiva do herói
                        if (heroi.tentarEsquivar(monstro)) {
                            System.out.println(Cores.WHITE_BOLD + "MISS! Você desviou do ataque do " + monstro.getNome() + "!" + Cores.RESET);
                        } else {
                            int danoDoMonstro = monstro.atacar(heroi);
                            System.out.println(Cores.RED + "O " + monstro.getNome() + " te acertou com força " + danoDoMonstro + "!" + Cores.RESET);
                            heroi.receberDano(danoDoMonstro);
                        }
                    }
                } else {
                    System.out.println(Cores.YELLOW + "\nO " + monstro.getNome() + " está atordoado e não atacou!" + Cores.RESET);
                }
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

            System.out.println(Cores.GREEN_BOLD + "VITÓRIA! O " + monstro.getNome() + " caiu!" + Cores.RESET);
            heroi.ganharXp(xpRecompensa);
            heroi.ganharOuro(ouroRecompensa);

        } else {

            System.out.println(Cores.RED_BOLD + "GAME OVER... " + heroi.getNome() + " caiu em combate." + Cores.RESET);
            heroi.perderXp(xpPenalidade);
            heroi.perderOuro(ouroPenalidade);
        }
    }

    // Método exclusivo para gerenciar a compra de itens
    private void loja() {
        System.out.println(Cores.PURPLE + "\nO Mercador acena para você!" + Cores.RESET);
        System.out.println("Mercador: 'Tenho ótimas poções para sua jornada.'");
        
        // Itens à venda
        Item pocaoVida = new Item("Poção de Vida Pequena", "Vida", 50, 20);
        Item pocaoMana = new Item("Elixir de Mana", "Mana", 30, 25);
        Item pocaoVidaGrande = new Item("Poção de Vida Grande", "Vida", 100, 40);

        while (true) {
            System.out.println(Cores.YELLOW_BOLD + "\n================ LOJA ================" + Cores.RESET);
            System.out.println("Seu Ouro: " + Cores.YELLOW + heroi.getOuro() + Cores.RESET);
            
            System.out.println(Cores.CYAN + "--- CONSUMÍVEIS ---" + Cores.RESET);
            System.out.println("1. " + pocaoVida.getNome() + " (Recupera 50 HP) - " + Cores.YELLOW + pocaoVida.getPreco() + "g" + Cores.RESET);
            System.out.println("2. " + pocaoMana.getNome() + " (Recupera 30 MP) - " + Cores.YELLOW + pocaoMana.getPreco() + "g" + Cores.RESET);
            System.out.println("3. " + pocaoVidaGrande.getNome() + " (Recupera 100 HP) - " + Cores.YELLOW + pocaoVidaGrande.getPreco() + "g" + Cores.RESET);
            
            System.out.println(Cores.CYAN + "--- UPGRADES (Permanentes) ---" + Cores.RESET);
            System.out.println("4. Afiar Arma (+2 Força) - " + Cores.YELLOW + "150g" + Cores.RESET);
            System.out.println("5. Reforçar Armadura (+2 Defesa) - " + Cores.YELLOW + "150g" + Cores.RESET);
            System.out.println("6. Sair da Loja");
            System.out.println(Cores.YELLOW_BOLD + "======================================" + Cores.RESET);
            
            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    heroi.comprarItem(pocaoVida);
                    break;

                case 2:
                    heroi.comprarItem(pocaoMana);
                    break;

                case 3:
                    heroi.comprarItem(pocaoVidaGrande);
                    break;
                
                case 4:
                    if (heroi.getOuro() >= 150) {
                        heroi.setOuro(heroi.getOuro() - 150);
                        heroi.setForca(heroi.getForca() + 2);
                        System.out.println(Cores.GREEN + "O ferreiro afiou sua arma! Sua Força subiu para " + heroi.getForca() + "!" + Cores.RESET);
                    } else {
                        System.out.println(Cores.RED + "Ouro insuficiente! Você precisa de 150g." + Cores.RESET);
                    }
                    break;

                case 5:
                    if (heroi.getOuro() >= 150) {
                        heroi.setOuro(heroi.getOuro() - 150);
                        heroi.setDefesa(heroi.getDefesa() + 2);
                        System.out.println(Cores.GREEN + "O ferreiro reforçou sua armadura! Sua Defesa subiu para " + heroi.getDefesa() + "!" + Cores.RESET);
                    } else {
                        System.out.println(Cores.RED + "Ouro insuficiente! Você precisa de 150g." + Cores.RESET);
                    }
                    break;

                case 6:
                    System.out.println("Mercador: 'Volte sempre!'");
                    return;
                default:
                    System.out.println(Cores.RED + "Opção inválida." + Cores.RESET);
            }
        }
    }
}