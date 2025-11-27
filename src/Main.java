package src;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Batalha batalha = new Batalha();
        Personagem heroi = null;
        
        System.out.println(Cores.YELLOW_BOLD + "=== RPG CONSOLE v2.4 ===" + Cores.RESET);
        System.out.println("1. Novo Jogo");
        System.out.println("2. Carregar Jogo");
        System.out.println("3. Sair");
        
        // --- TRATAMENTO DE ERRO NO MENU PRINCIPAL ---
        int opcao = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                entradaValida = true; // Se passou daqui, deu certo
            } catch (InputMismatchException e) {
                System.out.println(Cores.RED + "Erro: Digite apenas números!" + Cores.RESET);
                scanner.nextLine(); // Limpa o "lixo" (letra) que ficou no scanner
            }
        }
        // --------------------------------------------

        if (opcao == 1) {
            // Novo Jogo (Heroi continua null e o Batalha cria)
        } else if (opcao == 2) {
            // Tenta carregar
            heroi = JogoSalvo.carregar();
            if (heroi == null) {
                System.out.println(Cores.CYAN + "Iniciando novo jogo..." + Cores.RESET);
            }
        } else if (opcao == 3) {
            System.out.println("Até mais!");

            // Pequena pausa antes de fechar
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scanner.close();
            return;
        } else {
            System.out.println(Cores.RED + "Opção inválida! Iniciando novo jogo por padrão..." + Cores.RESET);
        }

        // Passamos o herói carregado (ou null) para a batalha
        batalha.setHeroi(heroi); 
        
        // LOOP DO JOGO
        boolean continuar = true;
        do {
            batalha.iniciar();
            
            System.out.println("\nO que deseja fazer?");
            System.out.println("1. Continuar Jogando");
            System.out.println("2. Salvar e Sair");
            System.out.println("3. Sair sem Salvar");
            
            // --- TRATAMENTO DE ERRO NO MENU DE SAÍDA ---
            int escolha = 0;
            boolean escolhaValida = false;

            while (!escolhaValida) {
                try {
                    System.out.print("Sua escolha: ");
                    escolha = scanner.nextInt();
                    scanner.nextLine(); 
                    escolhaValida = true;
                } catch (InputMismatchException e) {
                    System.out.println(Cores.RED + "Erro: Digite apenas números (1, 2 ou 3)!" + Cores.RESET);
                    scanner.nextLine(); 
                }
            }
            // -------------------------------------------

            if (escolha == 2) {
                // SALVAR
                if (batalha.getHeroi() != null) {
                    
                    JogoSalvo.salvar(batalha.getHeroi());
                }
                continuar = false;
            } else if (escolha == 3) {
                continuar = false;
            }
            
        } while (continuar);

        System.out.println(Cores.CYAN + "Obrigado por jogar! Até a próxima." + Cores.RESET);

        // Pequena pausa antes de fechar
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}