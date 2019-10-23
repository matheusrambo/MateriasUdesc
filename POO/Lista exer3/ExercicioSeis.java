import java.util.Scanner;

public class ExercicioSeis {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		System.out.println("Digite o numero de LINHAS da matriz A");
		int m1ordemLinha = in.nextInt();
		System.out.println("Digite o numero de COLUNAS da matriz A");
		int m1ordemColuna = in.nextInt();
		int m1[][] = new int[m1ordemLinha][m1ordemColuna];
		for(int i=0;i<m1ordemLinha;i++) {
			for(int j=0;j<m1ordemColuna;j++) {
				System.out.printf("Digite o valor da matriz A de M1[%d][%d]",i+1,j+1);
				m1[i][j] = in.nextInt();
			}
		}
		System.out.println("Digite o numero de LINHAS da matriz B");
		int m2ordemLinha = in.nextInt();
		System.out.println("Digite o numero de COLUNAS da matriz B");
		int m2ordemColuna = in.nextInt();
		int m2[][] = new int[m2ordemLinha][m2ordemColuna];
		for(int i=0;i<m2ordemLinha;i++) {
			for(int j=0;j<m2ordemColuna;j++) {
				System.out.printf("Digite o valor da matriz B de M2[%d][%d]",i+1,j+1);
				m2[i][j] = in.nextInt();
			}
		}
		if(m1ordemColuna == m2ordemLinha) {
			int result[][] = new int[m1ordemLinha][m2ordemColuna];
			int soma;
			for(int i=0;i<m1ordemLinha;i++) {
				for(int j=0;j<m2ordemColuna;j++) {
					soma = 0;
					for(int x=0;x<m1ordemLinha;x++) {
						soma += m1[i][x] * m2[x][j];
					}
					result[i][j] = soma;
				}
			}
			System.out.println("Resultado:");
			for(int i=0;i<m1ordemLinha;i++) {
				for(int j=0;j<m2ordemColuna;j++) {
					System.out.print(result[i][j] + " ");
				}
				System.out.printf("\n");
			}
		}else {
			System.out.println("Não é possivel multiplicar essa matriz");
		}

		
	}
}