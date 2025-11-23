package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JogoSalvo {
    
    private static final String CAMINHO_ARQUIVO = "saves/save.txt";

    // --- SALVAR O PROGRESSO ---
    public static void salvar(Personagem heroi) {
        try {
            FileWriter escritor = new FileWriter(CAMINHO_ARQUIVO);
            
            // 1. Dados Básicos
            escritor.write(heroi.getNome() + "\n");
            escritor.write(heroi.getClasse() + "\n");
            escritor.write(heroi.getNivel() + "\n");
            escritor.write(heroi.getXp() + "\n");
            escritor.write(heroi.getOuro() + "\n");
            
            // 2. Status Vitais
            escritor.write(heroi.getVida() + "\n");
            escritor.write(heroi.getVidaMaxima() + "\n");
            escritor.write(heroi.getMana() + "\n");
            escritor.write(heroi.getManaMaxima() + "\n");
            
            // 3. Atributos de Combate
            escritor.write(heroi.getForca() + "\n");
            escritor.write(heroi.getDefesa() + "\n");
            
            escritor.close();
            System.out.println("Jogo salvo com sucesso em 'save.txt'!");
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    // --- CARREGAR O PROGRESSO ---
    public static Personagem carregar() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        
        if (!arquivo.exists()) {
            System.out.println("Nenhum jogo salvo encontrado.");
            return null;
        }

        try {
            Scanner leitor = new Scanner(arquivo);
            
            // Lê na EXATA ordem que escreveu
            String nome = leitor.nextLine();
            String classe = leitor.nextLine();
            int nivel = Integer.parseInt(leitor.nextLine());
            int xp = Integer.parseInt(leitor.nextLine());
            int ouro = Integer.parseInt(leitor.nextLine());
            
            int vida = Integer.parseInt(leitor.nextLine());
            int vidaMax = Integer.parseInt(leitor.nextLine());
            int mana = Integer.parseInt(leitor.nextLine());
            int manaMax = Integer.parseInt(leitor.nextLine());
            
            int forca = Integer.parseInt(leitor.nextLine());
            int defesa = Integer.parseInt(leitor.nextLine());
            
            leitor.close();

            // Recria o herói
            // O construtor vai configurar a habilidade certa baseado na 'classe'
            Personagem heroiCarregado = new Personagem(nome, vidaMax, forca, defesa, classe);
            
            // Sobrescreve os valores padrão com os salvos
            heroiCarregado.setNivel(nivel);
            heroiCarregado.setXp(xp);
            heroiCarregado.setOuro(ouro);
            heroiCarregado.setVida(vida);
            heroiCarregado.setVidaMaxima(vidaMax); // Importante setar o máximo evoluído
            heroiCarregado.setMana(mana);
            heroiCarregado.setManaMaxima(manaMax);
            
            System.out.println("Jogo carregado com sucesso! Bem-vindo de volta, " + nome + ".");
            return heroiCarregado;

        } catch (Exception e) {
            System.out.println("Erro ao ler o save (Arquivo corrompido ou versão antiga): " + e.getMessage());
            return null;
        }
    }
}