package dados;

public class Itens {
	private int id_item;
	private String nome;
	
	@Override
	public String toString() {
		return this.nome;
	}

	//Constructors
	public Itens(int id) {
		this.id_item = id;
	}
	
	public Itens(int id, String nome) {
		this.id_item = id;
		this.nome = nome;
	}

	//Getters and Setters
	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
