//import java.util.Random;
import java.util.Scanner;

public class ExercicioUm {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int []vet = new int [50];
		//Random gerador = new Random();
		for(int i=0;i<50;i++) {//gera 50 numeros aleatorios
			//vet[i] = gerador.nextInt(100);
			System.out.printf("Digite o numero %d:",i+1);
			vet[i] = in.nextInt();
		}
		float media=0;
		for(int i=0;i<50;i++) {//soma os numeros
			media += vet[i];
		}
		media = media/50;
		System.out.println("A média é: "+media);
		System.out.println("Números do vetor menores que a média: ");
		for(int i=0;i<50;i++) {
			if(vet[i]<media) {
				System.out.println("pos "+i+": "+ vet[i]);
			}
		}
	}
}
