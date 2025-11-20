public class Personagem {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int forca;
    private int defesa;
    private int nivel;
    private int xp;
    private boolean eMonstro;
    private java.util.Random random;

    // Construtor
    public Personagem(String nome, int vida, int forca, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.nivel = 1;
        this.xp = 0;
        this.eMonstro = false;
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

    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
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

    public boolean getEMonstro() {
        return eMonstro;
    }
    public void setEMonstro(boolean eMonstro) {
        this.eMonstro = eMonstro;
    }

    // Métodos de combate
    public int atacar() {
        int danoAleatorio = random.nextInt(10);

        // Lógica para o Monstro
        if (this.eMonstro && (this.vida * 100) / this.vidaMaxima <= 40) {
            // A chance do monstro sobe para 40% se a vida chegar a 40%
            if (danoAleatorio >= 6) {
                System.out.println("\n>>> O MONSTRO FICOU FURIOSO! CRÍTICO! <<<");
                return (this.forca + danoAleatorio) * 2;
            }
        }

        // Lógica para o Herói
        if (danoAleatorio == 9) {
            // 10% de chance de cair crítico
            System.out.println("\n>>> ACERTO CRÍTICO!!! <<<");
            return (this.forca + danoAleatorio) * 2;
        }
        
        return this.forca + danoAleatorio;
    }

    // Recebe dano considerando a defesa
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

    // Ganha XP e verifica se sobe de nível
    public void ganharXp(int xpGanho) {
        this.xp += xpGanho;
        System.out.println(this.nome + " ganhou " + xpGanho + " XP.");
        System.out.println("XP atual: " + this.xp + "/" + (this.nivel * 100));

        if (this.xp >= this.nivel * 100) {
            xp -= (this.nivel * 100);
            this.nivel += 1;
            this.forca += 3;
            this.defesa += 2;

            System.out.println("PARABÉNS! Você subiu para o nível " + this.nivel + "!");
        }
    }

    // Perde uma porcentagem do XP atual
    public void perderXp(int porcentagem) {
        int perda = (this.xp * porcentagem) / 100;
        this.xp -= perda;
        
        System.out.println("☠️ PENALIDADE: Você perdeu " + perda + " XP por ter sido derrotado.");
        System.out.println("XP Atual: " + this.xp + "/" + (this.nivel * 100));
    }
}