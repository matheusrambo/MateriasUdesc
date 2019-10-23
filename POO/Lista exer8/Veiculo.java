
public abstract class Veiculo {
	private String marca;
	private String modelo;
	private int ano;
	private String cor;
	
	public abstract void listarVerificacoes();
	public abstract void ajustar();
	public abstract void limpar();
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public void trocarOleo() {
		System.out.println("o �leo foi trocado com sucesso");
	}
	
}
