package Aerolina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Pasajero {
    private int idPasajero;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private Reserva reserva;
    private int maleta;
    
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
 /*   public static Pasajero fromString(String line) {
        String[] parts = line.split("\\|");
        int idPasajero = Integer.parseInt(parts[0]);
        String nombre = parts[1];
        String apellido = parts[2];
        LocalDate fechaNacimiento = LocalDate.parse(parts[3], FORMATTER);
        String nacionalidad = parts[4];
        return new Pasajero(idPasajero, nombre, apellido, fechaNacimiento, nacionalidad);
    }*/

    public Pasajero(int idPasajero, String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento, String nacionalidad, String telefono, Reserva reserva, int maleta) {
        this.idPasajero = idPasajero;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reserva= reserva;
        this.maleta = maleta;
    }

   

  
    
    
    
    

    /**
     * @return the idPasajero
     */
    public int getIdPasajero() {
        return idPasajero;
    }

    /**
     * @param idPasajero the idPasajero to set
     */
    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
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
     * @return the apellidoP
     */
    public String getApellidoP() {
        return apellidoP;
    }

    /**
     * @param apellidoP the apellidoP to set
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /**
     * @return the apellidoM
     */
    public String getApellidoM() {
        return apellidoM;
    }

    /**
     * @param apellidoM the apellidoM to set
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    /**
     * @return the maleta
     */
    public int getMaleta() {
        return maleta;
    }

    /**
     * @param maleta the maleta to set
     */
    public void setMaleta(int maleta) {
        this.maleta = maleta;
    }
    
    
    
}
    
    

