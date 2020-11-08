package dados;

public class Quarto {
	private int id_quarto;
	private int quant_camas;
	private String preco;
	private String descricao;
	private String comodidade;
    private int id_frigobar;
    private int id_tipo_quarto;
    
    //Constructors
	public Quarto(int id_quarto) {
		this.id_quarto = id_quarto;
	}
	
	public Quarto(int quant_camas, String preco, String descricao, String comodidade, int id_frigobar, int id_tipo_quarto) {
		this.quant_camas = quant_camas;
		this.preco = preco;
		this.descricao = descricao;
		this.comodidade = comodidade;
		this.id_frigobar = id_frigobar;
		this.id_tipo_quarto = id_tipo_quarto;
	}
	
	public Quarto(int id_quarto, int quant_camas, String preco, String descricao, String comodidade, int id_frigobar, int id_tipo_quarto) {
		this.id_quarto = id_quarto;
		this.quant_camas = quant_camas;
		this.preco = preco;
		this.descricao = descricao;
		this.comodidade = comodidade;
		this.id_frigobar = id_frigobar;
		this.id_tipo_quarto = id_tipo_quarto;
	}
	
	//Getters and Setters
	public int getId_quarto() {
		return id_quarto;
	}
	public void setId_quarto(int id_quarto) {
		this.id_quarto = id_quarto;
	}
	public int getQuant_camas() {
		return quant_camas;
	}
	public void setQuant_camas(int quant_camas) {
		this.quant_camas = quant_camas;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getComodidade() {
		return comodidade;
	}
	public void setComodidade(String comodidade) {
		this.comodidade = comodidade;
	}
	public int getId_frigobar() {
		return id_frigobar;
	}
	public void setId_frigobar(int id_frigobar) {
		this.id_frigobar = id_frigobar;
	}
	public int getId_tipo_quarto() {
		return id_tipo_quarto;
	}
	public void setId_tipo_quarto(int id_tipo_quarto) {
		this.id_tipo_quarto = id_tipo_quarto;
	}
}
