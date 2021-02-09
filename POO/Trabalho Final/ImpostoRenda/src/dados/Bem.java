package dados;

public class Bem {
	private int id_bem;
	private String nome;
	private String tipo;
	private float valor;
	private int id_contribuinte;
	
	public Bem(int id) {
		this.id_bem = id;
	}
	
	public Bem(String nome, String tipo, float valor, int id_contribuinte) {
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
		this.id_contribuinte = id_contribuinte;
	}

	public Bem(int id_bem, String nome, String tipo, float valor, int id_contribuinte) {
		this.id_bem = id_bem;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
		this.id_contribuinte = id_contribuinte;
	}

	public int getId_bem() {
		return id_bem;
	}
	public void setId_bem(int id_bem) {
		this.id_bem = id_bem;
	}
	public int getId_contribuinte() {
		return id_contribuinte;
	}
	public void setId_contribuinte(int id_contribuinte) {
		this.id_contribuinte = id_contribuinte;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
