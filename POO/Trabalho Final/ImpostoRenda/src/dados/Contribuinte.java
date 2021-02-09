package dados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import persistencia.DBConnection;


public class Contribuinte {
	private int id_contribuinte;
	private String cpf;
	private String nome;
	private int idade;
	private String endereco;
	private int contaBancaria;
	
	public String toString() {
		return this.nome;
	}

	public Contribuinte(int id) {
		this.id_contribuinte = id;
	}
	public Contribuinte(String cpf, String nome, int idade, String end, int conta) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.endereco = end;
		this.contaBancaria = conta;
	}
	public Contribuinte(int id, String cpf, String nome, int idade, String end, int conta) {
		this.id_contribuinte = id;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.endereco = end;
		this.contaBancaria = conta;
	}
	
	
	public int getId_contribuinte() {
		return id_contribuinte;
	}

	public void setId_contribuinte(int id_contribuinte) {
		this.id_contribuinte = id_contribuinte;
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
	public int getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(int contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	//Função SALVAR
		public void salvarContri() {
			try {
				Connection con = DBConnection.faz_conexao();
				String sql = "insert into contribuintes(cpf, nome, idade, endereco, conta_bancaria) values (?, ?, ?, ?, ?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, cpf);
				stmt.setString(2, nome);
				stmt.setInt(3, idade);
				stmt.setString(4, endereco);
				stmt.setInt(5, contaBancaria);
				
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Cadastro do Contribuinte Realizado com Sucesso!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Função ATUALIZAR
		public void atualizarContri() {
			try {
				Connection con = DBConnection.faz_conexao();
				String sql = "update contribuintes set cpf=?, nome=?, idade=?, endereco=?, conta_bancaria=? where id_contribuinte=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, cpf);
				stmt.setString(2, nome);
				stmt.setInt(3, idade);
				stmt.setString(4, endereco);
				stmt.setInt(5, contaBancaria);
				stmt.setInt(6, id_contribuinte);
				
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Contribuinte Atualizado com sucesso!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
}
