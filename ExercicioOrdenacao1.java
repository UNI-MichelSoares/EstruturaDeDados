// Main:
package controle;

import java.util.Scanner;

import dominio.OrdenarArray;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] listaDeNumeros = new int[10];
		
		for (int i = 0; i < listaDeNumeros.length; i++) {
			System.out.println("Informe o número na posição " +i +": ");
			listaDeNumeros[i] = scan.nextInt();
		}
		
		OrdenarArray.ordenar(listaDeNumeros);
		
		System.out.println("Array ordenado: ");
		for(int numero : listaDeNumeros) {
			System.out.print(numero + " ");
		}
		
		scan.close();

	}

}

// Classe OrdenarArray:
package dominio;

public class OrdenarArray {
	public static void ordenar(int[] numeros) {
		int tamanho = numeros.length;
		
		for (int i = 0; i < tamanho - 1; i++) {
			for (int j = 0; j < tamanho - 1 - i; j++) {
				if (numeros[j] > numeros[j+1]) {
					int temp = numeros[j];
					numeros[j] = numeros[j+1];
					numeros[j+1] = temp;
				}
			}
		}
	}
}
