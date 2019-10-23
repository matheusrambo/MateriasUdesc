import java.util.Scanner;

public class Fibonacci {
	private static Scanner in;
	public static void main(String[] args) {
		System.out.println("Digite um numero");
		in = new Scanner(System.in);
		int n = in.nextInt();
		fibonacci(n);
	}
	public static int fibonacci(int n) {
		int i, fib1 = 0, fib2 = 1, soma;  
		System.out.print(fib1 + " " + fib2+ " ");
	    for (i = 2; i < n; i++)           
	    {                                        
			soma = fib1 + fib2;                    
			fib1 = fib2;                           
			fib2 = soma;
			System.out.print(soma + " ");
	    }                                        
	    return fib2;  
	}
}
