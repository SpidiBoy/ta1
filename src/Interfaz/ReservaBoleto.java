/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import Aerolina.*;
import Cuenta.*;
import Viaje.*;
import java.awt.Window;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.awt.Window;
import java.util.ArrayList; // Añadir import
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author BIENVENIDO
 */
public class ReservaBoleto extends javax.swing.JFrame {
    /**
     * Creates new form ComprarBoleto
     */
    private List<Pasajero> pasajerosDeLaReserva = new ArrayList<>();
    private Map<Integer, Pais> paisesMap = new HashMap<>();
    private Map<Integer, Departamento> departamentosMap = new HashMap<>();
    private Map<Integer, Vuelo> vuelosMap = new HashMap<>();
    private Map<Integer, Asiento> asientosMap = new HashMap<>();
     private Map<Integer, Pasajero> pasajerosMap = new HashMap<>();
    private Map<Integer, Reserva> reservasMap = new HashMap<>();
    private Map<Integer, Boleto> boletosMap = new HashMap<>();
    private Map<Integer,Compra> comprasMap = new HashMap<>();

    private int nextBoletoId = 1;
    private int nextReservaId = 1; 
    private int nextPasajeroId = 1;
    private Pasajero pasajeroSeleccionado;
    
    public ReservaBoleto() {
        initComponents();
        initializeData();
        setupComboBoxes();
        loadVuelosTable();
        setupTableSelection();
        loadVuelosTableRegreso();
        setupTableSelection2();
       
        
        
    }
    private void initializeData() {
    // Cargar en el orden correcto para evitar problemas de dependencias
    cargarPaises();
    cargarDepartamentos();
    
    // Debug: Verificar que los datos se han cargado correctamente
    System.out.println("Países cargados: " + paisesMap.size());
    for (Pais pais : paisesMap.values()) {
        System.out.println("País: " + pais.getNombre());
    }
    
    System.out.println("Departamentos cargados: " + departamentosMap.size());
    for (Departamento dept : departamentosMap.values()) {
        System.out.println("Departamento: " + dept.getNombre() + " - País: " + dept.getPais().getNombre());
    }
    
    cargarVuelos();
    cargarAsientos();
    cargarBoletos();
    cargarPasajeros();
}
    
     private void setupComboBoxes() {
    jComboBox3.removeAllItems();
    jComboBox2.removeAllItems();
    
    // Configurar ComboBox de origen (jComboBox3)
    jComboBox3.addItem("Seleccione origen");
    for (Departamento dept : departamentosMap.values()) {
        jComboBox3.addItem(dept.getNombre());
    }
    
    // Configurar ComboBox de destino (jComboBox2)
    jComboBox2.addItem("Seleccione destino");
    for (Departamento dept : departamentosMap.values()) {
        jComboBox2.addItem(dept.getNombre());
    }
    jComboBox3.setSelectedIndex(0);
    jComboBox2.setSelectedIndex(0);
    
    // Debug: Verificar que los datos se han cargado
    System.out.println("Departamentos cargados: " + departamentosMap.size());
    System.out.println("Países cargados: " + paisesMap.size());
}
    
