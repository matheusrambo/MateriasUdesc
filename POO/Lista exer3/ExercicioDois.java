
import java.util.Scanner;
public class ExercicioDois {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int m[][] = new int[5][5];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				System.out.printf("Digite o valor de M[%d][%d]",i+1,j+1);
				m[i][j] = in.nextInt();
			}
		}
		int soma=0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(i>j) {
					soma += m[i][j];
				}
			}
		}
		System.out.println("A soma dos elementos da parte inferior é "+ soma);
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.printf("\n");
		}

		
	}
}
