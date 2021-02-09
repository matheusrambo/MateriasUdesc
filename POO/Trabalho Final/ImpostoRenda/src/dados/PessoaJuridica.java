package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import persistencia.DBConnection;

public class PessoaJuridica {
	
	private int id_pj;
	private String cnpj;
	private String nomePJ;
	private String endereco;
	private int numFuncionarios;
	
	public String toString() {
		return this.nomePJ;
	}
	
	//Foi criado 3 construtores para cada função
	//Por exemplo, para EXCLUIR precisamos apenas do ID
	//ja para ATUALIZAR precisamos de todos os campos
	
	public PessoaJuridica(int id) {
		this.id_pj = id;
	}
	public PessoaJuridica(String c, String nome, String end, int num_fun) {
		this.cnpj = c;
		this.nomePJ = nome;
		this.endereco = end;
		this.numFuncionarios = num_fun;
	}
	public PessoaJuridica(int id, String c, String nome, String end, int num_fun) {
		this.id_pj = id;
		this.cnpj = c;
		this.nomePJ = nome;
		this.endereco = end;
		this.numFuncionarios = num_fun;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomePJ() {
		return nomePJ;
	}
	public void setNomePJ(String nomePJ) {
		this.nomePJ = nomePJ;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumFuncionarios() {
		return numFuncionarios;
	}
	public void setNumFuncionarios(int numFuncionarios) {
		this.numFuncionarios = numFuncionarios;
	}
	public int getId_pj() {
		return id_pj;
	}
	public void setId_pj(int id_pj) {
		this.id_pj = id_pj;
	}
	
	//Função SALVAR
	public void salvarPJ() {
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "insert into pessoa_juridica(cnpj, nome_pj, endereco, num_funcionarios) values (?, ?, ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cnpj);
			stmt.setString(2, nomePJ);
			stmt.setString(3, endereco);
			stmt.setInt(4, numFuncionarios);
			
			stmt.execute();
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null, "Cadastro da Pessoa Juridica Realizado com Sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Função ATUALIZAR
	public void atualizarPJ() {
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "update pessoa_juridica set cnpj=?, nome_pj=?, endereco=?, num_funcionarios=? where id_pj=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cnpj);
			stmt.setString(2, nomePJ);
			stmt.setString(3, endereco);
			stmt.setInt(4, numFuncionarios);
			stmt.setInt(5, id_pj);
			
			stmt.execute();
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null, "Pessoa Juidica Atualizada com sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Função EXCLUIR
	public void excluirPJ() throws SQLException{
		if(deletaNotaFiscalEContraCheque(id_pj)) {
			Connection con = DBConnection.faz_conexao();
			String sql = "delete from pessoa_juridica where id_pj=?";
				
			PreparedStatement stmt = con.prepareStatement(sql);
	
			stmt.setInt(1, id_pj);
				
			stmt.execute();
			stmt.close();
			con.close();
				
			JOptionPane.showMessageDialog(null, "Pessoa Juridica Excluida com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não Foi possivel Excluir Pessoa Juridica!");
		}
	}
	public boolean deletaNotaFiscalEContraCheque(int id_pj) throws SQLException{
		Connection con = DBConnection.faz_conexao();
		String sql = "delete from nota_fiscal where id_pessoajuridica=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, id_pj);
		
		stmt.execute();
		stmt.close();
		
		
		String sql2 = "delete from contra_cheque where id_pessoajuridica=?";

		PreparedStatement stmt2 = con.prepareStatement(sql2);
		
		stmt2.setInt(1, id_pj);
		
		stmt2.execute();
		stmt2.close();
		con.close();
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
