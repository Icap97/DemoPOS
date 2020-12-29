/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entidades.Empleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author iris
 */
public class VentanaEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form Usuario
     */
    Empleado empleado;
    public VentanaEmpleado(Empleado emp) {
        this.empleado=emp;
        
        initComponents();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        
        //lbUsuario.setText(empleado.getNombreEmpleado()+" "+empleado.getApellidoEmpleado());
        lbUsuario.setText(emp.getNombreEmpleado());
         //hora y fecha
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMM yyyy");
        fecha.setText(formato.format(sistemaFech));
        
        //Hora del sistema
        Timer tiempo = new Timer(100, new VentanaEmpleado.horas());
        tiempo.start();
        
         movimientoCaja mc = new movimientoCaja(this, false);
         mc.setEmpleado(emp);
         mc.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hora = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        btNuevaVenta = new javax.swing.JButton();
        btDesconectar = new javax.swing.JButton();
        btVerPrecio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        hora.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        hora.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(hora);
        hora.setBounds(860, 550, 330, 40);

        lbUsuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        getContentPane().add(lbUsuario);
        lbUsuario.setBounds(140, 90, 140, 50);

        fecha.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        fecha.setForeground(new java.awt.Color(204, 204, 204));
        getContentPane().add(fecha);
        fecha.setBounds(860, 610, 310, 40);

        btNuevaVenta.setBackground(new java.awt.Color(255, 255, 255));
        btNuevaVenta.setBorder(null);
        btNuevaVenta.setContentAreaFilled(false);
        btNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevaVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btNuevaVenta);
        btNuevaVenta.setBounds(350, 50, 190, 190);

        btDesconectar.setBackground(new java.awt.Color(255, 255, 255));
        btDesconectar.setBorder(null);
        btDesconectar.setContentAreaFilled(false);
        btDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesconectarActionPerformed(evt);
            }
        });
        getContentPane().add(btDesconectar);
        btDesconectar.setBounds(20, 170, 260, 80);

        btVerPrecio.setBorder(null);
        btVerPrecio.setContentAreaFilled(false);
        btVerPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVerPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(btVerPrecio);
        btVerPrecio.setBounds(570, 50, 190, 190);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/interfazUsuario.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1200, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesconectarActionPerformed
        if (JOptionPane.showConfirmDialog(null, "¿Esta segur@ de salir?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            new login().setVisible(true);
        } 
    }//GEN-LAST:event_btDesconectarActionPerformed

    private void btNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevaVentaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new NuevaVenta(this, true,empleado).setVisible(true);
        
    }//GEN-LAST:event_btNuevaVentaActionPerformed

    private void btVerPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVerPrecioActionPerformed
        new verPrecio(this, true).setVisible(true);
    }//GEN-LAST:event_btVerPrecioActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaEmpleado(null).setVisible(true);
            }
        });
    }
    
    class horas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Date sistemaHora = new Date();
            String pmAm = "hh:mm:ss a";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            hora.setText(String.format(formato.format(sistemaHora), now));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDesconectar;
    private javax.swing.JButton btNuevaVenta;
    private javax.swing.JButton btVerPrecio;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbUsuario;
    // End of variables declaration//GEN-END:variables
}