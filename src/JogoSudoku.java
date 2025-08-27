import model.Tabuleiro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoSudoku {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            exibirMenu();
            int opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    tabuleiro.iniciarJogo(args);
                    tabuleiro.exibir();
                    break;
                case 2:
                    colocarNumero(scanner, tabuleiro);
                    break;
                case 3:
                    removerNumero(scanner, tabuleiro);
                    break;
                case 4:
                    System.out.println("Situação atual do jogo:");
                    tabuleiro.exibir();
                    break;
                case 5:
                    System.out.println(tabuleiro.getStatus());
                    break;
                case 6:
                    tabuleiro.limpar();
                    tabuleiro.exibir();
                    break;
                case 7:
                    if (tabuleiro.isCompleto() && !tabuleiro.temErros()) {
                        System.out.println("Parabéns! Você completou o Sudoku corretamente!");
                        sair = true;
                    } else {
                        System.out.println("O jogo ainda não está completo ou contém erros. Continue tentando!");
                    }
                    break;
                case 0:
                    sair = true;
                    System.out.println("Obrigado por jogar!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- JOGO SUDOKU ---");
        System.out.println("1. Iniciar novo jogo (usando argumentos)");
        System.out.println("2. Colocar um número");
        System.out.println("3. Remover um número");
        System.out.println("4. Verificar jogo (exibir tabuleiro)");
        System.out.println("5. Verificar status do jogo");
        System.out.println("6. Limpar jogadas");
        System.out.println("7. Finalizar jogo");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }

    private static void colocarNumero(Scanner scanner, Tabuleiro tabuleiro) {
        try {
            System.out.print("Digite a linha (0-8): ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coluna (0-8): ");
            int coluna = scanner.nextInt();
            System.out.print("Digite o valor (1-9): ");
            int valor = scanner.nextInt();
            if(tabuleiro.colocarNumero(linha, coluna, valor)){
                System.out.println("Número colocado com sucesso!");
                tabuleiro.exibir();
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite apenas números.");
            scanner.next();
        }
    }

    private static void removerNumero(Scanner scanner, Tabuleiro tabuleiro) {
        try {
            System.out.print("Digite a linha (0-8) para remover: ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coluna (0-8) para remover: ");
            int coluna = scanner.nextInt();
            if(tabuleiro.removerNumero(linha, coluna)){
                System.out.println("Número removido com sucesso!");
                tabuleiro.exibir();
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite apenas números.");
            scanner.next();
        }
    }
}
