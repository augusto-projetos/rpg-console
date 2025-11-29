package src;

public class Cores {
    // O comando para RESETAR a cor (voltar ao branco padrão)
    public static final String RESET = "\u001B[0m";

    // Cores de Texto
    public static final String RED = "\u001B[31m";      // Monstros, Dano
    public static final String GREEN = "\u001B[32m";    // Heroi, Vida, Cura
    public static final String YELLOW = "\u001B[33m";   // Ouro, Avisos
    public static final String BLUE = "\u001B[34m";     // Mana, Magia
    public static final String PURPLE = "\u001B[35m";   // Itens Épicos, Magia Negra
    public static final String CYAN = "\u001B[36m";     // XP, Nível, Informações
    public static final String WHITE = "\u001B[37m";    // Texto padrão

    // Negrito (Deixa a cor mais brilhante/forte)
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String PURPLE_BOLD = "\033[1;35m";
}