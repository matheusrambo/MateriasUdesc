package ExercicioDois;

public class SistemaEmpresa {
	private Empresa empresa;
	public SistemaEmpresa(Empresa empresa) {
		super();
		this.empresa = empresa;
	}
	public void cadastrarEmpresa(String nome, Endereco endereco) {
		empresa = new Empresa(nome, endereco);
	}
	public void cadastrarEnderecoEmpresa(Endereco endereco) {
		empresa.setEndereco(endereco);
	}
	public void cadastrarFuncionario(Funcionario funcionario) {
		empresa.setFuncionario(funcionario);
	}
	public void cadastrarCargoEmpresa(Cargo cargo) {
		empresa.setCargo(cargo);
	}
	public void definirCargoFuncionario(Funcionario funcionario, Cargo cargo) {
		funcionario.setCargo(cargo);
	}
	public double salarioLiquidoFuncionario(Funcionario funcionario) {
		return funcionario.salarioLiquido();
	}
	public double salarioBrutoFuncionario(Funcionario funcionario) {
		return funcionario.salarioBruto();
	}
	public double mostrarDescontosFuncionario(Funcionario funcionario) {
		return funcionario.descontos();
	}
	public double mostrarHorasExtrasFuncionario(Funcionario funcionario) {
		Cargo cargo = funcionario.getCargo();
		return cargo.getValorHoraExtra();
	}
	public double mostrarAcrescimosFuncionario(Funcionario funcionario) {
		return funcionario.acrescimos();
	}
	public Funcionario[] pegarFuncionarios() {
		return empresa.getFuncionario();
	}
	public Cargo[] pegarCargo() {
		return empresa.getCargo();
	}
	public int pegarNumFunc() {
		return empresa.getContFunc();
	}
	public int pegarNumCargo() {
		return empresa.getContCargo();
	}
}
