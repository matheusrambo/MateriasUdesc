package logica;

public class Caminhao extends Veiculo{
	private int capacidadeMaxima;
	private String tipoCarroceria;
	private int potenciaCv;
	private int numEixos;
	
	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	public void setCapacidadeMaxima(int capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	public String getTipoCarroceria() {
		return tipoCarroceria;
	}
	public void setTipoCarroceria(String tipoCarroceria) {
		this.tipoCarroceria = tipoCarroceria;
	}
	public int getPotenciaCv() {
		return potenciaCv;
	}
	public void setPotenciaCv(int potenciaCv) {
		this.potenciaCv = potenciaCv;
	}
	public int getNumEixos() {
		return numEixos;
	}
	public void setNumEixos(int numEixos) {
		this.numEixos = numEixos;
	}
	
}
