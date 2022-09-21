/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosSQL;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author javie
 */
public class ConexionBD {
    
    
    public static Connection conectar(){
        Connection conexion = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/juegos","root","");
            System.out.println("conexion establecidad");
        }catch(ClassNotFoundException | SQLException e ){
            System.out.println(e);
        }
        return conexion;
    }
    
}
