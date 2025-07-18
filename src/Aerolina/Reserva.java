/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aerolina;

import Aerolina.Pasajero;
import Aerolina.Pasajero;
import Aerolina.Vuelo;
import Aerolina.Vuelo;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author BIENVENIDO
 */
public class Reserva {
    private int idReserva;
    private String codigoReserva;
    private Vuelo vueloIda;
    private Vuelo vueloRegreso;
    private List<Pasajero> pasajero;
    private LocalDateTime fechareserva;
    private boolean confirmada;

    public Reserva(int idReserva, String codigoReserva, Vuelo vueloIda, Vuelo vueloRegreso, List<Pasajero> pasajero, LocalDateTime fechareserva, boolean confirmada) {
        this.idReserva = idReserva;
        this.codigoReserva = codigoReserva;
        this.vueloIda = vueloIda;
        this.vueloRegreso = vueloRegreso;
        this.pasajero = pasajero;
        this.fechareserva = fechareserva;
        this.confirmada = false;
    }

  

    public boolean esSoloIda(){
        return getVueloRegreso() ==  null;
    }

    /**
     * @return the vueloIda
     */
    public Vuelo getVueloIda() {
        return vueloIda;
    }

    /**
     * @param vueloIda the vueloIda to set
     */
    public void setVueloIda(Vuelo vueloIda) {
        this.vueloIda = vueloIda;
    }

    /**
     * @return the vueloRegreso
     */
    public Vuelo getVueloRegreso() {
        return vueloRegreso;
    }

    /**
     * @param vueloRegreso the vueloRegreso to set
     */
    public void setVueloRegreso(Vuelo vueloRegreso) {
        this.vueloRegreso = vueloRegreso;
    }

    /**
     * @return the pasajero


    /**
     * @return the fechareserva
     */
    public LocalDateTime getFechareserva() {
        return fechareserva;
    }

    /**
     * @param fechareserva the fechareserva to set
     */
    public void setFechareserva(LocalDateTime fechareserva) {
        this.fechareserva = fechareserva;
    }

    /**
     * @return the confirmada
     */
    public boolean isConfirmada() {
        return confirmada;
    }

    /**
     * @param confirmada the confirmada to set
     */
    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    /**
     * @return the id
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * @param id the id to set
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return the codigoReserva
     */
    public String getCodigoReserva() {
        return codigoReserva;
    }

    /**
     * @param codigoReserva the codigoReserva to set
     */
    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    /**
     * @return the pasajero
     */
    public List<Pasajero> getPasajero() {
        return pasajero;
    }

    /**
     * @param pasajero the pasajero to set
     */
    public void setPasajero(List<Pasajero> pasajero) {
        this.pasajero = pasajero;
    }
     }
    
    
            
    
    
     
 