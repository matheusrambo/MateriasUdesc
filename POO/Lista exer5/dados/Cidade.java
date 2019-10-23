package dados;

public class Cidade {
	private String nome;
	private String estado;
	private Aeroporto[] aeroporto = new Aeroporto[50];
	private int contAeroporto;
	
	public Cidade(String nome, String estado) {
		super();
		this.nome = nome;
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setAeroporto(Aeroporto aero) {
		this.aeroporto[this.contAeroporto] = aero;
		this.contAeroporto++;
	}
	public int getContAeroporto() {
		return contAeroporto;
	}	
}
