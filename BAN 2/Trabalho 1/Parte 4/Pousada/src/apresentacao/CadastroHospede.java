package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.*;
import persistencia.DBConnection;

import java.awt.GridLayout;
import java.awt.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CadastroHospede extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTelefoneHospede;
	private JTextField textFieldenderecoHospede;
	private JTextField textFieldidadeHospede;
	private JTextField textFieldNomeHospede;
	private JTextField textFieldcpfHospede;
	private JTextField textFieldBuscaHospede;
	private JTextField textFieldIDHospede;
	private JTable tableHospede;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroHospede frame = new CadastroHospede();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroHospede() {
		setResizable(false);
		setTitle("Cadastro de Hospedes:");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Insira o CPF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(353, 19, 264, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnBuscarHospede = new JButton("Buscar");
		btnBuscarHospede.setBounds(10, 21, 86, 23);
		panel.add(btnBuscarHospede);
		
		textFieldBuscaHospede = new JTextField();
		textFieldBuscaHospede.setBounds(106, 22, 148, 20);
		panel.add(textFieldBuscaHospede);
		textFieldBuscaHospede.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 19, 324, 250);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 46, 14);
		panel_1.add(lblId);
		
		textFieldIDHospede = new JTextField();
		textFieldIDHospede.setEditable(false);
		textFieldIDHospede.setBounds(117, 8, 46, 20);
		panel_1.add(textFieldIDHospede);
		textFieldIDHospede.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(10, 39, 41, 14);
		panel_1.add(lblNewLabel);
		
		textFieldcpfHospede = new JTextField();
		textFieldcpfHospede.setBounds(117, 36, 188, 20);
		panel_1.add(textFieldcpfHospede);
		textFieldcpfHospede.setColumns(10);
		
		textFieldNomeHospede = new JTextField();
		textFieldNomeHospede.setBounds(117, 74, 188, 20);
		panel_1.add(textFieldNomeHospede);
		textFieldNomeHospede.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 77, 41, 14);
		panel_1.add(lblNome);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(10, 115, 41, 14);
		panel_1.add(lblIdade);
		
		textFieldidadeHospede = new JTextField();
		textFieldidadeHospede.setBounds(117, 112, 188, 20);
		panel_1.add(textFieldidadeHospede);
		textFieldidadeHospede.setColumns(10);
		
		textFieldenderecoHospede = new JTextField();
		textFieldenderecoHospede.setBounds(117, 150, 188, 20);
		panel_1.add(textFieldenderecoHospede);
		textFieldenderecoHospede.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 153, 57, 14);
		panel_1.add(lblEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 191, 107, 14);
		panel_1.add(lblTelefone);
		
		textFieldTelefoneHospede = new JTextField();
		textFieldTelefoneHospede.setBounds(117, 188, 188, 20);
		panel_1.add(textFieldTelefoneHospede);
		textFieldTelefoneHospede.setColumns(10);
		
		JButton btnCadastrarHospede = new JButton("Cadastrar");
		btnCadastrarHospede.setBounds(10, 216, 97, 23);
		panel_1.add(btnCadastrarHospede);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			//Botão de Atualizar
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDHospede.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				}else {
					Hospede hosp = new Hospede(Integer.parseInt(textFieldIDHospede.getText()), textFieldcpfHospede.getText(), textFieldNomeHospede.getText(), textFieldTelefoneHospede.getText(), Integer.parseInt(textFieldidadeHospede.getText()), textFieldenderecoHospede.getText());
					hosp.atualizarHospede();
					
					textFieldIDHospede.setText("");
					textFieldcpfHospede.setText("");
					textFieldNomeHospede.setText("");
					textFieldidadeHospede.setText("");
					textFieldenderecoHospede.setText("");
					textFieldTelefoneHospede.setText("");
					textFieldcpfHospede.setEditable(true);
					
				}
			}
		});
		btnAtualizar.setBounds(117, 216, 89, 23);
		panel_1.add(btnAtualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 290, 595, 118);
		contentPane.add(scrollPane);
		
		tableHospede = new JTable();
		tableHospede.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "CPF", "Nome", "Idade", "Endere\u00E7o", "Conta Bancaria"
			}
		));
		scrollPane.setViewportView(tableHospede);
		
		JButton btnListar = new JButton("Listar Dados");
		btnListar.addActionListener(new ActionListener() {
			//Botão LISTAR na Tabela
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from Hospede";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableHospede.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_hospede"), rs.getString("cpf"),rs.getString("nome"), rs.getString("telefone"), rs.getString("idade"), rs.getString("endereco")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnListar.setBounds(10, 419, 130, 23);
		contentPane.add(btnListar);
		btnCadastrarHospede.addActionListener(new ActionListener() {
			
			//Botão CADASTRAR
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldcpfHospede.getText().equals("") || textFieldNomeHospede.getText().equals("") || textFieldTelefoneHospede.getText().equals("") || textFieldidadeHospede.getText().equals("") ||textFieldenderecoHospede.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				} else {
					
					Hospede hosp = new Hospede(0 , textFieldcpfHospede.getText(), textFieldNomeHospede.getText(), textFieldTelefoneHospede.getText(), Integer.parseInt(textFieldidadeHospede.getText()), textFieldenderecoHospede.getText());
					hosp.salvarHospede();
					
					textFieldIDHospede.setText("");
					textFieldcpfHospede.setText("");
					textFieldNomeHospede.setText("");
					textFieldTelefoneHospede.setText("");
					textFieldidadeHospede.setText("");
					textFieldenderecoHospede.setText("");
					
				}
			}
		});
		btnBuscarHospede.addActionListener(new ActionListener() {
			
			//Botão de BUSCAR
			public void actionPerformed(ActionEvent arg0) {
				if(btnBuscarHospede.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from hospede where cpf like ?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, "%"+textFieldBuscaHospede.getText());
						
						ResultSet rs = stmt.executeQuery();
						textFieldcpfHospede.setEditable(false);
						while (rs.next()) {
							textFieldIDHospede.setText(rs.getString("id_hospede"));
							textFieldcpfHospede.setText(rs.getString("cpf"));
							textFieldNomeHospede.setText(rs.getString("nome"));
							textFieldTelefoneHospede.setText(rs.getString("telefone"));
							textFieldidadeHospede.setText(rs.getString("idade"));
							textFieldenderecoHospede.setText(rs.getString("endereco"));
						}
						textFieldBuscaHospede.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
}
