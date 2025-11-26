package src;
import java.util.Scanner;

public class Batalha {
    private Scanner scanner;
    private Personagem heroi;
    private Personagem monstro;
    private java.util.Random random;

    public Batalha() {
        this.scanner = new Scanner(System.in);
        this.random = new java.util.Random();
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

            heroi.setCapitulo(0); // Começa do zero

        } else {

            System.out.println("O herói " + Cores.GREEN + heroi.getNome() + Cores.RESET + " (Nível " + heroi.getNivel() + ") da classe " + heroi.getClasse() + " retorna para a arena!\n");
            
            // Recupera Vida e Mana
            heroi.setVida(heroi.getVidaMaxima());
            heroi.setMana(heroi.getManaMaxima());
            
            System.out.println(Cores.CYAN + "Vida e Mana recuperadas totalmente!" + Cores.RESET);
        }

        // Loop da história
        boolean jogoRodando = true;

        while (jogoRodando && heroi.getVida() > 0) {

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

            // Evento Aleatório antes da batalha
            eventoAleatorio();

            // --- MENU DE DECISÃO INTELIGENTE ---
            System.out.println("\nO que você deseja fazer?");
            System.out.println("1. Continuar História (Jogar Capítulo " + heroi.getCapitulo() + ")");
            System.out.println("2. Voltar para Farmar (Rejogar Capítulo Anterior)");
            
            int escolhaJornada = 0;
            try { escolhaJornada = scanner.nextInt(); scanner.nextLine(); } catch(Exception e) { scanner.nextLine(); }

            // Variável para saber qual capítulo vamos carregar
            int capituloParaJogar = heroi.getCapitulo(); 
            boolean ehFarm = false; // Para saber se avança a história ou não

            if (escolhaJornada == 2) {
                ehFarm = true;
                System.out.println(Cores.CYAN + "\n=== ESCOLHA O CAPÍTULO PARA FARMAR ===" + Cores.RESET);
                
                // Lista do 0 até o capítulo atual
                for (int i = 0; i <= heroi.getCapitulo(); i++) {
                    System.out.println(i + ". Capítulo " + i);
                }
                
                System.out.print("Digite o número do capítulo: ");
                try { 
                    capituloParaJogar = scanner.nextInt(); 
                    scanner.nextLine();
                } catch(Exception e) { scanner.nextLine(); }
                
                // Segurança: Se o cara tentar pular fase digitando 99
                if (capituloParaJogar > heroi.getCapitulo()) {
                    System.out.println("Você não pode ir para o futuro! Jogando capítulo atual.");
                    capituloParaJogar = heroi.getCapitulo();
                }
            }

            // O Capítulo define quem é o monstro da vez
            this.monstro = Capitulos.executarCapitulo(capituloParaJogar, scanner);

            // Se voltou um monstro, a gente luta
            if (this.monstro != null) {
                boolean venceu = lutar(); // Chama o método de pancadaria
                
                if (venceu) {
                    if (!ehFarm && capituloParaJogar == heroi.getCapitulo()) {
                        // Se ganhou, passa de fase!
                        heroi.setCapitulo(heroi.getCapitulo() + 1);
                        System.out.println(Cores.GREEN_BOLD + "VITÓRIA! O " + monstro.getNome() + " caiu!" + Cores.RESET);
                        heroi.ganharXp(monstro.getXpReward());
                        heroi.ganharOuro(monstro.getOuroReward());
                        // Salva automático
                        JogoSalvo.salvar(heroi); 
                    } else {

                        System.out.println(Cores.YELLOW + "Farm concluído! XP e Ouro garantidos." + Cores.RESET);
                        JogoSalvo.salvar(heroi);
                    }

                } else {
                    // Mostra o Game Over
                    System.out.println(Cores.RED_BOLD + "GAME OVER... " + heroi.getNome() + " caiu em combate." + Cores.RESET);
                    heroi.perderXp(monstro.getXpLose());
                    heroi.perderOuro(monstro.getOuroLose());
                    jogoRodando = false; // Game Over
                }
            } else {
                // Se voltou null, é porque o jogo acabou
                System.out.println(Cores.YELLOW_BOLD + "\nParabéns! Você completou a Jornada (por enquanto)." + Cores.RESET);
                jogoRodando = false;
            }
        }
    }

    private boolean lutar() {

        System.out.println(Cores.RED + "\nBatalha Iniciada: " + Cores.GREEN + heroi.getNome() + " (Força: " + heroi.getForca() + " | Defesa: " + heroi.getDefesa() + ")" + Cores.RESET + " vs " + 
                           Cores.RED_BOLD + monstro.getNome() + " (Força: " + monstro.getForca() + " | Defesa: " + monstro.getDefesa() + ")\n" + Cores.RESET);


        while (heroi.getVida() > 0 && monstro.getVida() > 0) {

            // Processa Status do Herói
            boolean heroiPodeAgir = heroi.processarStatus();

            // Se o veneno te matou, o jogo tem que parar AGORA.
            if (heroi.getVida() <= 0) {
                System.out.println(Cores.RED_BOLD + "\nVocê sucumbiu aos ferimentos!" + Cores.RESET);
                break; // Sai do loop imediatamente (vai pro Game Over)
            }

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
                return true;

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

        // Fim da Batalha
        return heroi.getVida() > 0; // Retorna true se vivo, false se morto
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

    // Eventos que podem acontecer antes de escolher o monstro
    private void eventoAleatorio() {
        int dado = random.nextInt(100) + 1; // Roda 1 a 100

        // --- EVENTO 1: BAU (10% Chance - 1 a 10) ---
        if (dado <= 10) {
            int ouroAchado = random.nextInt(30) + 20; // 20 a 50 de ouro
            System.out.println(Cores.YELLOW_BOLD + "\n[$] SORTE! Enquanto explorava, voce encontrou um Baú!" + Cores.RESET);
            heroi.ganharOuro(ouroAchado);
        } 
        
        // --- EVENTO 2: ARMADILHA (10% Chance - 11 a 20) ---
        else if (dado <= 20) {
            int danoArmadilha = random.nextInt(10) + 5; // 5 a 15 de dano
            System.out.println(Cores.RED_BOLD + "\n[!] CUIDADO! Voce pisou em uma armadilha de urso!" + Cores.RESET);
            heroi.receberDano(danoArmadilha);
            System.out.println("Voce comeca a proxima luta machucado...");
        }
        
        // --- EVENTO 3: VIAJANTE (5% Chance - 21 a 25) ---
        else if (dado <= 25) {
            System.out.println(Cores.CYAN + "\n[?] Um Viajante Misterioso sai das sombras..." + Cores.RESET);
            System.out.println("Viajante: 'Tenho uma *Essência de Força*. Aumenta seu dano permanentemente.'");
            System.out.println("Viajante: 'Na loja custa 150... para voce faco por 80 moedas. Aceita? (s/n)'");
            
            String resposta = scanner.nextLine().toLowerCase();

            while (!resposta.equals("s") && !resposta.equals("n")) {
                System.out.println("Opção inválida! Digite 's' para sim ou 'n' para não: ");
                resposta = scanner.nextLine().toLowerCase();
            }
            
            if (resposta.equals("s")) {
                if (heroi.getOuro() >= 80) {
                    heroi.setOuro(heroi.getOuro() - 80);
                    heroi.setForca(heroi.getForca() + 2); // Update barato
                    System.out.println(Cores.GREEN + "[+] Voce bebeu a essência! (+2 Forca)" + Cores.RESET);
                } else {
                    System.out.println("Viajante: 'Sem dinheiro, sem mercadoria...' (Ele desaparece)");
                }
            } else {
                System.out.println("Viajante: 'Sua perda...' (Ele some na neblina)");
            }
        }

        // --- EVENTO 4: ACHADO SORTUDO (5% Chance - 26 a 30) ---
        else if (dado <= 30) {
            System.out.println(Cores.BLUE + "\n[+] Voce encontrou uma caixa esquecida no canto!" + Cores.RESET);
            
            // Sorteia se acha Vida ou Mana (50/50)
            if (random.nextBoolean()) {
                Item itemAchado = new Item("Pocao de Vida Pequena", "Vida", 50, 20);
                System.out.println("Ao abrir, voce encontrou uma " + Cores.GREEN + itemAchado.getNome() + Cores.RESET + "!");
                heroi.getInventario().adicionar(itemAchado);
            } else {
                Item itemAchado = new Item("Elixir de Mana", "Mana", 30, 25);
                System.out.println("Ao abrir, voce encontrou um " + Cores.BLUE + itemAchado.getNome() + Cores.RESET + "!");
                heroi.getInventario().adicionar(itemAchado);
            }
        }

        // --- EVENTO 5: LADRAO (5% Chance - 31 a 35) ---
        else if (dado <= 35) {
            if (heroi.getOuro() > 10) {
                int roubo = random.nextInt(20) + 5;
                if (roubo > heroi.getOuro()) roubo = heroi.getOuro(); // Não rouba mais do que tem
                
                System.out.println(Cores.RED + "\n[!] Um ladrao esbarrou em voce e fugiu!" + Cores.RESET);
                heroi.setOuro(heroi.getOuro() - roubo);
                System.out.println("Voce percebe que sumiram " + roubo + " moedas da sua bolsa.");
            }
        }

        // --- EVENTO 6: PERGAMINHO (5% Chance - 36 a 40) ---
        else if (dado <= 40) {
            int xpAchado = random.nextInt(30) + 10; // 10 a 40 XP
            System.out.println(Cores.CYAN + "\n[i] Voce encontrou um pergaminho antigo no chao." + Cores.RESET);
            System.out.println("Ao ler, voce aprende novas taticas de combate.");
            heroi.ganharXp(xpAchado);
        }

        // Se cair 41 a 100, nada acontece.
    }
}