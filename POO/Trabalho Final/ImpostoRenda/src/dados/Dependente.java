package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import persistencia.DBConnection;

public class Dependente {
	private String cpf;
	private String nome;
	private int idade;
	private String endereco;
	private int id_dependente;
	private int id_contribuinte;
	
	public Dependente (int id) {
		this.id_dependente = id;
	}
	public Dependente (String cpf, String nome, int idade, String end, int id_contri) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.endereco = end;
		this.id_contribuinte = id_contri;
	}
	public Dependente (int id_depen, String cpf, String nome, int idade, String end, int id_contri) {
		this.id_dependente = id_depen;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.endereco = end;
		this.id_contribuinte = id_contri;
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getId_contribuinte() {
		return id_contribuinte;
	}
	public void setId_contribuinte(int id_contribuinte) {
		this.id_contribuinte = id_contribuinte;
	}
	public int getId_dependente() {
		return id_dependente;
	}
	public void setId_dependente(int id_dependente) {
		this.id_dependente = id_dependente;
	}
}
