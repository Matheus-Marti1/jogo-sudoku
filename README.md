# Jogo de Sudoku em Java (Console)

## Descrição

Este é um jogo de Sudoku totalmente funcional implementado em Java para ser executado no console. O projeto foi desenvolvido utilizando conceitos de Programação Orientada a Objetos (POO), e foi feito para um desafio de projeto da plataforma DIO, com a descrição do desafio encontrada [aqui](https://github.com/digitalinnovationone/exercicios-java-basico/blob/main/projetos/2%20-%20Programa%C3%A7%C3%A3o%20Orientada%20a%20Objetos%20e%20Estruturas%20de%20Dados%20com%20Java.md).

O jogo permite que o usuário inicie um tabuleiro com números pré-definidos, adicione e remova números, verifique o status do jogo em tempo real e valide a solução final.

## Funcionalidades

* **Menu Interativo:** Navegação por um menu no console.
* **Iniciar Jogo via Argumentos:** O tabuleiro inicial é configurado por meio de argumentos na execução do programa.
* **Validação de Jogadas:** O sistema impede que o jogador insira um número em uma posição que viole as regras do Sudoku (números repetidos na mesma linha, coluna ou subgrade 3x3).
* **Proteção de Números Fixos:** Os números iniciais do jogo são fixos e não podem ser alterados ou removidos pelo jogador.
* **Verificação de Status:** A qualquer momento, o jogador pode verificar o status do jogo, que informa se ele está incompleto ou completo, e se contém erros.
* **Limpeza do Tabuleiro:** Permite remover todas as jogadas feitas pelo usuário, retornando o tabuleiro ao seu estado inicial.
* **Finalização Segura:** O jogo só pode ser finalizado se o tabuleiro estiver 100% completo e sem nenhum erro.

## Estrutura do Projeto

O código é dividido em três classes principais, seguindo os princípios da POO:

* **`model/Celula.java`**: Representa uma única célula (quadrado) do tabuleiro. Armazena seu valor (1-9 ou 0 para vazio) e um indicador boolean `isFixo` para saber se é um número inicial.
* **`model/Tabuleiro.java`**: Representa o tabuleiro 9x9. Contém uma matriz de objetos `Celula` e toda a lógica principal do jogo, como iniciar, exibir, colocar/remover números e validar as regras.
* **`JogoSudoku.java`**: É a classe principal (`main`) que gerencia a execução e o fluxo do jogo. É responsável por exibir o menu, capturar a entrada do usuário e chamar os métodos apropriados da classe `Tabuleiro`.

## Como Executar o Jogo

Você pode executar o projeto de duas maneiras: por uma IDE ou diretamente pela linha de comando.

### Via IDE (IntelliJ IDEA, Eclipse, etc.)

1.  **Clone ou baixe o repositório:**
    Tenha todos os arquivos (`JogoSudoku.java`, `model/Tabuleiro.java`, `model/Celula.java`) em seu projeto.

2.  **Configure os Argumentos do Tabuleiro:**
    * Na sua IDE, vá até as configurações de execução (Run/Debug Configurations) da classe `JogoSudoku`.
    * No campo **"Program arguments"**, insira os números iniciais do tabuleiro no formato `linha:coluna:valor`, separados por espaços.

    **Exemplo de argumento:**
    ```
    0:0:9 0:1:5 0:2:8 0:7:2 1:3:2 1:4:5 1:5:6 1:7:4 2:2:6 2:6:5 2:7:1 2:8:7 3:0:6 3:3:3 3:4:7 3:5:8 4:0:7 4:1:8 4:2:4 4:6:9 4:7:3 4:8:2 5:3:4 5:4:2 5:5:9 5:8:8 6:0:4 6:1:9 6:2:2 6:6:1 7:1:6 7:3:5 7:4:8 7:5:1 8:1:1 8:6:7 8:7:6 8:8:3
    ```

3.  **Execute a Classe Principal:**
    * Execute o método `main` da classe `JogoSudoku.java`.
    * O menu interativo aparecerá no console. Escolha a **opção 1** para carregar os números que você configurou e começar a jogar.

### Via Linha de Comando

**Pré-requisito:** É necessário ter o [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) instalado e configurado no seu sistema.

1.  **Navegue até a pasta do projeto:**
    Abra um terminal ou prompt de comando e navegue até a pasta raiz do projeto (a pasta que contém a pasta `src`).

2.  **Compile os arquivos Java:**
    Execute o comando abaixo para compilar todos os arquivos `.java` e colocar os arquivos `.class` compilados em uma nova pasta chamada `out`.
    ```bash
    javac -d out src/JogoSudoku.java src/model/*.java
    ```

3.  **Execute o jogo:**
    Agora, execute o jogo a partir da pasta raiz, usando a pasta `out` como classpath e passando os argumentos do tabuleiro no final.
    ```bash
    java -cp out JogoSudoku 0:0:9 0:1:5 0:2:8 0:7:2 1:3:2 1:4:5 1:5:6 1:7:4 2:2:6 2:6:5 2:7:1 2:8:7 3:0:6 3:3:3 3:4:7 3:5:8 4:0:7 4:1:8 4:2:4 4:6:9 4:7:3 4:8:2 5:3:4 5:4:2 5:5:9 5:8:8 6:0:4 6:1:9 6:2:2 6:6:1 7:1:6 7:3:5 7:4:8 7:5:1 8:1:1 8:6:7 8:7:6 8:8:3
    ```

4.  **Jogue:**
    O menu aparecerá no terminal. Escolha a **opção 1** para iniciar e comece a jogar.
