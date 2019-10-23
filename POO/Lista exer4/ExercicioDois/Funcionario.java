package ExercicioDois;

public class Funcionario {
	private String nome;
	private Endereco endereco;
	private Cargo cargo;
	private int horasExtras;
	private int numFilhos;
	public Funcionario(String nome, Endereco endereco, Cargo cargo, int horasExtras, int numFilhos) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cargo = cargo;
		this.horasExtras = horasExtras;
		this.numFilhos = numFilhos;
	}
	public double calcINSS() {
		return (cargo.getSalarioBase()*0.11);
	}
	public double calcRI() {
		float salarioBase = cargo.getSalarioBase();
		if(salarioBase < 1372.81)
			return 0;
		else if(salarioBase>1372.82 && salarioBase<2743.25)
			return (salarioBase*0.15);
		else if(salarioBase > 2743.25)
			return (salarioBase*0.275);
		return -1;
	}
	public double descontos() {
		return calcINSS() + calcRI();
	}
	public double acrescimos() {
		return ((cargo.getValorPorFilho()*this.numFilhos)+(cargo.getValorHoraExtra()*this.horasExtras));
	}
	public double salarioLiquido() {
		return cargo.getSalarioBase() + this.acrescimos() - this.descontos();
	}
	public double salarioBruto() {
		return cargo.getSalarioBase() + this.acrescimos();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public int getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(int horasExtras) {
		this.horasExtras = horasExtras;
	}
	public int getNumFilhos() {
		return numFilhos;
	}
	public void setNumFilhos(int numFilhos) {
		this.numFilhos = numFilhos;
	}
}
