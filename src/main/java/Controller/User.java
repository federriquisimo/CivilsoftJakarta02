package Controller;


import Model.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dr. Bala
 */
@Named
@RequestScoped
//@SessionScoped
//@RequestScoped
//@ViewScoped
public class User {
 private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String company;
    private String usuario;
    private String password;
   
    GestorBD gestorBD = new GestorBD();

    /**
     * Creates a new instance of User
     */
    public User() {
    }

    public User(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    
    public void loginUser(){
        
        //System.out.println(usuario);
        if (gestorBD.consultar( usuario,password) != null )
        try{
            FacesContext.getCurrentInstance()
            .getExternalContext()
            .redirect("modulos.html");
        }catch(IOException ex) {
            Logger.getLogger(User
            .class.getName()).log(Level.SEVERE, null, ex);
        }
        else
        try{

            FacesContext.getCurrentInstance()
            .getExternalContext()
            .redirect("login.xhtml");
        }catch(IOException ex) {
            Logger.getLogger(User
            .class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public void registroUser(){
     
     System.out.println("Entró al método");
     if (gestorBD.registrar(primerNombre, segundoNombre, primerApellido, segundoApellido,email,company,usuario,password))
        try{
            FacesContext.getCurrentInstance()
            .getExternalContext()
            .redirect("login.xhtml");
        }catch(IOException ex) {
            Logger.getLogger(User
            .class.getName()).log(Level.SEVERE, null, ex);
        }
        else
        try{

            FacesContext.getCurrentInstance()
            .getExternalContext()
            .redirect("registro.xhtml");
        }catch(IOException ex) {
            Logger.getLogger(User
            .class.getName()).log(Level.SEVERE, null, ex);
        }
     
 }
    
    
    
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}