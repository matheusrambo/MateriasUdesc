package dados;

public class Reserva {
	private int numReserva;
	private String dataVoo;
	private String horaVoo;
	private float preco;
	private String classeVoo;
	private boolean idaEvolta;
	private Trecho trecho;
	private Cidade origem;
	private Cidade destino;
	
	public Reserva(int numReserva, String dataVoo, String horaVoo, float preco, String classeVoo, boolean idaEvolta) {
		super();
		this.numReserva = numReserva;
		this.dataVoo = dataVoo;
		this.horaVoo = horaVoo;
		this.preco = preco;
		this.classeVoo = classeVoo;
		this.idaEvolta = idaEvolta;
	}
	
	public Cidade getOrigem() {
		return origem;
	}

	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public void setTrecho(Trecho trecho) {
		this.trecho = trecho;
	}

	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}
	public String getDataVoo() {
		return dataVoo;
	}
	public void setDataVoo(String dataVoo) {
		this.dataVoo = dataVoo;
	}
	public String getHoraVoo() {
		return horaVoo;
	}
	public void setHoraVoo(String horaVoo) {
		this.horaVoo = horaVoo;
	}
	public float getPreco() {
		return preco;
	}
	public Trecho getTrecho() {
		return trecho;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getClasseVoo() {
		return classeVoo;
	}
	public void setClasseVoo(String classeVoo) {
		this.classeVoo = classeVoo;
	}
	public boolean isIdaEvolta() {
		return idaEvolta;
	}
	public void setIdaEvolta(boolean idaEvolta) {
		this.idaEvolta = idaEvolta;
	}
	public void reservarTrecho(Trecho trecho1) {
		this.trecho = trecho1;
	} 
	public void reservarPoltrona(Trecho trecho, int poltrona) {
		trecho.setNumPoltrona(poltrona);
	} 
}
