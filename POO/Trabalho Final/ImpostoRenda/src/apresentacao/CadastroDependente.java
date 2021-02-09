package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.DBConnection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.Contribuinte;

public class CadastroDependente extends JFrame {
	private JComboBox comboBoxContri;
	private JPanel contentPane;
	private JTextField textFieldendDepend;
	private JTextField textFieldidadeDepend;
	private JTextField textFieldnomeDepend;
	private JTextField textFieldcpfDepend;
	private JTextField textFieldBuscaDepend;
	private JTable tableDepend;
	private JTextField textFieldIDDepend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDependente frame = new CadastroDependente();
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
	public CadastroDependente() {
		setResizable(false);
		setTitle("Cadastro Dependente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insira o CPF", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnListarDados = new JButton("Listar Dados");
		btnListarDados.addActionListener(new ActionListener() {
			//Listar na TABELA
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from dependente";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableDepend.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_dependente"), rs.getString("cpf"),rs.getString("nome"), rs.getString("idade"), rs.getString("endereco"), rs.getString("id_contribuinte")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnListarDados))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnListarDados))
		);
		
		tableDepend = new JTable();
		tableDepend.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"id Dependente", "cpf", "nome", "idade", "endere\u00E7o", "id Contribuinte"
			}
		));
		scrollPane.setViewportView(tableDepend);
		panel_1.setLayout(null);
		
		JButton btnBuscarDepen = new JButton("Buscar");
		btnBuscarDepen.addActionListener(new ActionListener() {
			//Botão Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscaDepend.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from dependente where cpf like ?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, "%"+textFieldBuscaDepend.getText());

						
						ResultSet rs = stmt.executeQuery();
						textFieldcpfDepend.setEditable(false);
						while (rs.next()) {
							textFieldIDDepend.setText(rs.getString("id_dependente"));
							textFieldcpfDepend.setText(rs.getString("cpf"));
							textFieldnomeDepend.setText(rs.getString("nome"));
							textFieldidadeDepend.setText(rs.getString("idade"));
							textFieldendDepend.setText(rs.getString("endereco"));
							AtualizaComboBox(comboBoxContri, Integer.parseInt(rs.getString("id_contribuinte")));
						}
						
						textFieldBuscaDepend.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		btnBuscarDepen.setBounds(10, 27, 89, 23);
		panel_1.add(btnBuscarDepen);
		
		textFieldBuscaDepend = new JTextField();
		textFieldBuscaDepend.setBounds(112, 28, 147, 20);
		panel_1.add(textFieldBuscaDepend);
		textFieldBuscaDepend.setColumns(10);
		panel.setLayout(null);
		
		comboBoxContri = new JComboBox();
		comboBoxContri.setBounds(107, 200, 207, 20);
		panel.add(comboBoxContri);
		
		textFieldendDepend = new JTextField();
		textFieldendDepend.setBounds(107, 162, 207, 20);
		panel.add(textFieldendDepend);
		textFieldendDepend.setColumns(10);
		
		textFieldidadeDepend = new JTextField();
		textFieldidadeDepend.setBounds(107, 124, 207, 20);
		panel.add(textFieldidadeDepend);
		textFieldidadeDepend.setColumns(10);
		
		textFieldnomeDepend = new JTextField();
		textFieldnomeDepend.setBounds(107, 86, 207, 20);
		panel.add(textFieldnomeDepend);
		textFieldnomeDepend.setColumns(10);
		
		textFieldcpfDepend = new JTextField();
		textFieldcpfDepend.setBounds(107, 48, 207, 20);
		panel.add(textFieldcpfDepend);
		textFieldcpfDepend.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(10, 51, 54, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 89, 54, 14);
		panel.add(lblNome);
		
		JLabel lblNewLabel_1 = new JLabel("Idade:");
		lblNewLabel_1.setBounds(10, 127, 54, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 165, 72, 14);
		panel.add(lblEndereo);
		
		JLabel lblContribuinte = new JLabel("Contribuinte");
		lblContribuinte.setBounds(10, 203, 87, 14);
		panel.add(lblContribuinte);
		
		JButton btnCadastrarDepend = new JButton("Cadastrar");
		btnCadastrarDepend.setBounds(10, 231, 97, 23);
		panel.add(btnCadastrarDepend);
		
		JButton btnAtualizarDepen = new JButton("Atualizar");
		btnAtualizarDepen.addActionListener(new ActionListener() {
			//Botao Atualizar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldIDDepend.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "update dependente set cpf=?, nome=?, idade=?, endereco=?, id_contribuinte=? where id_dependente=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Contribuinte c = (Contribuinte) comboBoxContri.getSelectedItem();
						
						stmt.setString(1, textFieldcpfDepend.getText());
						stmt.setString(2, textFieldnomeDepend.getText());
						stmt.setInt(3, Integer.parseInt(textFieldidadeDepend.getText()));
						stmt.setString(4, textFieldendDepend.getText());
						stmt.setInt(5, c.getId_contribuinte());
						stmt.setInt(6, Integer.parseInt(textFieldIDDepend.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Dependente Atualizado com sucesso!");
						
						textFieldIDDepend.setText("");
						textFieldcpfDepend.setText("");
						textFieldnomeDepend.setText("");
						textFieldidadeDepend.setText("");
						textFieldendDepend.setText("");
						comboBoxContri.setSelectedIndex(0);
						textFieldcpfDepend.setEditable(true);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnAtualizarDepen.setBounds(117, 231, 99, 23);
		panel.add(btnAtualizarDepen);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			//Botao Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDDepend.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from dependente where id_dependente=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDDepend.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Dependente Excluido com sucesso!");
						
						textFieldIDDepend.setText("");
						textFieldcpfDepend.setText("");
						textFieldnomeDepend.setText("");
						textFieldidadeDepend.setText("");
						textFieldendDepend.setText("");
						comboBoxContri.setSelectedIndex(0);
						textFieldcpfDepend.setEditable(true);
						

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}
			}
		});
		btnExcluir.setBounds(227, 231, 87, 23);
		panel.add(btnExcluir);
		btnCadastrarDepend.addActionListener(new ActionListener() {
			
			//Botão Cadastrar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldcpfDepend.getText().equals("") || textFieldnomeDepend.getText().equals("") || textFieldidadeDepend.getText().equals("") ||textFieldendDepend.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into dependente(cpf, nome, idade, endereco,id_contribuinte) values (?, ?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Contribuinte c = (Contribuinte) comboBoxContri.getSelectedItem();
						
						stmt.setString(1, textFieldcpfDepend.getText());
						stmt.setString(2, textFieldnomeDepend.getText());
						stmt.setInt(3, Integer.parseInt(textFieldidadeDepend.getText()));
						stmt.setString(4, textFieldendDepend.getText());
						stmt.setInt(5, c.getId_contribuinte());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Dependente Realizado com Sucesso!");
						
						textFieldIDDepend.setText("");
						textFieldcpfDepend.setText("");
						textFieldnomeDepend.setText("");
						textFieldidadeDepend.setText("");
						textFieldendDepend.setText("");
						comboBoxContri.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		AtualizaComboBox(comboBoxContri,0);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 14, 46, 14);
		panel.add(lblId);
		
		textFieldIDDepend = new JTextField();
		textFieldIDDepend.setEditable(false);
		textFieldIDDepend.setBounds(108, 11, 54, 20);
		panel.add(textFieldIDDepend);
		textFieldIDDepend.setColumns(10);
		contentPane.setLayout(gl_contentPane);
	}
	public void AtualizaComboBox(JComboBox<Contribuinte> combobox, int idContri) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select *from contribuintes";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Contribuinte c = new Contribuinte(Integer.parseInt(rs.getString("id_contribuinte")), rs.getString("cpf"), rs.getString("nome"), Integer.parseInt(rs.getString("idade")), rs.getString("endereco"), Integer.parseInt(rs.getString("conta_bancaria")));
				if(idContri != 0 && c.getId_contribuinte()==idContri) {
					index = cont;
				}
				cont++;
				combobox.addItem(c);
			}
			combobox.setSelectedIndex(index);
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
