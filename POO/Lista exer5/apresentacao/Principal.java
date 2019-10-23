package apresentacao;
import negocio.*;

import dados.*;
import java.util.Scanner;

public class Principal {
	private static Scanner in;
	private static ReservaPassagem rp = new ReservaPassagem(new Cidade[50],new Cliente[50],new Aeroporto[50]);
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int op;
		do {
			System.out.println("Menu:");
			System.out.println("1 - Fazer reserva");
			System.out.println("2 - Cadastrar cliente");
			System.out.println("3 - Cadastrar cidade");
			System.out.println("4 - Cadastrar aeroporto");
			System.out.println("5 - Mostrar reservas");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					System.out.println("Escolha o cliente:");
					Cliente[] cli = rp.getListaDeCliente();
					for(int i=0;i<rp.getQuantClientes();i++) {
						System.out.println("Pos: "+i + " - " +cli[i].getNome());
					}
					int opcao = in.nextInt();
					fazerReserva(rp,cli[opcao]);
					break;
				case 2:
					cadastrarCliente(rp);
					break;
				case 3:
					cadastrarCidade(rp);
					break;
				case 4:
					cadastrarAeroporto(rp);
					break;
				case 5:
					mostrarReservas(rp);
					break;
			}
		}while(op != 0);
		
	}
	public static void fazerReserva(ReservaPassagem rp, Cliente cli) {
		System.out.println("Reserva ida:");
		System.out.println("Numero da reserva:");
		int num = in.nextInt();
		System.out.println("Data do voo: (Ex: 20/10/2019)");
		String dateString = in.next();
		
		System.out.println("Hora do voo: (Ex: 20:30)");
		String horaString = in.next();

		
		System.out.println("Preço:");
		float preco = in.nextFloat();
		System.out.println("Classe:");
		String classe = in.next();
		System.out.println("Ida e volta? (false ou true):");
		boolean idaEvolta = in.nextBoolean();
		Reserva reserva1 = new Reserva(num,dateString,horaString,preco,classe,idaEvolta);
		if(idaEvolta) {
			System.out.println("Numero da reserva:");
			int num2 = in.nextInt();
			System.out.println("Data do voo: (Ex: 20/10/2019)");
			String dateString2 = in.next();
			
			System.out.println("Hora do voo: (Ex: 20:30)");
			String horaString2 = in.next();

			
			System.out.println("Preço:");
			float preco2 = in.nextFloat();
			System.out.println("Classe:");
			String classe2 = in.next();
			Reserva reserva2 = new Reserva(num2,dateString2,horaString2,preco2,classe2,false);
			rp.reservaVolta(cli,reserva1,reserva2);
		}else {
			rp.reservaIda(cli,reserva1);
		}
	}
	public static void cadastrarCliente(ReservaPassagem rp) {
		System.out.println("CPF:");
		int cpf = in.nextInt();
		System.out.println("Nome:");
		String nome = in.next();
		System.out.println("Endereço:");
		String endereco = in.next();
		System.out.println("Telefone:");
		int tel = in.nextInt();
		Cliente cli = new Cliente(cpf,nome,endereco,tel);
		rp.cadastrarCliente(cli);
		
	}
	public static void cadastrarCidade(ReservaPassagem rp) {
		System.out.println("Nome:");
		String nome = in.next();
		System.out.println("Estado:");
		String estado = in.next();
		Cidade cidade = new Cidade(nome,estado);
		rp.cadastrarCidade(cidade);
	}
	public static void cadastrarAeroporto(ReservaPassagem rp) {
		System.out.println("Código:");
		String codigo = in.next();
		System.out.println("Nome:");
		String nome = in.next();
		
		Cidade[] cidade = rp.getListaDeCidade();
		for(int i=0;i<rp.getQuantCidades();i++) {
			System.out.println(i +" - "+cidade[i].getNome() + " Estado: "+ cidade[i].getEstado());
		}
		int opcao = in.nextInt();
		
		Aeroporto aero = new Aeroporto(codigo,nome,cidade[opcao]);
		rp.cadastrarAeroporto(aero);
		
	}
	public static void mostrarReservas(ReservaPassagem rp) {
		System.out.println("Digite o CPF do cliente:");
		int cpf = in.nextInt();
		Reserva[] re = rp.mostrarReservas(cpf);
		for(int i=0;i<re.length;i++) {
			if(re[i]!=null) {
				System.out.println("Num reserva: "+re[i].getNumReserva()+" Data: "+re[i].getDataVoo()+" Hora: "+re[i].getHoraVoo()+" Preço: "+re[i].getPreco());	
			}
		}
	}
}
