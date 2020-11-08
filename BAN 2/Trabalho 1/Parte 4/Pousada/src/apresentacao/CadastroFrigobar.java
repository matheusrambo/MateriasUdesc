package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.Frigobar;
import dados.Hospede;
import persistencia.DBConnection;

import javax.swing.DefaultComboBoxModel;
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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroFrigobar extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldDescricaoFrigobar;
	private JTextField textFieldIDFrigobar;
	private JTextField textFieldBuscarFrigobar;
	private JTable tableFrigobar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFrigobar frame = new CadastroFrigobar();
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
	public CadastroFrigobar() {
		setTitle("Cadastro Frigobar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 11, 320, 226);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 48, 88, 14);
		panel.add(lblDescricao);
		
		textFieldDescricaoFrigobar = new JTextField();
		textFieldDescricaoFrigobar.setBounds(108, 42, 197, 20);
		panel.add(textFieldDescricaoFrigobar);
		textFieldDescricaoFrigobar.setColumns(10);

		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 15, 56, 14);
		panel.add(lblId);
		
		textFieldIDFrigobar = new JTextField();
		textFieldIDFrigobar.setEditable(false);
		textFieldIDFrigobar.setBounds(108, 11, 63, 20);
		panel.add(textFieldIDFrigobar);
		textFieldIDFrigobar.setColumns(10);
		
		JButton btnCadastrarFrigobar = new JButton("Cadastrar");
		btnCadastrarFrigobar.setBounds(10, 187, 96, 23);
		panel.add(btnCadastrarFrigobar);
	
		JButton btnExcluirBem = new JButton("Excluir");
		btnExcluirBem.addActionListener(new ActionListener() {
			//Botao Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDFrigobar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Frigobar");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from frigobar where id_frigobar=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDFrigobar.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Frigobar Excluido com sucesso!");
						
						textFieldIDFrigobar.setText("");
						textFieldDescricaoFrigobar.setText("");
						

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}
			}
		});
		btnExcluirBem.setBounds(118, 187, 89, 23);
		panel.add(btnExcluirBem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informe o ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(340, 11, 278, 89);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textFieldBuscarFrigobar = new JTextField();
		textFieldBuscarFrigobar.setBounds(109, 38, 159, 20);
		panel_1.add(textFieldBuscarFrigobar);
		textFieldBuscarFrigobar.setColumns(10);
		
		JButton btnBuscarBem = new JButton("Buscar");
		btnBuscarBem.addActionListener(new ActionListener() {
			//Botao Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscarFrigobar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Frigobar");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from frigobar where id_frigobar=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscarFrigobar.getText()));

						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							textFieldIDFrigobar.setText(rs.getString("id_frigobar"));
							textFieldDescricaoFrigobar.setText(rs.getString("descricao"));
						}
						
						textFieldBuscarFrigobar.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		btnBuscarBem.setBounds(10, 37, 89, 23);
		panel_1.add(btnBuscarBem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 269, 608, 125);
		contentPane.add(scrollPane);
		
		tableFrigobar = new JTable();
		tableFrigobar.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"ID Frigobar", "Descricao"
			}
		));
		scrollPane.setViewportView(tableFrigobar);
		
		JButton btnListarDadosBem = new JButton("Listar Dados");
		btnListarDadosBem.addActionListener(new ActionListener() {
			//Listar Dados na TABELA
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from frigobar";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableFrigobar.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_frigobar"), rs.getString("descricao")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnListarDadosBem.setBounds(10, 405, 110, 23);
		contentPane.add(btnListarDadosBem);
		
		JButton btnAtualizarDepen = new JButton("Atualizar");
		btnAtualizarDepen.addActionListener(new ActionListener() {
			//Botao Atualizar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldIDFrigobar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Frigobar");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "update frigobar set descricao=? where id_frigobar=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						stmt.setString(1, textFieldDescricaoFrigobar.getText());
						stmt.setInt(2, Integer.parseInt(textFieldIDFrigobar.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Frigobar Atualizado com sucesso!");
						
						textFieldIDFrigobar.setText("");
						textFieldDescricaoFrigobar.setText("");
						textFieldDescricaoFrigobar.setEditable(true);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnAtualizarDepen.setBounds(217, 187, 99, 23);
		panel.add(btnAtualizarDepen);
		
		
		//BOTAO INSERIR DADOS
		btnCadastrarFrigobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldDescricaoFrigobar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into frigobar(descricao) values (?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						stmt.setString(1, textFieldDescricaoFrigobar.getText());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Bem Realizado com Sucesso!");
						
						textFieldIDFrigobar.setText("");
						textFieldDescricaoFrigobar.setText("");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
}
