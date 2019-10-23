package apresentação;
import java.util.Scanner;

import dados.*;
import negocio.*;

public class Main {
	private static Scanner in;
	private static SistemaBiblioteca sb = new SistemaBiblioteca();
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int op;

		do {
			System.out.println("Menu:");
			System.out.println("1 - Mostrar professores");
			System.out.println("2 - Mostrar alunos");
			System.out.println("3 - Mostrar professores salário");
			System.out.println("4 - Mostrar tempo emprestimo");
			System.out.println("5 - Cadastrar professore");
			System.out.println("6 - Cadastrar aluno");
			System.out.println("7 - Cadastrar pessoa");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;
				case 6:

					break;
				case 7:

					break;
					
			}
		}while(op != 0);
	}
	public void mostrarProfessores() {
		Professor[] prof = sb.listaProfessor();
		for(int i=0;i<sb.getQuantProfessor();i++) {
			System.out.println(i+" Nome: "+prof[i].getNome()+" Formação: "+prof[i].getFormacao());
		}
	}
	public void mostrarAluno() {
		Aluno[] aluno = sb.listaAlunos();
		for(int i=0;i<sb.getQuantAluno();i++) {
			System.out.println(i+" Nome: "+aluno[i].getNome()+" Matricula: "+aluno[i].getMatricula());
		}
	}
	public void mostrarProfessoresSalario(float salario) {
		Professor[] prof = sb.listaProfessoresSalario(salario);
		for(int i=0;i<prof.length;i++) {
			System.out.println(i+" Nome: "+prof[i].getNome()+" Formação: "+prof[i].getFormacao()+ " Salario: "+prof[i].getSalario());
		}
	}
	public void mostraTempoEmprestimo() {
		System.out.println("");
	}

}