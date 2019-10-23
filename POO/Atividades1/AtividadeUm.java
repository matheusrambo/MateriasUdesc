import java.util.Scanner;

public class AtividadeUm {
	public static void main(String[] args) {
		int a;
		System.out.println("----Atividade 1----");
		Scanner in = new Scanner(System.in);
		System.out.println("Digite um numero:");
		a = in.nextInt();
		for(int i=1;i<=10;i++) {
			System.out.println(a + " x " + i + " = " + (a*i));
		}
	}
}
