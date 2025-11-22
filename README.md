# ⚔️ RPG Console - Java Adventure

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![VS Code](https://img.shields.io/badge/VS_Code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-green?style=for-the-badge)

> **Um RPG de texto estratégico onde suas escolhas de classe definem seu destino.**
> Desenvolvido inteiramente em Java para rodar no console, este projeto foca na aplicação prática de Lógica de Programação, Orientação a Objetos (POO) e Game Design.

---

## 🎮 Sobre o Jogo

O **RPG Console** simula uma campanha completa de RPG textual, indo muito além de batalhas aleatórias. O jogador deve gerenciar recursos, escolher táticas corretas e evoluir seu personagem para sobreviver a desafios crescentes.

### Principais Funcionalidades

- **🛡️ Sistema de Classes (A Trindade):** Escolha entre **Guerreiro**, **Mago** ou **Arqueiro**. Cada classe possui status únicos e interage de forma diferente com os inimigos.
- **🔥 Mecânica de Vantagens:** Sistema estilo "Pedra, Papel e Tesoura". Escolher a classe certa contra o inimigo certo garante dano massivo, punindo escolhas erradas.
- **🗺️ Campanha "Boss Rush":** Enfrente uma progressão de **9 Chefes**, desde o tutorial até desafios lendários e secretos.
- **📊 Interface Visual:** Barra de XP dinâmica no terminal para acompanhar o progresso do nível.
- **💾 Persistência:** O herói é salvo na memória entre as batalhas, permitindo acumular poder e atributos.
- **🎲 Fator Aleatoriedade:** Dano variável, chance de Crítico e mecânica de **Fúria** (Inimigos ficam mais perigosos quando estão morrendo).

---

## ⚔️ Mecânicas de Combate

O sistema de batalha exige estratégia. Conheça as vantagens de cada classe:

| Sua Classe | Vantagem Contra (+Dano) | Fraqueza/Desvantagem | Estilo de Jogo |
| :--- | :--- | :--- | :--- |
| **⚔️ Guerreiro** | Arqueiros e Feras | Magos | **Tanque:** Alta Vida e Defesa. Aguenta pancada. |
| **🔮 Mago** | Guerreiros (Orcs/Golems) | Arqueiros e Guerreiros | **Canhão de Vidro:** Dano Explosivo, mas morre rápido. |
| **🏹 Arqueiro** | Feras (Dragões) e Magos | Guerreiros (Blindados) | **Tático:** Dano Crítico alto e equilibrado. |

---

## 🏆 Lista de Chefes (Campanha)

1.  **Slime Gosmento** (Tutorial)
2.  **Esqueleto Arqueiro** (Dano Alto / Vida Baixa)
3.  **Goblin Furioso** (Equilibrado)
4.  **Necromante Sombrio** (Mago Explosivo)
5.  **Orc Blindado** (Tanque - Defesa Alta)
6.  **Aranha Rainha** (Resistência - Vida Alta)
7.  **Golem de Pedra** (Tanque Supremo)
8.  **Dragão Ancião** (Lenda - Boss Final)
9.  **???** (Desafio Secreto Impossível)

---

## 🚀 Como Jogar

### Opção 1: Para Jogadores (Executável Windows)
Quer apenas jogar? Baixe a versão portátil que já vem com tudo configurado (não precisa instalar Java).

1.  Vá até a aba **[Releases](../../releases)** deste repositório.
2.  Baixe o arquivo `.zip` da versão mais recente.
3.  Extraia a pasta e execute o arquivo `RPG.exe`.

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

* **POO (Programação Orientada a Objetos):**
    * **Polimorfismo:** O método `atacar(alvo)` muda de comportamento dependendo das classes envolvidas.
    * **Encapsulamento:** Proteção de atributos vitais (`vidaMaxima`, `xp`) via Getters/Setters.
* **Lógica de Programação:**
    * Estruturas condicionais complexas e aninhadas (`switch`, `if/else`).
    * Laços de repetição (`while`, `do while`) para o game loop e level up.
* **Java Core:**
    * `java.util.Scanner` (Inputs blindados contra erros de digitação).
    * `Math` e `Random` (Cálculos de porcentagem e probabilidade).
    * `Thread.sleep()` (Manipulação de tempo para criar suspense).

---

## 👨‍💻 Autor

Desenvolvido por **Luiz Augusto** como projeto de estudo prático em Java.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-augusto-39b985367/)
