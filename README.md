# ⚔️ RPG Console - Java Adventure

![Status](https://img.shields.io/badge/STATUS-CONCLUÍDO-brightgreen?style=for-the-badge)
![Versão](https://img.shields.io/badge/RELEASE-v3.0.0-blue?style=for-the-badge)
![Linguagem](https://img.shields.io/badge/JAVA-100%25-orange?style=for-the-badge&logo=java)
![Licença](https://img.shields.io/github/license/augusto-projetos/rpg-console?style=for-the-badge)

> **Um RPG de texto estratégico onde suas escolhas, gerenciamento e sorte definem seu destino.**
> 
> Desenvolvido inteiramente em Java para rodar no console, este projeto demonstra a aplicação prática de Arquitetura de Software, Lógica Avançada e Orientação a Objetos.

---

## 🎮 Sobre o Jogo

O **RPG Console** simula uma campanha completa de RPG. O jogador deve administrar Ouro e Mana, escolher táticas corretas e evoluir seu personagem para sobreviver a uma jornada de desafios crescentes. O jogo conta com persistência de dados, economia funcional e um sistema de combate profundo.

### 🌟 Destaques & Funcionalidades

#### 🗡️ Combate e Estratégia
- **Sistema de Classes:** Jogue como **Guerreiro** (Tanque), **Mago** (Dano Mágico) ou **Arqueiro** (Crítico). Cada um com status e mecânicas únicas.
- **Habilidades e Mana:** Gerencie seus pontos de magia (MP) para lançar ataques especiais que nunca erram.
- **Pedra, Papel e Tesoura:** Sistema de vantagens onde classes específicas causam dano massivo em tipos de inimigos específicos.
- **Hit & Miss:** Ataques físicos dependem da sua Destreza contra a Agilidade do inimigo. Monstros rápidos podem esquivar!

#### 🎒 Loot e Progressão
- **Equipamentos Reais:** Monstros dropam armas e armaduras com Tiers de raridade. Equipar o item certo aumenta seu Dano e Defesa.
- **Economia Viva:** Ganhe Ouro, compre poções vitais ou invista em **Upgrades Permanentes** no Ferreiro.
- **Sobrevivência Hardcore:** Vida e Mana **não regeneram** sozinhas entre fases. A gestão de recursos é a chave da vitória.

#### 🏆 Metagame (O Legado)
- **Bestiário:** Um registro permanente de todas as criaturas derrotadas.
- **Conquistas:** Sistema de *Achievements* que desbloqueia troféus por feitos heroicos (Ex: "Sobrevivente", "Milionário").
- **Persistência Total:** Sistema de Save/Load criptografado que mantém seu progresso, inventário e conquistas.

#### 🗺️ Mundo e Narrativa
- **Modo História:** 9 Capítulos com narrativa imersiva e escolhas de caminho ramificadas.
- **Eventos Aleatórios:** Encontre tesouros, caia em armadilhas ou negocie com viajantes misteriosos entre as batalhas.
- **NPCs:** Interaja com o Mercador para comprar itens ou pagar por informações secretas (Lore).

---

## ⚔️ O Triângulo de Classes

| Classe | Vantagem Contra | Fraqueza | Estilo de Jogo |
| :--- | :--- | :--- | :--- |
| **🛡️ Guerreiro** | Arqueiros e Feras | Magos | **Tanque:** Alta Vida e Defesa física. |
| **🔮 Mago** | Guerreiros (Blindados) | Guerreiros | **Burst:** Dano Explosivo que ignora defesa. |
| **🏹 Arqueiro** | Feras e Magos | - | **Tático:** Alta Precisão e Crítico. |

---

## 🏆 A Campanha (Boss Rush)

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

### Opção 1: Para Jogadores (Windows)
Baixe a versão portátil completa (Java embutido).

1.  Vá até a aba **[Releases](../../releases)**.
2.  Baixe o arquivo `.zip` da versão **v3.0.0**.
3.  Extraia a pasta e execute:
    * **`Corrigir_Cores.bat`** (Recomendado: Garante as cores do terminal - Encontra-se na v2.1).
    * ou `RPG-Console.exe` (Padrão).

> **⚠️ Nota:** Se o Windows exibir um alerta de segurança, é porque o aplicativo não possui certificado digital pago (comum em projetos open-source). Clique em *"Mais Informações"* -> *"Executar assim mesmo"*.

### Opção 2: Para Desenvolvedores (Código Fonte)
1.  Clone o repositório:
    ```bash
    git clone https://github.com/augusto-projetos/rpg-console.git
    ```
2.  Abra a pasta no **VS Code**.
3.  Execute o arquivo `src/Main.java`.

---

## 🛠️ Tecnologias e Arquitetura

Este projeto foi construído para aplicar conceitos avançados de Java:

* **Arquitetura Modular (MVC Simplificado):** Separação clara entre Lógica (`Personagem`), Sistema (`Batalha`) e Conteúdo (`Capitulos`).
* **Collections Framework:** Uso extensivo de `ArrayList` para gerenciamento dinâmico de Inventário, Bestiário e Conquistas.
* **Polimorfismo & Herança:** Sistema de Itens onde `Equipamento extends Item`, alterando comportamento de uso e salvamento.
* **File I/O & Security:** Sistema de persistência robusto com serialização de dados e criptografia **Base64**.
* **UX/UI no Console:** Feedback visual com cores ANSI, barras de progresso e tratamento de exceções (`try-catch`) para evitar crashes.

---

## 👨‍💻 Autor

Desenvolvido por **Luiz Augusto** como projeto de estudo prático em Java.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luiz-augusto-39b985367/)
