package dados;

public class Reserva {
	private int id_reserva;
	private int pago; //1 - sim || 2 - não
	private String desconto;
	private String data_entrada;
    private String data_saida;
    private int id_hospede;
	
    //Constructors
    public Reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	public Reserva(int pago, String desconto, int id_hospede, String data_entrada, String data_saida) {
		this.pago = pago;
		this.desconto = desconto;
		this.data_entrada = data_entrada;
		this.data_saida = data_saida;
		this.id_hospede = id_hospede;
	}
	public Reserva(int id_reserva, int pago, String desconto, int id_hospede, String data_entrada, String data_saida) {
		this.id_reserva = id_reserva;
		this.pago = pago;
		this.desconto = desconto;
		this.data_entrada = data_entrada;
		this.data_saida = data_saida;
		this.id_hospede = id_hospede;
	}
	
	//Getters and Setters
	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	public int getPago() {
		return pago;
	}
	public void setPago(int pago) {
		this.pago = pago;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	public String getData_entrada() {
		return data_entrada;
	}
	public void setData_entrada(String data_entrada) {
		this.data_entrada = data_entrada;
	}
	public String getData_saida() {
		return data_saida;
	}
	public void setData_saida(String data_saida) {
		this.data_saida = data_saida;
	}
	public int getId_hospede() {
		return id_hospede;
	}
	public void setId_hospede(int id_hospede) {
		this.id_hospede = id_hospede;
	}
}
