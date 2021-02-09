package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.DBConnection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.PessoaJuridica;

public class CadastroPessoaJuridica extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNumFunc;
	private JTextField textFieldEnderecoPJ;
	private JTextField textFieldNomePJ;
	private JTextField textFieldCnpjPJ;
	private JTextField textFieldBuscarPJ;
	private JTable tablePJ;
	private JTextField textFieldIDPJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoaJuridica frame = new CadastroPessoaJuridica();
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
	public CadastroPessoaJuridica() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle("Cadastro de Pessoa Jur\u00EDdica");
		setBounds(100, 100, 646, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(349, 11, 278, 59);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Insira o CNPJ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.activeCaption);
		
		JButton button = new JButton("Buscar");
		
		button.setBounds(10, 21, 86, 23);
		panel.add(button);
		
		textFieldBuscarPJ = new JTextField();
		textFieldBuscarPJ.setColumns(10);
		textFieldBuscarPJ.setBounds(106, 22, 162, 20);
		panel.add(textFieldBuscarPJ);
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 11, 329, 234);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textFieldNumFunc = new JTextField();
		textFieldNumFunc.setBounds(159, 169, 151, 20);
		panel_1.add(textFieldNumFunc);
		textFieldNumFunc.setColumns(10);
		
		JLabel lblNmeroDeFuncionrios = new JLabel("N\u00FAmero de \r\nFuncion\u00E1rios:");
		lblNmeroDeFuncionrios.setBounds(10, 172, 154, 14);
		panel_1.add(lblNmeroDeFuncionrios);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10, 200, 102, 23);
		panel_1.add(btnCadastrar);
		btnCadastrar.setForeground(new Color(0, 0, 0));
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 134, 61, 14);
		panel_1.add(lblEndereo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 96, 49, 14);
		panel_1.add(lblNome);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(10, 58, 49, 14);
		panel_1.add(lblCnpj);
		
		textFieldCnpjPJ = new JTextField();
		textFieldCnpjPJ.setBounds(159, 55, 151, 20);
		panel_1.add(textFieldCnpjPJ);
		textFieldCnpjPJ.setColumns(10);
		
		textFieldNomePJ = new JTextField();
		textFieldNomePJ.setBounds(159, 93, 151, 20);
		panel_1.add(textFieldNomePJ);
		textFieldNomePJ.setColumns(10);
		
		textFieldEnderecoPJ = new JTextField();
		textFieldEnderecoPJ.setBounds(159, 131, 151, 20);
		panel_1.add(textFieldEnderecoPJ);
		textFieldEnderecoPJ.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 27, 46, 14);
		panel_1.add(lblId);
		
		textFieldIDPJ = new JTextField();
		textFieldIDPJ.setEditable(false);
		textFieldIDPJ.setBounds(159, 24, 37, 20);
		panel_1.add(textFieldIDPJ);
		textFieldIDPJ.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 256, 617, 136);
		contentPane.add(scrollPane);
		
		tablePJ = new JTable();
		tablePJ.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "CNPJ", "Nome", "Endere\u00E7o", "N\u00FAmero Funcion\u00E1rios"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tablePJ);
		
		JButton btnListarDados = new JButton("Listar Dados");
		
		//BOTÃO DE LISTAR DADOS NA TABELA
		btnListarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from pessoa_juridica";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tablePJ.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_pj"), rs.getString("cnpj"),rs.getString("nome_pj"), rs.getString("endereco"), rs.getString("num_funcionarios")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		//BOTÃO DE BUSCAR DADOS
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldBuscarPJ.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CNPJ");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from pessoa_juridica where cnpj like ?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, "%"+textFieldBuscarPJ.getText());
						
						ResultSet rs = stmt.executeQuery();
						textFieldCnpjPJ.setEditable(false);
						while (rs.next()) {
							
							textFieldIDPJ.setText(rs.getString("id_pj"));
							textFieldCnpjPJ.setText(rs.getString("cnpj"));
							textFieldNomePJ.setText(rs.getString("nome_pj"));
							textFieldEnderecoPJ.setText(rs.getString("endereco"));
							textFieldNumFunc.setText(rs.getString("num_funcionarios"));
			
						}
						
						rs.close();
						con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnListarDados.setBounds(10, 403, 122, 23);
		contentPane.add(btnListarDados);
		
		//BOTÃO DE INSERIR DADOS
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldCnpjPJ.getText().equals("") || textFieldNomePJ.getText().equals("") || textFieldEnderecoPJ.getText().equals("") || textFieldNumFunc.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				} else {
					
					PessoaJuridica pj = new PessoaJuridica(textFieldCnpjPJ.getText(), textFieldNomePJ.getText(), textFieldEnderecoPJ.getText(), Integer.parseInt(textFieldNumFunc.getText()));
					pj.salvarPJ();
					textFieldCnpjPJ.setText("");
					textFieldNomePJ.setText("");
					textFieldEnderecoPJ.setText("");
					textFieldNumFunc.setText("");
					
				}
			}
		});
		//BOTÃO DE ATUALIZAR
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textFieldIDPJ.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CNPJ");
				}else {
					PessoaJuridica pj = new PessoaJuridica(Integer.parseInt(textFieldIDPJ.getText()),textFieldCnpjPJ.getText(), textFieldNomePJ.getText(), textFieldEnderecoPJ.getText(), Integer.parseInt(textFieldNumFunc.getText()));
					pj.atualizarPJ();
					
					textFieldIDPJ.setText("");
					textFieldCnpjPJ.setText("");
					textFieldNomePJ.setText("");
					textFieldEnderecoPJ.setText("");
					textFieldNumFunc.setText("");
					textFieldCnpjPJ.setEditable(true);
					
				}
			}
		});
		btnAtualizar.setBounds(122, 200, 89, 23);
		panel_1.add(btnAtualizar);
		
		//BOTÃO DE EXCLUIR
		JButton btnExcluirPJ = new JButton("Excluir");
		btnExcluirPJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textFieldIDPJ.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CNPJ");
				}else {
					PessoaJuridica pj = new PessoaJuridica(Integer.parseInt(textFieldIDPJ.getText()));
					try {
						pj.excluirPJ();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					textFieldIDPJ.setText("");
					textFieldCnpjPJ.setText("");
					textFieldNomePJ.setText("");
					textFieldEnderecoPJ.setText("");
					textFieldNumFunc.setText("");
					textFieldCnpjPJ.setEditable(true);
				}
			}
		});
		btnExcluirPJ.setBounds(221, 200, 89, 23);
		panel_1.add(btnExcluirPJ);
	}
}
