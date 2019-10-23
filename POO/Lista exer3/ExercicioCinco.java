import java.util.Scanner;

public class ExercicioCinco {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		float nota1,nota2,nota3,nota4;
		int alunos = 3;
		String[] nome = new String[alunos];
		float[] media = new float[alunos];
		for(int i=0;i<alunos;i++) {
			System.out.println("Digite o nome do aluno "+ (i+1)+ ": ");
			nome[i] = in.next();
			System.out.println("Digite a nota 1");
			nota1 = in.nextInt();
			System.out.println("Digite a nota 2");
			nota2 = in.nextInt();
			System.out.println("Digite a nota 3");
			nota3 = in.nextInt();
			System.out.println("Digite a nota 4");
			nota4 = in.nextInt();
			media[i] = (nota1+nota2+nota3+nota4)/4;
		}
		bubble(nome,media, alunos);
	}
	public static void bubble(String[] nome, float[] media, int alunos) {
		float aux;
		String aux2;
		int i, j;
		for(i = 0; i<alunos; i++){
	        for(j = 0; j<alunos-1; j++){
	            if(media[j] < media[j + 1]){
	                aux = media[j];
	                media[j] = media[j+1];
	                media[j+1] = aux;
	                aux2 = nome[j];
	                nome[j] = nome[j+1];
	                nome[j+1] = aux2;
	            }
	        }
	    }
		for(i=0; i<alunos;i++) {
			System.out.println(i+ " - Nome: "+nome[i]+" média: "+ media[i]);
		}
	}
}
