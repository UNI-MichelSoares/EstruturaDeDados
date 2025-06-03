// Classe Automovel:
package dominio;

public class Automovel {

	private String placa;
	private String marca;
	private String modelo;
	private int ano;
	private double valor;

	public Automovel(String placa, String marca, String modelo, int ano, double valor) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void exibir() {
		System.out.println("Placa: " + placa);
		System.out.println("Modelo: " + modelo);
		System.out.println("Marca: " + marca);
		System.out.println("Ano: " + ano);
		System.out.println("Valor: " + String.format("%.2f", valor));
	}

}

// Classe ArquivoAutomovel:
package dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoAutomovel {

	public void salvar(List<Automovel> lista, String caminho) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
			for (Automovel automovel : lista) {
				writer.write(automovel.getPlaca() + (",") + automovel.getMarca() + (",") + automovel.getModelo() + (",")
						+ automovel.getAno() + (",") + automovel.getValor());
				writer.newLine();
			}
			writer.close();
		}
	}

	public List<Automovel> carregar(String caminho) throws IOException {
		List<Automovel> lista = new ArrayList<>();

		File arquivo = new File(caminho);
		if (!arquivo.exists())
			return lista;

		try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
			String linha;

			while ((linha = reader.readLine()) != null) {
				String[] partes = linha.split(",");
				lista.add(new Automovel(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]),
						Double.parseDouble(partes[4])));
			}
			reader.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void adicionarAutomovel(Automovel novo, String caminho) throws IOException {
		List<Automovel> lista = carregar(caminho);
		lista.add(novo);
		salvar(lista, caminho);
	}

	public void removerAutomovel(String placa, String caminho) throws IOException {
		List<Automovel> lista = carregar(caminho);
		Automovel alvo = null;

		for (Automovel automovel : lista) {
			if (automovel.getPlaca().equalsIgnoreCase(placa)) {
				alvo = automovel;
				break;
			}
		}

		if (alvo != null) {
			lista.remove(alvo);
			salvar(lista, caminho);
		}
	}

	public void editarAutomovel(String placa, Automovel novo, String caminho) throws IOException {
		List<Automovel> lista = carregar(caminho);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPlaca().equalsIgnoreCase(placa)) {
				lista.set(i, novo);
				break;
			}
		}
		salvar(lista, caminho);
	}
}

// Classe ControlaAutomovel:
package controle;

import dominio.Automovel;
import dominio.ArquivoAutomovel;
import java.util.*;
import java.io.*;

public class ControlaAutomovel {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArquivoAutomovel arquivo = new ArquivoAutomovel();
		String caminho = "automoveis.csv";

		String placa;
		
		int opcao;

		do {
			System.out.println("-------------------------------");
			System.out.println("MENU:");
			System.out.println("1 - Incluir automóvel");
			System.out.println("2 - Remover automóvel");
			System.out.println("3 - Alterar dados de automóvel");
			System.out.println("4 - Consultar automóvel por placa");
			System.out.println("5 - Listar automóveis (ordenado)");
			System.out.println("6 - Salvar e Sair");
			System.out.println("-------------------------------");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				Automovel novo = lerAutomovel(scanner);
				try {
					arquivo.adicionarAutomovel(novo, caminho);
				} catch (IOException e) {
					System.out.println("Erro ao adicionar automóvel: " + e.getMessage());
				}
				break;

			case 2:
				System.out.println("Informe a placa do veículo a ser removido: ");
				placa = scanner.nextLine();

				try {
					arquivo.removerAutomovel(placa, caminho);
					System.out.println("Automóvel removido com sucesso!");
				} catch (IOException e) {
					System.out.println("Erro ao remover automóvel: " + e.getMessage());
				}
				break;

			case 3:
				Automovel atualizado = lerAutomovel(scanner);
				try {
					arquivo.editarAutomovel(atualizado.getPlaca(), atualizado, caminho);
				} catch (IOException e) {
					System.out.println("Erro ao editar automóvel: " + e.getMessage());
				}
				break;

			case 4:
				System.out.println("Informe a placa a ser consultada: ");
				placa = scanner.nextLine();

				try {
					boolean encontrado = false;
					for (Automovel automovel : arquivo.carregar(caminho)) {
						if (automovel.getPlaca().equalsIgnoreCase(placa)) {
							encontrado = true;
							automovel.exibir();
						}
					}

					if (!encontrado) {
						System.out.println("Nenhum automóvel encontrado com a placa informada.");
					}

				} catch (IOException e) {
					System.out.println("Não foi possível localizar o automóvel: " + e.getMessage());
				}
				break;

			case 5:
				try {
					List<Automovel> lista = arquivo.carregar(caminho);

					if (lista.isEmpty()) {
						System.out.println("Nenhum automóvel cadastrado.");
						break;
					}

					System.out.println("Ordenar por:");
					System.out.println("1 - Placa");
					System.out.println("2 - Modelo");
					System.out.println("3 - Marca");
					System.out.print("Escolha: ");
					int ordem = scanner.nextInt();
					scanner.nextLine();

					switch (ordem) {
					case 1:
						Collections.sort(lista, Comparator.comparing(Automovel::getPlaca));
						break;
					case 2:
						Collections.sort(lista, Comparator.comparing(Automovel::getModelo));
						break;
					case 3:
						Collections.sort(lista, Comparator.comparing(Automovel::getMarca));
						break;
					default:
						System.out.println("Opção inválida.");
						break;
					}

					System.out.println("\nAutomóveis ordenados:");
					for (Automovel a : lista) {
						System.out.println("--------------------------");
						a.exibir();
					}

				} catch (IOException e) {
					System.out.println("Erro ao carregar os dados: " + e.getMessage());
				}
				break;

			case 6:
				System.out.println("Dados salvos automaticamente. Encerrando programa.");
				break;

			default:
				System.out.println("Opção inválida. Selecione outra opção.");
				break;
			}

		} while (opcao != 6);
		
		scanner.close();
	}
	
	private static Automovel lerAutomovel(Scanner scanner) {
	    System.out.println("Informe a placa: ");
	    String placa = scanner.nextLine();

	    System.out.println("Informe a marca: ");
	    String marca = scanner.nextLine();

	    System.out.println("Informe o modelo: ");
	    String modelo = scanner.nextLine();

	    System.out.println("Informe o ano: ");
	    int ano = scanner.nextInt();

	    System.out.println("Informe o valor: ");
	    double valor = scanner.nextDouble();
	    scanner.nextLine();

	    return new Automovel(placa, marca, modelo, ano, valor);
	}

}
