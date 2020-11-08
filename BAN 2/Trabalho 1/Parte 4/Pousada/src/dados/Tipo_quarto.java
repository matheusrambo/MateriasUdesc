package dados;

public class Tipo_quarto {
	private int id_tipo_quarto;
	private String descricao;
	
	//Constructors
	public Tipo_quarto(int id) {
		this.id_tipo_quarto = id;
	}

	public Tipo_quarto(int id, String descricao) {
		this.id_tipo_quarto = id;
		this.descricao = descricao;
	}

	//Getters and Setters
	public int getId_tipo_quarto() {
		return id_tipo_quarto;
	}

	public void setId_tipo_quarto(int id_tipo_quarto) {
		this.id_tipo_quarto = id_tipo_quarto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}		
}
