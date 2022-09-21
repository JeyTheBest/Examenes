/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author javie
 */
public class MetodosSQL {
    public static ConexionBD conexion = new ConexionBD();
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero =0;
    
    
    public int guardar(String Nombres, String Apellidos, String Correo ){
        int resultado =0;
        Connection conexion=null;
        
        String sentencia_guardar =("INSERT INTO Usuarios (Nombres,Apellidos,Correo)values(?,?,?)" );
        
          try {
            conexion = ConexionBD.conectar();
            sentencia_preparada=conexion.prepareStatement(sentencia_guardar);
            sentencia_preparada.setString(1,Nombres);
            sentencia_preparada.setString(2,Apellidos);
            sentencia_preparada.setString(3,Correo);
            
            
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            
            conexion.close();     
            
        } catch (Exception e) {
           
            System.out.println(e);
        }
     return resultado;
    }
    
    public static String BuscarNombre(String Usuario){
        
        String Buscar_Nombre = null;
        Connection conexion = null ;
        
        try {
            conexion = ConexionBD.conectar();
            String sentencia_buscar =("SELECT Nombres,Apellidos FROM users WHERE Usuario = '"+Usuario+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();
            
            if (resultado.next()) {
                String Nombres=resultado.getString("Nombres");
                String Apellidos=resultado.getString("Apellidos");
               Buscar_Nombre =(Nombres +" "+Apellidos);
   
            }
            conexion.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return Buscar_Nombre;
    }
    
    
    public static String BuscarUsuarioRegistrado(String Usuario, String Contrasena){
        
        String busqueda_usuario = null;
        Connection conexion = null ;
        
        try {
            conexion = ConexionBD.conectar();
            String sentencia_buscar_usuario = ("SELECT Nombres, Usuario , Contrasena FROM users WHERE Usuario = '"+Usuario+"'&& Contrasena = '"+Contrasena+"'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar_usuario);
            resultado = sentencia_preparada.executeQuery();
            if(resultado.next()){
                busqueda_usuario ="usuario encontrado";
            }else{
                busqueda_usuario= "usuario no existe";
            }
            conexion.close();
        } catch (Exception e) {
            
            System.out.println(e);
        }
        return busqueda_usuario;
    }
    
    
    
    public static boolean ValidarLetras(String datos ){
        
      
        return  datos.matches("[a-zA-Z]");
       
        
        
    }
    
    public static boolean ValidarNumeros(String Datos){
        
        return Datos.matches("[0-9]");
    }
    
    
    public int ValidarUsuarioRepetido(String Usuario){
        
        PreparedStatement ps = null;
        ResultSet rs = null; 
        Connection conexion = null ;
      
        String BuscarRepetido=("SELECT count(id) FROM users WHERE Usuario = ?" );
        
          try {
            conexion = ConexionBD.conectar();
            ps=conexion.prepareStatement(BuscarRepetido);
            ps.setString(1,Usuario);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            
            conexion.close();
            return 1;
           
            
        } catch (Exception e) {
       
            System.out.println(e);
            return 1;
        }
  
    }
    
    public boolean verificar_Email(String correo){
    
    Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,})$");
    
    //Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
    
    Matcher mat = patron.matcher(correo);        
    return mat.find();
        
    }
    
    
}
