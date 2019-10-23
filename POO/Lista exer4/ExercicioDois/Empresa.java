package ExercicioDois;

public class Empresa {
	private String nome;
	private Endereco endereco;
	private Funcionario[] funcionario = new Funcionario[50];
	private int contFunc;
	private Cargo[] cargo = new Cargo[50];
	private int contCargo;
	
	public Empresa(String nome, Endereco endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.contFunc = 0;
		this.contCargo = 0;
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
	public Funcionario[] getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario[this.contFunc] = funcionario;
		this.contFunc++;
	}
	public Cargo[] getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo[this.contCargo] = cargo;
		this.contCargo++;
	}
	public int getContFunc() {
		return this.contFunc;
	}
	public int getContCargo() {
		return this.contCargo;
	}
	
}
