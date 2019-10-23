
public class Agenda {
	private Contato contatos[] = new Contato[15];
	private int contContatos = 0;
	
	public Agenda(Contato[] contatos) {
		super();
		this.contatos = contatos;
	}

	public Contato[] getContatos() {
		return contatos;
	}
	public void setContatos(Contato[] contatos) {
		this.contatos = contatos;
	}
	public void insereContato(int telefone, String nome, String email, String endereco) {
		if(this.contContatos < 15) {
			this.contatos[contContatos] = new Contato(nome, email, endereco, telefone);
			this.contContatos++;
		}
	}
	public void removeContato(int telefone) {
		int pos = 0;
		for(int i=0;i<this.contContatos;i++) {
			if(contatos[i].getTelefone() == telefone) {
				contatos[i] = null;
				pos = i;
			}
		}
		for(int j=pos;j<this.contContatos;j++) {//reordena
			contatos[j] = contatos[j+1];
		}
	}
	public Contato consultaContato(int telefone) {
		Contato cont = new Contato(null,null,null,0);
		for(int i=0;i<this.contContatos;i++) {
			if(contatos[i].getTelefone() == telefone) {
				cont = contatos[i];
			}
		}
		return cont;
	}
}
