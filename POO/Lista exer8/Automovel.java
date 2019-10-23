
public class Automovel extends Veiculo{
	private String combustivel;
	private String potencia;
	private int Nportas;
	public Automovel() {
		super();
	}
	@Override
	public void listarVerificacoes() {
		System.out.println("verificar no automóvel: pneu, suspensão, motor");
	}
	@Override
	public void ajustar() {
		System.out.println("todos os itens do automóvel foram verificados com sucesso");
	}
	@Override
	public void limpar() {
		System.out.println("o automóvel já foi lavado e encerado");
	}
	public void trocarOleo() {
		System.out.println("o óleo foi trocado com sucesso");
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getPotencia() {
		return potencia;
	}
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	public int getNportas() {
		return Nportas;
	}
	public void setNportas(int nportas) {
		Nportas = nportas;
	}
	
}
