package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.Frigobar;
import dados.Itens;
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

public class CadastroItens extends JFrame {
	private JComboBox<Frigobar> comboBoxFrigobar;
	private JComboBox<Itens> comboBoxItens;
	private JPanel contentPane;
	private JTextField textFieldNomeItem;
	private JTextField textFieldIDItem;
	private JTextField textFieldBuscarItem;
	private JTable tableItem;
	private JTextField textField_1;
	private JTextField textFieldQuant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroItens frame = new CadastroItens();
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
	public CadastroItens() {
		setTitle("Cadastro Itens");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 11, 320, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 48, 88, 14);
		panel.add(lblNome);
		
		textFieldNomeItem = new JTextField();
		textFieldNomeItem.setBounds(108, 42, 197, 20);
		panel.add(textFieldNomeItem);
		textFieldNomeItem.setColumns(10);
		
		JButton btnCadastrarItem = new JButton("Cadastrar");
		btnCadastrarItem.setBounds(10, 91, 96, 23);
		panel.add(btnCadastrarItem);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 15, 56, 14);
		panel.add(lblId);
		
		textFieldIDItem = new JTextField();
		textFieldIDItem.setEditable(false);
		textFieldIDItem.setBounds(108, 11, 63, 20);
		panel.add(textFieldIDItem);
		textFieldIDItem.setColumns(10);
	
		JButton btnExcluirItem = new JButton("Excluir");
		btnExcluirItem.addActionListener(new ActionListener() {
			//Botao Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDItem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Item");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from itens where id_item=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDItem.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Item Excluido com sucesso!");
						
						textFieldIDItem.setText("");
						textFieldNomeItem.setText("");
						comboBoxFrigobar.setSelectedIndex(0);
						

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}
			}
		});
		btnExcluirItem.setBounds(131, 91, 89, 23);
		panel.add(btnExcluirItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informe o ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 154, 320, 89);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textFieldBuscarItem = new JTextField();
		textFieldBuscarItem.setBounds(109, 38, 159, 20);
		panel_1.add(textFieldBuscarItem);
		textFieldBuscarItem.setColumns(10);
		
		JButton btnBuscarBem = new JButton("Buscar");
		btnBuscarBem.addActionListener(new ActionListener() {
			//Botao Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscarItem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Item");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from itens where id_item=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscarItem.getText()));

						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							textFieldIDItem.setText(rs.getString("id_item"));
							textFieldNomeItem.setText(rs.getString("nome"));
						}
						
						textFieldBuscarItem.setText("");
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
		
		tableItem = new JTable();
		tableItem.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID Item", "ID Frigobar", "Quantidade"
			}
		));
		scrollPane.setViewportView(tableItem);
		
		JButton btnListarDadosBem = new JButton("Listar Dados");
		btnListarDadosBem.addActionListener(new ActionListener() {
			//Listar Dados na TABELA
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.faz_conexao();
					
					String sql = "select *from itens_frigobar";
					PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableItem.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_item"), rs.getString("id_frigobar"), rs.getString("quantidade")});
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Associa\u00E7\u00E3o do Item com Frigobar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(340, 11, 278, 232);
		contentPane.add(panel_2);
		
		JButton btnCadastrarItemFrigobar = new JButton("Cadastrar");
		btnCadastrarItemFrigobar.setBounds(10, 179, 96, 23);
		panel_2.add(btnCadastrarItemFrigobar);
		
		JLabel label_2 = new JLabel("ID:");
		label_2.setBounds(10, 23, 56, 14);
		panel_2.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(96, 20, 63, 20);
		panel_2.add(textField_1);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(10, 54, 46, 14);
		panel_2.add(lblItem);
		
		JLabel lblFrigobar_1 = new JLabel("Frigobar");
		lblFrigobar_1.setBounds(10, 85, 56, 14);
		panel_2.add(lblFrigobar_1);
		
		JComboBox<Frigobar> comboBoxFrigobar = new JComboBox<Frigobar>();
		comboBoxFrigobar.setBounds(96, 82, 165, 20);
		panel_2.add(comboBoxFrigobar);
		
		JComboBox<Itens> comboBoxItem = new JComboBox<Itens>();
		comboBoxItem.setBounds(96, 51, 165, 20);
		panel_2.add(comboBoxItem);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(10, 120, 84, 14);
		panel_2.add(lblQuantidade);
		
		textFieldQuant = new JTextField();
		textFieldQuant.setBounds(96, 117, 165, 20);
		panel_2.add(textFieldQuant);
		textFieldQuant.setColumns(10);
		
		//BOTAO INSERIR DADOS
		btnCadastrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldNomeItem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						
						String sql = "insert into itens(id_item, nome) values (?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, 0);
						stmt.setString(2, textFieldNomeItem.getText());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Item Realizado com Sucesso!");
						
						textFieldIDItem.setText("");
						textFieldNomeItem.setText("");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		//Cadastrar Tabela associação
		btnCadastrarItemFrigobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldQuant.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into itens_frigobar(id_itens_frigobar, id_frigobar, id_item, quantidade) values (?, ?, ?, ?)";
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Itens i = (Itens) comboBoxItem.getSelectedItem();
						
						Frigobar f = (Frigobar) comboBoxFrigobar.getSelectedItem();
						
						stmt.setInt(1, 0);
						stmt.setInt(2, f.getId_frigobar());
						stmt.setInt(3, i.getId_item());
						stmt.setInt(4, Integer.parseInt(textFieldQuant.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Item Realizado com Sucesso!");
						
						textFieldIDItem.setText("");
						textFieldNomeItem.setText("");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		AtualizaComboBox(comboBoxFrigobar,0);
		AtualizaComboBox2(comboBoxItem,0);
	}
	public void AtualizaComboBox(JComboBox<Frigobar> combobox, int idFrigobar) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select * from frigobar";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Frigobar f = new Frigobar(Integer.parseInt(rs.getString("id_frigobar")), rs.getString("descricao"));
				if(idFrigobar != 0 && f.getId_frigobar()==idFrigobar) {
					index = cont;
				}
				cont++;
				combobox.addItem(f);
			}
			combobox.setSelectedIndex(index);
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AtualizaComboBox2(JComboBox<Itens> combobox, int idItens) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select * from itens";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Itens i = new Itens(Integer.parseInt(rs.getString("id_item")), rs.getString("nome"));
				if(idItens != 0 && i.getId_item()==idItens) {
					index = cont;
				}
				cont++;
				combobox.addItem(i);
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
