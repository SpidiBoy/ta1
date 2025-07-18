package Aerolina;
import Aerolina.Asiento;
import Viaje.Departamento;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vuelo {
    private int id;
    private String codigoVuelo;
    private Departamento departamentoOrigen;
    private Departamento departamentoDestino;
    private LocalDateTime fechaSalida;
    private LocalTime horaSalida;
    private LocalDateTime fechaLlegada;
    private LocalTime horaLlegada;
    private double duracion;
    private double distanciaMillas;
    private String tipo;
    private double precio;
    private List<Asiento> asientos;
    private List<Reserva> reservas;

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Vuelo(int id, String codigoVuelo, Departamento departamentoOrigen, Departamento departamentoDestino, LocalDateTime fechaSalida, LocalTime horaSalida, LocalDateTime fechaLlegada, LocalTime horaLlegada, double duracion, double distanciaMillas, String tipo, double precio, List<Asiento> asientos, List<Reserva> reservas) {
        this.id = id;
        this.codigoVuelo = codigoVuelo;
        this.departamentoOrigen = departamentoOrigen;
        this.departamentoDestino = departamentoDestino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaLlegada = horaLlegada;
        this.duracion = duracion;
        this.distanciaMillas = distanciaMillas;
        this.tipo = tipo;
        this.precio = precio;
        this.asientos = asientos;
        this.reservas = reservas;
    }



    

    
    

    public Vuelo(int id, String codigoVuelo, Departamento departamentoOrigen, Departamento departamentoDestino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, double distanciaMillas) {
        this.id = id;
        this.codigoVuelo = codigoVuelo;
        this.departamentoOrigen = departamentoOrigen;
        this.departamentoDestino = departamentoDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.distanciaMillas = distanciaMillas;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Vuelo{" + "id=" + getId() + ", codigoVuelo=" + getCodigoVuelo() + ", departamentoOrigen=" + getDepartamentoOrigen() + ", departamentoDestino=" + getDepartamentoDestino() + ", fechaSalida=" + getFechaSalida() + ", horaSalida=" + getHoraSalida() + ", fechaLlegada=" + getFechaLlegada() + ", horaLlegada=" + getHoraLlegada() + ", duracion=" + getDuracion() + ", distanciaMillas=" + getDistanciaMillas() + ", asientos=" + getAsientos() + ", reservas=" + getReservas() + '}';
    }

    
    public static Vuelo fromString(String line, Map<Integer, Departamento> departamentosMap) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 7) {
                throw new IllegalArgumentException("Formato de línea inválido: " + line);
            }

            int idVuelo = Integer.parseInt(parts[0]);
            String codigoVuelo = parts[1];
            int idOrigen = Integer.parseInt(parts[2]);
            int idDestino = Integer.parseInt(parts[3]);
            LocalDateTime fechaSalida = LocalDateTime.parse(parts[4], getFORMATTER());
            LocalDateTime fechaLlegada = LocalDateTime.parse(parts[5], getFORMATTER());
            double distanciaMillas = Double.parseDouble(parts[6]);

            Departamento origen = departamentosMap.get(idOrigen);
            Departamento destino = departamentosMap.get(idDestino);

            if (origen == null || destino == null) {
                throw new IllegalArgumentException("Departamento no encontrado para el vuelo: " + idVuelo);
            }

            return new Vuelo(idVuelo, codigoVuelo, origen, destino, fechaSalida, fechaLlegada, distanciaMillas);
        } catch (Exception e) {
            System.err.println("Error al procesar línea de vuelo: " + line + " - " + e.getMessage());
            return null;
        }
    }
    
    
    
    
      public void addAsiento(Asiento asiento) {
        if (getAsientos() == null) {
            setAsientos(new ArrayList<>());
        }
        getAsientos().add(asiento);
    }

    public void removeAsiento(Asiento asiento) {
        if (getAsientos() != null) {
            getAsientos().remove(asiento);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the codigoVuelo
     */
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    /**
     * @param codigoVuelo the codigoVuelo to set
     */
    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    /**
     * @return the departamentoOrigen
     */
    public Departamento getDepartamentoOrigen() {
        return departamentoOrigen;
    }

    /**
     * @param departamentoOrigen the departamentoOrigen to set
     */
    public void setDepartamentoOrigen(Departamento departamentoOrigen) {
        this.departamentoOrigen = departamentoOrigen;
    }

    /**
     * @return the departamentoDestino
     */
    public Departamento getDepartamentoDestino() {
        return departamentoDestino;
    }

    /**
     * @param departamentoDestino the departamentoDestino to set
     */
    public void setDepartamentoDestino(Departamento departamentoDestino) {
        this.departamentoDestino = departamentoDestino;
    }

    /**
     * @return the fechaSalida
     */
    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the horaSalida
     */
    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return the fechaLlegada
     */
    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * @param fechaLlegada the fechaLlegada to set
     */
    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * @return the horaLlegada
     */
    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * @param horaLlegada the horaLlegada to set
     */
    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    /**
     * @return the duracion
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the distanciaMillas
     */
    public double getDistanciaMillas() {
        return distanciaMillas;
    }

    /**
     * @param distanciaMillas the distanciaMillas to set
     */
    public void setDistanciaMillas(double distanciaMillas) {
        this.distanciaMillas = distanciaMillas;
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
     * @return the asientos
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }

    /**
     * @param asientos the asientos to set
     */
    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    /**
     * @return the reservas
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
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
    
    
    
  