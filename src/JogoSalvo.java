package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.ArrayList;

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
            dados.append(heroi.getCapitulo()).append("\n"); 
            
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

            // Equipamentos
            if (heroi.getArma() != null) dados.append(heroi.getArma().toSaveString()).append("\n");
            else dados.append("VAZIO").append("\n");

            if (heroi.getArmadura() != null) dados.append(heroi.getArmadura().toSaveString()).append("\n");
            else dados.append("VAZIO").append("\n");

            // Status
            dados.append(heroi.getEfeitoStatus()).append("\n");
            dados.append(heroi.getTurnosStatus()).append("\n");
            dados.append(heroi.getDanoStatus()).append("\n");
            
            // Inventário
            dados.append(heroi.getInventario().toSaveString()).append("\n");

            // Monstros Derrotados e Conquistas
            dados.append(serializarLista(heroi.getMonstrosDerrotados())).append("\n");
            dados.append(serializarLista(heroi.getConquistas())).append("\n");

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

            // --- VERIFICAÇÃO DE VERSÃO ---
            // Na v3.0, temos 22 linhas de dados.
            if (linhas.length < 22) {
                System.out.println(Cores.RED + "Save de versão antiga detectado e incompatível com a Campanha v3.0." + Cores.RESET);
                System.out.println(Cores.YELLOW + "Iniciando um novo jogo para garantir a experiência correta." + Cores.RESET);
                // Deleta o arquivo velho para não atrapalhar o novo save
                arquivo.delete();
                return null; // Retorna null força o Main a criar um novo herói
            }

            // 4. Reconstrói o herói (Lendo do array 'linhas')
            // A ordem tem que ser IGUAL à do salvar
            String nome = linhas[0];
            String classe = linhas[1];
            int nivel = Integer.parseInt(linhas[2]);
            int xp = Integer.parseInt(linhas[3]);
            int ouro = Integer.parseInt(linhas[4]);
            int capitulo = Integer.parseInt(linhas[5]); 

            int vida = Integer.parseInt(linhas[6]);
            int vidaMax = Integer.parseInt(linhas[7]);
            int mana = Integer.parseInt(linhas[8]);
            int manaMax = Integer.parseInt(linhas[9]);
            
            int forca = Integer.parseInt(linhas[10]);
            int defesa = Integer.parseInt(linhas[11]);
            int agilidade = Integer.parseInt(linhas[12]);
            int destreza = Integer.parseInt(linhas[13]);
            
            String linhaArma = linhas[14];
            String linhaArmadura = linhas[15];
            String efeitoStatus = linhas[16];
            int turnosStatus = Integer.parseInt(linhas[17]);
            int danoStatus = Integer.parseInt(linhas[18]);
            
            String dadosInventario = (linhas.length > 19) ? linhas[19] : "VAZIO";

            String dadosBestiario = linhas[20];
            String dadosConquistas = linhas[21];

            Personagem heroiCarregado = new Personagem(nome, vidaMax, forca, defesa, classe);
            
            heroiCarregado.setNivel(nivel);
            heroiCarregado.setXp(xp);
            heroiCarregado.setOuro(ouro);
            heroiCarregado.setCapitulo(capitulo);
            heroiCarregado.setVida(vida);
            heroiCarregado.setVidaMaxima(vidaMax);
            heroiCarregado.setMana(mana);
            heroiCarregado.setManaMaxima(manaMax);
            heroiCarregado.setAgilidade(agilidade);
            heroiCarregado.setDestreza(destreza);

            if (!linhaArma.equals("VAZIO")) {
                Equipamento arma = (Equipamento) Item.fromSaveString(linhaArma);
                heroiCarregado.equiparItem(arma);
            }
            if (!linhaArmadura.equals("VAZIO")) {
                Equipamento armadura = (Equipamento) Item.fromSaveString(linhaArmadura);
                heroiCarregado.equiparItem(armadura);
            }

            heroiCarregado.receberStatus(efeitoStatus, turnosStatus, danoStatus);
            heroiCarregado.getInventario().carregarDoSave(dadosInventario);

            heroiCarregado.setMonstrosDerrotados(deserializarLista(dadosBestiario));
            heroiCarregado.setConquistas(deserializarLista(dadosConquistas));
            
            System.out.println(Cores.GREEN + "Jogo carregado com sucesso!" + Cores.RESET);
            return heroiCarregado;

        } catch (Exception e) {
            System.out.println(Cores.RED + "Save corrompido ou modificado! Iniciando novo jogo." + Cores.RESET);
            return null;
        }
    }

    // --- MÉTODOS AUXILIARES PARA LISTAS ---
    
    // Transforma ArrayList em String: "Slime###Orc###Dragão"
    private static String serializarLista(ArrayList<String> lista) {
        if (lista.isEmpty()) return "VAZIO";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i));
            if (i < lista.size() - 1) sb.append("###");
        }
        return sb.toString();
    }

    // Transforma String em ArrayList
    private static ArrayList<String> deserializarLista(String dados) {
        ArrayList<String> lista = new ArrayList<>();
        if (dados.equals("VAZIO") || dados.isEmpty()) return lista;
        
        String[] partes = dados.split("###");
        for (String s : partes) {
            lista.add(s);
        }
        return lista;
    }
}