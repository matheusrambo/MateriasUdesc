import java.util.Scanner;

public class ExercicioTres {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int m[][] = new int[3][2];
		int mt[][] = new int[2][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.printf("Digite o valor de M[%d][%d]",i+1,j+1);
				m[i][j] = in.nextInt();
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				mt[j][i] = m[i][j];
			}
		}
		System.out.println("Matriz:");
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.printf("\n");
		}
		System.out.println("Transposta da matriz:");
		for(int i=0;i<2;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(mt[i][j] + " ");
			}
			System.out.printf("\n");
		}

		
	}
}
