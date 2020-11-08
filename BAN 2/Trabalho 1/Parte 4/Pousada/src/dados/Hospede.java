package dados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import persistencia.DBConnection;

public class Hospede {
	private int id_hospede;
	private String cpf;
	private String nome;
	private String telefone;
	private int idade;
	private String endereco;
	
	public String toString() {
		return this.nome;
	}
	
	//Contructors
	public Hospede(int id) {
		this.id_hospede = id;
	}

	public Hospede(String cpf, String nome, String telefone, int idade, String endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.endereco = endereco;
	}

	public Hospede(int id_hospede, String cpf, String nome, String telefone, int idade, String endereco) {
		this.id_hospede = id_hospede;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.endereco = endereco;
	}

	//Getters and Setters
	public int getId_hospede() {
		return id_hospede;
	}

	public void setId_hospede(int id_hospede) {
		this.id_hospede = id_hospede;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	
	//Função SALVAR
	public void salvarHospede() {
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "insert into Hospede(id_hospede, cpf, nome, telefone, idade, endereco) values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1,id_hospede);
			stmt.setString(2, cpf);
			stmt.setString(3, nome);
			stmt.setString(4, telefone);
			stmt.setInt(5, idade);
			stmt.setString(6, endereco);
			
			
			stmt.execute();
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null, "Cadastro do Hospede Realizado com Sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Função ATUALIZAR
	public void atualizarHospede() {
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "update Hospede set cpf=?, nome=?, telefone=?, idade=?, endereco=? where id_hospede=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cpf);
			stmt.setString(2, nome);
			stmt.setString(3, telefone);
			stmt.setInt(4, idade);
			stmt.setString(5, endereco);
			stmt.setInt(6, id_hospede);
			
			stmt.execute();
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null, "Hospede Atualizado com sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
