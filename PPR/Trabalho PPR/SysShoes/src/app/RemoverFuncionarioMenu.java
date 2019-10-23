package app;

import dto.Funcionario;
import dto.Loja;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class RemoverFuncionarioMenu extends javax.swing.JFrame {

    private Funcionario funcionarioAtual;
    DefaultListModel dm = new DefaultListModel();
    
    public RemoverFuncionarioMenu(Funcionario f) {
        this.funcionarioAtual = f;
        initComponents();
        mostrarFuncionarios();
    }
    
    private void mostrarFuncionarios()
    {
        jList1.setModel(dm);
        Funcionario[] funcionarios = Loja.getInstance().getFuncionarios();
        for ( int i = 0 ; i < funcionarios.length ; i++ )
        {
            if ( funcionarios[i] != null ){
                dm.addElement(funcionarios[i]);
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

        jButton2.setText("Remover");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jButton1)
                .addGap(81, 81, 81)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
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
        GerenteMenu menuGerente = new GerenteMenu(funcionarioAtual);
        menuGerente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ( jList1.getModel().getSize() == 0 ){
            JOptionPane.showMessageDialog(this, "Não há funcionários instânciados", "Erro", JOptionPane.ERROR_MESSAGE);
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
                Funcionario[] funcionarios = Loja.getInstance().getFuncionarios();

                if ( funcionarios[index] == funcionarioAtual )
                {
                    JOptionPane.showMessageDialog(this, "Não é possível se remover","Remover funcionário",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if ( negocio.Negocio.removerFuncionario((Funcionario) dm.get(index)) ){
                        JOptionPane.showMessageDialog(this, "Funcionário removido com sucesso", "Remover funcionário", JOptionPane.PLAIN_MESSAGE);
                        dm.removeElementAt(index);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Não foi possível remover funcionário","Remover funcionário", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
