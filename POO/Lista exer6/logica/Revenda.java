package logica;

public class Revenda {
	
	Veiculo[] veiculos;
	int quantVeiculos = 0;

	public Revenda(int tam) {
		veiculos = new Veiculo[tam];
	}
	
	public void cadastraVeiculo(Veiculo v) {
		veiculos[quantVeiculos] = v;
		quantVeiculos++;
	}
	
	public Veiculo[] getVeiculos() {
		return veiculos;
	}
	
	public Veiculo[] getVeiculoNaoVendidos() {
		Veiculo[] v = new Veiculo[quantVeiculos];
		int quantNaoVendido = 0;
		for (int i = 0; i < quantVeiculos; i++) {
			if (!veiculos[i].isVendido()) {
				v[quantNaoVendido] = veiculos[i];
				quantNaoVendido++;
			}
		}
		return v;
	}
	
	public void vendaVeiculo(int pos) {
		veiculos[pos].setVendido(true);
	}

	public int getQuantVeiculos() {
		return quantVeiculos;
	}

	public void setQuantVeiculos(int quantVeiculos) {
		this.quantVeiculos = quantVeiculos;
	}
	
}
