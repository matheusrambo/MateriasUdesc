package dados;

public class Professor extends Pessoa {
	private String formacao;
	private float salario;
	private String departamento;
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public int mostraTempoEmprestimo() {
		return (int) (super.mostraTempoEmprestimo()*1.70);
	}
	
}
