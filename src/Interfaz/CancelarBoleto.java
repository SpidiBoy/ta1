/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Aerolina.Asiento;
import Aerolina.Boleto;
import Aerolina.Pasajero;
import Aerolina.Vuelo;
import Cuenta.DataLector;
import Viaje.Departamento;
import Viaje.Pais;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author BIENVENIDO
 */
public class CancelarBoleto extends JFrame {

    // --- Nueva Paleta de Colores (Tema Claro y Profesional) ---
    private final Color COLOR_AZUL_PRINCIPAL = new Color(13, 110, 253);
    private final Color COLOR_VERDE_EXITO = new Color(25, 135, 84);
    private final Color COLOR_ROJO_PELIGRO = new Color(220, 53, 69);
    private final Color COLOR_GRIS_INFO = new Color(108, 117, 125);
    
    private final Color COLOR_FONDO = new Color(248, 249, 250);
    private final Color COLOR_PANEL = Color.WHITE;
    private final Color COLOR_TEXTO_PRINCIPAL = new Color(33, 37, 41);
    private final Color COLOR_BORDE = new Color(222, 226, 230);
    
    private JTabbedPane tabbedPane;
    private JTable vuelosTable;
    private JTable boletosTable;
    private JTextField codigoReservaField;

    private Map<Integer, Pais> paisesMap = new HashMap<>();
    private Map<Integer, Departamento> departamentosMap = new HashMap<>();
    private Map<Integer, Vuelo> vuelosMap = new HashMap<>();
    private Map<Integer, Asiento> asientosMap = new HashMap<>();
    private Map<Integer, Boleto> boletosMap = new HashMap<>();
    private Map<Integer, Pasajero> pasajerosMap = new HashMap<>();

    public CancelarBoleto() {
        initializeData();
        initializeUI();
        loadTablesData();
    }

    private void initializeData() {
        try {
            // Usar DataLector para cargar datos de manera consistente
            paisesMap = DataLector.getPaises();
            departamentosMap = DataLector.getDepartamentos(paisesMap);
            vuelosMap = DataLector.getVuelos(departamentosMap);
            asientosMap = DataLector.getAsientos(vuelosMap);
            
            // Cargar boletos y pasajeros
            cargarBoletos();
            cargarPasajeros();
            
            System.out.println("Datos cargados exitosamente:");
            System.out.println("Países: " + paisesMap.size());
            System.out.println("Departamentos: " + departamentosMap.size());
            System.out.println("Vuelos: " + vuelosMap.size());
            System.out.println("Asientos: " + asientosMap.size());
            System.out.println("Boletos: " + boletosMap.size());
            System.out.println("Pasajeros: " + pasajerosMap.size());
            
        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
            // Inicializar mapas vacíos para evitar errores
            if (paisesMap == null) paisesMap = new HashMap<>();
            if (departamentosMap == null) departamentosMap = new HashMap<>();
            if (vuelosMap == null) vuelosMap = new HashMap<>();
            if (asientosMap == null) asientosMap = new HashMap<>();
            if (boletosMap == null) boletosMap = new HashMap<>();
            if (pasajerosMap == null) pasajerosMap = new HashMap<>();
        }
    }

