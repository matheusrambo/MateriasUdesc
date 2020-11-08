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

import dados.Acompanhantes;
import dados.Hospede;

public class CadastroAcompanhantes extends JFrame {
	private JComboBox comboBoxHospede;
	private JPanel contentPane;
	private JTextField textFieldidadeAcompanhante;
	private JTextField textFieldnomeAcompanhante;
	private JTextField textFieldcpfAcompanhante;
	private JTextField textFieldBuscaAcompanhante;
	private JTable tableAcompanhante;
	private JTextField textFieldIDAcompanhante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAcompanhantes frame = new CadastroAcompanhantes();
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
	public CadastroAcompanhantes() {
		setResizable(false);
		setTitle("Cadastro Acompanhantes");
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
					String sql = "select *from acompanhantes";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableAcompanhante.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_acompanhante"), rs.getString("cpf"),rs.getString("nome"), rs.getString("idade"), rs.getString("id_hospede")});
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnListarDados))
		);
		
		tableAcompanhante = new JTable();
		tableAcompanhante.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"id Acompanhante", "cpf", "nome", "idade", "id Hospede"
			}
		));
		scrollPane.setViewportView(tableAcompanhante);
		panel_1.setLayout(null);
		
		JButton btnBuscarDepen = new JButton("Buscar");
		btnBuscarDepen.addActionListener(new ActionListener() {
			//Botão Buscar
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscaAcompanhante.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				} else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "select *from acompanhantes where cpf like ?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, "%"+textFieldBuscaAcompanhante.getText());

						
						ResultSet rs = stmt.executeQuery();
						textFieldcpfAcompanhante.setEditable(false);
						while (rs.next()) {
							textFieldIDAcompanhante.setText(rs.getString("id_acompanhante"));
							textFieldcpfAcompanhante.setText(rs.getString("cpf"));
							textFieldnomeAcompanhante.setText(rs.getString("nome"));
							textFieldidadeAcompanhante.setText(rs.getString("idade"));
							AtualizaComboBox(comboBoxHospede, Integer.parseInt(rs.getString("id_hospede")));
						}
						
						textFieldBuscaAcompanhante.setText("");
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
		
		textFieldBuscaAcompanhante = new JTextField();
		textFieldBuscaAcompanhante.setBounds(112, 28, 147, 20);
		panel_1.add(textFieldBuscaAcompanhante);
		textFieldBuscaAcompanhante.setColumns(10);
		panel.setLayout(null);
		
		comboBoxHospede = new JComboBox();
		comboBoxHospede.setBounds(107, 159, 207, 20);
		panel.add(comboBoxHospede);
		
		textFieldidadeAcompanhante = new JTextField();
		textFieldidadeAcompanhante.setBounds(107, 124, 207, 20);
		panel.add(textFieldidadeAcompanhante);
		textFieldidadeAcompanhante.setColumns(10);
		
		textFieldnomeAcompanhante = new JTextField();
		textFieldnomeAcompanhante.setBounds(107, 86, 207, 20);
		panel.add(textFieldnomeAcompanhante);
		textFieldnomeAcompanhante.setColumns(10);
		
		textFieldcpfAcompanhante = new JTextField();
		textFieldcpfAcompanhante.setBounds(107, 48, 207, 20);
		panel.add(textFieldcpfAcompanhante);
		textFieldcpfAcompanhante.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(10, 51, 54, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 89, 54, 14);
		panel.add(lblNome);
		
		JLabel lblNewLabel_1 = new JLabel("Idade:");
		lblNewLabel_1.setBounds(10, 127, 54, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblContribuinte = new JLabel("Contribuinte");
		lblContribuinte.setBounds(10, 162, 87, 14);
		panel.add(lblContribuinte);
		
		JButton btnCadastrarDepend = new JButton("Cadastrar");
		btnCadastrarDepend.setBounds(10, 202, 97, 23);
		panel.add(btnCadastrarDepend);
		
		JButton btnAtualizarDepen = new JButton("Atualizar");
		btnAtualizarDepen.addActionListener(new ActionListener() {
			//Botao Atualizar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldIDAcompanhante.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "update acompanhantes set cpf=?, nome=?, idade=?, id_hospede=? where id_acompanhante=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Hospede h = (Hospede) comboBoxHospede.getSelectedItem();
						
						stmt.setString(1, textFieldcpfAcompanhante.getText());
						stmt.setString(2, textFieldnomeAcompanhante.getText());
						stmt.setInt(3, Integer.parseInt(textFieldidadeAcompanhante.getText()));
						stmt.setInt(4, h.getId_hospede());
						stmt.setInt(5, Integer.parseInt(textFieldIDAcompanhante.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Acompanhante Atualizado com sucesso!");
						
						textFieldIDAcompanhante.setText("");
						textFieldcpfAcompanhante.setText("");
						textFieldnomeAcompanhante.setText("");
						textFieldidadeAcompanhante.setText("");
						comboBoxHospede.setSelectedIndex(0);
						textFieldcpfAcompanhante.setEditable(true);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnAtualizarDepen.setBounds(117, 202, 99, 23);
		panel.add(btnAtualizarDepen);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			//Botao Excluir
			public void actionPerformed(ActionEvent e) {
				if(textFieldIDAcompanhante.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CPF");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "delete from acompanhantes where id_acompanhante=?";
						
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setInt(1, Integer.parseInt(textFieldIDAcompanhante.getText()));
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Acompanhante Excluido com sucesso!");
						
						textFieldIDAcompanhante.setText("");
						textFieldcpfAcompanhante.setText("");
						textFieldnomeAcompanhante.setText("");
						textFieldidadeAcompanhante.setText("");
						comboBoxHospede.setSelectedIndex(0);
						textFieldcpfAcompanhante.setEditable(true);
						

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				}
			}
		});
		btnExcluir.setBounds(227, 202, 87, 23);
		panel.add(btnExcluir);
		btnCadastrarDepend.addActionListener(new ActionListener() {
			
			//Botao Cadastrar
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldcpfAcompanhante.getText().equals("") || textFieldnomeAcompanhante.getText().equals("") || textFieldidadeAcompanhante.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");
				}else {
					try {
						Connection con = DBConnection.faz_conexao();
						String sql = "insert into Acompanhantes(id_acompanhante, cpf, nome, idade, id_hospede) values (?, ?, ?, ?, ?)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						Hospede h = (Hospede) comboBoxHospede.getSelectedItem();
						
						stmt.setInt(1, 0);
						stmt.setString(2, textFieldcpfAcompanhante.getText());
						stmt.setString(3, textFieldnomeAcompanhante.getText());
						stmt.setInt(4, Integer.parseInt(textFieldidadeAcompanhante.getText()));
						stmt.setInt(5, h.getId_hospede());
						
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Cadastro do Acompanhante Realizado com Sucesso!");
						
						textFieldIDAcompanhante.setText("");
						textFieldcpfAcompanhante.setText("");
						textFieldnomeAcompanhante.setText("");
						textFieldidadeAcompanhante.setText("");
						comboBoxHospede.setSelectedIndex(0);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		AtualizaComboBox(comboBoxHospede,0);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 14, 46, 14);
		panel.add(lblId);
		
		textFieldIDAcompanhante = new JTextField();
		textFieldIDAcompanhante.setEditable(false);
		textFieldIDAcompanhante.setBounds(108, 11, 54, 20);
		panel.add(textFieldIDAcompanhante);
		textFieldIDAcompanhante.setColumns(10);
		contentPane.setLayout(gl_contentPane);
	}
	public void AtualizaComboBox(JComboBox<Hospede> combobox, int idHospede) {
		combobox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select *from Hospede";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Hospede h = new Hospede(Integer.parseInt(rs.getString("id_hospede")), rs.getString("cpf"), rs.getString("nome"), rs.getString("telefone"), Integer.parseInt(rs.getString("idade")), rs.getString("endereco"));
				if(idHospede != 0 && h.getId_hospede()==idHospede) {
					index = cont;
				}
				cont++;
				combobox.addItem(h);
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
