import java.util.Scanner;

public class AtividadeSeis {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int input,money,nota50 = 0, nota20 = 0, nota10 = 0, nota5 = 0, nota1 = 0;
		System.out.println("Digite o valor que deseja sacar:");
		input = in.nextInt();
		money = input;
		if(money>=10 && money<=600) {
			while(money>0) {
				if(money>=50) {
					nota50 = (int) money/50;
					money = money - (50*nota50);
				}
				if(money>=20) {
					nota20 = (int) money/20;
					money = money - (20*nota20);
				}
				if(money>=10) {
					nota10 = (int) money/10;
					money = money - (10*nota10);
				}
				if(money>=5) {
					nota5 = (int) money/5;
					money = money - (5*nota5);
				}
				if(money>=1) {
					nota1 = (int) money;
					money = money - nota1;
				}
			}
			System.out.println("Voce recebeu: "+nota50+" notas de 50,"+nota20+" notas de 20, "+nota10+" notas de 10, "+nota5+" notas de 5, "+nota1+" notas de 1.");
		}else {
			System.out.println("O valor minimo para sacar é 10 reais e o maximo 600");
		}
	}
}
