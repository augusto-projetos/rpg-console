package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class JogoSalvo {
    
    private static final String NOME_PASTA = "saves";
    private static final String NOME_ARQUIVO = "save.dat";

    // --- MÉTODO SALVAR (CRIPTOGRAFADO) ---
    public static void salvar(Personagem heroi) {
        try {
            // 1. Prepara a pasta
            File pasta = new File(NOME_PASTA);
            if (!pasta.exists()) pasta.mkdirs();

            File arquivo = new File(pasta, NOME_ARQUIVO);

            // 2. Monta os dados numa String única
            StringBuilder dados = new StringBuilder();
            
            // Dados Básicos
            dados.append(heroi.getNome()).append("\n");
            dados.append(heroi.getClasse()).append("\n");
            dados.append(heroi.getNivel()).append("\n");
            dados.append(heroi.getXp()).append("\n");
            dados.append(heroi.getOuro()).append("\n");
            
            // Vida e Mana
            dados.append(heroi.getVida()).append("\n");
            dados.append(heroi.getVidaMaxima()).append("\n");
            dados.append(heroi.getMana()).append("\n");
            dados.append(heroi.getManaMaxima()).append("\n");
            
            // Atributos de Combate
            dados.append(heroi.getForca()).append("\n");
            dados.append(heroi.getDefesa()).append("\n");
            dados.append(heroi.getAgilidade()).append("\n");
            dados.append(heroi.getDestreza()).append("\n");
            
            // Inventário
            dados.append(heroi.getInventario().toSaveString()).append("\n");

            // 3. CRIPTOGRAFIA (Codifica para Base64)
            String dadosCriptografados = Base64.getEncoder().encodeToString(dados.toString().getBytes());

            // 4. Escreve o código doido no arquivo
            FileWriter escritor = new FileWriter(arquivo); // Sobrescreve por padrão
            escritor.write(dadosCriptografados);
            escritor.close();
            
            System.out.println(Cores.GREEN + "Jogo salvo e protegido em '" + arquivo.getAbsolutePath() + "'!" + Cores.RESET);
            
        } catch (IOException e) {
            System.out.println(Cores.RED + "Erro ao salvar: " + e.getMessage() + Cores.RESET);
        }
    }

    // --- MÉTODO CARREGAR (DESCRIPTOGRAFAR) ---
    public static Personagem carregar() {
        File arquivo = new File(NOME_PASTA, NOME_ARQUIVO);

        if (!arquivo.exists()) {
            System.out.println(Cores.YELLOW + "Nenhum save encontrado." + Cores.RESET);
            return null;
        }

        try {
            // 1. Lê o arquivo criptografado inteiro
            BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
            String dadosCriptografados = leitor.readLine();
            leitor.close();

            if (dadosCriptografados == null) return null;

            // 2. DESCRIPTOGRAFA (Decodifica de Base64 para texto normal)
            byte[] bytesDecodificados = Base64.getDecoder().decode(dadosCriptografados);
            String dadosReais = new String(bytesDecodificados);

            // 3. Separa as linhas novamente (o \n que colocamos antes)
            String[] linhas = dadosReais.split("\n");

            // 4. Reconstrói o herói (Lendo do array 'linhas')
            // A ordem tem que ser IGUAL à do salvar
            String nome = linhas[0];
            String classe = linhas[1];
            int nivel = Integer.parseInt(linhas[2]);
            int xp = Integer.parseInt(linhas[3]);
            int ouro = Integer.parseInt(linhas[4]);
            int vida = Integer.parseInt(linhas[5]);
            int vidaMax = Integer.parseInt(linhas[6]);
            int mana = Integer.parseInt(linhas[7]);
            int manaMax = Integer.parseInt(linhas[8]);
            int forca = Integer.parseInt(linhas[9]);
            int defesa = Integer.parseInt(linhas[10]);
            int agilidade = Integer.parseInt(linhas[11]);
            int destreza = Integer.parseInt(linhas[12]);
            
            // Inventário (Linha 13)
            String dadosInventario = (linhas.length > 13) ? linhas[13] : "VAZIO";

            Personagem heroiCarregado = new Personagem(nome, vidaMax, forca, defesa, classe);
            
            heroiCarregado.setNivel(nivel);
            heroiCarregado.setXp(xp);
            heroiCarregado.setOuro(ouro);
            heroiCarregado.setVida(vida);
            heroiCarregado.setVidaMaxima(vidaMax);
            heroiCarregado.setMana(mana);
            heroiCarregado.setManaMaxima(manaMax);
            heroiCarregado.setAgilidade(agilidade);
            heroiCarregado.setDestreza(destreza);
            heroiCarregado.getInventario().carregarDoSave(dadosInventario);
            
            System.out.println(Cores.GREEN + "Jogo carregado com sucesso!" + Cores.RESET);
            return heroiCarregado;

        } catch (Exception e) {
            System.out.println(Cores.RED + "Save corrompido ou modificado! Iniciando novo jogo." + Cores.RESET);
            return null;
        }
    }
}