    private void loadVuelosTable() {
        String[] columnNames = {"ID", "Codigo Vuelo", "Origen", "Destino", "Salida", "Llegada", "Distancia", "Precio Base"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        for (Vuelo vuelo : vuelosMap.values()) {
            double precioBase = calcularPrecioPorClase(vuelo.getDistanciaMillas(), "Económico");
            model.addRow(new Object[]{
                vuelo.getId(),
                vuelo.getCodigoVuelo(),
                vuelo.getDepartamentoOrigen().getNombre(),
                vuelo.getDepartamentoDestino().getNombre(),
                vuelo.getFechaSalida().format(formatter),
                vuelo.getFechaLlegada().format(formatter),
                String.format("%.1f mi", vuelo.getDistanciaMillas()),
                String.format("$%.2f", precioBase)
            });
        }
        
        jTable2.setModel(model);
        
        // Ajustar ancho de columnas
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTable2.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(80);
    }
    
    private void setupTableSelection() {
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable2.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updatePriceDisplay();
            }
        });
    }
    
    
        private void loadVuelosTableRegreso() {
        String[] columnNames = {"ID", "Codigo Vuelo", "Origen", "Destino", "Salida", "Llegada", "Distancia", "Precio Base"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        for (Vuelo vuelo : vuelosMap.values()) {
            double precioBase = calcularPrecioPorClase(vuelo.getDistanciaMillas(), "Económico");
            model.addRow(new Object[]{
                vuelo.getId(),
                vuelo.getCodigoVuelo(),
                vuelo.getDepartamentoOrigen().getNombre(),
                vuelo.getDepartamentoDestino().getNombre(),
                vuelo.getFechaSalida().format(formatter),
                vuelo.getFechaLlegada().format(formatter),
                String.format("%.1f mi", vuelo.getDistanciaMillas()),
                String.format("$%.2f", precioBase)
            });
        }
        
        jTable4.setModel(model);
        
        // Ajustar ancho de columnas
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable4.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTable4.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable4.getColumnModel().getColumn(7).setPreferredWidth(80);
    }
    
    
    
    private void setupTableSelection2() {
    jTable4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTable4.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            updatePriceDisplay();
        }
    });
}

    
    private void updatePriceDisplay() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow != -1) {
            int vueloId = (Integer) jTable2.getValueAt(selectedRow, 0);
            Vuelo vuelo = vuelosMap.get(vueloId);
            String clase = (String) jComboBox1.getSelectedItem();
            
            if (vuelo != null && clase != null) {
                double precio = calcularPrecioPorClase(vuelo.getDistanciaMillas(), clase);
                // Podrías agregar un JLabel para mostrar el precio actualizado
                System.out.println("Precio calculado: $" + String.format("%.2f", precio));
            }
        }
        
        int selectedRow2 = jTable4.getSelectedRow();
        if (selectedRow2 != -1) {
            int vueloId2 = (Integer) jTable4.getValueAt(selectedRow2, 0);
            Vuelo vuelo2 = vuelosMap.get(vueloId2);
            String clase = (String) jComboBox1.getSelectedItem();
            
            if (vuelo2 != null && clase != null) {
                double precio = calcularPrecioPorClase(vuelo2.getDistanciaMillas(), clase);
                // Podrías agregar un JLabel para mostrar el precio actualizado
                System.out.println("Precio calculado: $" + String.format("%.2f", precio));
            }
        }
    }
    private double calcularPrecioPorClase(double distancia, String clase) {
        double precioPorMilla = 0.10;
        double multiplicador = 1.0;
        
        switch (clase) {
            case "Negocios":
                multiplicador = 1.5;
                break;
            case "Primera Clase":
                multiplicador = 2.0;
                break;
            case "Económico":
            default:
                multiplicador = 1.0;
                break;
        }
        
        return distancia * precioPorMilla * multiplicador;
    }
    
    
    
    
    private void filtrarVuelos() {
        String origenSeleccionado = (String) jComboBox3.getSelectedItem();
        String destinoSeleccionado = (String) jComboBox2.getSelectedItem();
        
        if (origenSeleccionado == null || destinoSeleccionado == null || 
            origenSeleccionado.equals("Seleccione origen") || 
            destinoSeleccionado.equals("Seleccione destino")) {
            loadVuelosTable(); // Mostrar todos los vuelos
            return;
        }
        
        String[] columnNames = {"ID", "Número Vuelo", "Origen", "Destino", "Salida", "Llegada", "Distancia", "Precio Base"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        for (Vuelo vuelo : vuelosMap.values()) {
            if (vuelo.getDepartamentoOrigen().getNombre().equals(origenSeleccionado) &&
                vuelo.getDepartamentoDestino().getNombre().equals(destinoSeleccionado)) {
                
                double precioBase = calcularPrecioPorClase(vuelo.getDistanciaMillas(), "Económico");
                model.addRow(new Object[]{
                    vuelo.getId(),
                    vuelo.getCodigoVuelo(),
                    vuelo.getDepartamentoOrigen().getNombre(),
                    vuelo.getDepartamentoDestino().getNombre(),
                    vuelo.getFechaSalida().format(formatter),
                    vuelo.getFechaLlegada().format(formatter),
                    String.format("%.1f mi", vuelo.getDistanciaMillas()),
                    String.format("$%.2f", precioBase)
                });
            }
        }
        
        jTable2.setModel(model);
    }
    
     private void procesarSeleccion() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un vuelo de la tabla.", 
                "Selección requerida", JOptionPane.WARNING_MESSAGE);
            return;
        } 
  
        
        int vueloId = (Integer) jTable2.getValueAt(selectedRow, 0);
        Vuelo vuelo = vuelosMap.get(vueloId);
        String clase = (String) jComboBox1.getSelectedItem();
        
        // Verificar disponibilidad de asientos
        boolean hayAsientosDisponibles = vuelo.getAsientos().stream()
            .anyMatch(asiento -> asiento.isDisponible() && asiento.getClase().equals(clase));
        
       
        
        if (!hayAsientosDisponibles) {
            JOptionPane.showMessageDialog(this, 
                "No hay asientos disponibles en clase " + clase + " para este vuelo.", 
                "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        double precio = calcularPrecioPorClase(vuelo.getDistanciaMillas(), clase);
        
        String mensaje = String.format(
            "Vuelo seleccionado: %s\n" +
            "Origen: %s\n" +
            "Destino: %s\n" +
            "Clase: %s\n" +
            "Precio: $%.2f\n\n" +
            "¿Desea proceder con la compra?",
            vuelo.getCodigoVuelo(),
            vuelo.getDepartamentoOrigen().getNombre(),
            vuelo.getDepartamentoDestino().getNombre(),
            clase,
            precio
        );
        
        int respuesta = JOptionPane.showConfirmDialog(this, mensaje, 
            "Confirmar selección", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            // Aquí podrías abrir otra ventana para ingresar datos del pasajero
            // o proceder directamente con la compra
            JOptionPane.showMessageDialog(this, "Procediendo con la compra...", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
  private void cargarPaises() {
    try (BufferedReader br = new BufferedReader(new FileReader("src/paises.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 2) {
                try {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    Pais pais = new Pais(id, nombre);
                    paisesMap.put(id, pais);
                    System.out.println("País cargado: " + nombre);
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear ID de país en línea: " + line);
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error al cargar países: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    private void cargarDepartamentos() {
    try (BufferedReader br = new BufferedReader(new FileReader("src/departamentos.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 3) {
                try {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    int paisId = Integer.parseInt(parts[2]);
                    
                    Pais pais = paisesMap.get(paisId);
                    if (pais != null) {
                        Departamento dept = new Departamento(id, nombre, pais);
                        departamentosMap.put(id, dept);
                        System.out.println("Departamento cargado: " + nombre + " - País: " + pais.getNombre());
                    } else {
                        System.err.println("País no encontrado para departamento: " + nombre + " (paisId: " + paisId + ")");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear números en línea: " + line);
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error al cargar departamentos: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    private void cargarVuelos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/vuelos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Vuelo vuelo = Vuelo.fromString(line, departamentosMap);
                    if (vuelo != null) {
                        vuelosMap.put(vuelo.getId(), vuelo);
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar vuelo: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar vuelos: " + e.getMessage());
        }
    }
    
    private void cargarAsientos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/asientos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0]);
                    String numeroAsiento = parts[1];
                    String clase = parts[2];
                    boolean disponible = Boolean.parseBoolean(parts[3]);
                    int vueloId = Integer.parseInt(parts[4]);
                    
                    Vuelo vuelo = vuelosMap.get(vueloId);
                    if (vuelo != null) {
                        Asiento asiento = new Asiento(id, numeroAsiento, clase, disponible, vuelo);
                        asientosMap.put(id, asiento);
                        vuelo.addAsiento(asiento);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar asientos: " + e.getMessage());
        }
    }
    
   public void agregarPasajeroAReserva(Pasajero pasajero) {
    this.pasajerosDeLaReserva.add(pasajero); // Añade el pasajero a la lista
    
    JOptionPane.showMessageDialog(this, 
        "Pasajero '" + pasajero.getNombre() + "' añadido a la reserva.\n" +
        "Total de pasajeros: " + this.pasajerosDeLaReserva.size(), 
        "Pasajero Añadido", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // **Recomendación:** Actualiza un JList o un JTextArea para mostrar la lista de pasajeros añadidos.
}
    
    
    private void cargarBoletos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/boletos.txt"))) {
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                // Implementar según el formato del archivo boletos.txt
                // Por ahora está vacío según el archivo proporcionado
            }
        } catch (IOException e) {
            // Archivo no existe o está vacío, no es un error crítico
        }
    }
    
private void guardarReservaEnArchivo(Reserva reserva) {
    // Une los IDs de todos los pasajeros de la lista en un solo String, separado por comas
    String idsDePasajeros = reserva.getPasajero().stream()
                                   .map(p -> String.valueOf(p.getIdPasajero()))
                                   .collect(Collectors.joining(","));

    // Formato: ID|CodigoReserva|IDVueloIda|IDVueloRegreso|IDsPasajeros|FechaReserva|Confirmada
    String linea = String.format("%d|%s|%d|%s|%s|%s|%b%n",
            reserva.getIdReserva(),
            reserva.getCodigoReserva(),
            reserva.getVueloIda().getId(),
            (reserva.getVueloRegreso() != null) ? String.valueOf(reserva.getVueloRegreso().getId()) : "null",
            idsDePasajeros, // <-- Usar el string con los IDs separados por comas
            reserva.getFechareserva().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            reserva.isConfirmada()
    );

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/reservas.txt", true))) {
        writer.write(linea);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al guardar la reserva: " + e.getMessage(), 
            "Error de Archivo", 
            JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void cargarPasajeros() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/pasajeros.txt"))) {
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                // Implementar según el formato del archivo pasajeros.txt
                // Por ahora está vacío según el archivo proporcionado
            }
        } catch (IOException e) {
            // Archivo no existe o está vacío, no es un error crítico
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        Buscar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jButtonPasajero = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        Seleccionar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Información del vuelo");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1440, 10));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setSelectedItem(jComboBox3);
        jComboBox2.setToolTipText("");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 120, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Seleccione la cantidad de Pasajero");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setToolTipText("");
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("Fecha de vuelta");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Origen");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Clase");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Fecha de ida");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Económico", "Negocios", "Primera Clase" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 110, -1));

        Buscar.setBackground(new java.awt.Color(51, 153, 255));
        Buscar.setForeground(new java.awt.Color(255, 255, 255));
        Buscar.setText("🔎Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jPanel2.add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 110, 30));
        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, -20, -1, -1));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, -1));
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 120, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Destino");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jButtonPasajero.setText("Anadir");
        jButtonPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasajeroActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        Regresar.setBackground(new java.awt.Color(255, 51, 51));
        Regresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Regresar.setText("X");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });
        jPanel2.add(Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 60, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1320, 250));

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(255, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1440, 10));

        Seleccionar.setBackground(new java.awt.Color(51, 255, 0));
        Seleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Seleccionar.setText("SIGUIENTE");
        Seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionarActionPerformed(evt);
            }
        });
        jPanel3.add(Seleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1211, 423, 100, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setText("Vuelos Disponibles ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 204, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel11.setText("Vuelos de Ida");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 610, 330));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 630, 380));

        jPanel4.setBackground(new java.awt.Color(255, 204, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Vuelos de Vuelta");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 590, 330));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 610, 380));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1320, 470));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1510, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
     updatePriceDisplay();
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
        filtrarVuelos();
    }//GEN-LAST:event_BuscarActionPerformed

    private void SeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionarActionPerformed
        // TODO add your handling code here:
        //procesarSeleccion();
    // 1. Validar que se ha seleccionado un vuelo
    int selectedRow = jTable2.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un vuelo de la tabla.", "Vuelo Requerido", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    
   int selectedRow2 = jTable4.getSelectedRow();
    if (selectedRow2 == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un vuelo de la tabla.", "Vuelo Requerido", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 2. Validar que se ha añadido AL MENOS UN pasajero
    if (pasajerosDeLaReserva.isEmpty()) { // <-- CAMBIAR LA CONDICIÓN
        JOptionPane.showMessageDialog(this, "Por favor, añada al menos un pasajero antes de continuar.", "Pasajero Requerido", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Obtener el Vuelo seleccionado
    int vueloId = (Integer) jTable2.getValueAt(selectedRow, 0);
    Vuelo vueloSeleccionado = vuelosMap.get(vueloId);
    
    int vueloId2 = (Integer) jTable4.getValueAt(selectedRow2, 0);
    Vuelo vueloSeleccionado2 = vuelosMap.get(vueloId2);

    // 4. Crear el objeto Reserva, pasando la lista completa
    String codigoReserva = "RES" + System.currentTimeMillis(); 
    Reserva nuevaReserva = new Reserva(
            nextReservaId++,
            codigoReserva,
            vueloSeleccionado,
            null, 
            pasajerosDeLaReserva, // <-- PASAR LA LISTA COMPLETA
            LocalDateTime.now(),
            false
    );
    
     String codigoReserva2 = "RES" + System.currentTimeMillis(); 
    Reserva nuevaReserva2 = new Reserva(
            nextReservaId++,
            codigoReserva2,
            vueloSeleccionado2,
            null, 
            pasajerosDeLaReserva, // <-- PASAR LA LISTA COMPLETA
            LocalDateTime.now(),
            false
    );

    // 5. Guardar la reserva en el archivo (se necesita actualizar el método)
    guardarReservaEnArchivo(nuevaReserva);
    guardarReservaEnArchivo(nuevaReserva2);
    
    // 6. Abrir MetodoPago
    MetodoPago pago = new MetodoPago(nuevaReserva);
    pago.setVisible(true);
    this.dispose();
        
    }//GEN-LAST:event_SeleccionarActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed

               // TODO add your handling code here:
                   Menu menu = new Menu();
    // Hace visible la ventana del menú.
    menu.setVisible(true);
    // Cierra la ventana actual (el formulario de boletos).
    this.dispose();
        
    }//GEN-LAST:event_RegresarActionPerformed

    private void jButtonPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasajeroActionPerformed
        // TODO add your handling code here:
        
        CrearPasajero menuPasajero = new CrearPasajero(this);
        menuPasajero.setVisible(true);
        
        
        
    // Cierra la ventana actual (el formulario de boletos).
    
        
    }//GEN-LAST:event_jButtonPasajeroActionPerformed
 
    
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReservaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservaBoleto().setVisible(true);
                
            
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton Seleccionar;
    private javax.swing.JButton jButtonPasajero;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
