package util;

public class ListaArray {
	//
	// CONSTANTES - Sempre definidas com todas as letras maiúsculas.
	// Ao invés de usar uma literal (valor) no código, devemos usar uma constante.
	//
	public static final int TAMANHO_INICIAL = 3;
	public static final int FATOR_CRESCIMENTO = 5;
	public static final int NAO_ESTA_PRESENTE = -1;

	//
	// ATRIBUTOS
	//
	private Object[] arrayInterno;
	private int numElementos;

	//
	// CONSTRUTOR
	//
	public ListaArray() {
		this.numElementos = 0; // Inicialização desnecessária, mas explícita.
		this.arrayInterno = new Object[TAMANHO_INICIAL];
	}

	/**
	 * Verifica a necessidade de crescimento do array interno. Caso o array esteja
	 * cheio, cria um novo array com tamanho aumentado.
	 */
	private void verificarNecessidadeDeCrescimento() {
		int tamanho = this.arrayInterno.length;

		if (tamanho == this.numElementos) { // Array cheio, precisa crescer
			Object[] novoArray = new Object[tamanho + FATOR_CRESCIMENTO];

			// Copiando elementos para o novo array
			System.arraycopy(this.arrayInterno, 0, novoArray, 0, tamanho);

			// Atualizando a referência para o novo array
			this.arrayInterno = novoArray;
		}
	}

	/**
	 * Adiciona um elemento no final da lista.
	 * 
	 * @param elemento - Referência para o objeto a ser adicionado.
	 * @return true, indicando que o elemento foi adicionado com sucesso.
	 */
	public boolean adicionar(Object elemento) {
		this.verificarNecessidadeDeCrescimento();
		this.arrayInterno[this.numElementos] = elemento;
		this.numElementos++;
		return true;
	}

	/**
	 * Adiciona um elemento na posição especificada da lista.
	 * 
	 * @param elemento - Referência para o objeto a ser adicionado.
	 * @param posicao  - Índice onde o elemento deve ser inserido.
	 * @return true se a posição for válida e a inserção ocorrer; false caso
	 *         contrário.
	 */
	public boolean adicionar(Object elemento, int posicao) {
		if (posicao < 0 || posicao > this.numElementos) {
			return false; // Posição inválida
		}

		this.verificarNecessidadeDeCrescimento();

		// Deslocando elementos para abrir espaço para o novo
		System.arraycopy(this.arrayInterno, posicao, this.arrayInterno, posicao + 1, this.numElementos - posicao);

		this.arrayInterno[posicao] = elemento;
		this.numElementos++;
		return true;
	}

	/**
	 * Retorna o elemento presente na posição especificada.
	 * 
	 * @param posicao - Índice do elemento a ser obtido.
	 * @return O elemento na posição ou null se a posição for inválida.
	 */
	public Object obter(int posicao) {
		if (posicao < 0 || posicao >= this.numElementos) {
			return null;
		}
		return this.arrayInterno[posicao];
	}

	/**
	 * Retorna a posição de um elemento na lista.
	 * 
	 * @param elemento - Referência para o elemento a ser localizado.
	 * @return A posição do elemento ou NAO_ESTA_PRESENTE (-1) se não encontrado.
	 */
	public int posicaoDe(Object elemento) {
		for (int i = 0; i < this.numElementos; i++) {
			if (this.arrayInterno[i].equals(elemento)) { // Comparação correta
				return i;
			}
		}
		return NAO_ESTA_PRESENTE;
	}

	/**
	 * Remove o elemento na posição especificada e reorganiza a lista.
	 * 
	 * @param posicao - Índice do elemento a ser removido.
	 * @return true se o elemento for removido; false se a posição for inválida.
	 */
	public boolean remover(int posicao) {
		if (posicao < 0 || posicao >= this.numElementos) {
			return false;
		}

		// Deslocando os elementos para preencher o espaço do removido
		System.arraycopy(this.arrayInterno, posicao + 1, this.arrayInterno, posicao, this.numElementos - posicao - 1);

		// Evita referência residual ao último elemento
		this.arrayInterno[this.numElementos - 1] = null;

		this.numElementos--;
		return true;
	}

	/**
	 * Remove a primeira ocorrência de um determinado elemento na lista.
	 * 
	 * @param elemento - Referência para o objeto a ser removido.
	 * @return true se o elemento foi removido; false se não encontrado.
	 */
	public boolean remover(Object elemento) {
		int posicao = this.posicaoDe(elemento);
		if (posicao == NAO_ESTA_PRESENTE) {
			return false;
		}
		return this.remover(posicao);
	}

	/**
	 * Retorna a quantidade de elementos na lista.
	 * 
	 * @return O número de elementos atualmente armazenados na lista.
	 */
	public int obterNumElementos() {
		return this.numElementos;
	}
}