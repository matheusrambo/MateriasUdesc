package dados;

public class Itens_frigobar {
	private int id_itens_frigobar;
	private int id_frigobar;
	private int id_item;
	private int quantidade;
	
	//Constructors
	public Itens_frigobar(int id_itens_frigobar) {
		this.id_itens_frigobar = id_itens_frigobar;
	}
	
	public Itens_frigobar(int id_frigobar, int id_item, int quantidade) {
		this.id_frigobar = id_frigobar;
		this.id_item = id_item;
		this.quantidade = quantidade;
	}
	
	public Itens_frigobar(int id_itens_frigobar, int id_frigobar, int id_item, int quantidade) {
		this.id_itens_frigobar = id_itens_frigobar;
		this.id_frigobar = id_frigobar;
		this.id_item = id_item;
		this.quantidade = quantidade;
	}

	//Getters and Setters
	public int getId_itens_frigobar() {
		return id_itens_frigobar;
	}

	public void setId_itens_frigobar(int id_itens_frigobar) {
		this.id_itens_frigobar = id_itens_frigobar;
	}

	public int getId_frigobar() {
		return id_frigobar;
	}

	public void setId_frigobar(int id_frigobar) {
		this.id_frigobar = id_frigobar;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