    private void initializeUI() {
        setTitle("Sistema de Cancelación de Boletos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(COLOR_FONDO);
        tabbedPane.setForeground(COLOR_TEXTO_PRINCIPAL);

        tabbedPane.addTab("Cancelar Boleto", createCancelacionPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createCancelacionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Panel superior con búsqueda
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(COLOR_PANEL);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel codigoLabel = new JLabel("Código de Reserva:");
        codigoLabel.setForeground(COLOR_TEXTO_PRINCIPAL);
        codigoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        topPanel.add(codigoLabel);
        
        codigoReservaField = new JTextField(15);
        styleTextField(codigoReservaField);
        topPanel.add(codigoReservaField);

        JButton buscarButton = new JButton("Buscar");
        styleButton(buscarButton, COLOR_AZUL_PRINCIPAL, Color.WHITE);
        buscarButton.addActionListener(e -> buscarBoleto());
        topPanel.add(buscarButton);
        
        JButton actualizarButton = new JButton("Mostrar Todos");
        styleButton(actualizarButton, COLOR_GRIS_INFO, Color.WHITE);
        actualizarButton.addActionListener(e -> actualizarTablaBoletos());
        topPanel.add(actualizarButton);

        // Panel central con tabla de boletos
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(COLOR_PANEL);
        centerPanel.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        
        // Título del panel
        JLabel tituloLabel = new JLabel("Boletos Disponibles para Cancelar");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tituloLabel.setForeground(COLOR_TEXTO_PRINCIPAL);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(tituloLabel, BorderLayout.NORTH);
        
        boletosTable = new JTable();
        styleTable(boletosTable);
        JScrollPane scrollPane = new JScrollPane(boletosTable);
        scrollPane.setBorder(null);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        bottomPanel.setBackground(COLOR_FONDO);

        JButton cancelarButton = new JButton("Cancelar Boleto Seleccionado");
        styleButton(cancelarButton, COLOR_ROJO_PELIGRO, Color.WHITE);
        cancelarButton.addActionListener(e -> cancelarBoleto());
        bottomPanel.add(cancelarButton);
        
        JButton regresarButton = new JButton("Regresar");
        styleButton(regresarButton, COLOR_ROJO_PELIGRO, Color.WHITE);
        regresarButton.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                window.dispose();
            }
        });
        bottomPanel.add(regresarButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    // --- Métodos de estilo reutilizables ---
    private void styleButton(JButton button, Color backgroundColor, Color foregroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        Color hoverColor = backgroundColor.brighter();
        Color pressColor = backgroundColor.darker();

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(hoverColor);
            }
        });
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBackground(Color.WHITE);
        textField.setForeground(COLOR_TEXTO_PRINCIPAL);
        textField.setCaretColor(COLOR_AZUL_PRINCIPAL);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleTable(JTable table) {
        table.setBackground(COLOR_PANEL);
        table.setForeground(COLOR_TEXTO_PRINCIPAL);
        table.setGridColor(COLOR_BORDE);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setSelectionBackground(COLOR_AZUL_PRINCIPAL.brighter());
        table.setSelectionForeground(Color.WHITE);
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(COLOR_AZUL_PRINCIPAL);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);
        header.setBorder(null);
    }

    private void cargarBoletos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/boletos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        Boleto boleto = Boleto.fromString(line, vuelosMap, pasajerosMap);
                        if (boleto != null) {
                            boletosMap.put(boleto.getIdBoleto(), boleto);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al parsear boleto: " + line);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo boletos.txt no encontrado o vacío. Iniciando con lista vacía.");
        }
    }

    private void cargarPasajeros() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/pasajeros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        Pasajero pasajero = Pasajero.fromString(line);
                        if (pasajero != null) {
                            pasajerosMap.put(pasajero.getIdPasajero(), pasajero);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al parsear pasajero: " + line);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo pasajeros.txt no encontrado o vacío. Iniciando con lista vacía.");
        }
    }

    private void loadTablesData() {
        try {
            // Cargar tabla de vuelos
            if (vuelosTable != null) {
                String[] columnasVuelos = {"ID", "Número", "Origen", "Destino", "Salida", "Llegada", "Distancia"};
                DefaultTableModel modeloVuelos = new DefaultTableModel(columnasVuelos, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                for (Vuelo vuelo : vuelosMap.values()) {
                    modeloVuelos.addRow(new Object[]{
                        vuelo.getIdVuelo(),
                        vuelo.getcodigoVuelo(),
                        vuelo.getDepartamentoOrigen().getNombre(),
                        vuelo.getDepartamentoDestino().getNombre(),
                        vuelo.getFechaSalida().format(formatter),
                        vuelo.getFechaLlegada().format(formatter),
                        vuelo.getDistanciaMillas() + " mi"
                    });
                }
                vuelosTable.setModel(modeloVuelos);
            }

            // Cargar tabla de boletos
            actualizarTablaBoletos();
            
        } catch (Exception e) {
            System.err.println("Error al cargar datos de tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cancelarBoleto() {
        int selectedRow = boletosTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione un boleto para cancelar.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int boletoId = (Integer) boletosTable.getValueAt(selectedRow, 0);
            Boleto boleto = boletosMap.get(boletoId);
            
            if (boleto == null) {
                JOptionPane.showMessageDialog(this,
                        "Error: No se pudo encontrar el boleto seleccionado.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ("Cancelado".equals(boleto.getEstado())) {
                JOptionPane.showMessageDialog(this,
                        "Este boleto ya ha sido cancelado.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea cancelar el boleto " + boleto.getCodigoReserva() + "?",
                    "Confirmar Cancelación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Cancelar el boleto
                boleto.setEstado("Cancelado");
                
                // Liberar el asiento
                Vuelo vuelo = boleto.getVuelo();
                if (vuelo != null && vuelo.getAsientos() != null) {
                    for (Asiento asiento : vuelo.getAsientos()) {
                        if (!asiento.isDisponible()) {
                            asiento.setDisponible(true);
                            break; // Solo liberar el primer asiento ocupado
                        }
                    }
                }
                
                // Guardar cambios
                guardarDatos();
                
                JOptionPane.showMessageDialog(this,
                        "Boleto cancelado exitosamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                
                // Actualizar tabla
                actualizarTablaBoletos();
            }
        } catch (Exception e) {
            System.err.println("Error al cancelar boleto: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cancelar el boleto: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarBoleto() {
        String codigo = codigoReservaField.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un código de reserva para buscar.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Boleto> boletosEncontrados = new ArrayList<>();
        for (Boleto boleto : boletosMap.values()) {
            if (boleto.getCodigoReserva().equalsIgnoreCase(codigo)) {
                boletosEncontrados.add(boleto);
            }
        }

        if (boletosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se encontraron boletos con el código: " + codigo,
                    "Sin Resultados",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            actualizarTablaBoletos(boletosEncontrados);
        }
    }

    private void actualizarTablaBoletos() {
        actualizarTablaBoletos(new ArrayList<>(boletosMap.values()));
    }

    private void actualizarTablaBoletos(List<Boleto> listaBoletos) {
        try {
            String[] columnas = {"ID", "Código", "Precio", "Estado", "Vuelo", "Pasajero", "Fecha Compra"};
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            if (listaBoletos != null && !listaBoletos.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                for (Boleto boleto : listaBoletos) {
                    try {
                        Object[] fila = new Object[]{
                            boleto.getIdBoleto(),
                            boleto.getCodigoReserva(),
                            String.format("$%.2f", boleto.getPrecio()),
                            boleto.getEstado(),
                            boleto.getVuelo() != null ? boleto.getVuelo().getcodigoVuelo() : "N/A",
                            boleto.getPasajero() != null ? 
                                boleto.getPasajero().getNombre() + " " + boleto.getPasajero().getApellidoP() : "N/A",
                            boleto.getFechaCompra() != null ? boleto.getFechaCompra().format(formatter) : "N/A"
                        };
                        modelo.addRow(fila);
                    } catch (Exception e) {
                        System.err.println("Error al agregar boleto a tabla: " + e.getMessage());
                    }
                }
            }

            boletosTable.setModel(modelo);
        } catch (Exception e) {
            System.err.println("Error al actualizar tabla de boletos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void guardarDatos() {
        try {
            // Guardar pasajeros
            try (PrintWriter pw = new PrintWriter(new FileWriter("src/pasajeros.txt"))) {
                for (Pasajero p : pasajerosMap.values()) {
                    pw.println(p.toString());
                }
            }

            // Guardar boletos
            try (PrintWriter pw = new PrintWriter(new FileWriter("src/boletos.txt"))) {
                for (Boleto b : boletosMap.values()) {
                    pw.println(b.toString());
                }
            }

            // Guardar asientos
            try (PrintWriter pw = new PrintWriter(new FileWriter("src/asientos.txt"))) {
                for (Asiento a : asientosMap.values()) {
                    pw.println(a.getIdAsiento() + "|" + a.getNumeroAsiento() + "|" + 
                              a.getClase() + "|" + a.isDisponible() + "|" + 
                              a.getVuelo().getIdVuelo());
                }
            }

            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CancelarBoleto().setVisible(true);
            } catch (Exception e) {
                System.err.println("Error al iniciar CancelarBoleto: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}