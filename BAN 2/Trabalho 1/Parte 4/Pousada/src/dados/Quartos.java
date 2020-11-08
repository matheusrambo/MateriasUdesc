package dados;

public class Quartos {
	private int id_quartos;
	private int id_quarto;
	private int id_reserva;
	
	//Constructors
	public Quartos(int id_quartos) {
		this.id_quartos = id_quartos;
	}
	
	public Quartos(int id_quarto, int id_reserva) {
		this.id_quarto = id_quarto;
		this.id_reserva = id_reserva;
	}
	
	public Quartos(int id_quartos, int id_quarto, int id_reserva) {
		this.id_quartos = id_quartos;
		this.id_quarto = id_quarto;
		this.id_reserva = id_reserva;
	}
	
	//Getters and Setters
	public int getId_quartos() {
		return id_quartos;
	}
	public void setId_quartos(int id_quartos) {
		this.id_quartos = id_quartos;
	}
	public int getId_quarto() {
		return id_quarto;
	}
	public void setId_quarto(int id_quarto) {
		this.id_quarto = id_quarto;
	}
	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	
	
}
