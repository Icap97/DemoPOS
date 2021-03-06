/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controladores.GastoJpaController;
import Entidades.Gasto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author irisa
 */
public class nuevoGasto extends javax.swing.JDialog {

    /**
     * Creates new form nuevoGasto
     */
    GastoJpaController controladorGasto;
    public nuevoGasto(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        controladorGasto = new GastoJpaController();
        
        this.setSize(420,450);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descripcion = new javax.swing.JTextField();
        monto = new javax.swing.JTextField();
        btCancelar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        descripcion.setBorder(null);
        getContentPane().add(descripcion);
        descripcion.setBounds(50, 149, 320, 35);

        monto.setBorder(null);
        getContentPane().add(monto);
        monto.setBounds(53, 259, 320, 36);

        btCancelar.setBorder(null);
        btCancelar.setContentAreaFilled(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btCancelar);
        btCancelar.setBounds(43, 350, 150, 60);

        btGuardar.setBorder(null);
        btGuardar.setContentAreaFilled(false);
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btGuardar);
        btGuardar.setBounds(220, 350, 160, 60);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nuevoGasto.png"))); // NOI18N
        getContentPane().add(fondo);
        fondo.setBounds(0, 0, 420, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        Gasto gas = new Gasto();

        try {
            gas.setDescripcion(descripcion.getText());
            gas.setMonto(Double.parseDouble(monto.getText()));
            controladorGasto.create(gas);
            JOptionPane.showMessageDialog(null, "Gasto creado correctamente");
            descripcion.setText("");
            monto.setText("");
           
        } catch (Exception ex) {
            Logger.getLogger(nuevoGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btGuardarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        descripcion.setText("");
        monto.setText("");
    }//GEN-LAST:event_btCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(nuevoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                nuevoGasto dialog = new nuevoGasto(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JTextField descripcion;
    private javax.swing.JLabel fondo;
    private javax.swing.JTextField monto;
    // End of variables declaration//GEN-END:variables
}
