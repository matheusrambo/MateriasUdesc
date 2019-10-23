package app;

import dto.Funcionario;
import dto.Loja;
import dto.Produto;
import dto.TipoFuncionario;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class EfetuarVendaMenu extends javax.swing.JFrame {

    private Funcionario funcionarioAtual;
    DefaultListModel dm = new DefaultListModel();
    
    public EfetuarVendaMenu(Funcionario f) {
        this.funcionarioAtual = f;
        initComponents();
        mostrarProdutos();
    }
    
    private void mostrarProdutos()
    {
        jList1.setModel(dm);
        Produto[] produtos = Loja.getInstance().getProdutos();
        for ( int i = 0 ; i < produtos.length ; i++ )
        {
            if ( produtos[i] != null ){
                dm.addElement(produtos[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Confirmar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if ( this.funcionarioAtual.getTipoFuncionario() == TipoFuncionario.VENDEDOR )
        {
            VendedorMenu menuVendedor = new VendedorMenu(funcionarioAtual);
            menuVendedor.setVisible(true);
            this.dispose();
        }
        
        if ( this.funcionarioAtual.getTipoFuncionario() == TipoFuncionario.GERENTE )
        {
            GerenteMenu menuGerente = new GerenteMenu(funcionarioAtual);
            menuGerente.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ( jList1.getModel().getSize() == 0 ){
            JOptionPane.showMessageDialog(this, "Não há produtos instânciados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            int index = jList1.getSelectedIndex();
            if ( index == -1 )
            {
                JOptionPane.showMessageDialog(this, "Índice inválido","Erro",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Produto[] produtos = Loja.getInstance().getProdutos();
                if ( negocio.Negocio.efetuarVenda(funcionarioAtual, produtos[index]) ){
                    JOptionPane.showMessageDialog(this, "Venda efetuada", "Registrar venda", JOptionPane.PLAIN_MESSAGE);
                    dm.clear();
                    mostrarProdutos();
                }
                else
                    JOptionPane.showMessageDialog(this, "Não foi possível registrar a venda", "Registrar venda",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
