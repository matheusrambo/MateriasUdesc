package logica;

public class Veiculo {
	private int anoFabricacao;
	private String modelo;
	private String numPlaca;
	private String color;
	private String numChassi;
	private float quilometragem;
	private String marca;
	private int numMarchas;
	private float valor;
	private String dataEntrada;
	private String dataVenda;
	private boolean vendido;
	
	public Veiculo() {
		vendido = false;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumPlaca() {
		return numPlaca;
	}
	public void setNumPlaca(String numPlaca) {
		this.numPlaca = numPlaca;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getNumChassi() {
		return numChassi;
	}
	public void setNumChassi(String numChassi) {
		this.numChassi = numChassi;
	}
	public float getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(float quilometragem) {
		this.quilometragem = quilometragem;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getNumMarchas() {
		return numMarchas;
	}
	public void setNumMarchas(int numMarchas) {
		this.numMarchas = numMarchas;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	public boolean isVendido() {
		return vendido;
	}
	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
	
	
}
