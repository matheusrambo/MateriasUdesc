package negocio;

import dados.Pessoa;

public class Nodo {
	private Pessoa pessoa;
	private Nodo proximo = null;
	
	public Nodo() {
		
	}
	
	public Nodo(Pessoa p) {
		pessoa = p;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Nodo getProximo() {
		return proximo;
	}

	public void setProximo(Nodo proximo) {
		this.proximo = proximo;
	}

}
