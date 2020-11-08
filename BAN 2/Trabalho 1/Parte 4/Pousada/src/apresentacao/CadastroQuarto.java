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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.Frigobar;
import dados.Hospede;
import dados.Tipo_quarto;

import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

public class CadastroQuarto extends JFrame {
	private JComboBox<Frigobar> comboBoxFrigobar;
	private JComboBox<Tipo_quarto> comboBoxTipoQuarto;
	private JPanel contentPane;
	private JTextField textFieldQuantCamas;
	private JTextField textFieldPrecoQuarto;
	private JTextField textFieldBuscaQuarto;
	private JTable tableQuarto;
	private JTextField textFieldIDQuarto;
	private JTextField textFieldDescricao;
	private JTextField textFieldComodidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroQuarto frame = new CadastroQuarto();
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
	public CadastroQuarto() {
		setResizable(false);
		setTitle("Cadastro Quarto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Insira o ID do Quarto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnListarDados = new JButton("Listar Dados");
		btnListarDados.addActionListener(new ActionListener() {
			//Listar na TABELA
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select * from Quarto";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableQuarto.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_quarto"), rs.getString("quant_camas"),rs.getString("preco"), rs.getString("comodidade"), rs.getString("id_tipo_quarto")});
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
						.addComponent(btnListarDados)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnListarDados))
		);
		
		tableQuarto = new JTable();
		tableQuarto.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id Quarto", "Quant Camas", "Preço", "Comodidades", "Id Tipo Quarto"
			}
		));
		scrollPane.setViewportView(tableQuarto);
		panel_1.setLayout(null);
		
		JButton btnBuscarQuarto = new JButton("Buscar");
		btnBuscarQuarto.addActionListener(new ActionListener() {
			//Botao Buscar
			public void actionPerformed(ActionEvent e) {
				if(btnBuscarQuarto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Quarto");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from quarto where id_quarto=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscaQuarto.getText()));
						
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							textFieldIDQuarto.setText(rs.getString("id_quarto"));
							textFieldQuantCamas.setText(rs.getString("quant_camas"));
							textFieldPrecoQuarto.setText(rs.getString("preco"));
							textFieldComodidade.setText(rs.getString("comodidade"));
							textFieldDescricao.setText(rs.getString("descricao"));
							AtualizaComboBox(comboBoxFrigobar, Integer.parseInt(rs.getString("id_frigobar")));
							AtualizaComboBox2(comboBoxTipoQuarto, Integer.parseInt(rs.getString("id_tipo_quarto")));
						}
						
						textFieldBuscaQuarto.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		btnBuscarQuarto.setBounds(10, 27, 89, 23);
		panel_1.add(btnBuscarQuarto);
		
		textFieldBuscaQuarto = new JTextField();
		textFieldBuscaQuarto.setBounds(112, 28, 147, 20);
		panel_1.add(textFieldBuscaQuarto);
		textFieldBuscaQuarto.setColumns(10);
		panel.setLayout(null);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setColumns(10);
		textFieldDescricao.setBounds(134, 104, 180, 60);
		panel.add(textFieldDescricao);
		
		textFieldComodidade = new JTextField();
		textFieldComodidade.setColumns(10);
		textFieldComodidade.setBounds(134, 178, 180, 72);
		panel.add(textFieldComodidade);
		contentPane.setLayout(gl_contentPane);
		
		comboBoxFrigobar = new JComboBox<Frigobar>();
		comboBoxFrigobar.setBounds(134, 261, 180, 20);
		panel.add(comboBoxFrigobar);

		comboBoxTipoQuarto = new JComboBox<Tipo_quarto>();
		comboBoxTipoQuarto.setBounds(134, 292, 180, 20);
		panel.add(comboBoxTipoQuarto);
		
		textFieldQuantCamas = new JTextField();
		textFieldQuantCamas.setBounds(134, 42, 28, 20);
		panel.add(textFieldQuantCamas);
		textFieldQuantCamas.setColumns(10);
		
		textFieldPrecoQuarto = new JTextField();
		textFieldPrecoQuarto.setBounds(134, 73, 180, 20);
		panel.add(textFieldPrecoQuarto);
		textFieldPrecoQuarto.setColumns(10);
		
		JButton btnCadastrarQuarto = new JButton("Cadastrar");
		btnCadastrarQuarto.setBounds(10, 354, 97, 23);
		panel.add(btnCadastrarQuarto);
		
		JButton btnAtualizarQuarto = new JButton("Atualizar");
		btnAtualizarQuarto.addActionListener(new ActionListener() {
			//Botao Atualizar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldIDQuarto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe ID do Quarto");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "update quarto set quant_camas=?, preco=?, descricao=?, comodidade=?, id_frigobar=?, id_tipo_quarto=? where id_quarto=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Frigobar f = (Frigobar) comboBoxFrigobar.getSelectedItem();
						
						Tipo_quarto tq = (Tipo_quarto) comboBoxTipoQuarto.getSelectedItem();
						
						stmt.setInt(7, 0);
						stmt.setInt(1, Integer.parseInt(textFieldQuantCamas.getText()));
						stmt.setString(2, textFieldPrecoQuarto.getText());
						stmt.setString(3, textFieldDescricao.getText());
						stmt.setString(4, textFieldComodidade.getText());
						stmt.setInt(5, f.getId_frigobar());
						stmt.setInt(6, tq.getId_tipo_quarto());
						
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Quarto Atualizado com sucesso!");
						
						textFieldIDQuarto.setText("");
						textFieldQuantCamas.setText("");
						textFieldPrecoQuarto.setText("");
						textFieldDescricao.setText("");
						textFieldComodidade.setText("");
						comboBoxFrigobar.setSelectedIndex(0);
						comboBoxTipoQuarto.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnAtualizarQuarto.setBounds(118, 354, 99, 23);
		panel.add(btnAtualizarQuarto);
		
		JButton btnExcluirQuarto = new JButton("Excluir");
		btnExcluirQuarto.addActionListener(new ActionListener() {
			//Botao Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDQuarto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID do Quarto");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from quarto where id_quarto=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDQuarto.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Quarto Excluido com sucesso!");
						
						textFieldIDQuarto.setText("");
						textFieldQuantCamas.setText("");
						textFieldPrecoQuarto.setText("");
						textFieldDescricao.setText("");
						textFieldComodidade.setText("");
						comboBoxFrigobar.setSelectedIndex(0);
						comboBoxTipoQuarto.setSelectedIndex(0);
						

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}
			}
		});
		btnExcluirQuarto.setBounds(227, 354, 87, 23);
		panel.add(btnExcluirQuarto);
		btnCadastrarQuarto.addActionListener(new ActionListener() {
			
			//Botao Cadastrar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldQuantCamas.getText().equals("") || textFieldPrecoQuarto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into quarto(id_quarto, quant_camas, preco, descricao, comodidade, id_frigobar, id_tipo_quarto) values (?, ?, ?, ?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Frigobar f = (Frigobar) comboBoxFrigobar.getSelectedItem();
						
						Tipo_quarto tq = (Tipo_quarto) comboBoxTipoQuarto.getSelectedItem();

						stmt.setInt(1, 0);
						stmt.setInt(2, Integer.parseInt(textFieldQuantCamas.getText()));
						stmt.setString(3, textFieldPrecoQuarto.getText());
						stmt.setString(4, textFieldDescricao.getText());
						stmt.setString(5, textFieldComodidade.getText());
						stmt.setInt(6, f.getId_frigobar());
						stmt.setInt(7, tq.getId_tipo_quarto());
						
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Quarto Realizado com Sucesso!");
						
						textFieldIDQuarto.setText("");
						textFieldQuantCamas.setText("");
						textFieldPrecoQuarto.setText("");
						textFieldDescricao.setText("");
						textFieldComodidade.setText("");
						comboBoxFrigobar.setSelectedIndex(0);
						comboBoxTipoQuarto.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		AtualizaComboBox(comboBoxFrigobar,0);
		AtualizaComboBox2(comboBoxTipoQuarto,0);
		
		textFieldIDQuarto = new JTextField();
		textFieldIDQuarto.setEditable(false);
		textFieldIDQuarto.setBounds(134, 11, 54, 20);
		panel.add(textFieldIDQuarto);
		textFieldIDQuarto.setColumns(10);
		
		JLabel lblIdQuarto = new JLabel("ID Quarto:");
		lblIdQuarto.setBounds(10, 14, 69, 14);
		panel.add(lblIdQuarto);
		
		JLabel lblQuantidadeDeCamas = new JLabel("Quantidade de camas:");
		lblQuantidadeDeCamas.setBounds(10, 45, 125, 14);
		panel.add(lblQuantidadeDeCamas);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setBounds(10, 76, 46, 14);
		panel.add(lblPreo);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 104, 79, 14);
		panel.add(lblDescrio);
		
		JLabel lblComodidade = new JLabel("Comodidade:");
		lblComodidade.setBounds(10, 181, 79, 14);
		panel.add(lblComodidade);
		
		JLabel lblFrigobar = new JLabel("Frigobar:");
		lblFrigobar.setBounds(10, 264, 79, 14);
		panel.add(lblFrigobar);
		
		JLabel lblTipoQuarto = new JLabel("Tipo Quarto:");
		lblTipoQuarto.setBounds(10, 295, 97, 14);
		panel.add(lblTipoQuarto);
		
	}
	public void AtualizaComboBox(JComboBox<Frigobar> combobox, int idFrigobar) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select *from frigobar";
			
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
	public void AtualizaComboBox2(JComboBox<Tipo_quarto> combobox2, int idTipoQuarto) {
		combobox2.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select * from tipo_quarto";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tipo_quarto tq = new Tipo_quarto(Integer.parseInt(rs.getString("id_tipo_quarto")), rs.getString("descricao"));
				if(idTipoQuarto != 0 && tq.getId_tipo_quarto()==idTipoQuarto) {
					index = cont;
				}
				cont++;
				combobox2.addItem(tq);
			}
			combobox2.setSelectedIndex(index);
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
