package negocio;

import dados.*;

public class ReservaPassagem {
	private Cidade listaDeCidade[] = new Cidade[50];
	private int quantCidades = 0;
	private Cliente listaDeCliente[] = new Cliente[50];
	private int quantClientes = 0;
	private Aeroporto listaDeAeroporto[] = new Aeroporto[50];
	private int quantAeroporto = 0;
	
	public ReservaPassagem(Cidade[] listaDeCidade, Cliente[] listaDeCliente, Aeroporto[] listaDeAeroporto) {
		super();
		this.listaDeCidade = listaDeCidade;
		this.listaDeCliente = listaDeCliente;
		this.listaDeAeroporto = listaDeAeroporto;
	}
	public void cadastrarCidade(Cidade cidade) {
		if(this.quantCidades < this.listaDeCidade.length) {
			this.listaDeCidade[this.quantCidades] = cidade;
			this.quantCidades++;
		}
	}
	public void cadastrarCliente(Cliente cliente) {
		if(this.quantClientes < this.listaDeCliente.length) {
			this.listaDeCliente[this.quantClientes] = cliente;
			this.quantClientes++;
		}
	}
	public void cadastrarAeroporto(Aeroporto aeroporto) {
		if(this.quantAeroporto < this.listaDeAeroporto.length) {
			this.listaDeAeroporto[this.quantAeroporto] = aeroporto;
			this.quantAeroporto++;
		}
	}
	
	public Cidade[] getListaDeCidade() {
		return listaDeCidade;
	}
	public void setListaDeCidade(Cidade[] listaDeCidade) {
		this.listaDeCidade = listaDeCidade;
	}
	public Cliente[] getListaDeCliente() {
		return listaDeCliente;
	}
	public void setListaDeCliente(Cliente[] listaDeCliente) {
		this.listaDeCliente = listaDeCliente;
	}
	public Aeroporto[] getListaDeAeroporto() {
		return listaDeAeroporto;
	}
	public void setListaDeAeroporto(Aeroporto[] listaDeAeroporto) {
		this.listaDeAeroporto = listaDeAeroporto;
	}
	
	public int getQuantCidades() {
		return quantCidades;
	}
	public void setQuantCidades(int quantCidades) {
		this.quantCidades = quantCidades;
	}
	public int getQuantClientes() {
		return quantClientes;
	}
	public void setQuantClientes(int quantClientes) {
		this.quantClientes = quantClientes;
	}
	public int getQuantAeroporto() {
		return quantAeroporto;
	}
	public void setQuantAeroporto(int quantAeroporto) {
		this.quantAeroporto = quantAeroporto;
	}
	public void reservaIda(Cliente cliente, Reserva reserva) {
		cliente.reservarIda(reserva);
	}
	public void reservaVolta(Cliente cliente, Reserva ida, Reserva volta) {
		cliente.reservarVolta(ida, volta);
	}
	public void reservaTrecho(Cliente cliente, Reserva reserva, Trecho trecho) {
		cliente.reservarTrecho(reserva, trecho);
	}
	public void reservaPoltrona(Cliente cliente, Reserva reserva, Trecho trecho, int poltrona) {
		cliente.reservarPoltrona(reserva, trecho, poltrona);
	}
	public Reserva[] mostrarReservas(int cpfCliente) {
		Reserva re[] = new Reserva[50];
		for(int i=0;i<this.quantClientes;i++) {
			if(listaDeCliente[i].getCpf() == cpfCliente) {
				re = listaDeCliente[i].getReservas();
			}
		}
		return re;
	}
}
