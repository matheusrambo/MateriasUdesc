import java.util.Scanner;

public class ExercicioQuatro {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int m1[][] = new int[3][2];
		int m2[][] = new int[3][2];
		int result[][] = new int[3][2];
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.printf("Digite o valor da matriz A de M1[%d][%d]: ",i+1,j+1);
				m1[i][j] = in.nextInt();
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.printf("Digite o valor da matriz B de M2[%d][%d]: ",i+1,j+1);
				m2[i][j] = in.nextInt();
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				if(i<=j) {
					result[i][j] = m1[i][j];
				}else {
					result[i][j] = m2[i][j];
				}
			}
		}
		System.out.println("Resultado:");
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.printf("\n");
		}


		
	}
}