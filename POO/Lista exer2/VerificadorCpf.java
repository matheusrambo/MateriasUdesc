import java.util.Scanner;

public class VerificadorCpf {
	private static Scanner in;
	public static void main(String[] args) {
		String cpf;
		System.out.println("Digite os 9 números do CPF");
		in = new Scanner(System.in);
		cpf = in.nextLine();
		int i=0;
		int []vetCpf = new int [11];
		int []vetAux = {10,9,8,7,6,5,4,3,2};
		int []vetAux2 = {11,10,9,8,7,6,5,4,3,2};
		int []vetMulti = new int [11];
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		cpf = cpf.replace("X", "");
		if(cpf.length()==9) {
			for(i=0;i<9;i++) {
				vetCpf[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
			}
			for(i=0;i<9;i++) {//multi primeiro digito
				vetMulti[i] = vetCpf[i] * vetAux[i];
			}
			//somatorio do 1 digito
			int soma=0;
			for(i=0;i<9;i++) {
				soma += vetMulti[i];
			}
			int resto = soma % 11;
			int digito1;
			if(resto<2) {
				digito1 = 0;
			}else {
				digito1 = 11 - resto;
			}
			vetCpf[9] = digito1;
			for(i=0;i<10;i++) {//multi segundo digito
				vetMulti[i] = vetCpf[i] * vetAux2[i];
			}
			//somatorio do 2 digito
			soma=0;
			for(i=0;i<10;i++) {
				soma += vetMulti[i];
			}
			resto = soma % 11;
			int digito2;
			if(resto<2) {
				digito2 = 0;
			}else {
				digito2 = 11 - resto;
			}
			vetCpf[10] = digito2;
			System.out.println("Seu CPF fica:");
			for(i=0;i<11;i++) {
				if(i==3 || i==6) {
					System.out.print("."+vetCpf[i]);
				}else {
					if(i==9) {
						System.out.print("-"+vetCpf[i]);
					}else {
						System.out.print(vetCpf[i]);
					}
				}
			}
		}
	}
}
