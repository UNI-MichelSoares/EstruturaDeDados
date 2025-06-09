// Classe produto

public class Produto implements Comparable<Produto> {
	private int id;
	private String nome;
	private double preco;

	public Produto(int id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	@Override
	public int compareTo(Produto outro) {
		return Integer.compare(this.id, outro.id);
	}

	@Override
	public String toString() {
		return String.format("ID: %d | Nome: %s | Preço: %.2f", id, nome, preco);
	}
}

// Classe ProdutoService

import java.util.*;

public class ProdutoService {
	private final List<Produto> produtos = new ArrayList<>();

	public void adicionarProduto(Produto novo) {
		produtos.add(novo);
		Collections.sort(produtos); // Garante que a lista esteja ordenada

	}

	public Produto buscarPorId(int id) {
		int inicio = 0;
		int fim = produtos.size() - 1;
		while (inicio <= fim) {
			int meio = (inicio + fim) / 2;
			Produto atual = produtos.get(meio);
			if (atual.getId() == id)
				return atual;
			else if (atual.getId() < id)
				inicio = meio + 1;
			else
				fim = meio - 1;
		}
		return null; // Não encontrado
	}

	public void listarProdutos() {
		produtos.forEach(System.out::println);
	}
}

// Classe Main

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ProdutoService service = new ProdutoService();
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("""
					\n=== Menu Produtos ===
					1 - Adicionar produto
					2 - Buscar por ID (binária)
					3 - Listar produtos
					0 - Sair
					""");
			System.out.print("Escolha: ");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1 -> {
				System.out.print("ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				System.out.print("Preço: ");
				double preco = sc.nextDouble();

				service.adicionarProduto(new Produto(id, nome, preco));
			}
			case 2 -> {
				System.out.print("ID para buscar: ");

				int idBusca = sc.nextInt();

				Produto encontrado = service.buscarPorId(idBusca);
				if (encontrado != null) {
					System.out.println("Produto encontrado: " + encontrado);
				} else {

					System.out.println("Produto não encontrado.");
				}
			}
			case 3 -> service.listarProdutos();
			}
		} while (opcao != 0);
		sc.close();
	}
}
