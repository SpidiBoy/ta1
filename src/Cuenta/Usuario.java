/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cuenta;


import Aerolina.Boleto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win12
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private String tipo;
    private double millas;

    public Usuario(int idUsuario, String nombre, String email, String contrasena, String tipo, double millas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.millas = millas;
    }

   
    
    public Usuario(String email, String contrasena) {
        this.email= email;
        this.contrasena = contrasena;
       
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the compras
     */
   
    
    
    
    

    


   
    

    
}
