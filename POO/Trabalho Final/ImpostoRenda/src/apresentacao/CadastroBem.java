package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dados.Contribuinte;
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

public class CadastroBem extends JFrame {
	private JComboBox comboBoxContri;
	private JPanel contentPane;
	private JTextField textFieldNomeBem;
	private JTextField textFieldTipoBem;
	private JTextField textFieldValorBem;
	private JTextField textFieldIDBem;
	private JTextField textFieldBuscarBem;
	private JTable tableBem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroBem frame = new CadastroBem();
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
	public CadastroBem() {
		setTitle("Cadastro Bem");
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
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 48, 88, 14);
		panel.add(lblNome);
		
		textFieldNomeBem = new JTextField();
		textFieldNomeBem.setBounds(108, 42, 197, 20);
		panel.add(textFieldNomeBem);
		textFieldNomeBem.setColumns(10);
		
		textFieldTipoBem = new JTextField();
		textFieldTipoBem.setBounds(108, 80, 197, 20);
		panel.add(textFieldTipoBem);
		textFieldTipoBem.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 86, 56, 14);
		panel.add(lblTipo);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 124, 56, 14);
		panel.add(lblValor);
		
		textFieldValorBem = new JTextField();
		textFieldValorBem.setBounds(108, 118, 197, 20);
		panel.add(textFieldValorBem);
		textFieldValorBem.setColumns(10);
		
		JLabel lblContribuinte = new JLabel("Contribuinte:");
		lblContribuinte.setBounds(10, 162, 88, 14);
		panel.add(lblContribuinte);
		
		comboBoxContri = new JComboBox();
		comboBoxContri.setBounds(108, 156, 197, 20);
		panel.add(comboBoxContri);
		
		JButton btnCadastrarBem = new JButton("Cadastrar");
		btnCadastrarBem.setBounds(10, 187, 96, 23);
		panel.add(btnCadastrarBem);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 15, 56, 14);
		panel.add(lblId);
		
		textFieldIDBem = new JTextField();
		textFieldIDBem.setEditable(false);
		textFieldIDBem.setBounds(108, 11, 63, 20);
		panel.add(textFieldIDBem);
		textFieldIDBem.setColumns(10);
	
		JButton btnExcluirBem = new JButton("Excluir");
		btnExcluirBem.addActionListener(new ActionListener() {
			//Botão Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDBem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Bem");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from bem where id_bem=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDBem.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Bem Excluido com sucesso!");
						
						textFieldIDBem.setText("");
						textFieldNomeBem.setText("");
						textFieldTipoBem.setText("");
						textFieldValorBem.setText("");
						comboBoxContri.setSelectedIndex(0);
						

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
		
		textFieldBuscarBem = new JTextField();
		textFieldBuscarBem.setBounds(109, 38, 159, 20);
		panel_1.add(textFieldBuscarBem);
		textFieldBuscarBem.setColumns(10);
		
		JButton btnBuscarBem = new JButton("Buscar");
		btnBuscarBem.addActionListener(new ActionListener() {
			//Botão Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscarBem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do BEM");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from bem where id_bem=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscarBem.getText()));

						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							textFieldIDBem.setText(rs.getString("id_bem"));
							textFieldNomeBem.setText(rs.getString("nome"));
							textFieldTipoBem.setText(rs.getString("tipo"));
							textFieldValorBem.setText(rs.getString("valor"));
							AtualizaComboBox(comboBoxContri, Integer.parseInt(rs.getString("id_contribuinte")));
						}
						
						textFieldBuscarBem.setText("");
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
		
		tableBem = new JTable();
		tableBem.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID Bem", "Nome", "Tipo", "Valor", "ID Contribuinte"
			}
		));
		scrollPane.setViewportView(tableBem);
		
		JButton btnListarDadosBem = new JButton("Listar Dados");
		btnListarDadosBem.addActionListener(new ActionListener() {
			//Listar Dados na TABELA
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from bem";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableBem.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_bem"), rs.getString("nome"),rs.getString("tipo"), rs.getString("valor"), rs.getString("id_contribuinte")});
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
		
		//BOTÃO INSERIR DADOS
		btnCadastrarBem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldNomeBem.getText().equals("") || textFieldTipoBem.getText().equals("") || textFieldValorBem.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into bem(nome, tipo, valor, id_contribuinte) values (?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Contribuinte c = (Contribuinte) comboBoxContri.getSelectedItem();
						
						stmt.setString(1, textFieldNomeBem.getText());
						stmt.setString(2, textFieldTipoBem.getText());
						stmt.setFloat(3, Float.parseFloat(textFieldValorBem.getText()));
						stmt.setInt(4, c.getId_contribuinte());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Bem Realizado com Sucesso!");
						
						textFieldIDBem.setText("");
						textFieldNomeBem.setText("");
						textFieldTipoBem.setText("");
						textFieldValorBem.setText("");
						comboBoxContri.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		AtualizaComboBox(comboBoxContri,0);
		
		DefaultComboBoxModel modelo = (DefaultComboBoxModel) comboBoxContri.getModel();
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
