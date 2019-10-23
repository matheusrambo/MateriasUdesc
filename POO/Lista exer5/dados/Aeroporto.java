package dados;

public class Aeroporto {
	private String codigo;
	private String nome;
	private Cidade cidade;
	
	public Aeroporto(String codigo, String nome, Cidade cidade) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cidade = cidade;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
}
