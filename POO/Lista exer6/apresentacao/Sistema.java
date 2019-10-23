package apresentacao;
import logica.*;
import java.util.Scanner;
public class Sistema {
	private static Scanner in;
	private static Revenda revenda = new Revenda(15);
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int op;

		do {
			System.out.println("Menu:");
			System.out.println("1 - Cadastrar veiculo");
			System.out.println("2 - Listar veiculos");
			System.out.println("3 - Vender veiculos");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					cadastrarVeiculos();
					break;
				case 2:
					listarVeiculos();
					break;
				case 3:

					break;
			}
		}while(op != 0);
	}
	public static void cadastrarVeiculos() {
		int tipo = 0;
		System.out.println("1 - Carro");
		System.out.println("2 - Caminhão");
		System.out.println("3 - Caminhonete");
		System.out.println("4 - Moto");
		System.out.println("5 - Onibus");
		System.out.println("Informe o tipo do veículo:");
		tipo = in.nextInt();
		switch (tipo) {
		case 1:
			Carro carro = new Carro();
			System.out.print("Digite a marca do carro:");
			carro.setMarca(in.nextLine());
			in.nextLine();
			System.out.print("Digite o modelo do carro:");
			carro.setModelo(in.nextLine());
			System.out.print("Digite o ano do carro:");
			carro.setAnoFabricacao(in.nextInt());
			System.out.print("Digite a placa:");
			carro.setNumPlaca(in.nextLine());
			in.nextLine();
			System.out.print("Digite a cor:");
			carro.setColor(in.nextLine());
			System.out.print("Digite o chassi:");
			carro.setNumChassi(in.nextLine());
			System.out.print("Digite a quilometragem:");
			carro.setQuilometragem(in.nextFloat());
			System.out.print("Digite o numero de marchas:");
			carro.setNumMarchas(in.nextInt());
			System.out.print("Digite o valor:");
			carro.setValor(in.nextFloat());
			System.out.print("Digite a data de entrada:");
			carro.setDataEntrada(in.nextLine());
			in.nextLine();
			
			System.out.print("Digite o numero de assentos:");
			carro.setNumAssentos(in.nextInt());
			System.out.print("Digite a potencia:");
			carro.setPotencia(in.nextInt());
			System.out.print("Digite o numero de portas:");
			carro.setNumPortas(in.nextInt());
			revenda.cadastraVeiculo(carro);
			break;
		case 2:
			Caminhao caminhao = new Caminhao();
			System.out.print("Digite a marca do caminhao:");
			caminhao.setMarca(in.nextLine());
			in.nextLine();
			System.out.print("Digite o modelo do caminhao:");
			caminhao.setModelo(in.nextLine());
			System.out.print("Digite o ano do caminhao:");
			caminhao.setAnoFabricacao(in.nextInt());
			System.out.print("Digite a placa:");
			caminhao.setNumPlaca(in.nextLine());
			in.nextLine();
			System.out.print("Digite a cor:");
			caminhao.setColor(in.nextLine());
			System.out.print("Digite o chassi:");
			caminhao.setNumChassi(in.nextLine());
			System.out.print("Digite a quilometragem:");
			caminhao.setQuilometragem(in.nextFloat());
			System.out.print("Digite o numero de marchas:");
			caminhao.setNumMarchas(in.nextInt());
			System.out.print("Digite o valor:");
			caminhao.setValor(in.nextFloat());
			System.out.print("Digite a data de entrada:");
			caminhao.setDataEntrada(in.nextLine());
			in.nextLine();
			
			System.out.print("Digite a capacidade maxima:");
			caminhao.setCapacidadeMaxima(in.nextInt());
			System.out.print("Digite a potencia:");
			caminhao.setPotenciaCv(in.nextInt());
			System.out.print("Digite o numero de eixos:");
			caminhao.setNumEixos(in.nextInt());
			System.out.print("Digite o tipo de carroceria:");
			caminhao.setTipoCarroceria(in.nextLine());
			
			revenda.cadastraVeiculo(caminhao);
			break;
		case 3:
			Caminhonete caminhonete = new Caminhonete();
			System.out.print("Digite a marca do caminhonete:");
			caminhonete.setMarca(in.nextLine());
			in.nextLine();
			System.out.print("Digite o modelo do caminhonete:");
			caminhonete.setModelo(in.nextLine());
			System.out.print("Digite o ano do caminhonete:");
			caminhonete.setAnoFabricacao(in.nextInt());
			System.out.print("Digite a placa:");
			caminhonete.setNumPlaca(in.nextLine());
			in.nextLine();
			System.out.print("Digite a cor:");
			caminhonete.setColor(in.nextLine());
			System.out.print("Digite o chassi:");
			caminhonete.setNumChassi(in.nextLine());
			System.out.print("Digite a quilometragem:");
			caminhonete.setQuilometragem(in.nextFloat());
			System.out.print("Digite o numero de marchas:");
			caminhonete.setNumMarchas(in.nextInt());
			System.out.print("Digite o valor:");
			caminhonete.setValor(in.nextFloat());
			System.out.print("Digite a data de entrada:");
			caminhonete.setDataEntrada(in.nextLine());
			in.nextLine();
			
			System.out.print("Digite a capacidade de carga:");
			caminhonete.setCapacidadeCarga(in.nextInt());
			System.out.print("Digite a potencia:");
			caminhonete.setPotenciaCv(in.nextInt());
			System.out.print("Digite o tipo de carroceria:");
			caminhonete.setTipoCarroceria(in.nextLine());
			System.out.print("Possui cabine dupla? (true ou false):");
			caminhonete.setCabinaDupla(in.nextBoolean());
			
			revenda.cadastraVeiculo(caminhonete);
			break;
		case 4:
			Moto moto = new Moto();
			System.out.print("Digite a marca do moto:");
			moto.setMarca(in.nextLine());
			in.nextLine();
			System.out.print("Digite o modelo do moto:");
			moto.setModelo(in.nextLine());
			System.out.print("Digite o ano do moto:");
			moto.setAnoFabricacao(in.nextInt());
			System.out.print("Digite a placa:");
			moto.setNumPlaca(in.nextLine());
			in.nextLine();
			System.out.print("Digite a cor:");
			moto.setColor(in.nextLine());
			System.out.print("Digite o chassi:");
			moto.setNumChassi(in.nextLine());
			System.out.print("Digite a quilometragem:");
			moto.setQuilometragem(in.nextFloat());
			System.out.print("Digite o numero de marchas:");
			moto.setNumMarchas(in.nextInt());
			System.out.print("Digite o valor:");
			moto.setValor(in.nextFloat());
			System.out.print("Digite a data de entrada:");
			moto.setDataEntrada(in.nextLine());
			in.nextLine();
			
			System.out.print("Digite cilindradas da moto:");
			moto.setCilindrada(in.nextInt());
			System.out.print("Possui partida eletrica? (true ou false):");
			moto.setPartidaEletrica(in.nextBoolean());
			
			revenda.cadastraVeiculo(moto);
			break;
		case 5:
			Onibus onibus = new Onibus();
			System.out.print("Digite a marca do onibus:");
			onibus.setMarca(in.nextLine());
			in.nextLine();
			System.out.print("Digite o modelo do onibus:");
			onibus.setModelo(in.nextLine());
			System.out.print("Digite o ano do onibus:");
			onibus.setAnoFabricacao(in.nextInt());
			System.out.print("Digite a placa:");
			onibus.setNumPlaca(in.nextLine());
			in.nextLine();
			System.out.print("Digite a cor:");
			onibus.setColor(in.nextLine());
			System.out.print("Digite o chassi:");
			onibus.setNumChassi(in.nextLine());
			System.out.print("Digite a quilometragem:");
			onibus.setQuilometragem(in.nextFloat());
			System.out.print("Digite o numero de marchas:");
			onibus.setNumMarchas(in.nextInt());
			System.out.print("Digite o valor:");
			onibus.setValor(in.nextFloat());
			System.out.print("Digite a data de entrada:");
			onibus.setDataEntrada(in.nextLine());
			in.nextLine();
			
			System.out.print("Digite o numero de assentos:");
			onibus.setNumAssentos(in.nextInt());
			System.out.print("Digite a potencia:");
			onibus.setPotenciaCv(in.nextInt());
			
			revenda.cadastraVeiculo(onibus);
			break;
		default:
			break;
		}
	}
	public static void listarVeiculos() {
		Veiculo v[] = revenda.getVeiculoNaoVendidos();
		for(int i=0;i<v.length;i++) {
			System.out.println("Modelo: "+ v[i].getModelo()+" Marca: "+v[i].getMarca()+" Preço:"+v[i].getValor());
		}
		
			
	}
	public static void venderVeiculo() {
		Veiculo v[] = revenda.getVeiculoNaoVendidos();
		System.out.println("Escolha o veiculo para marcar como vendido:");
		for(int i=0;i<v.length;i++) {
			System.out.println(i+" - Modelo: "+ v[i].getModelo()+" Marca: "+v[i].getMarca()+" Preço:"+v[i].getValor());
		}
		int op = in.nextInt();
		revenda.vendaVeiculo(op);
	}
}
