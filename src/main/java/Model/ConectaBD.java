/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author USUARIO
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBD {
    public static Connection con=null;
    private static String bd = "civilsoft";
    public static String usuario = "root";
    public static String passw = "adminFede2023Vives$4";
    public static String url = "jdbc:mysql://localhost:3306/"+bd;
    
    public static Connection abrir(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,usuario,passw);
        System.out.println("Conexión exitosa:"+con);
    } catch (Exception e) {
        System.out.println("Error en la conexion...");
        e.printStackTrace();
    return null;
    }
    return con;
    }
    
    
    public static void cerrar(){
        try{
            if(con != null)
            con.close();
            System.out.println(" Se cerró la conexión");
        } catch (Exception e){
            System.out.println("Error: No se logro cerrar la conexion:\n"+e);
        }
    }
}