/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.ConexionBD;
import BD.Consultas;
import Controladores.EmpleadoJpaController;
import Entidades.Empleado;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


/**
 *
 * @author iris
 */
public class login extends javax.swing.JFrame {

  
    /**
     * Creates new form Principal
     */
    public login() {
        initComponents(); 
        setSize(420,450);
        this.setLocationRelativeTo(null);
        usuario.requestFocus();
        
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cerrar = new javax.swing.JButton();
        acceder = new javax.swing.JButton();
        usuario = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        panelImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POS");
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel1.setLayout(null);

        cerrar.setBackground(new java.awt.Color(255, 51, 51));
        cerrar.setBorder(null);
        cerrar.setContentAreaFilled(false);
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        jPanel1.add(cerrar);
        cerrar.setBounds(367, 0, 50, 50);

        acceder.setContentAreaFilled(false);
        acceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accederActionPerformed(evt);
            }
        });
        jPanel1.add(acceder);
        acceder.setBounds(140, 390, 140, 40);

        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario.setBorder(null);
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
        });
        jPanel1.add(usuario);
        usuario.setBounds(90, 210, 240, 30);

        pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass.setBorder(null);
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        jPanel1.add(pass);
        pass.setBounds(90, 304, 240, 35);

        panelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/login.png"))); // NOI18N
        jPanel1.add(panelImagen);
        panelImagen.setBounds(0, 0, 420, 450);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static void iniciarServidor(){
    
            try {
    	String cmd = "\\xampp\\xampp_start.exe"; //Comando de apagado en linux
    	Runtime.getRuntime().exec(cmd);
        System.out.println("Servidor encendido...");
       
    } catch (IOException ioe) {
    	System.out.println (ioe);
        } 
    }
    
    private void apagarServidor(){
    
         try {
    	String cmd = "\\xampp\\xampp_stop.exe"; //Comando de apagado en linux
    	Runtime.getRuntime().exec(cmd); 
        System.out.println("Servidor apagado...");
    } catch (IOException ioe) {
    	System.out.println (ioe);
        }
    
    }
    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        // TODO add your handling code here:
        apagarServidor();
        System.exit(0);
    }//GEN-LAST:event_cerrarActionPerformed

    private void accederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accederActionPerformed
        String us = usuario.getText();
        String contra = pass.getText();
        if (us.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos son obligatorios");
        } else {
            Ingresar(us, contra);
        }
    }//GEN-LAST:event_accederActionPerformed

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            pass.requestFocus();
            }
    }//GEN-LAST:event_usuarioKeyPressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            
           String us = usuario.getText();
        String contra = pass.getText();
        if (us.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos son obligatorios");
        } else {
            Ingresar(us, contra);
        }
            }
    }//GEN-LAST:event_passKeyPressed

    public void Ingresar(String id, String con){
    
        EmpleadoJpaController empleados = new EmpleadoJpaController();
        Empleado empleado = empleados.findEmpleado(Integer.parseInt(id));
        if(empleado != null){
            if(empleado.getContrasena().equals(con)){
            
                if(empleado.getTipoEmpleado().equals("ADMIN")){
                    JOptionPane.showMessageDialog(this, "bienvenid@: "+empleado.getNombreEmpleado()+" "+empleado.getApellidoEmpleado());
                    this.dispose();
                    new Administrador().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "bienvenid@: "+empleado.getNombreEmpleado()+" "+empleado.getApellidoEmpleado());
                    this.dispose();
                    new VentanaEmpleado(empleado).setVisible(true);
                } 
            }else{
            JOptionPane.showMessageDialog(this, "Password incorrecto");
            }
            
        }
        else{
        JOptionPane.showMessageDialog(this, "Usuario no existe");
        }
    
    }
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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                Thread.sleep(5000);
                }
                catch(Exception e){}
                iniciarServidor();
                try {
                Connection con =new ConexionBD().conectar();
                if(con==null){
                    System.out.println("ConexionBD no conecto");
               }else{
                    con.close();
               new login().setVisible(true);    
                }
                
                } catch (Exception e) {
                    System.out.println("Error iniciando servidor");
                    System.out.println("Error: "+e.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceder;
    private javax.swing.JButton cerrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel panelImagen;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}