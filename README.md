# ⚔️ RPG Console - Java Adventure

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![VS Code](https://img.shields.io/badge/VS_Code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-green?style=for-the-badge)

> Um sistema de batalha RPG baseado em turnos, desenvolvido inteiramente em Java para rodar no console. O projeto foca na aplicação prática de Lógica de Programação e Orientação a Objetos (POO).

---

## 🎮 Funcionalidades do Jogo

O projeto simula uma experiência completa de RPG textual:

- **⚔️ Sistema de Combate:** Lógica de turnos onde o jogador escolhe entre Atacar, Defender ou Fugir.
- **🎲 Fator Aleatoriedade (RNG):** Dano variável e chance de **Acerto Crítico** (dano dobrado).
- **🛡️ Modos de Dificuldade:**
    - 🟢 Fácil: Slime (Ideal para farmar XP).
    - 🟡 Médio: Goblin (O desafio clássico).
    - 🔴 Difícil: Orc de Guerra (Para quem gosta de sofrer).
    - 💀 IMPOSSÍVEL: Dragão Ancião (Desafio para lendas).
- **📈 Progressão de Personagem:** Sistema de XP e Nível. Ao subir de nível, o herói ganha vida máxima, força e defesa.
- **💾 Persistência em Memória:** O herói é mantido entre as batalhas (não reseta ao iniciar uma nova luta), permitindo acumular poder.
- **🏃 Mecânica de Fuga:** Sistema de risco vs. recompensa (30% de chance de fugir ou perder o turno).

---

## 🛠️ Tecnologias e Conceitos Aplicados

Este projeto foi desenvolvido para consolidar conhecimentos fundamentais da linguagem Java:

* **POO (Programação Orientada a Objetos):**
    * **Encapsulamento:** Atributos privados (`private`) acessados via Getters/Setters.
    * **Classes e Objetos:** Instanciação de heróis e monstros distintos baseados na classe modelo `Personagem`.
* **Lógica de Programação:**
    * Estruturas condicionais (`if/else`, `switch case`).
    * Laços de repetição (`while`, `do while`) para o game loop.
* **Java Core:**
    * `java.util.Scanner` para entrada de dados.
    * `java.util.Random` para geração de números aleatórios.
    * `Thread.sleep()` para manipulação temporal (criar suspense nas mensagens).
    * **Tratamento de Exceções:** Uso de `try/catch` para gerenciar interrupções de thread.

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
* Java JDK instalado (recomendado versão 17 ou superior).
* VS Code (com Extension Pack for Java) ou qualquer IDE Java.

### Passo a Passo
1.  Clone o repositório:
    ```bash
    git clone https://github.com/augusto-projetos/rpg-console.git
    ```
2.  Abra a pasta no VS Code.
3.  Navegue até o arquivo `Main.java`.
4.  Execute o projeto (pressione `F5` ou clique em "Run").

---

## 🧠 Estrutura do Código

* `Main.java`: Ponto de entrada. Gerencia o loop principal do jogo (Jogar Novamente).
* `Batalha.java`: Controla a lógica do combate, turnos e fluxo da luta.
* `Personagem.java`: Classe modelo que define os atributos (Vida, Força, XP) e comportamentos (Atacar, Receber Dano, Ganhar XP).

---

## 👨‍💻 Autor

Desenvolvido por **Luiz Augusto** como projeto de estudo prático em Java.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-augusto-39b985367/)
