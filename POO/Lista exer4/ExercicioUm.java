import java.util.*;

public class ExercicioUm {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		System.out.println("Digite o numero de alunos:");
		int turma = in.nextInt();
		Estudante aluno[] = new Estudante[turma];
		for(int i=0; i<turma;i++) {
			System.out.println("Digite o nome do aluno:");
			String nome = in.next();
			float[] notas = new float[4];
			for(int j =0;j<4;j++) {
				System.out.println("Digite a nota "+(j+1)+":");
				notas[j] = in.nextInt();
			}
			aluno[i] = new Estudante(nome, notas);
		}
		for(int i=0; i<turma;i++) {
			System.out.println("Média do aluno "+aluno[i].getNome()+": "+aluno[i].getMedia());
		}
	}
}
