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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.Contribuinte;
import dados.PessoaJuridica;
import javax.swing.UIManager;
import java.awt.Color;

public class CadastroNotaFiscal extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldBuscarNF;
	private JTable tableNF;
	private JTextField textFieldNumProtocoloNF;
	private JTextField textFieldCNPJNF;
	private JTextField textFieldValorNF;
	private JTextField textFieldIDNF;
	private JTextField textFieldReceitasTotais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroNotaFiscal frame = new CadastroNotaFiscal();
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
	public CadastroNotaFiscal() {
		setResizable(false);
		setTitle("Cadastro Nota Fiscal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 698, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insira o ID do contribuinte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(366, 11, 289, 63);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			//Botão de Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscarNF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Contribuinte");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from nota_fiscal where id_contribuinte=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscarNF.getText()));

						ResultSet rs = stmt.executeQuery();
						
						DefaultTableModel modelo = (DefaultTableModel) tableNF.getModel();
						modelo.setNumRows(0);
						float soma=0;
						while (rs.next()) {
							modelo.addRow(new Object[]{ rs.getString("id_notafiscal"), rs.getString("descricao"),rs.getString("num_protocolo"), rs.getString("cnpj"), rs.getString("valor"), rs.getString("id_pessoajuridica"), rs.getString("id_contribuinte")});
							soma = soma + Float.parseFloat(rs.getString("valor"));
						}
						
						textFieldReceitasTotais.setText(String.valueOf(soma));

						textFieldBuscarNF.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		btnBuscar.setBounds(10, 24, 89, 23);
		panel_1.add(btnBuscar);
		
		textFieldBuscarNF = new JTextField();
		textFieldBuscarNF.setBounds(109, 25, 170, 20);
		panel_1.add(textFieldBuscarNF);
		textFieldBuscarNF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 323, 672, 169);
		contentPane.add(scrollPane);
		
		tableNF = new JTable();
		tableNF.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID Nota Fiscal", "descri\u00E7\u00E3o", "Num Protocolo", "CNPJ", "Valor", "ID Pessoa Juridica", "ID Contribuinte"
			}
		));
		scrollPane.setViewportView(tableNF);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			//Botão Excluir
			public void actionPerformed(ActionEvent arg0) {
				if (tableNF.getSelectedRowCount() == 1) {
					String idNF = (String) tableNF.getModel().getValueAt(tableNF.getSelectedRow(), 0);
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from nota_fiscal where id_notafiscal=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(idNF));

						JOptionPane.showMessageDialog(null, "Nota Fiscal Excluida!");
						
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
		btnExcluir.setBounds(593, 292, 89, 23);
		contentPane.add(btnExcluir);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 11, 335, 293);
		contentPane.add(panel);
		
		JLabel label = new JLabel("ID:");
		label.setBounds(10, 11, 46, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("N\u00FAmero Protocolo:");
		label_1.setBounds(10, 39, 111, 14);
		panel.add(label_1);
		
		textFieldNumProtocoloNF = new JTextField();
		textFieldNumProtocoloNF.setColumns(10);
		textFieldNumProtocoloNF.setBounds(125, 36, 188, 20);
		panel.add(textFieldNumProtocoloNF);
		
		textFieldCNPJNF = new JTextField();
		textFieldCNPJNF.setColumns(10);
		textFieldCNPJNF.setBounds(125, 64, 188, 20);
		panel.add(textFieldCNPJNF);
		
		JLabel label_2 = new JLabel("CNPJ:");
		label_2.setBounds(10, 67, 60, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Valor:");
		label_3.setBounds(10, 98, 60, 14);
		panel.add(label_3);
		
		textFieldValorNF = new JTextField();
		textFieldValorNF.setColumns(10);
		textFieldValorNF.setBounds(125, 95, 188, 20);
		panel.add(textFieldValorNF);
		
		JLabel label_4 = new JLabel("Descri\u00E7\u00E3o:");
		label_4.setBounds(10, 131, 83, 14);
		panel.add(label_4);
		
		JTextArea textAreaDescNF = new JTextArea();
		textAreaDescNF.setLineWrap(true);
		textAreaDescNF.setBounds(125, 126, 188, 61);
		panel.add(textAreaDescNF);
		
		JLabel label_5 = new JLabel("Pessoa Jur\u00EDdica:");
		label_5.setBounds(10, 201, 111, 14);
		panel.add(label_5);
		
		JComboBox comboBoxPJNF = new JComboBox();
		comboBoxPJNF.setBounds(125, 198, 188, 20);
		panel.add(comboBoxPJNF);
		
		JLabel label_6 = new JLabel("Contribuinte:");
		label_6.setBounds(10, 232, 111, 14);
		panel.add(label_6);
		
		JComboBox comboBoxContriNF = new JComboBox();
		comboBoxContriNF.setBounds(125, 229, 188, 20);
		panel.add(comboBoxContriNF);
		
		JButton button = new JButton("Cadastrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldNumProtocoloNF.getText().equals("") || textFieldCNPJNF.getText().equals("") || textFieldValorNF.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into nota_fiscal(descricao, num_protocolo, cnpj, valor, id_pessoajuridica, id_contribuinte) values (?, ?, ?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Contribuinte c = (Contribuinte) comboBoxContriNF.getSelectedItem();
						PessoaJuridica pj = (PessoaJuridica) comboBoxPJNF.getSelectedItem();
						
						stmt.setString(1, textAreaDescNF.getText());
						stmt.setInt(2, Integer.parseInt(textFieldNumProtocoloNF.getText()));
						stmt.setString(3, textFieldCNPJNF.getText());
						stmt.setFloat(4, Float.parseFloat(textFieldValorNF.getText()));
						stmt.setInt(5, pj.getId_pj());
						stmt.setInt(6, c.getId_contribuinte());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro da Nota Fiscal Realizada com Sucesso!");
						
						textFieldIDNF.setText("");
						textAreaDescNF.setText("");
						textFieldNumProtocoloNF.setText("");
						textFieldCNPJNF.setText("");
						textFieldValorNF.setText("");
						comboBoxContriNF.setSelectedIndex(0);	
						comboBoxPJNF.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		button.setBounds(10, 257, 98, 23);
		panel.add(button);
		
		textFieldIDNF = new JTextField();
		textFieldIDNF.setEditable(false);
		textFieldIDNF.setColumns(10);
		textFieldIDNF.setBounds(125, 8, 86, 20);
		panel.add(textFieldIDNF);
		AtualizaComboBox(comboBoxContriNF,0);
		AtualizaComboBox2(comboBoxPJNF,0);
		
		JLabel lblDespesasTotais = new JLabel("Despesas Totais:");
		lblDespesasTotais.setBounds(366, 96, 99, 14);
		contentPane.add(lblDespesasTotais);
		
		textFieldReceitasTotais = new JTextField();
		textFieldReceitasTotais.setEditable(false);
		textFieldReceitasTotais.setBounds(475, 93, 86, 20);
		contentPane.add(textFieldReceitasTotais);
		textFieldReceitasTotais.setColumns(10);
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
