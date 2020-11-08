package dados;

public class Acompanhantes {
	private int id_acompanhante;
	private String cpf;
	private String nome;
	private int idade;
	private int id_hospede;
	
	//Constructors
	public Acompanhantes(int id_acompa) {
		this.id_acompanhante = id_acompa;
	}

	public Acompanhantes(String cpf, String nome, int idade, int id_hos) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.id_hospede = id_hos;
	}

	public Acompanhantes(int id_acompa, String cpf, String nome, int idade, int id_hos) {
		this.id_acompanhante = id_acompa;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.id_hospede = id_hos;
	}

	//Getters and Setters
	public int getId_acompanhante() {
		return id_acompanhante;
	}

	public void setId_acompanhante(int id_acompanhante) {
		this.id_acompanhante = id_acompanhante;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getId_hospede() {
		return id_hospede;
	}

	public void setId_hospede(int id_hospede) {
		this.id_hospede = id_hospede;
	}
}
