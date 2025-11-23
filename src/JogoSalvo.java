package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JogoSalvo {
    
    // Caminho relativo: pasta "saves" na mesma raiz do executável
    private static final String NOME_PASTA = "saves";
    private static final String NOME_ARQUIVO = "save.txt";

    // --- MÉTODO SALVAR ---
    public static void salvar(Personagem heroi) {
        try {
            // 1. Identifica a pasta
            File pasta = new File(NOME_PASTA);
            
            // 2. Se a pasta não existe, CRIA (mkdirs cria até subpastas se precisar)
            if (!pasta.exists()) {
                boolean criou = pasta.mkdirs();
                if (criou) {
                    System.out.println("Pasta 'saves' criada com sucesso.");
                } else {
                    System.out.println("Erro: Não foi possível criar a pasta 'saves'. Verifique as permissões.");
                }
            }

            // 3. Cria o arquivo dentro da pasta
            File arquivo = new File(pasta, NOME_ARQUIVO);
            
            // DEBUG: Mostra onde o computador está tentando salvar
            System.out.println("Tentando salvar em: " + arquivo.getAbsolutePath());

            FileWriter escritor = new FileWriter(arquivo);
            
            // --- DADOS DO HEROI ---
            escritor.write(heroi.getNome() + "\n");
            escritor.write(heroi.getClasse() + "\n");
            escritor.write(heroi.getNivel() + "\n");
            escritor.write(heroi.getXp() + "\n");
            escritor.write(heroi.getOuro() + "\n");
            
            // Status
            escritor.write(heroi.getVida() + "\n");
            escritor.write(heroi.getVidaMaxima() + "\n");
            escritor.write(heroi.getMana() + "\n");
            escritor.write(heroi.getManaMaxima() + "\n");
            
            // Atributos
            escritor.write(heroi.getForca() + "\n");
            escritor.write(heroi.getDefesa() + "\n");
            
            escritor.close();
            System.out.println("Jogo salvo com sucesso!");
            
        } catch (IOException e) {
            System.out.println("ERRO CRÍTICO AO SALVAR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --- MÉTODO CARREGAR ---
    public static Personagem carregar() {
        // Procura na pasta saves/save.txt
        File arquivo = new File(NOME_PASTA, NOME_ARQUIVO);
        
        System.out.println("Tentando carregar de: " + arquivo.getAbsolutePath());

        if (!arquivo.exists()) {
            System.out.println("Nenhum save encontrado.");
            return null;
        }

        try {
            Scanner leitor = new Scanner(arquivo);
            
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

            Personagem heroiCarregado = new Personagem(nome, vidaMax, forca, defesa, classe);
            
            heroiCarregado.setNivel(nivel);
            heroiCarregado.setXp(xp);
            heroiCarregado.setOuro(ouro);
            heroiCarregado.setVida(vida);
            heroiCarregado.setVidaMaxima(vidaMax);
            heroiCarregado.setMana(mana);
            heroiCarregado.setManaMaxima(manaMax);
            
            System.out.println("Jogo carregado com sucesso!");
            return heroiCarregado;

        } catch (Exception e) {
            System.out.println("Erro ao ler o save: " + e.getMessage());
            return null;
        }
    }
}