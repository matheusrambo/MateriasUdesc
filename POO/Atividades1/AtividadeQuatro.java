import java.util.Scanner;

public class AtividadeQuatro {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		float peso, altura;
		int acimaDoPeso=0;
		do {
			System.out.println("Digite o peso");
			peso = in.nextFloat();
			System.out.println("Digite a altura");
			altura = in.nextFloat();
			if((peso /(altura*altura)) > 25) {
				acimaDoPeso++;
			}
		}while(peso!=-1 || altura!=-1);
		System.out.println(acimaDoPeso + " pessoas acima do peso");
	}
}
