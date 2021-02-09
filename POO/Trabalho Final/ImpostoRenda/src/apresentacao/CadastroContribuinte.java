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

public class CadastroContribuinte extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldcontaBanContri;
	private JTextField textFieldenderecoContri;
	private JTextField textFieldidadeContri;
	private JTextField textFieldNomeContri;
	private JTextField textFieldcpfContri;
	private JTextField textFieldBuscaContri;
	private JTextField textFieldIDContri;
	private JTable tableContri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroContribuinte frame = new CadastroContribuinte();
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
	public CadastroContribuinte() {
		setResizable(false);
		setTitle("Cadastro Contribuinte");
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
		
		JButton btnBuscarContri = new JButton("Buscar");
		btnBuscarContri.setBounds(10, 21, 86, 23);
		panel.add(btnBuscarContri);
		
		textFieldBuscaContri = new JTextField();
		textFieldBuscaContri.setBounds(106, 22, 148, 20);
		panel.add(textFieldBuscaContri);
		textFieldBuscaContri.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 19, 324, 250);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 46, 14);
		panel_1.add(lblId);
		
		textFieldIDContri = new JTextField();
		textFieldIDContri.setEditable(false);
		textFieldIDContri.setBounds(117, 8, 46, 20);
		panel_1.add(textFieldIDContri);
		textFieldIDContri.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(10, 39, 41, 14);
		panel_1.add(lblNewLabel);
		
		textFieldcpfContri = new JTextField();
		textFieldcpfContri.setBounds(117, 36, 188, 20);
		panel_1.add(textFieldcpfContri);
		textFieldcpfContri.setColumns(10);
		
		textFieldNomeContri = new JTextField();
		textFieldNomeContri.setBounds(117, 74, 188, 20);
		panel_1.add(textFieldNomeContri);
		textFieldNomeContri.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 77, 41, 14);
		panel_1.add(lblNome);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(10, 115, 41, 14);
		panel_1.add(lblIdade);
		
		textFieldidadeContri = new JTextField();
		textFieldidadeContri.setBounds(117, 112, 188, 20);
		panel_1.add(textFieldidadeContri);
		textFieldidadeContri.setColumns(10);
		
		textFieldenderecoContri = new JTextField();
		textFieldenderecoContri.setBounds(117, 150, 188, 20);
		panel_1.add(textFieldenderecoContri);
		textFieldenderecoContri.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 153, 57, 14);
		panel_1.add(lblEndereo);
		
		JLabel lblContaBancria = new JLabel("Conta Banc\u00E1ria:");
		lblContaBancria.setBounds(10, 191, 107, 14);
		panel_1.add(lblContaBancria);
		
		textFieldcontaBanContri = new JTextField();
		textFieldcontaBanContri.setBounds(117, 188, 188, 20);
		panel_1.add(textFieldcontaBanContri);
		textFieldcontaBanContri.setColumns(10);
		
		JButton btnCadastrarContri = new JButton("Cadastrar");
		btnCadastrarContri.setBounds(10, 216, 97, 23);
		panel_1.add(btnCadastrarContri);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			//Botão de Atualizar
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDContri.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				}else {
					Contribuinte contri = new Contribuinte(Integer.parseInt(textFieldIDContri.getText()), textFieldcpfContri.getText(), textFieldNomeContri.getText(), Integer.parseInt(textFieldidadeContri.getText()), textFieldenderecoContri.getText(), Integer.parseInt(textFieldcontaBanContri.getText()));
					contri.atualizarContri();
					
					textFieldIDContri.setText("");
					textFieldcpfContri.setText("");
					textFieldNomeContri.setText("");
					textFieldidadeContri.setText("");
					textFieldenderecoContri.setText("");
					textFieldcontaBanContri.setText("");
					textFieldcpfContri.setEditable(true);
					
				}
			}
		});
		btnAtualizar.setBounds(117, 216, 89, 23);
		panel_1.add(btnAtualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 290, 595, 118);
		contentPane.add(scrollPane);
		
		tableContri = new JTable();
		tableContri.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "CPF", "Nome", "Idade", "Endere\u00E7o", "Conta Bancaria"
			}
		));
		scrollPane.setViewportView(tableContri);
		
		JButton btnListar = new JButton("Listar Dados");
		btnListar.addActionListener(new ActionListener() {
			//Botão LISTAR na Tabela
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from contribuintes";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableContri.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_contribuinte"), rs.getString("cpf"),rs.getString("nome"), rs.getString("idade"), rs.getString("endereco"), rs.getString("conta_bancaria")});
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
		btnCadastrarContri.addActionListener(new ActionListener() {
			
			//Botão CADASTRAR
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldcpfContri.getText().equals("") || textFieldNomeContri.getText().equals("") || textFieldidadeContri.getText().equals("") ||textFieldenderecoContri.getText().equals("") || textFieldcontaBanContri.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				} else {
					
					Contribuinte contri = new Contribuinte(textFieldcpfContri.getText(), textFieldNomeContri.getText(), Integer.parseInt(textFieldidadeContri.getText()), textFieldenderecoContri.getText(), Integer.parseInt(textFieldcontaBanContri.getText()));
					contri.salvarContri();
					
					textFieldcpfContri.setText("");
					textFieldNomeContri.setText("");
					textFieldidadeContri.setText("");
					textFieldenderecoContri.setText("");
					textFieldcontaBanContri.setText("");
					
				}
			}
		});
		btnBuscarContri.addActionListener(new ActionListener() {
			
			//Botão de BUSCAR
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldBuscaContri.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from contribuintes where cpf like ?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, "%"+textFieldBuscaContri.getText());
						
						ResultSet rs = stmt.executeQuery();
						textFieldcpfContri.setEditable(false);
						while (rs.next()) {
							textFieldIDContri.setText(rs.getString("id_contribuinte"));
							textFieldcpfContri.setText(rs.getString("cpf"));
							textFieldNomeContri.setText(rs.getString("nome"));
							textFieldidadeContri.setText(rs.getString("idade"));
							textFieldenderecoContri.setText(rs.getString("endereco"));
							textFieldcontaBanContri.setText(rs.getString("conta_bancaria"));
						}
						textFieldBuscaContri.setText("");
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
