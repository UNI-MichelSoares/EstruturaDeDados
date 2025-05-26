// Classe Aluno:
package dominio;

public class Aluno {

	private int matricula;
	private String nome;
	private double cr;

	public Aluno(int matricula, String nome, double cr) {
		this.matricula = matricula;
		this.nome = nome;
		this.cr = cr;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCr() {
		return cr;
	}

	public void setCr(double cr) {
		this.cr = cr;
	}

	public void exibir() {
		System.out.println("Matrícula: " + matricula);
		System.out.println("Nome: " + nome);
		System.out.println("Coeficiente de Rendimento: " + cr);
		System.out.println("-------------------------------");
	}
}

// Classe ArquivoAluno:
package dominio;

import java.io.*;
import java.util.*;

public class ArquivoAluno {
	public void salvar(List<Aluno> lista, String caminho) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));

		for (Aluno aluno : lista) {
			writer.write(aluno.getMatricula() + "," + aluno.getNome() + "," + aluno.getCr());
			writer.newLine();
		}
		writer.close();
	}

	public List<Aluno> carregar(String caminho) throws IOException {
		List<Aluno> lista = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(caminho));
		String linha;
		
		while ((linha = reader.readLine()) != null){
			String[] partes = linha.split(",");
			lista.add(new Aluno(Integer.parseInt(partes[0]), partes[1], Double.parseDouble(partes[2])));
		}
		reader.close();
		return lista;
	}
}

// Classe ControlaAluno:
package controle;

import dominio.Aluno;
import dominio.ArquivoAluno;
import java.util.*;
import java.io.*;

public class ControlaAluno {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Aluno> alunos = new ArrayList<>();
		ArquivoAluno arquivo = new ArquivoAluno();
		String caminho = "alunos.txt";

		int opcao;

		do {
			System.out.println("-------------------------------");
			System.out.println("MENU:");
			System.out.println("1 - Cadastrar alunos");
			System.out.println("2 - Salvar alunos em arquivo");
			System.out.println("3 - Carregar e exibir alunos do arquivo");
			System.out.println("4 - Sair");
			System.out.println("Escolha uma opção: ");
			System.out.println("-------------------------------");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				while (true) {
					System.out.print("Digite a matrícula (0 para parar): ");
					int matricula = scanner.nextInt();
					scanner.nextLine();
					if (matricula == 0)
						break;

					System.out.print("Digite o nome: ");
					String nome = scanner.nextLine();

					System.out.print("Digite o CR: ");
					double cr = scanner.nextDouble();
					scanner.nextLine();

					alunos.add(new Aluno(matricula, nome, cr));
					System.out.println("Aluno cadastrado!\n");
				}
				break;

			case 2:
				try {
					arquivo.salvar(alunos, caminho);
					System.out.println("Alunos salvos com sucesso!");
				} catch (IOException e) {
					System.out.println("Erro ao salvar: " + e.getMessage());
				}
				break;

			case 3:
				try {
					List<Aluno> lidos = arquivo.carregar(caminho);
					System.out.println("Alunos carregados do arquivo:");
					System.out.println("-------------------------------");
					for (Aluno a : lidos) {
						a.exibir();
					}
					System.out.println("Pressione ENTER para voltar ao menu...");
					scanner.nextLine();
				} catch (IOException e) {
					System.out.println("Erro ao carregar: " + e.getMessage());
				}
				break;

			case 4:
				System.out.println("Encerrando...");
				break;

			default:
				System.out.println("Opção inválida!");
			}

			System.out.println();

		} while (opcao != 4);

		scanner.close();
	}
}

