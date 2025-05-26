// Main:
package controle;

import dominio.BubbleSort;

public class Main {

	public static void main(String[] args) {

		int[] codigo = { 105, 101, 110, 102, 109, 106, 108, 103, 107, 104 };
		String[] nome = { "Teclado", "Mouse", "Notebook", "Monitor", "Webcam", "Caixa Som", "Cabo USB", "Pen Drive",
				"HD Externo", "Mousepad" };
		double[] valor = { 129.90, 59.90, 3500.00, 899.00, 199.90, 89.90, 25.00, 49.90, 320.00, 29.90 };

		// Por código
		BubbleSort.ordenarCodigo(codigo, nome, valor);
		System.out.println("Produtos por código: ");
		for (int i = 0; i < codigo.length; i++) {
			System.out.printf("Código: %d | Nome: %-10s | Valor: R$ %.2f\n", codigo[i], nome[i], valor[i]);
		}

		// Por valor
		BubbleSort.ordenarValor(codigo, nome, valor);
		System.out.println("Produtos por valor: ");
		for (int i = 0; i < codigo.length; i++) {
			System.out.printf("Código: %d | Nome: %-10s | Valor: R$ %.2f\n", codigo[i], nome[i], valor[i]);
		}
	}

}

// Classe BubbleSort:
package dominio;

public class BubbleSort {

	private static void trocarCod(int[] arr, int i, int j) {
		int aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	private static void trocarValor(double[] arr, int i, int j) {
		double aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	private static void trocarNome(String[] arr, int i, int j) {
		String aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}

	public static void ordenarCodigo(int[] codigo, String[] nome, double[] valor) {
		for (int i = 0; i < codigo.length - 1; i++) {
			for (int j = 0; j < codigo.length - 1 - i; j++) {
				if (codigo[j] < codigo[j + 1]) {
					trocarCod(codigo, j, j + 1);
					trocarNome(nome, j, j + 1);
					trocarValor(valor, j, j + 1);
				}
			}
		}
	}

	public static void ordenarValor(int[] codigo, String[] nome, double[] valor) {
		for (int i = 0; i < valor.length - 1; i++) {
			for (int j = 0; j < valor.length - 1 - i; j++) {
				if (valor[j] < valor[j + 1]) {
					trocarCod(codigo, j, j + 1);
					trocarNome(nome, j, j + 1);
					trocarValor(valor, j, j + 1);
				}
			}
		}
	}
}
