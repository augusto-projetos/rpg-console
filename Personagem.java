public class Personagem {
    private String nome;
    private int vida;
    private int forca;
    private int defesa;
    private int nivel = 1;
    private int xp = 0;
    private java.util.Random random;

    // Construtor
    public Personagem(String nome, int vida, int forca, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.random = new java.util.Random();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getForca() {
        return forca;
    }
    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }

    // Métodos de combate
    public int atacar() {
        int danoAleatorio = random.nextInt(10);

        if (danoAleatorio == 9) {
            System.out.println(">>> ACERTO CRÍTICO!!! <<<");
            return (this.forca + danoAleatorio) * 2;
        }
        
        return this.forca + danoAleatorio;
    }

    public void receberDano(int danoRecebido) {
        int danoReal = danoRecebido - this.defesa;

        if (danoReal < 0) {
            danoReal = 0;
        }

        this.vida -= danoReal;
        if (this.vida < 0) {
            this.vida = 0;
        }

        System.out.println(this.nome + " recebeu " + danoReal + " de dano. Vida restante: " + this.vida);
    }

    public void ganharXp(int xpGanho) {
        this.xp += xpGanho;
        System.out.println(this.nome + " ganhou " + xpGanho + " XP.");

        if (this.xp >= this.nivel * 100) {
            xp -= (this.nivel * 100);
            this.nivel += 1;
            this.forca += 3;
            this.defesa += 2;

            System.out.println("PARABÉNS! Você subiu para o nível " + this.nivel + "!");
        }
    }
}