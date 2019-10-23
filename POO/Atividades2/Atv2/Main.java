
public class Main {
	public static void main(String[] args) {
		boolean[] mentira = {false};

		boolean[] meiaverdade = {true,false};

		Or or2 = new Or(); 

		Or or3 = new Or();
		or2.or(meiaverdade);
		if ( or2.bool() ) System.out.println("Meia verdade");
		or3.or(mentira);
		if ( or3.bool() ) System.out.println("Mentira");
		
		int[] numeros = {1,0,1}; // Cria um array contendo 0’s e 1’s

		And operacao = new And(); // Cria uma instância da classe E que guarda o resultado da operação E sobre o array numeros no atributo resultado da classe, como ( 1 E 0 E 1 ) = false, então o atributo resultado recebe o valor false.
		operacao.and(numeros);
		System.out.println(operacao.num()); 
		
	}
}
