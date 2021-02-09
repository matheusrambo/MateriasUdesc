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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.Contribuinte;
import dados.PessoaJuridica;

public class CadastroContraCheque extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNumProtoContraCheque;
	private JTextField textFieldCNPJContraCheque;
	private JTextField textFieldValorContraCheque;
	private JTextField textFieldIDContraCheque;
	private JTextField textFieldBuscaContraCheque;
	private JTable tableContraCheque;
	private JTextField textFieldReceitasTotais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroContraCheque frame = new CadastroContraCheque();
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
	public CadastroContraCheque() {
		setResizable(false);
		setTitle("Cadastro Contra Cheque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 694, 510);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 16, 335, 293);
		panel.setBackground(SystemColor.activeCaption);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(368, 16, 289, 63);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insira o ID do Contribuinte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		
		JButton buttonBuscar = new JButton("Buscar");
		
		buttonBuscar.setBounds(10, 24, 89, 23);
		panel_1.add(buttonBuscar);
		
		textFieldBuscaContraCheque = new JTextField();
		textFieldBuscaContraCheque.setColumns(10);
		textFieldBuscaContraCheque.setBounds(109, 25, 170, 20);
		panel_1.add(textFieldBuscaContraCheque);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 336, 660, 140);
		
		JLabel lblNewLabel = new JLabel("Receitas Totais:");
		lblNewLabel.setBounds(368, 100, 107, 14);
		
		textFieldReceitasTotais = new JTextField();
		textFieldReceitasTotais.setBounds(475, 97, 86, 20);
		textFieldReceitasTotais.setEditable(false);
		textFieldReceitasTotais.setColumns(10);
		
		JButton button = new JButton("Excluir");
		button.addActionListener(new ActionListener() {
			//Botão Excluir
			public void actionPerformed(ActionEvent arg0) {
				if (tableContraCheque.getSelectedRowCount() == 1) {
					String idNF = (String) tableContraCheque.getModel().getValueAt(tableContraCheque.getSelectedRow(), 0);
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from contra_cheque where id_contracheque=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(idNF));

						JOptionPane.showMessageDialog(null, "Contra Cheque Excluido!");
						
						stmt.execute();
						stmt.close();
						con.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		});
		button.setBounds(591, 299, 84, 23);
		
		tableContraCheque = new JTable();
		tableContraCheque.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID Contra Cheque", "Descri\u00E7\u00E3o", "Num Protocolo", "CNPJ", "Valor", "ID Contribuinte", "ID Pessoa Juridica"
			}
		));
		scrollPane.setViewportView(tableContraCheque);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 46, 14);
		panel.add(lblId);
		
		JLabel label = new JLabel("N\u00FAmero Protocolo:");
		label.setBounds(10, 39, 111, 14);
		panel.add(label);
		
		textFieldNumProtoContraCheque = new JTextField();
		textFieldNumProtoContraCheque.setBounds(125, 36, 188, 20);
		panel.add(textFieldNumProtoContraCheque);
		textFieldNumProtoContraCheque.setColumns(10);
		
		textFieldCNPJContraCheque = new JTextField();
		textFieldCNPJContraCheque.setBounds(125, 64, 188, 20);
		panel.add(textFieldCNPJContraCheque);
		textFieldCNPJContraCheque.setColumns(10);
		
		JLabel label_1 = new JLabel("CNPJ:");
		label_1.setBounds(10, 67, 60, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Valor:");
		label_2.setBounds(10, 98, 60, 14);
		panel.add(label_2);
		
		textFieldValorContraCheque = new JTextField();
		textFieldValorContraCheque.setBounds(125, 95, 188, 20);
		panel.add(textFieldValorContraCheque);
		textFieldValorContraCheque.setColumns(10);
		
		JLabel label_3 = new JLabel("Descri\u00E7\u00E3o:");
		label_3.setBounds(10, 131, 83, 14);
		panel.add(label_3);
		
		JTextArea textAreaDescContraCheque = new JTextArea();
		textAreaDescContraCheque.setBounds(125, 126, 188, 61);
		panel.add(textAreaDescContraCheque);
		textAreaDescContraCheque.setLineWrap(true);
		
		JLabel label_4 = new JLabel("Pessoa Jur\u00EDdica:");
		label_4.setBounds(10, 201, 111, 14);
		panel.add(label_4);
		
		JComboBox comboBoxPjContraCheque = new JComboBox();
		comboBoxPjContraCheque.setBounds(125, 198, 188, 20);
		panel.add(comboBoxPjContraCheque);
		
		JLabel label_5 = new JLabel("Contribuinte:");
		label_5.setBounds(10, 232, 111, 14);
		panel.add(label_5);
		
		JComboBox comboBoxContriContraCheque = new JComboBox();
		comboBoxContriContraCheque.setBounds(125, 229, 188, 20);
		panel.add(comboBoxContriContraCheque);
		
		JButton buttonCadastrarContraCheque = new JButton("Cadastrar");
		buttonCadastrarContraCheque.setBounds(10, 257, 98, 23);
		panel.add(buttonCadastrarContraCheque);
		
		textFieldIDContraCheque = new JTextField();
		textFieldIDContraCheque.setEditable(false);
		textFieldIDContraCheque.setBounds(125, 8, 86, 20);
		panel.add(textFieldIDContraCheque);
		textFieldIDContraCheque.setColumns(10);
		buttonBuscar.addActionListener(new ActionListener() {
			//Botão Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscaContraCheque.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Contribuinte");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from contra_cheque where id_contribuinte=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscaContraCheque.getText()));

						ResultSet rs = stmt.executeQuery();
						
						DefaultTableModel modelo = (DefaultTableModel) tableContraCheque.getModel();
						modelo.setNumRows(0);
						float soma=0;
						while (rs.next()) {
							modelo.addRow(new Object[]{ rs.getString("id_contracheque"), rs.getString("descricao"),rs.getString("num_protocolo"), rs.getString("cnpj"), rs.getString("valor"), rs.getString("id_contribuinte"),rs.getString("id_pessoajuridica")});
							soma = soma + Float.parseFloat(rs.getString("valor"));
						}
						
						textFieldReceitasTotais.setText(String.valueOf(soma));
						
						textFieldBuscaContraCheque.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		buttonCadastrarContraCheque.addActionListener(new ActionListener() {
			//Botão Cadastrar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldNumProtoContraCheque.getText().equals("") || textFieldCNPJContraCheque.getText().equals("") || textFieldValorContraCheque.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into contra_cheque(descricao, num_protocolo, cnpj, valor, id_pessoajuridica, id_contribuinte) values (?, ?, ?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Contribuinte c = (Contribuinte) comboBoxContriContraCheque.getSelectedItem();
						PessoaJuridica pj = (PessoaJuridica) comboBoxPjContraCheque.getSelectedItem();
						
						stmt.setString(1, textAreaDescContraCheque.getText());
						stmt.setInt(2, Integer.parseInt(textFieldNumProtoContraCheque.getText()));
						stmt.setString(3, textFieldCNPJContraCheque.getText());
						stmt.setFloat(4, Float.parseFloat(textFieldValorContraCheque.getText()));
						stmt.setInt(5, pj.getId_pj());
						stmt.setInt(6, c.getId_contribuinte());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Contra Cheque Realizado com Sucesso!");
						
						textFieldIDContraCheque.setText("");
						textAreaDescContraCheque.setText("");
						textFieldNumProtoContraCheque.setText("");
						textFieldCNPJContraCheque.setText("");
						textFieldValorContraCheque.setText("");
						comboBoxContriContraCheque.setSelectedIndex(0);	
						comboBoxPjContraCheque.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		AtualizaComboBox(comboBoxContriContraCheque,0);
		AtualizaComboBox2(comboBoxPjContraCheque,0);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(textFieldReceitasTotais);
		contentPane.add(button);
		contentPane.add(panel);
		contentPane.add(panel_1);
		contentPane.add(scrollPane);
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
	public void AtualizaComboBox2(JComboBox<PessoaJuridica> combobox, int idPJ) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select *from pessoa_juridica";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				PessoaJuridica pj = new PessoaJuridica(Integer.parseInt(rs.getString("id_pj")), rs.getString("cnpj"), rs.getString("nome_pj"),  rs.getString("endereco"), Integer.parseInt(rs.getString("num_funcionarios")));
				if(idPJ != 0 && pj.getId_pj()==idPJ) {
					index = cont;
				}
				cont++;
				combobox.addItem(pj);
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
