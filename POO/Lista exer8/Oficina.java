import java.util.Random;

public class Oficina {

	Random r = new Random();
	public Veiculo proximo() {
	
		Veiculo v;
		int code = r.nextInt();
		if (code%2 == 0)
			v = new Automovel();
		else
			v = new Bicicleta();
		
		return v;
	
	}
	public void manutencao(Veiculo v) {
		v.listarVerificacoes();
		v.ajustar();
		v.limpar();
		if(v instanceof Automovel)
			((Automovel) v).trocarOleo();
	}

}