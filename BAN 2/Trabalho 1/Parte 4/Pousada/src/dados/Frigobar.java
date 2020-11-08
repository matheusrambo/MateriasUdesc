package dados;

public class Frigobar {
	private int id_frigobar;
	private String descricao;
	
	
	//Constructors
	public Frigobar(int id) {
		this.id_frigobar = id;
	}

	public Frigobar(int id, String descricao) {
		this.id_frigobar = id;
		this.descricao = descricao;
	}

	//Getters and Setters
	public int getId_frigobar() {
		return id_frigobar;
	}

	public void setId_frigobar(int id_frigobar) {
		this.id_frigobar = id_frigobar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
