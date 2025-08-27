package model;

import java.util.HashSet;
import java.util.Set;

public class Tabuleiro {
    private static final int TAMANHO = 9;
    private Celula[][] grade;
    private boolean jogoIniciado;

    public Tabuleiro() {
        grade = new Celula[TAMANHO][TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                grade[i][j] = new Celula();
            }
        }
        jogoIniciado = false;
    }

    public void iniciarJogo(String[] args) {
        if (args.length == 0) {
            System.out.println("Nenhum número inicial fornecido. O tabuleiro está vazio.");
            return;
        }

        for (String arg : args) {
            try {
                String[] partes = arg.split(":");
                int linha = Integer.parseInt(partes[0]);
                int coluna = Integer.parseInt(partes[1]);
                int valor = Integer.parseInt(partes[2]);

                if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO && valor > 0 && valor <= 9) {
                    grade[linha][coluna] = new Celula(valor, true);
                } else {
                    System.out.println("Argumento inválido (fora dos limites): " + arg);
                }
            } catch (Exception e) {
                System.out.println("Formato de argumento inválido: " + arg + ". Use linha:coluna:valor.");
            }
        }
        jogoIniciado = true;
        System.out.println("Jogo iniciado com sucesso!");
    }

    public void exibir() {
        System.out.println("\n    0 1 2   3 4 5   6 7 8");
        System.out.println("  +-------+-------+-------+");
        for (int i = 0; i < TAMANHO; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("  +-------+-------+-------+");
            }
            System.out.print(i + " | ");
            for (int j = 0; j < TAMANHO; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                int valor = grade[i][j].getValor();
                System.out.print((valor == 0 ? "." : valor) + " ");
            }
            System.out.println("|");
        }
        System.out.println("  +-------+-------+-------+");
    }

    private boolean isJogadaValida(int linha, int coluna, int valor) {
        for (int j = 0; j < TAMANHO; j++) {
            if (grade[linha][j].getValor() == valor) {
                System.out.println("Erro: O número " + valor + " já existe na linha " + linha + ".");
                return false;
            }
        }

        for (int i = 0; i < TAMANHO; i++) {
            if (grade[i][coluna].getValor() == valor) {
                System.out.println("Erro: O número " + valor + " já existe na coluna " + coluna + ".");
                return false;
            }
        }

        int inicioLinha = linha - linha % 3;
        int inicioColuna = coluna - coluna % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grade[inicioLinha + i][inicioColuna + j].getValor() == valor) {
                    System.out.println("Erro: O número " + valor + " já existe na subgrade 3x3.");
                    return false;
                }
            }
        }

        return true;
    }

    public boolean colocarNumero(int linha, int coluna, int valor) {
        if (linha < 0 || linha >= TAMANHO || coluna < 0 || coluna >= TAMANHO) {
            System.out.println("Erro: Posição fora do tabuleiro.");
            return false;
        }
        if (valor < 1 || valor > 9) {
            System.out.println("Erro: O valor deve estar entre 1 e 9.");
            return false;
        }

        Celula celula = grade[linha][coluna];
        if (celula.isFixo()) {
            System.out.println("Erro: Não é possível alterar um número fixo do jogo.");
            return false;
        }
        if (celula.getValor() != 0) {
            System.out.println("Erro: A posição já está preenchida. Remova o número antes de colocar um novo.");
            return false;
        }

        if (!isJogadaValida(linha, coluna, valor)) {
            return false;
        }

        celula.setValor(valor);
        return true;
    }

    public boolean removerNumero(int linha, int coluna) {
        if (linha < 0 || linha >= TAMANHO || coluna < 0 || coluna >= TAMANHO) {
            System.out.println("Erro: Posição fora do tabuleiro.");
            return false;
        }

        Celula celula = grade[linha][coluna];
        if (celula.isFixo()) {
            System.out.println("Erro: Não é possível remover um número fixo do jogo.");
            return false;
        }
        if (celula.getValor() == 0) {
            System.out.println("A posição já está vazia.");
            return false;
        }

        celula.setValor(0);
        return true;
    }

    public void limpar() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (!grade[i][j].isFixo()) {
                    grade[i][j].setValor(0);
                }
            }
        }
        System.out.println("Tabuleiro limpo. Apenas os números iniciais foram mantidos.");
    }

    public boolean isCompleto() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (grade[i][j].getValor() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean temErros() {
        for (int i = 0; i < TAMANHO; i++) {
            Set<Integer> numerosLinha = new HashSet<>();
            Set<Integer> numerosColuna = new HashSet<>();
            for (int j = 0; j < TAMANHO; j++) {
                int valorLinha = grade[i][j].getValor();
                if (valorLinha != 0) {
                    if (!numerosLinha.add(valorLinha)) return true;
                }
                int valorColuna = grade[j][i].getValor();
                if (valorColuna != 0) {
                    if (!numerosColuna.add(valorColuna)) return true;
                }
            }
        }

        for (int blocoLinha = 0; blocoLinha < TAMANHO; blocoLinha += 3) {
            for (int blocoColuna = 0; blocoColuna < TAMANHO; blocoColuna += 3) {
                Set<Integer> numerosBloco = new HashSet<>();
                for (int i = blocoLinha; i < blocoLinha + 3; i++) {
                    for (int j = blocoColuna; j < blocoColuna + 3; j++) {
                        int valor = grade[i][j].getValor();
                        if (valor != 0) {
                            if (!numerosBloco.add(valor)) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getStatus() {
        if (!jogoIniciado) {
            return "Status: Jogo não iniciado (sem erros).";
        }

        String statusBase;
        if (isCompleto()) {
            statusBase = "Status: Completo";
        } else {
            statusBase = "Status: Incompleto";
        }

        if (temErros()) {
            return statusBase + " (com erros).";
        } else {
            return statusBase + " (sem erros).";
        }
    }
}
