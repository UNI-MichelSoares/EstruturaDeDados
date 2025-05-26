// Classe Produto:
package dominio;

public class Produto {
	
	private int codigo;
	private String nome;
	private double valor;
	
	public Produto(int codigo, String nome, double valor) {
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void exibir() {
		System.out.println("Código: " + codigo);
		System.out.println("Nome: " + nome);
		System.out.println("Valor: R$" + String.format("%.2f", valor));
	}
}

// Classe OrdenarProduto:
package dominio;

public class OrdenarProduto {
	public static void ordenar(Produto[] produtos) {
		int tamanho = produtos.length;
		
		for (int i = 0; i < tamanho - 1; i++) {
			for(int j = 0; j < tamanho - 1 - i; j++) {
				if (produtos[j].getValor() < produtos[j+1].getValor()) {
					Produto temp = produtos[j];
					produtos[j] = produtos[j+1];
					produtos[j+1] = temp;
				}
			}
		}
	}
}

// Classe ControlaProduto:
package controle;

import dominio.OrdenarProduto;
import dominio.Produto;
import java.util.Scanner;

public class ControlaProduto {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Produto[] produtos = new Produto[10];
		
		for (int i = 0; i < produtos.length; i++) {
			// Código do produto
			System.out.println("Informe o código do produto " + i + ": ");
			int codigo = scan.nextInt();
			scan.nextLine();
			
			//Nome do produto
			System.out.println("Informe o nome do produto " + i + ": ");
			String nome = scan.nextLine();
			
			//Valor do produto
			System.out.println("Informe o valor do produto " + i + ": ");
			double valor = scan.nextDouble();
			scan.nextLine();
			
			produtos[i] = new Produto(codigo, nome, valor);
			
		}
		
		OrdenarProduto.ordenar(produtos);
		
		System.out.println("Produtos ordenados do mais caro para o mais barato: ");
		for (Produto produto: produtos) {
			produto.exibir();
		}
		
		scan.close();
		
	}
}
