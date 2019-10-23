import java.util.Scanner;
public class MenuPrincipal {
	private static Scanner in;
	private static Agenda agenda = new Agenda(new Contato[15]);
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int op;

		do {
			System.out.println("Menu:");
			System.out.println("1 - Inserir");
			System.out.println("2 - Remover");
			System.out.println("3 - Consultar");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					inserir();
					break;
				case 2:
					remover();
					break;
				case 3:
					consultar();
					break;
			}
		}while(op != 0);
		
	}
	public static void inserir() {
		System.out.println("Nome: ");
		String nome = in.next();
		System.out.println("Email: ");
		String email = in.next();
		System.out.println("Endereço: ");
		String endereco = in.next();
		System.out.println("Telefone: ");
		int telefone = in.nextInt();
		agenda.insereContato(telefone,nome,email,endereco);
	}
	public static void remover() {
		System.out.println("Telefone: ");
		int telefone2 = in.nextInt();
		agenda.removeContato(telefone2);
	}
	public static void consultar() {
		System.out.println("Telefone: ");
		int telefone3 = in.nextInt();
		Contato cont = agenda.consultaContato(telefone3);
		if(cont!=null) {
			System.out.println("Nome: "+cont.getNome()+" Email: "+cont.getEmail()+" Endereço: "+cont.getEndereco()+" Telefone: "+cont.getTelefone());
		}
	}

}
