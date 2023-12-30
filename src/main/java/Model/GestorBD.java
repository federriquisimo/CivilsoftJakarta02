/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GestorBD {
    Connection conn = null;
    Statement stm=null;
    ResultSet usuarioResultSet;
    ResultSet idResultSet;
    User usuarioHallado;
    String cuenta,clave;
    public int id;
    
    public boolean registrar(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, 
                             String email,String company,String usuario,String password) {
        
        // Para definir el id
        try{
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            idResultSet = stm.executeQuery("select *  from clients  order by  clientId desc limit 1;");
            if(!idResultSet.next()){
               id = 0;
                ConectaBD.cerrar();
            
            }else{
                 System.out.println("Se encontró el registro"+idResultSet.getInt("clientId"));
                id= idResultSet.getInt("clientId");
                ConectaBD.cerrar();
                
            }
        }catch(Exception e){
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            
        }  
        
        
        // Para insertar un registro en la tabla clients
        
        
        int resultUpdate = 0;
        try{
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            id++;
            resultUpdate=stm.executeUpdate("INSERT INTO clients VALUES('" +id+ "','"+ primerNombre + "','" + segundoNombre + "','" + primerApellido
                                            +"','"+segundoApellido+ "','"+email+ "','"+company+ "','"+usuario+ "','"+password+ "');");
            if(resultUpdate != 0){
                ConectaBD.cerrar();
                return true;
            }else{
                ConectaBD.cerrar();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public User consultar(String usuario, String password){
        System.out.println("Llega a consultar");//agregado para probar
        try{
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            usuarioResultSet = stm.executeQuery("SELECT * FROM clients WHERE userName='"+usuario+ "' and password='"+password+"';");
            if(!usuarioResultSet.next()){
                System.out.println(" No se encontro el registro");
                ConectaBD.cerrar();
                return null;
            }else{
                System.out.println("Se encontró el registro");
                cuenta = usuarioResultSet.getString("userName");
                clave = usuarioResultSet.getString("password");
                usuarioHallado = new User(cuenta,clave);
                ConectaBD.cerrar();
                return usuarioHallado;
            }
        }catch(Exception e){
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
}
    
    
   public ArrayList<User> leeTodos(){
    ArrayList<User> usuarios = new ArrayList<User>();
    try{
        conn = ConectaBD.abrir();
        stm = conn.createStatement();
        usuarioResultSet = stm.executeQuery("SELECT * FROM clients");
        if(!usuarioResultSet.next()){
            System.out.println(" No se encontraron registros");
            ConectaBD.cerrar();
            return null;
        }else{
            do{
            cuenta = usuarioResultSet.getString("userName");
            clave = usuarioResultSet.getString("password");
            usuarioHallado = new User(cuenta,clave);
            usuarios.add(usuarioHallado);
            }while(usuarioResultSet.next());
            ConectaBD.cerrar();
            return usuarios;
        }
    }catch(Exception e){
        System.out.println("Error en la base de datos.");
        e.printStackTrace();
        return null;
    }
    } 
    
}