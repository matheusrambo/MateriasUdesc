package logica;

public class Caminhonete extends Veiculo{
	private int capacidadeCarga;
	private String tipoCarroceria;
	private int potenciaCv;
	private boolean cabinaDupla;
	
	public int getCapacidadeCarga() {
		return capacidadeCarga;
	}
	public void setCapacidadeCarga(int capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
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
	public boolean isCabinaDupla() {
		return cabinaDupla;
	}
	public void setCabinaDupla(boolean cabinaDupla) {
		this.cabinaDupla = cabinaDupla;
	}
	
}
