package dados;

public class Cliente {
	private int cpf;
	private String nome;
	private String endereco;
	private int telefone;
	private Reserva[] reservas = new Reserva[50];
	private int quantReservas = 0;
	public Cliente(int cpf, String nome, String endereco, int telefone) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getTelefone() {
		return telefone;
	}
	public Reserva[] getReservas() {
		return reservas;
	}
	public void setReservas(Reserva[] reservas) {
		this.reservas = reservas;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public void reservarIda(Reserva reserva) {
		if(this.quantReservas < this.reservas.length) {
			this.reservas[this.quantReservas] = reserva;
			this.quantReservas++;
		}
	}
	
	public void reservarVolta(Reserva ida, Reserva volta) {
		if(this.quantReservas < this.reservas.length + 1) {
			this.reservas[this.quantReservas] = ida;
			this.quantReservas++;
			this.reservas[this.quantReservas] = volta;
			this.quantReservas++;
		}
	}
	
	public void reservarTrecho(Reserva reserva, Trecho trecho) {
		reserva.reservarTrecho(trecho);
	}
	
	public void reservarPoltrona(Reserva reserva, Trecho trecho, int poltrona) {
		reserva.reservarPoltrona(trecho, poltrona);
	}
}
