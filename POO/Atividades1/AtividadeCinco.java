import java.util.Scanner;

public class AtividadeCinco {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a, fat;
		System.out.println("Digite um número:");
		a = in.nextInt();
		for(fat = 1; a > 1; a--) {
			fat = fat * a;
		}
		System.out.println("O fatorial é "+fat);
	}
}
