import java.util.Scanner;

public class AtividadeTres {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int lado1, lado2, lado3;
		System.out.println("Digite lado 1:");
		lado1 = in.nextInt();
		System.out.println("Digite lado 2:");
		lado2 = in.nextInt();
		System.out.println("Digite lado 3:");
		lado3 = in.nextInt();
		if(lado1>lado2+lado3 || lado2>lado1+lado3 || lado3>lado1+lado2 || lado1==0 || lado2==0 || lado3==0) {
			System.out.println("Não é um triângulo");
		}else {
			if(lado1 == lado2 && lado2 == lado3 && lado3 == lado1) {
				System.out.println("Triangulo equilatero");
			}
			if(lado1 != lado2 && lado2 != lado3 && lado3 != lado1) {
				System.out.println("Triangulo escaleno");
			}
			if((lado1 == lado2 && lado2 != lado3 && lado3 != lado1)||(lado1 != lado2 && lado2 == lado3 && lado3 != lado1)||(lado1 != lado2 && lado2 != lado3 && lado3 == lado1)) {
				System.out.println("Triangulo isósceles");
			}
		}
	}
}
