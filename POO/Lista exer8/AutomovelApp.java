import java.util.Scanner;

public class AutomovelApp {
	private static Veiculo[] veiculos = new Veiculo[50];
	private static int numVeiculos = 0;
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int op;

		do {
			System.out.println("Menu:");
			System.out.println("1 - Cadastrar veiculo");
			System.out.println("2 - Listar veiculos");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					cadastrarVeiculo();
					break;
				case 2:
					listarVeiculo();
					break;

			}
		}while(op != 0);
	}
	public static void cadastrarVeiculo() {
		System.out.println("Tipo de veiculo, 1 - Automovel, 2 - Bicicleta");
		int tipo = in.nextInt();
		if(tipo == 1) {
			Automovel auto = new Automovel();
			System.out.println("Digite a marca:");
			auto.setMarca(in.next());
			System.out.println("Digite o modelo:");
			auto.setModelo(in.next());
			System.out.println("Digite a ano:");
			auto.setAno(in.nextInt());
			System.out.println("Digite a cor:");
			auto.setCor(in.next());
			System.out.println("Digite o tipo de combustivel:");
			auto.setCombustivel(in.next());
			System.out.println("Digite a potencia:");
			auto.setPotencia(in.next());
			System.out.println("Digite o numero de portas:");
			auto.setNportas(in.nextInt());
			veiculos[numVeiculos] = auto;
			numVeiculos++;
		}else if(tipo == 2){
			Bicicleta bike = new Bicicleta();
			System.out.println("Digite a marca:");
			bike.setMarca(in.next());
			System.out.println("Digite o modelo:");
			bike.setModelo(in.next());
			System.out.println("Digite a ano:");
			bike.setAno(in.nextInt());
			System.out.println("Digite a cor:");
			bike.setCor(in.next());
			System.out.println("Digite o numero de marchas:");
			bike.setNmarchas(in.nextInt());
			System.out.println("Digite o tamanho do quadro:");
			bike.setTamQuadro(in.nextInt());
			System.out.println("Digite o tipo do freio:");
			bike.setTipoFreio(in.next());
			veiculos[numVeiculos] = bike;
			numVeiculos++;
		}
	}
	public static void listarVeiculo() {
		System.out.println("Escolha o veiculo:");
		for(int i=0;i<numVeiculos;i++) {
			System.out.println((i+1)+" "+veiculos[i].getMarca()+" "+veiculos[i].getModelo()+" "+veiculos[i].getCor()+" "+veiculos[i].getAno());
		}
		int opc = in.nextInt()-1;
		veiculos[opc].listarVerificacoes();
		veiculos[opc].ajustar();
		veiculos[opc].limpar();
		if(veiculos[opc] instanceof Automovel)
			veiculos[opc].trocarOleo();
	}
}
