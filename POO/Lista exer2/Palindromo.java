import java.util.Scanner;

public class Palindromo {
	private static Scanner in;
	public static void main(String[] args) {
		String pali;
		do {
			System.out.println("Digite um numero");
			in = new Scanner(System.in);
			pali = in.nextLine();
			if(pali.length()!=5) {
				System.out.println("Valor invalido, precisa ter 5 digitos");
			}
		}while(pali.length()!=5);
		if(pali.charAt(0)==pali.charAt(4) && pali.charAt(1)==pali.charAt(3)) {
			System.out.println("É palindromo");
		}else {
			System.out.println("Não é palindromo");
		}
		
	}
}
