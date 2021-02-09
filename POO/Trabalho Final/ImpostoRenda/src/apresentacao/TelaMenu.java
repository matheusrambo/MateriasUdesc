package apresentacao;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.DBConnection;

import java.awt.Color;

public class TelaMenu extends JFrame {

	private JPanel contentPane;
	private JTable tableContriDesktop;
	private JTable tablePJDesktop;

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
		setTitle("Imposto de Renda");
        setSize(1300, 700);
         
        // Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();
         
        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);
        
        JMenuItem mntmContribuinte = new JMenuItem("Contribuinte");
        mntmContribuinte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		CadastroContribuinte obj = new CadastroContribuinte();
        		obj.main(null);
        	}
        });
        mntmContribuinte.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmContribuinte);
        
        JMenuItem mntmPessoaJurdica = new JMenuItem("Pessoa Jur\u00EDdica");
        mntmPessoaJurdica.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		CadastroPessoaJuridica obj = new CadastroPessoaJuridica();
        		obj.main(null);
        	}
        });
        mntmPessoaJurdica.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmPessoaJurdica);
        
        JMenuItem mntmDependente = new JMenuItem("Dependente");
        mntmDependente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroDependente obj = new CadastroDependente();
        		obj.main(null);
        	}
        });
        menuBar.add(mntmDependente);
        
        JMenuItem mntmBem = new JMenuItem("Bem");
        mntmBem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroBem obj = new CadastroBem();
        		obj.main(null);
        	}
        });
        menuBar.add(mntmBem);

        
        JMenuItem mntmDespesas = new JMenuItem("Despesas");
        mntmDespesas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroNotaFiscal obj = new CadastroNotaFiscal();
        		obj.main(null);
        	}
        });
        mntmDespesas.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmDespesas);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Receitas");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CadastroContraCheque obj = new CadastroContraCheque();
        		obj.main(null);
        	}
        });
        mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mntmNewMenuItem);
        getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(SystemColor.activeCaption);
        scrollPane.setBounds(10, 11, 405, 212);
        getContentPane().add(scrollPane);
        
        tableContriDesktop = new JTable();
        tableContriDesktop.setBackground(Color.WHITE);
        tableContriDesktop.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"ID", "Contribuinte", "CPF"
        	}
        ));
        scrollPane.setViewportView(tableContriDesktop);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 264, 405, 212);
        getContentPane().add(scrollPane_1);
        
        tablePJDesktop = new JTable();
        tablePJDesktop.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"ID", "Pessoa Jur\u00EDdica", "CNPJ"
        	}
        ));
        tablePJDesktop.getColumnModel().getColumn(1).setPreferredWidth(96);
        scrollPane_1.setViewportView(tablePJDesktop);
        
        JButton btnListarContribuinte = new JButton("Listar Contribuinte");
        btnListarContribuinte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from contribuintes";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tableContriDesktop.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_contribuinte"), rs.getString("nome"), rs.getString("cpf")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        	}
        });
        btnListarContribuinte.setBounds(10, 230, 180, 23);
        getContentPane().add(btnListarContribuinte);
        
        JButton btnListarPessoaJurdica = new JButton("Listar Pessoa Jur\u00EDdica");
        btnListarPessoaJurdica.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Connection con = DBConnection.faz_conexao();
					String sql = "select *from pessoa_juridica";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tablePJDesktop.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[]{ rs.getString("id_pj"), rs.getString("nome_pj"), rs.getString("cnpj")});
					}
					rs.close();
					con.close();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
        	}
        });
        btnListarPessoaJurdica.setBounds(235, 230, 180, 23);
        getContentPane().add(btnListarPessoaJurdica);
	}
}
