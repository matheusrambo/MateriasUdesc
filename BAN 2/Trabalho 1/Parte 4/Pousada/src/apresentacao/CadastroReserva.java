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

import dados.Hospede;

public class CadastroReserva extends JFrame {
	private JComboBox comboBoxHospede;
	private JPanel contentPane;
	private JTextField textFieldPagoReserva;
	private JTextField textFieldDescontoReserva;
	private JTextField textFieldDataEntradaReserva;
	private JTextField textFieldDataSaidaReserva;
	private JTextField textFieldBuscaReserva;
	private JTable tableReserva;
	private JTextField textFieldIDReserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroReserva frame = new CadastroReserva();
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
	public CadastroReserva() {
		setResizable(false);
		setTitle("Cadastro Reserva");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insira o ID da Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnListarDados = new JButton("Listar Dados");
		btnListarDados.addActionListener(new ActionListener() {
			//Listar na TABELA
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from reserva";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableReserva.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_reserva"), rs.getString("pago"),rs.getString("desconto"), rs.getString("data_entrada"), rs.getString("data_saida"), rs.getString("id_hospede")});
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(btnListarDados))
		);
		
		tableReserva = new JTable();
		tableReserva.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"id Reserva", "pago", "desconto", "data entrada", "data saida", "id hospede"
			}
		));
		scrollPane.setViewportView(tableReserva);
		panel_1.setLayout(null);
		
		JButton btnBuscarReserva = new JButton("Buscar");
		btnBuscarReserva.addActionListener(new ActionListener() {
			//Botao Buscar
			public void actionPerformed(ActionEvent e) {
				if(btnBuscarReserva.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID da Reserva");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from reserva where id_reserva=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, Integer.parseInt(textFieldBuscaReserva.getText()));
						
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							textFieldIDReserva.setText(rs.getString("id_reserva"));
							textFieldPagoReserva.setText(rs.getString("pago"));
							textFieldDescontoReserva.setText(rs.getString("desconto"));
							textFieldDataEntradaReserva.setText(rs.getString("data_entrada"));
							textFieldDataSaidaReserva.setText(rs.getString("data_saida"));
							AtualizaComboBox(comboBoxHospede, Integer.parseInt(rs.getString("id_hospede")));
						}
						
						textFieldBuscaReserva.setText("");
						rs.close();
						con.close();
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		btnBuscarReserva.setBounds(10, 27, 89, 23);
		panel_1.add(btnBuscarReserva);
		
		textFieldBuscaReserva = new JTextField();
		textFieldBuscaReserva.setBounds(112, 28, 147, 20);
		panel_1.add(textFieldBuscaReserva);
		textFieldBuscaReserva.setColumns(10);
		panel.setLayout(null);
		
		comboBoxHospede = new JComboBox();
		comboBoxHospede.setBounds(107, 166, 182, 20);
		panel.add(comboBoxHospede);
		
		textFieldPagoReserva = new JTextField();
		textFieldPagoReserva.setBounds(107, 42, 28, 20);
		panel.add(textFieldPagoReserva);
		textFieldPagoReserva.setColumns(10);
		
		textFieldDescontoReserva = new JTextField();
		textFieldDescontoReserva.setBounds(107, 73, 182, 20);
		panel.add(textFieldDescontoReserva);
		textFieldDescontoReserva.setColumns(10);
		
		textFieldDataEntradaReserva = new JTextField();
		textFieldDataEntradaReserva.setBounds(107, 104, 182, 20);
		panel.add(textFieldDataEntradaReserva);
		textFieldDataEntradaReserva.setColumns(10);
		
		textFieldDataSaidaReserva = new JTextField();
		textFieldDataSaidaReserva.setBounds(107, 135, 182, 20);
		panel.add(textFieldDataSaidaReserva);
		textFieldDataSaidaReserva.setColumns(10);
		
		JButton btnCadastrarDepend = new JButton("Cadastrar");
		btnCadastrarDepend.setBounds(10, 220, 97, 23);
		panel.add(btnCadastrarDepend);
		
		JButton btnAtualizarDepen = new JButton("Atualizar");
		btnAtualizarDepen.addActionListener(new ActionListener() {
			//Botao Atualizar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldIDReserva.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe ID da Reserva");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "update reserva set id_hospede=?, pago=?, desconto=?, data_entrada=?, data_saida=? where id_reserva=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Hospede h = (Hospede) comboBoxHospede.getSelectedItem();
						
						stmt.setInt(6, 0);
						stmt.setInt(2, Integer.parseInt(textFieldPagoReserva.getText()));
						stmt.setString(3, textFieldDescontoReserva.getText());
						stmt.setInt(1, h.getId_hospede());
						stmt.setString(4, textFieldDataEntradaReserva.getText());
						stmt.setString(5, textFieldDataSaidaReserva.getText());
						
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Reserva Atualizada com sucesso!");
						
						textFieldIDReserva.setText("");
						textFieldPagoReserva.setText("");
						textFieldDescontoReserva.setText("");
						textFieldDataEntradaReserva.setText("");
						textFieldDataSaidaReserva.setText("");
						comboBoxHospede.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnAtualizarDepen.setBounds(118, 220, 99, 23);
		panel.add(btnAtualizarDepen);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			//Botao Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDReserva.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID da Reserva");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from reserva where id_reserva=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDReserva.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Reserva Excluida com sucesso!");
						
						textFieldIDReserva.setText("");
						textFieldPagoReserva.setText("");
						textFieldDescontoReserva.setText("");
						textFieldDataEntradaReserva.setText("");
						textFieldDataSaidaReserva.setText("");
						comboBoxHospede.setSelectedIndex(0);
						

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}
			}
		});
		btnExcluir.setBounds(227, 220, 87, 23);
		panel.add(btnExcluir);
		btnCadastrarDepend.addActionListener(new ActionListener() {
			
			//Botao Cadastrar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldPagoReserva.getText().equals("") || textFieldDescontoReserva.getText().equals("") || textFieldDataEntradaReserva.getText().equals("") ||textFieldDataSaidaReserva.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into reserva(id_reserva, pago, desconto, id_hospede, data_entrada, data_saida) values (?, ?, ?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Hospede h = (Hospede) comboBoxHospede.getSelectedItem();

						stmt.setInt(1, 0);
						stmt.setInt(2, Integer.parseInt(textFieldPagoReserva.getText()));
						stmt.setString(3, textFieldDescontoReserva.getText());
						stmt.setInt(4, h.getId_hospede());
						stmt.setString(5, textFieldDataEntradaReserva.getText());
						stmt.setString(6, textFieldDataSaidaReserva.getText());
						
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Dependente Realizado com Sucesso!");
						
						textFieldIDReserva.setText("");
						textFieldPagoReserva.setText("");
						textFieldDescontoReserva.setText("");
						textFieldDataEntradaReserva.setText("");
						textFieldDataSaidaReserva.setText("");
						comboBoxHospede.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		AtualizaComboBox(comboBoxHospede,0);
		
		textFieldIDReserva = new JTextField();
		textFieldIDReserva.setEditable(false);
		textFieldIDReserva.setBounds(108, 11, 54, 20);
		panel.add(textFieldIDReserva);
		textFieldIDReserva.setColumns(10);
		
		JLabel lblIdReserva = new JLabel("ID Reserva:");
		lblIdReserva.setBounds(10, 14, 72, 14);
		panel.add(lblIdReserva);
		
		JLabel lblPago = new JLabel("Pago:");
		lblPago.setBounds(10, 45, 46, 14);
		panel.add(lblPago);
		
		JLabel lblSim = new JLabel("1 - Sim");
		lblSim.setBounds(143, 45, 46, 14);
		panel.add(lblSim);
		
		JLabel lblNo = new JLabel("2 - Não");
		lblNo.setBounds(199, 45, 46, 14);
		panel.add(lblNo);
		
		JLabel lblDesconto = new JLabel("Desconto:");
		lblDesconto.setBounds(10, 76, 72, 14);
		panel.add(lblDesconto);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setBounds(10, 107, 87, 14);
		panel.add(lblDataEntrada);
		
		JLabel lblDataSaida = new JLabel("Data Saida:");
		lblDataSaida.setBounds(10, 138, 72, 14);
		panel.add(lblDataSaida);
		
		JLabel lblHospede = new JLabel("Hospede:");
		lblHospede.setBounds(10, 169, 72, 14);
		panel.add(lblHospede);
		contentPane.setLayout(gl_contentPane);
	}
	public void AtualizaComboBox(JComboBox<Hospede> combobox, int idHospede) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select *from hospede";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Hospede c = new Hospede(Integer.parseInt(rs.getString("id_hospede")), rs.getString("cpf"), rs.getString("nome"), rs.getString("telefone"),  Integer.parseInt(rs.getString("idade")), rs.getString("endereco"));
				if(idHospede != 0 && c.getId_hospede()==idHospede) {
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
