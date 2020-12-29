/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Consultas;
import Controladores.ProductoJpaController;
import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.Empleado;
import Entidades.Producto;
import static GUI.vistaEmpleados.modelo;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author irisa
 */
public class vistaProductos extends javax.swing.JDialog {

    ProductoJpaController controlProductos = new ProductoJpaController();
    Producto producto;
    public vistaProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CrearModelo();
        Llenar_Tabla();
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null); 
    }

    public static DefaultTableModel modelo;
     private void CrearModelo(){
         try {
             modelo = (new DefaultTableModel(
                null, new String [] {
                "id","Nombre",
                "Precio","Codigo de barras","Stock","Proveedor"}){
                Class[] types = new Class [] {
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class
                };
                boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false
                };
                @Override
                public Class getColumnClass(int columnIndex) {
                   return types [columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex){
                   return canEdit [colIndex];
                }
            });
          tablaProductos.setModel(modelo);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,e.toString()+"error");
            }    
        }
    private void Llenar_Tabla(){
        try {
            Object A[] = null;
            List<Producto>listaProductos;
            listaProductos = controlProductos.findProductoEntities();
            for (int i = 0; i < listaProductos.size(); i++) {
              modelo.addRow(A);
              modelo.setValueAt(listaProductos.get(i).getIdProducto(), i, 0);
               modelo.setValueAt(listaProductos.get(i).getNombreProducto(), i, 1);
              modelo.setValueAt(listaProductos.get(i).getPrecioProducto(), i, 2);
              modelo.setValueAt(listaProductos.get(i).getCodigoBarras(), i, 3);
              modelo.setValueAt(listaProductos.get(i).getStock(), i, 4);
              modelo.setValueAt(listaProductos.get(i).getProveedor(), i, 5);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
     private void limpiarTabla(){
        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        tablaProductos.setModel(new DefaultTableModel());
        
    }
     
      private void eliminar(){
    
        try {
            
            int sel = tablaProductos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
            
            int dato =  (int) model.getValueAt(sel, 0);
            controlProductos.destroy(dato);
                       
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(vistaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(vistaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      private void editar(){
    
        Producto prod;
        int sel = tablaProductos.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        int dato =  (int) model.getValueAt(sel, 0);
        prod = controlProductos.findProducto(dato);
        
        editarProducto ep = new editarProducto(this, false);
        ep.llenarDatos(prod.getIdProducto(), prod.getNombreProducto(), prod.getCodigoBarras(), prod.getPrecioProducto(), prod.getStock());
        ep.setVisible(true);
    }
      
      private void buscarPorCodigo(){
          
        String busqueda = buscar.getText();
        producto= new Consultas().buscarCodigoBarras(busqueda);
        if(producto==null){
            new nuevoProducto(this, false).setVisible(true);
        }else{

           // SeleccionProductoVariasOpciones seleccionProductoVariasOpciones = new SeleccionProductoVariasOpciones(this, false, busqueda);
           // seleccionProductoVariasOpciones.setVisible(true);
           JOptionPane.showMessageDialog(null, "Producto ya existente");            
            buscar.setText("");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaProductos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(80, 160, 1040, 390);

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(70, 570, 165, 60);

        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(260, 570, 200, 60);

        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(485, 570, 165, 60);

        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        getContentPane().add(jButton4);
        jButton4.setBounds(925, 570, 195, 60);

        jButton5.setBackground(new java.awt.Color(255, 255, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("ACTUALIZAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(670, 570, 160, 60);

        buscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarKeyPressed(evt);
            }
        });
        getContentPane().add(buscar);
        buscar.setBounds(400, 70, 440, 50);

        jButton6.setBackground(new java.awt.Color(102, 255, 102));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(860, 70, 130, 50);

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(910, 30, 260, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vistaProductos.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1200, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new nuevoProducto(this, true).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limpiarTabla();
        CrearModelo();
        Llenar_Tabla();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        buscarPorCodigo();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            buscarPorCodigo();
            }
    }//GEN-LAST:event_buscarKeyPressed

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
            java.util.logging.Logger.getLogger(vistaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vistaProductos dialog = new vistaProductos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField buscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
