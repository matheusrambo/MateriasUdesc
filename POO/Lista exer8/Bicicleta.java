
public class Bicicleta extends Veiculo{
	private int Nmarchas;
	private int tamQuadro;
	private String tipoFreio;
	
	public Bicicleta() {
		super();
	}
	@Override
	public void listarVerificacoes() {
		System.out.println("verificar na bicicleta: pneu, guidão");
	}
	@Override
	public void ajustar() {
		System.out.println("todos os itens da bicicleta foram verificados com sucesso");
	}
	@Override
	public void limpar() {
		System.out.println("a bicicleta já foi lavada e encerada");
	}
	public int getNmarchas() {
		return Nmarchas;
	}
	public void setNmarchas(int nmarchas) {
		Nmarchas = nmarchas;
	}
	public int getTamQuadro() {
		return tamQuadro;
	}
	public void setTamQuadro(int tamQuadro) {
		this.tamQuadro = tamQuadro;
	}
	public String getTipoFreio() {
		return tipoFreio;
	}
	public void setTipoFreio(String tipoFreio) {
		this.tipoFreio = tipoFreio;
	}
	
}
