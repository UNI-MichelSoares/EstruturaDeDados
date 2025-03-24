package util;

class ListaArrayString {
	private String[] array;
	private int tamanho;

	public ListaArrayString(int capacidade) {
		this.array = new String[capacidade];
		this.tamanho = 0;
	}

	public void adicionar(String elemento) {
		if (tamanho == array.length) {
			System.out.println("Erro: Lista cheia!");
			return;
		}
		array[tamanho++] = elemento;
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

	public int buscar(String nome) {
		for (int i = 0; i < tamanho; i++) {
			if (array[i].equals(nome)) {
				return i;
			}
		}
		return -1;
	}

	public void imprimirLista() {
		System.out.print("Lista de Nomes: ");
		for (int i = 0; i < tamanho; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}

public class TesteListaString {
	public static void main(String[] args) {
		ListaArrayString lista = new ListaArrayString(5);
		lista.adicionar("Ana");
		lista.adicionar("Bruno");
		lista.adicionar("Carlos");
		lista.adicionar("Daniela");
		lista.imprimirLista();

		lista.remover(1);
		lista.imprimirLista();

		System.out.println("Posição de 'Carlos': " + lista.buscar("Carlos"));
		System.out.println("Posição de 'Bruno': " + lista.buscar("Bruno"));
	}
}
