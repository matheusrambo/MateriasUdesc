package ExercicioDois;

public class Cargo {
	private String descricao;
	private float salarioBase;
	private float valorHoraExtra;
	private float valorPorFilho;
	public Cargo(String descricao, float salarioBase, float valorHoraExtra, float valorPorFilho) {
		super();
		this.descricao = descricao;
		this.salarioBase = salarioBase;
		this.valorHoraExtra = valorHoraExtra;
		this.valorPorFilho = valorPorFilho;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(float salarioBase) {
		this.salarioBase = salarioBase;
	}
	public float getValorHoraExtra() {
		return valorHoraExtra;
	}
	public void setValorHoraExtra(float valorHoraExtra) {
		this.valorHoraExtra = valorHoraExtra;
	}
	public float getValorPorFilho() {
		return valorPorFilho;
	}
	public void setValorPorFilho(float valorPorFilho) {
		this.valorPorFilho = valorPorFilho;
	}
	
}
