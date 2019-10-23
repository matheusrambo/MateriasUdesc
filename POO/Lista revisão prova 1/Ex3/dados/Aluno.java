package dados;
public class Aluno extends Pessoa{
	private String curso;
	private String fase;
	private int matricula;
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public int mostraTempoEmprestimo() {
		return (int) (super.mostraTempoEmprestimo()*1.40);
	}
}
