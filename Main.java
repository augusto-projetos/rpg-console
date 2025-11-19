import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Batalha batalha = new Batalha();
        
        boolean continuar = true;

        do {
            batalha.iniciar();

            System.out.println("Deseja iniciar uma nova batalha? (s/n)");
            String resposta = scanner.nextLine().toLowerCase();

            while (!resposta.equals("s") && !resposta.equals("n")) {
                System.out.println("Opção inválida! Digite 's' para sim ou 'n' para não: ");
                resposta = scanner.nextLine().toLowerCase();
            }

            if (resposta.equals("n")) {
                continuar = false;
                System.out.println("Obrigado por jogar! Até a próxima.");
            }
        } while (continuar);

        scanner.close();
    }
}
