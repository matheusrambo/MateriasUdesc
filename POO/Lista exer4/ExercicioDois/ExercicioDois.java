package ExercicioDois;
import java.util.Scanner;

public class ExercicioDois {
	private static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		//System.out.println("Digite o numero de alunos:");
		//int turma = in.nextInt();
		int op;
		SistemaEmpresa sistemaempresa = null;
		Funcionario f = null;
		double salario = 0;
		do {
			System.out.println("Menu:");
			System.out.println("1 - Cadastrar empresa");
			System.out.println("2 - Cadastrar endereço da empresa");
			System.out.println("3 - Cadastrar funcionario da empresa");
			System.out.println("4 - Cadastrar cargo da empresa");
			System.out.println("5 - Definir cargo do funcionario");
			System.out.println("6 - Mostrar salario liquido do funcionario");
			System.out.println("7 - Mostrar salario bruto do funcionario");
			System.out.println("8 - Mostrar descontos do salario do funcionario");
			System.out.println("9 - Mostrar valor das horas extras do funcionario");
			System.out.println("10 - Mostrar valor dos beneficios do funcionario");
			System.out.println("0 - Sair");
			op = in.nextInt();
			switch(op) {
				case 1:
					System.out.println("Digite o nome da empresa:");
					String nome = in.next();
					Empresa emp = new Empresa(nome,null);
					sistemaempresa = new SistemaEmpresa(emp);
					System.out.println("Empresa cadastrada com sucesso");
					break;
				case 2:
					if(sistemaempresa!=null) {
						Endereco endereco = newEndereco();
						sistemaempresa.cadastrarEnderecoEmpresa(endereco);
						System.out.println("Endereco da empresa atribuido com sucesso");
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 3:
					if(sistemaempresa!=null) {
						Funcionario func = newFuncionario();
						sistemaempresa.cadastrarFuncionario(func);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 4:
					if(sistemaempresa!=null) {
						Cargo cargo = newCargo();
						sistemaempresa.cadastrarCargoEmpresa(cargo);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 5:
					if(sistemaempresa!=null) {
						System.out.println("Escolha o funcionário:");
						f = procurarFuncionario(sistemaempresa);
						System.out.println("Escolha o cargo:");
						Cargo c = procurarCargo(sistemaempresa);
						sistemaempresa.definirCargoFuncionario(f, c);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 6:
					if(sistemaempresa!=null) {
						System.out.println("Escolha o funcionário para mostrar o salario liquido:");
						f = procurarFuncionario(sistemaempresa);
						salario = sistemaempresa.salarioLiquidoFuncionario(f);
						System.out.println("O sálario liquido é "+ salario);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 7:
					if(sistemaempresa!=null) {
						System.out.println("Escolha o funcionário para mostrar o salario bruto:");
						f = procurarFuncionario(sistemaempresa);
						salario = sistemaempresa.salarioBrutoFuncionario(f);
						System.out.println("O sálario liquido é "+ salario);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 8:
					if(sistemaempresa!=null) {
						System.out.println("Escolha o funcionário para mostrar os descontos do salario:");
						f = procurarFuncionario(sistemaempresa);
						double descontos = sistemaempresa.mostrarDescontosFuncionario(f);
						System.out.println("Os descontos do sálario são "+ descontos);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 9:
					if(sistemaempresa!=null) {
						System.out.println("Escolha o funcionário para mostrar o valor das horas extras:");
						f = procurarFuncionario(sistemaempresa);
						double valorhorasextras = sistemaempresa.mostrarHorasExtrasFuncionario(f);
						System.out.println("O valor das horas extras é "+ valorhorasextras);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
				case 10:
					if(sistemaempresa!=null) {
						System.out.println("Escolha o funcionário para mostrar o valor das horas extras:");
						f = procurarFuncionario(sistemaempresa);
						double extrabeneficios = sistemaempresa.mostrarAcrescimosFuncionario(f);
						System.out.println("O valor das horas extras é "+ extrabeneficios);
					}else {
						System.out.println("Crie uma empresa antes");
					}
					break;
			}
		}while(op != 0);
	}
	public static Endereco newEndereco() {
		System.out.println("Digite a rua:");
		String rua = in.next();
		System.out.println("Digite o numero:");
		int numero = in.nextInt();
		System.out.println("Digite o bairro:");
		String bairro = in.next();
		System.out.println("Digite a cidade:");
		String cidade = in.next();
		System.out.println("Digite o estado:");
		String estado = in.next();
		System.out.println("Digite o CEP:");
		int cep = in.nextInt();
		Endereco endereco = new Endereco(rua,numero,bairro,cidade,estado,cep);
		return endereco;
	}
	public static Funcionario newFuncionario() {
		System.out.println("Digite o nome do funcionario:");
		String nome = in.next();
		Endereco endereco = newEndereco();
		System.out.println("Digite o numero de filhos:");
		int nfilhos = in.nextInt();
		Funcionario func = new Funcionario(nome,endereco,null,0,nfilhos);
		return func;
	}
	public static Cargo newCargo() {
		System.out.println("Descrição do cargo:");
		String descricao = in.next();
		System.out.println("Salario base:");
		float salario = in.nextFloat();
		System.out.println("Valor da hora extra:");
		float horaextra = in.nextFloat();
		System.out.println("Valor por filho:");
		float valorporfilho = in.nextFloat();
		Cargo cargo = new Cargo(descricao, salario, horaextra,valorporfilho);
		return cargo;
	}
	public static Funcionario procurarFuncionario(SistemaEmpresa sistemaempresa) {
		Funcionario[] f = sistemaempresa.pegarFuncionarios();
		for(int i=0;i<sistemaempresa.pegarNumFunc();i++) {
			System.out.print("Pos: "+i + " - " +f[i].getNome());
			if(f[i].getCargo() != null) {
				Cargo car = f[i].getCargo();
				System.out.print(" Cargo = " + car.getDescricao());
			}
			System.out.printf("\n");
		}
		int opcao = in.nextInt();
		return f[opcao];
	}
	public static Cargo procurarCargo(SistemaEmpresa sistemaempresa) {
		Cargo[] c = sistemaempresa.pegarCargo();
		for(int i=0;i<sistemaempresa.pegarNumCargo();i++) {
			System.out.println("Pos: "+i + " - " +c[i].getDescricao());
		}
		int opcao = in.nextInt();
		return c[opcao];
	}
}
