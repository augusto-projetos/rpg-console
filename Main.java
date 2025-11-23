import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Batalha batalha = new Batalha();
        Personagem heroi = null;
        
        System.out.println("=== RPG CONSOLE v2.0 ===");
        System.out.println("1. Novo Jogo");
        System.out.println("2. Carregar Jogo");
        System.out.println("3. Sair");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (opcao == 2) {
            // Tenta carregar
            heroi = JogoSalvo.carregar();
            if (heroi == null) {
                System.out.println("Iniciando novo jogo...");
                // Se falhar o load, heroi continua null e o Batalha.iniciar() vai criar um novo
            }
        } else if (opcao == 3) {
            System.out.println("Até mais!");
            scanner.close();
            return;
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
            
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 2) {
                // AQUI SALVAMOS!
                JogoSalvo.salvar(batalha.getHeroi());
                continuar = false;
            } else if (escolha == 3) {
                continuar = false;
            }
            
        } while (continuar);
        
        scanner.close();
    }
}