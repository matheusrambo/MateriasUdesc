package apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.DBConnection;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import dados.Frigobar;
import dados.Quarto;
import dados.Quartos;
import dados.Reserva;
import dados.Tipo_quarto;
import javax.swing.ImageIcon;

public class TelaMenu extends JFrame {
	private JComboBox comboBoxIDQuarto;
	private JComboBox comboBoxIDReserva;
	private JPanel contentPane;
	private JTable tableHospedeDesktop;
	private JTable tableReservaDesktop;
	private JTable tableReservasConfirmadas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu();
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
	public TelaMenu() {
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("Sistema Pousada");
        setSize(1300, 700);
         
        // Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();
         
        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);
        
        JMenuItem mntmHospede = new JMenuItem("Hospede");
        mntmHospede.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		CadastroHospede obj = new CadastroHospede();
        		obj.main(null);
        	}
        });
        mntmHospede.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmHospede);
        
        JMenuItem mntmFrigobar = new JMenuItem("Frigobar");
        mntmFrigobar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		CadastroFrigobar obj = new CadastroFrigobar();
        		obj.main(null);
        	}
        });
        mntmFrigobar.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmFrigobar);
        
        JMenuItem mntmAcompanhantes = new JMenuItem("Acompanhantes");
        mntmAcompanhantes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroAcompanhantes obj = new CadastroAcompanhantes();
        		obj.main(null);
        	}
        });
        mntmAcompanhantes.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmAcompanhantes);
        
        JMenuItem mntmBem = new JMenuItem("Itens");
        mntmBem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroItens obj = new CadastroItens();
        		obj.main(null);
        	}
        });
        mntmBem.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmBem);
        
        
        JMenuItem mntmQuarto = new JMenuItem("Quarto"); //Ter tela de cadastro pra cadastrar tipo de quarto
        mntmQuarto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroQuarto obj = new CadastroQuarto();
        		obj.main(null);
        	}
        });
        mntmQuarto.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmQuarto);
        
        JMenuItem mntmReserva = new JMenuItem("Reserva");
        mntmReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroReserva obj = new CadastroReserva();
        		obj.main(null);
        	}
        });
        mntmReserva.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmReserva);
        
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setForeground(Color.BLACK);
        panel.setBorder(new TitledBorder(null, "Confirmar Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setLayout(null);
        panel.setBackground(SystemColor.menu);
        panel.setBounds(24, 362, 235, 154);
        getContentPane().add(panel);
        
        comboBoxIDQuarto = new JComboBox();
        comboBoxIDQuarto.setBounds(120, 26, 97, 20);
        panel.add(comboBoxIDQuarto);
        
        comboBoxIDReserva = new JComboBox();
        comboBoxIDReserva.setBounds(120, 68, 97, 20);
        panel.add(comboBoxIDReserva);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(SystemColor.activeCaption);
        scrollPane.setBounds(760, 54, 405, 212);
        getContentPane().add(scrollPane);
        
        tableHospedeDesktop = new JTable();
        tableHospedeDesktop.setBackground(Color.WHITE);
        tableHospedeDesktop.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"ID", "Hospede", "CPF"
        	}
        ));
        scrollPane.setViewportView(tableHospedeDesktop);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(760, 362, 405, 212);
        getContentPane().add(scrollPane_1);
        
        tableReservaDesktop = new JTable();
        tableReservaDesktop.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null},
        	},
        	new String[] {
        		"ID", "data entrada", "data saida" , "ID Hospede"
        	}
        ));
        tableReservaDesktop.getColumnModel().getColumn(1).setPreferredWidth(96);
        scrollPane_1.setViewportView(tableReservaDesktop);
        
        JButton btnListarHospede = new JButton("Listar Hospedes");
        btnListarHospede.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from Hospede";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableHospedeDesktop.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_hospede"), rs.getString("nome"), rs.getString("cpf")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        	}
        });
        btnListarHospede.setBounds(985, 277, 180, 23);
        getContentPane().add(btnListarHospede);
        
        JButton btnListarReserva = new JButton("Listar Reservas");
        btnListarReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from reserva";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableReservaDesktop.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_reserva"), rs.getString("data_entrada"), rs.getString("data_saida"), rs.getString("id_hospede")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
        	}
        });
        btnListarReserva.setBounds(985, 585, 180, 23);
        getContentPane().add(btnListarReserva);
        
        JLabel lblTabelaDasReservas = new JLabel("Tabela das Reservas:");
        lblTabelaDasReservas.setBackground(Color.WHITE);
        lblTabelaDasReservas.setBounds(760, 337, 147, 14);
        getContentPane().add(lblTabelaDasReservas);
        
        JLabel lblTabelaDeHospedes = new JLabel("Tabela de Hospedes:");
        lblTabelaDeHospedes.setBounds(762, 29, 145, 14);
        getContentPane().add(lblTabelaDeHospedes);
        
        JButton button = new JButton("Cadastrar");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DBConnection.faz_conexao();
					String sql = "insert into quartos(id_quartos, id_quarto, id_reserva) values (?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					Reserva r = (Reserva) comboBoxIDReserva.getSelectedItem();
					
					Quarto q = (Quarto) comboBoxIDQuarto.getSelectedItem();

					stmt.setInt(1, 0);
					stmt.setInt(2, r.getId_reserva());
					stmt.setInt(3, q.getId_quarto());
					
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Reserva Confirmada com Sucesso!");
				
					comboBoxIDReserva.setSelectedIndex(0);
					comboBoxIDQuarto.setSelectedIndex(0);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
        AtualizaComboBox(comboBoxIDReserva,0);
		AtualizaComboBox2(comboBoxIDQuarto,0);
        
        button.setBounds(10, 110, 97, 23);
        panel.add(button);
        
        JButton button_2 = new JButton("Excluir");
        button_2.setBounds(130, 110, 87, 23);
        panel.add(button_2);
        
        JLabel lblIdQuarto = new JLabel("ID Quarto:");
        lblIdQuarto.setBounds(10, 29, 87, 14);
        panel.add(lblIdQuarto);
        
        JLabel lblIdReserva = new JLabel("ID Reserva:");
        lblIdReserva.setBounds(10, 71, 73, 14);
        panel.add(lblIdReserva);
        
        JButton button_1 = new JButton("Listar Reservas Confirmadas");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from quartos";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableReservasConfirmadas.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_reserva"), rs.getString("id_quarto")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
        	}
        });
        button_1.setBounds(502, 585, 216, 23);
        getContentPane().add(button_1);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(313, 362, 405, 212);
        getContentPane().add(scrollPane_2);
        
        tableReservasConfirmadas = new JTable();
        tableReservasConfirmadas.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null},
        	},
        	new String[] {
        		"ID Reserva", "ID Quarto"
        	}
        ));
        scrollPane_2.setViewportView(tableReservasConfirmadas);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(TelaMenu.class.getResource("/Imagens/pousada.png")));
        label.setBounds(10, 11, 461, 421);
        getContentPane().add(label);
	}
	
	public void AtualizaComboBox(JComboBox<Reserva> comboBox, int idReserva) {
		comboBox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select *from Reserva";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reserva r = new Reserva(Integer.parseInt(rs.getString("id_reserva")));
				if(idReserva != 0 && r.getId_reserva()==idReserva) {
					index = cont;
				}
				cont++;
				comboBox.addItem(r);
			}
			comboBox.setSelectedIndex(index);
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void AtualizaComboBox2(JComboBox<Quarto> comboBox, int idQuarto) {
		comboBox.removeAllItems();
		int index=0,cont=0;
		try {
			Connection con = DBConnection.faz_conexao();
			String sql = "select * from quarto";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Quarto q = new Quarto(Integer.parseInt(rs.getString("id_quarto")), Integer.parseInt(rs.getString("quant_camas")), rs.getString("preco"), rs.getString("descricao"), rs.getString("comodidade"), Integer.parseInt(rs.getString("id_frigobar")), Integer.parseInt(rs.getString("id_tipo_quarto")));
				if(idQuarto != 0 && q.getId_quarto()==idQuarto) {
					index = cont;
				}
				cont++;
				comboBox.addItem(q);
			}
			comboBox.setSelectedIndex(index);
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
