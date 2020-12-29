/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author edu
 */
public class ConexionBD {
   
 

    
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bdposv4","root","");
            System.out.println("Conexion Exitosa");
            return con;
        }catch(Exception e){
            System.out.println("Error-> Conexion -> Conectar. No se pudo conectar a la base de datos");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
}
