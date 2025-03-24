package util;

import java.util.Scanner;

class ListaArray {
	private int[] array;
	private int tamanho;

	public ListaArray(int capacidade) {
		this.array = new int[capacidade];
		this.tamanho = 0;
	}

	public void adicionar(int elemento) {
		if (tamanho == array.length) {
			System.out.println("Erro: Lista cheia!");
			return;
		}
		array[tamanho++] = elemento;
	}

	public void adicionarEmPosicao(int elemento, int posicao) {
		if (tamanho == array.length) {
			System.out.println("Erro: Lista cheia!");
			return;
		}
		if (posicao < 0 || posicao > tamanho) {
			System.out.println("Erro: Posição inválida!");
			return;
		}
		for (int i = tamanho; i > posicao; i--) {
			array[i] = array[i - 1];
		}
		array[posicao] = elemento;
		tamanho++;
	}

	public void remover(int posicao) {
		if (posicao < 0 || posicao >= tamanho) {
			System.out.println("Erro: Posição inválida!");
			return;
		}
		for (int i = posicao; i < tamanho - 1; i++) {
			array[i] = array[i + 1];
		}
		tamanho--;
	}

	public void imprimirLista() {
		System.out.print("Lista: ");
		for (int i = 0; i < tamanho; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}

public class TesteLista {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite a capacidade da lista: ");
		int capacidade = scanner.nextInt();

		ListaArray lista = new ListaArray(capacidade);

		while (true) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1 - Adicionar elemento");
			System.out.println("2 - Adicionar elemento em posição específica");
			System.out.println("3 - Remover elemento");
			System.out.println("4 - Imprimir lista");
			System.out.println("5 - Sair");
			System.out.print("Opção: ");
			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("Digite o elemento a adicionar: ");
				int elemento = scanner.nextInt();
				lista.adicionar(elemento);
				break;
			case 2:
				System.out.print("Digite o elemento: ");
				int elem = scanner.nextInt();
				System.out.print("Digite a posição: ");
				int pos = scanner.nextInt();
				lista.adicionarEmPosicao(elem, pos);
				break;
			case 3:
				System.out.print("Digite a posição a remover: ");
				int posicao = scanner.nextInt();
				lista.remover(posicao);
				break;
			case 4:
				lista.imprimirLista();
				break;
			case 5:
				System.out.println("Saindo...");
				scanner.close();
				return;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}
}
