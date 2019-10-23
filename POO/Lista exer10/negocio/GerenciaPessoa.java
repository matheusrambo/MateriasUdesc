package negocio;

import dados.Pessoa;
import excecoes.ListaVaziaException;
import excecoes.PosicaoInvalidaException;

public class GerenciaPessoa {
	private int quantPessoas = 0;
	private Nodo cabeca = null;
	private Nodo ultimo = null;
	
	public void inserirFinalLista(Pessoa p) {
		if(cabeca == null) {
			cabeca = new Nodo(p);
			ultimo = cabeca;
			quantPessoas++;
		}else{
			Nodo novoNodo = new Nodo(p);
			ultimo.setProximo(novoNodo);
			ultimo = novoNodo;
			quantPessoas++;
		}
	}
	
	public void inserirNaPosicao(Pessoa p, int pos) throws PosicaoInvalidaException{
		if(pos < 0 || pos > quantPessoas) {
			throw new PosicaoInvalidaException("Posição invalida");
		}else {
			if(pos < quantPessoas) {
				Nodo aux = cabeca;
				for(int i = 0;i < pos;i++) {
					aux = aux.getProximo();
				}
				Nodo novoNodo = new Nodo(p);
				novoNodo.setProximo(aux.getProximo());
				aux.setProximo(novoNodo);
				quantPessoas++;				
			}else {
				inserirFinalLista(p);
			}
		}
	}
	public void excluirNaPosicao(Pessoa p, int pos) throws PosicaoInvalidaException, ListaVaziaException{
		if(quantPessoas == 0) {
			throw new ListaVaziaException("A lista esta vazia");
		}else {
			if(pos < 0 || pos > quantPessoas) {
				throw new PosicaoInvalidaException("Posição invalida");
			}else {
				if(pos==0 && quantPessoas > 1) {
					cabeca = cabeca.getProximo();
					quantPessoas--;
				}else {
					Nodo aux = cabeca;
					if(pos == quantPessoas-1) {
						for(int i = 0;i < pos-2;i++) {
							aux = aux.getProximo();
						}
						aux.setProximo(null);
						quantPessoas--;
					}else {
						for(int i = 0;i < pos;i++) {
							aux = aux.getProximo();
						}
						aux.setProximo(aux.getProximo().getProximo());
						quantPessoas--;
						
					}
				}
			}
		}
	}
}
