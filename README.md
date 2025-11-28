# ⚔️ RPG Console - Java Adventure

![Status](https://img.shields.io/badge/STATUS-CONCLUÍDO-brightgreen?style=for-the-badge)
![Versão](https://img.shields.io/github/v/release/augusto-projetos/rpg-console?style=for-the-badge&label=VERSÃO&color=blue)
![Linguagem](https://img.shields.io/badge/JAVA-100%25-orange?style=for-the-badge&logo=java)
![Licença](https://img.shields.io/github/license/augusto-projetos/rpg-console?style=for-the-badge)

> **Um RPG de texto estratégico onde suas escolhas de classe definem seu destino.**
> 
> Desenvolvido inteiramente em Java para rodar no console, este projeto foca na aplicação prática de Lógica de Programação, Orientação a Objetos (POO) e Game Design.

---

## 🎮 Sobre o Jogo

O **RPG Console** simula uma campanha completa de RPG textual, combinando estratégia de combate com gestão de recursos. O jogador deve administrar Ouro e Mana, escolher táticas corretas e evoluir seu personagem para sobreviver a uma jornada de desafios crescentes.

### Principais Funcionalidades

- **🛡️ Classes & Habilidades:** Escolha entre **Guerreiro**, **Mago** ou **Arqueiro**. Cada classe possui status únicos e **Habilidades Especiais** que consomem Mana (MP).
- **💰 Economia & Loja:** Derrote monstros para ganhar Ouro. Visite o **Mercador** para comprar poções vitais ou investir em **Upgrades Permanentes** de força e defesa.
- **💾 Sistema de Save/Load:** Seu progresso (Nível, Ouro, Atributos) é salvo localmente em arquivo criptografado, permitindo pausar e continuar sua aventura a qualquer momento.
- **🔥 Mecânica de Vantagens:** Sistema tático estilo "Pedra, Papel e Tesoura". Escolher a classe certa contra o inimigo certo garante dano massivo.
- **📖 Modo História:** O jogo segue uma narrativa linear com descrições imersivas e **escolhas de caminho** (ex: *Cemitério* ou *Acampamento*?).
- **🎒 Sobrevivência Hardcore:** A Vida e Mana **não enchem automaticamente** entre os capítulos. Você deve gerenciar poções e encontrar Fontes Sagradas para continuar vivo.
- **🔄 Sistema de Farm:** Travou em um chefe difícil? Volte para capítulos anteriores para treinar, ganhar XP e Ouro sem avançar a história.
- **🗣️ NPCs & Lore:** O Mercador não apenas vende itens, ele vende **Informação**. Pague para ouvir rumores sobre fraquezas de chefes e segredos do mundo.
- **📊 Interface Visual:** Barra de XP dinâmica e HUD informativo no terminal para acompanhar seu status.
- **🎲 Fator Aleatoriedade:** Dano variável, chance de Crítico e mecânica de **Fúria** (Inimigos ficam mais perigosos quando estão morrendo).
- **🧭 Eventos & Exploração:** O mundo é dinâmico. Entre batalhas, você pode encontrar tesouros, cair em armadilhas ou negociar com viajantes misteriosos.

---

## ⚔️ Mecânicas de Combate

O sistema de batalha exige estratégia. Conheça as vantagens de cada classe:

| Sua Classe | Vantagem Contra (+Dano) | Fraqueza/Desvantagem | Estilo de Jogo |
| :--- | :--- | :--- | :--- |
| **⚔️ Guerreiro** | Arqueiros e Feras | Magos | **Tanque:** Alta Vida e Defesa. Aguenta pancada. |
| **🔮 Mago** | Guerreiros (Orcs/Golems) | Arqueiros e Guerreiros | **Canhão de Vidro:** Dano Explosivo, mas morre rápido. |
| **🏹 Arqueiro** | Feras (Dragões) e Magos | Guerreiros (Blindados) | **Tático:** Dano Crítico alto e equilibrado. |

---

## 🏆 A Campanha (Capítulos)

1.  **Prólogo:** O Despertar (Tutorial)
2.  **Capítulo 1:** A Encruzilhada (Escolha seu Destino)
3.  **Capítulo 2:** A Torre de Obsidiana (Necromante)
4.  **Capítulo 3:** A Ponte do Guardião (Orc Blindado)
5.  **Capítulo 4:** O Ninho da Escuridão (Aranha Rainha)
6.  **Capítulo 5:** As Ruínas Antigas (Golem)
7.  **Capítulo Final:** O Pico da Montanha (Dragão Ancião)
8.  **Pós-Game:** O Desafio Secreto

---

## 🚀 Como Jogar

### Opção 1: Para Jogadores (Executável Windows)
Quer apenas jogar? Baixe a versão portátil que já vem com tudo configurado (não precisa instalar Java).

1.  Vá até a aba **[Releases](../../releases)** deste repositório.
2.  Baixe o arquivo `.zip` da versão mais recente.
3.  Extraia a pasta e execute o arquivo `RPG-Console.exe`.

> **⚠️ Aviso sobre Antivírus:**
> O executável **não possui uma Assinatura Digital** (certificado pago). O Windows pode exibir um alerta de "Arquivo Desconhecido".
> * **Para jogar:** Clique em *"Mais Informações"* -> *"Executar assim mesmo"*. O código é 100% seguro e aberto.

### Opção 2: Para Desenvolvedores (Código Fonte)
1.  Clone o repositório:
    ```bash
    git clone https://github.com/augusto-projetos/rpg-console.git
    ```
2.  Abra a pasta no **VS Code**.
3.  Execute o arquivo `Main.java`.

---

## 🛠️ Tecnologias e Conceitos

Este projeto foi desenvolvido para consolidar conhecimentos avançados de Java:

* **Arquitetura Modular:** Separação clara de responsabilidades (`Capitulos.java` para narrativa, `Batalha.java` para sistema, `Personagem.java` para dados).
* **POO (Programação Orientada a Objetos):**
    * **Polimorfismo:** Comportamento de ataque e defesa variável por classe.
    * **Encapsulamento:** Proteção de atributos vitais (`vidaMaxima`, `xp`) via Getters/Setters.
* **Lógica de Programação:**
    * Estruturas condicionais complexas (`switch`, `if/else`) para gerenciamento de fluxo de história.
    * Laços de repetição (`while`, `do while`) para o game loop.
* **Java Core:**
    * `java.util.Scanner` com tratamento de exceções (`try-catch`).
    * `Thread.sleep()` para efeito de digitação (narrativa).
    * `File I/O` para sistema de Save/Load.

---

## 👨‍💻 Autor

Desenvolvido por **Luiz Augusto** como projeto de estudo prático em Java.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-augusto-39b985367/)
