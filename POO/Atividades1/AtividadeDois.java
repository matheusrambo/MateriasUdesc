import java.util.Scanner;

public class AtividadeDois {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x;
		System.out.println("Digite um numero:");
		x = in.nextInt();
		if(0 <= x && x < 5) {
			System.out.println("Saida: " + x);
		}
		if(5<=x && x<10) {
			System.out.println("Saida: " + (2*x+1));
		}
		if(x >= 10) {
			System.out.println("Saida: " + (x-3));
		}
	}
}
