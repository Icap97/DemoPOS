/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu
 */
public class Consultas {
    public void restarStock(int restar, int idProducto){
        System.out.println("Entrando a restar: "+restar+" : "+idProducto);
        String consulta="update producto set stock = stock -"+restar+" where idProducto = "+idProducto;
        System.out.println("Consulta: "+consulta);
        PreparedStatement ps;
        
        Connection conn = new ConexionBD().conectar();
        
        if(conn==null){
            System.out.println("BD.Consultas.ultimaFactura()");
        }else{
            try {
                ps = conn.prepareStatement(consulta);
                ps.executeUpdate(); 
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public int ultimaFactura(){
        int factura=0;
        
        String consulta="select max(numFactura) from factura";
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = new ConexionBD().conectar();
        
        if(conn==null){
            System.out.println("BD.Consultas.ultimaFactura()");
        }else{
            try {
               ps = conn.prepareStatement(consulta);
               rs = ps.executeQuery(); 
          if(rs==null){
              System.out.println("Es cero en factura");
              return 0;
          }else{
          rs.next();
          factura = rs.getInt(consulta);
          }
            } catch (Exception e) {
                System.out.println("BD.Consultas.ultimaFactura() en consulta");
            }
        }
        
        return factura;
    }
    
    public Producto buscarCodigoBarras(String codigoBarras){
        Producto producto=null;
        PreparedStatement ps;
        ResultSet rs;
        
        Connection conn = new ConexionBD().conectar();
        String consulta="select * from producto where codigoBarras='"+codigoBarras+"'";
        
        if(conn==null){
            System.out.println("BD.consultas.buscarCodigoBarras() 1");
        }else{
          try{  
          ps = conn.prepareStatement(consulta);
          rs = ps.executeQuery(); 
          rs.next();
          producto=new Producto();
          producto.setIdProducto(rs.getInt(1));
          producto.setNombreProducto(rs.getString(2));
          producto.setPrecioProducto(rs.getBigDecimal(3));
          producto.setCodigoBarras(rs.getString(4));
          producto.setStock(rs.getInt(5));
          conn.close();
        }catch(Exception e){
              System.out.println("BD.consultas.buscarCodigoBarras() 2");
              System.out.println("Error: "+e.getMessage());
              return null;
        }
        }
        
        
        return producto;
    }
}
