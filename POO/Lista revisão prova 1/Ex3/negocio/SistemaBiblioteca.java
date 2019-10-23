package negocio;

import dados.*;

public class SistemaBiblioteca {
	private Professor[] professor = new Professor[100];
	private Aluno[] aluno = new Aluno[100];
	private Pessoa[] pessoa = new Pessoa[100];
	private int quantProfessor = 0;
	private int quantAluno = 0;
	private int quantPessoa = 0;
	
	public Professor[] listaProfessor() {
		return professor;
	}
	public Aluno[] listaAlunos() {
		return aluno;
	}
	public Professor[] listaProfessoresSalario(float salario) {
		Professor[] salarioProf = new Professor[100];
		int count = 0;
		for(int i=0;i<quantProfessor;i++) {
			if(professor[i].getSalario() >= salario) {
				salarioProf[count] = professor[i];
				count++;
			}
		}
		return salarioProf;
	}
	public void cadastraProfessores(Professor professor2) {
		if(quantProfessor<=professor.length) {
			pessoa[quantProfessor] = professor2;
			quantProfessor++;
		}
	}
	public void cadastraAluno(Aluno aluno2) {
		if(quantAluno<=aluno.length) {
			aluno[quantAluno] = aluno2;
			quantAluno++;
		}
	}
	public void cadastraPessoa(Pessoa pessoa2) {
		if(quantProfessor<=professor.length) {
			pessoa[quantPessoa] = pessoa2;
			quantPessoa++;
		}
	}
	public int mostrarTempoEmprestimo(Pessoa pessoa){
		return pessoa.getTempoEmprestimo();
	}
	public int getQuantProfessor() {
		return quantProfessor;
	}
	public void setQuantProfessor(int quantProfessor) {
		this.quantProfessor = quantProfessor;
	}
	public int getQuantAluno() {
		return quantAluno;
	}
	public void setQuantAluno(int quantAluno) {
		this.quantAluno = quantAluno;
	}
	public int getQuantPessoa() {
		return quantPessoa;
	}
	public void setQuantPessoa(int quantPessoa) {
		this.quantPessoa = quantPessoa;
	}
	
}
