import java.io.*;
import java.util.*;
public class Turma {
	static Estudante aluno[];
	static int quantEstudante = 0;
	private static Scanner in;
	private static Scanner ler;
	public static void main(String[] args) throws IOException{
		in = new Scanner(System.in);

		int op;
		do {
			System.out.println("Menu:");
			System.out.println("1 - Cadastrar em um Arquivo Texto");
			System.out.println("2 - Ler de um Arquivo Texto");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					System.out.println("Digite a quantidade de alunos da turma:");
					int n = in.nextInt();
					aluno = new Estudante[n];
					quantEstudante = 0;
					escreverArquivoTexto(n);
					break;
				case 2:
					lerArquivoTexto();
					break;
			}
		}while(op != 0);
	}
	
	
	private static void lerArquivoTexto() {
		ler = new Scanner(System.in);
		System.out.printf("Informe o nome do arquivo texto:\n");
		String nome = ler.nextLine();
		
		System.out.printf("\nAlunos e suas Notas:\n");
		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			
			String linha = lerArq.readLine();
			int quantAluno = new Integer(linha);
			aluno = new Estudante[quantAluno];
			quantEstudante = 0;
			
			while(quantAluno>0) {
				Estudante estud = new Estudante();
				linha = lerArq.readLine();
				System.out.println("Nome: "+linha);
				estud.setNome(linha);
				float[] notas = new float[4];
				for(int i=0;i<4;i++) {
					String nota = lerArq.readLine();
					float constant = new Float(nota);
					estud.setNota(i, constant);
					System.out.println("Nota: "+constant);
				}
				aluno[quantEstudante] = estud;
				quantEstudante++;
				quantAluno--;
			}
			
			arq.close();
			salvaMedia(quantEstudante);
		}catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s\n", e.getMessage());
		}
		System.out.println();
	}
	
	private static void escreverArquivoTexto(int n) throws IOException {
		FileWriter arq = new FileWriter("alunos.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		for(int i=0; i<n;i++) {
			Estudante estud = new Estudante();
			System.out.println("Digite o nome do aluno:");
			String nome = in.next();
			estud.setNome(nome);
			for(int j =0;j<4;j++) {
				System.out.println("Digite a nota "+(j+1)+":");
				float notas = in.nextFloat();
				estud.setNota(j, notas);
			}
			gravarArq.println(estud.getNome()+ " "+estud.getNotas()[0]+ " "+estud.getNotas()[1]+ " "+estud.getNotas()[2]+ " "+estud.getNotas()[3]);

			aluno[quantEstudante] = estud;
			quantEstudante++;
		}
		salvaMedia(n);
		arq.close();
	}
	private static void salvaMedia(int n) throws IOException {
		FileWriter arq2 = new FileWriter("medias.txt");//arquivo de média
		PrintWriter gravarArq2 = new PrintWriter(arq2);
		float media = 0;
		for(int j=0;j<n;j++) {
			gravarArq2.println(aluno[j].getNome()+ " "+ aluno[j].getMedia());
			media += aluno[j].getMedia();
			System.out.println("Média do aluno "+aluno[j].getNome()+": "+aluno[j].getMedia());
		}
		gravarArq2.print("Média da turma:" +(media/n));
		arq2.close();
	}

}

