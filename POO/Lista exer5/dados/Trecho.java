package dados;

import java.util.Date;

public class Trecho {
	private int numTrecho;
	private int duracao;
	private Date dataPartida;
	private Date dataChegada;
	private Date horaPartida;
	private Date horaChegada;
	private String classeVoo;
	private int numPoltrona;
	private Aeroporto aeroportoOrigem;
	private Aeroporto aeroportoDestino;
	private Trecho proximoTrecho;
	
	public Trecho(int numTrecho, int duracao, Date dataPartida, Date dataChegada, Date horaPartida, Date horaChegada,
			String classeVoo, int numPoltrona) {
		super();
		this.numTrecho = numTrecho;
		this.duracao = duracao;
		this.dataPartida = dataPartida;
		this.dataChegada = dataChegada;
		this.horaPartida = horaPartida;
		this.horaChegada = horaChegada;
		this.classeVoo = classeVoo;
		this.numPoltrona = numPoltrona;
	}

	public int getNumTrecho() {
		return numTrecho;
	}

	public void setNumTrecho(int numTrecho) {
		this.numTrecho = numTrecho;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(Date horaPartida) {
		this.horaPartida = horaPartida;
	}

	public Date getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(Date horaChegada) {
		this.horaChegada = horaChegada;
	}
	
	public Trecho getProximoTrecho() {
		return proximoTrecho;
	}

	public void setProximoTrecho(Trecho proximoTrecho) {
		this.proximoTrecho = proximoTrecho;
	}

	public String getClasseVoo() {
		return classeVoo;
	}

	public void setClasseVoo(String classeVoo) {
		this.classeVoo = classeVoo;
	}

	public int getNumPoltrona() {
		return numPoltrona;
	}

	public void setNumPoltrona(int numPoltrona) {
		this.numPoltrona = numPoltrona;
	}
	
	public Aeroporto getAeroportoOrigem() {
		return aeroportoOrigem;
	}

	public void setAeroportoOrigem(Aeroporto aeroportoOrigem) {
		this.aeroportoOrigem = aeroportoOrigem;
	}

	public Aeroporto getAeroportoDestino() {
		return aeroportoDestino;
	}

	public void setAeroportoDestino(Aeroporto aeroportoDestino) {
		this.aeroportoDestino = aeroportoDestino;
	}

}
