package Aerolina;
import Cuenta.Usuario;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Boleto {
    private int idBoleto;
    private double precio;
    private String estado;
    private LocalDateTime fechaCompra;
    private String tipo;
    private Reserva reserva;
   

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Boleto(int idBoleto, double precio, String estado, LocalDateTime fechaCompra, String tipo, Reserva reserva) {
        this.idBoleto = idBoleto;
        this.precio = precio;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.tipo = tipo;
        this.reserva = reserva;
    }
    


    /**
     * @return the id
     */
    public int getIdBoleto() {
        return idBoleto;
    }

    /**
     * @param id the id to set
     */
    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaCompra
     */
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the reserva
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the FORMATTER
     */
    public static DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }

    /**
     * @param aFORMATTER the FORMATTER to set
     */
    public static void setFORMATTER(DateTimeFormatter aFORMATTER) {
        FORMATTER = aFORMATTER;
    }


    
    
    
    
    
    
    
    }


  