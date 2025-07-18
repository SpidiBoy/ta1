/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Aerolina.Pasajero;
import Aerolina.Boleto;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class CrearPasajero extends javax.swing.JFrame {

    private static int contadorId = 1; // Contador para generar IDs únicos
    private ReservaBoleto owner;
    /**
     * Creates new form CrearPasajero
     */
    public CrearPasajero() {
        initComponents();
        // Configurar validación en tiempo real para el campo de maletas
        configurarValidacionMaletas();
    }
    public CrearPasajero(ReservaBoleto owner) { // <-- MODIFICAR CONSTRUCTOR
        this.owner = owner; // <-- AÑADIR ESTA LÍNEA
        initComponents();
        configurarValidacionMaletas();
    }
    /**
     * Configura la validación en tiempo real para el campo de maletas
     */
    private void configurarValidacionMaletas() {
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                // Solo permitir dígitos
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
            
            public void keyReleased(java.awt.event.KeyEvent evt) {
                validarCantidadMaletas();
            }
        });
    }
    
    /**
     * Valida que la cantidad de maletas no exceda 4
     */
    private void validarCantidadMaletas() {
        String texto = jTextField8.getText().trim();
        if (!texto.isEmpty() && !texto.equals("Ingrese la Cantidad")) {
            try {
                int cantidad = Integer.parseInt(texto);
                if (cantidad > 4) {
                    jTextField8.setForeground(Color.RED);
                    jTextField8.setText("4"); // Limitar a 4
                } else {
                    jTextField8.setForeground(Color.BLACK);
                }
            } catch (NumberFormatException e) {
                jTextField8.setForeground(Color.RED);
            }
        }
    }
    
    /**
     * Valida que todos los campos estén llenos
     */
    private boolean validarCampos() {
        // Validar nombre
        if (jTextField2.getText().trim().isEmpty() || jTextField2.getText().equals("Ingrese el Nombre")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del pasajero.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
            return false;
        }
        
        // Validar apellido paterno
        if (jTextField5.getText().trim().isEmpty() || jTextField5.getText().equals("Ingrese el Apellido")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el apellido paterno.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField5.requestFocus();
            return false;
        }
        
        // Validar apellido materno
        if (jTextField3.getText().trim().isEmpty() || jTextField3.getText().equals("Ingrese el Apellido")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el apellido materno.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField3.requestFocus();
            return false;
        }
        
        // Validar DNI
        if (jTextField4.getText().trim().isEmpty() || jTextField4.getText().equals("Ingrese el DNI")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el DNI.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField4.requestFocus();
            return false;
        }
        
        // Validar que el DNI tenga 8 dígitos
        String dni = jTextField4.getText().trim();
        if (dni.length() != 8 || !dni.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos.", "DNI Inválido", JOptionPane.ERROR_MESSAGE);
            jTextField4.requestFocus();
            return false;
        }
        
        // Validar fecha de nacimiento
        if (jDateChooser2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione la fecha de nacimiento.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validar nacionalidad
        if (jTextField1.getText().trim().isEmpty() || jTextField1.getText().equals("Ingrese la Nacionalidad")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese la nacionalidad.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField1.requestFocus();
            return false;
        }
        
        // Validar teléfono
        if (jTextField6.getText().trim().isEmpty() || jTextField6.getText().equals("Ingrese el Telefono")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el teléfono.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField6.requestFocus();
            return false;
        }
        
        // Validar cantidad de maletas
        if (jTextField8.getText().trim().isEmpty() || jTextField8.getText().equals("Ingrese la Cantidad")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese la cantidad de maletas.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            jTextField8.requestFocus();
            return false;
        }
        
        try {
            int maletas = Integer.parseInt(jTextField8.getText().trim());
            if (maletas < 0 || maletas > 4) {
                JOptionPane.showMessageDialog(this, "La cantidad de maletas debe estar entre 0 y 4.", "Cantidad Inválida", JOptionPane.ERROR_MESSAGE);
                jTextField8.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para las maletas.", "Formato Inválido", JOptionPane.ERROR_MESSAGE);
            jTextField8.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarCampos() {
        jTextField2.setText("Ingrese el Nombre");
        jTextField2.setForeground(new Color(204, 204, 204));
        
        jTextField5.setText("Ingrese el Apellido");
        jTextField5.setForeground(new Color(204, 204, 204));
        
        jTextField3.setText("Ingrese el Apellido");
        jTextField3.setForeground(new Color(204, 204, 204));
        
        jTextField4.setText("Ingrese el DNI");
        jTextField4.setForeground(new Color(204, 204, 204));
        
        jDateChooser2.setDate(null);
        
        jTextField1.setText("Ingrese la Nacionalidad");
        jTextField1.setForeground(new Color(204, 204, 204));
        
        jTextField6.setText("Ingrese el Telefono");
        jTextField6.setForeground(new Color(204, 204, 204));
        
        jTextField8.setText("Ingrese la Cantidad");
        jTextField8.setForeground(new Color(204, 204, 204));
    }
    
    /**
     * Guarda el pasajero en el archivo de texto
     */
    private void guardarPasajeroEnArchivo(Pasajero pasajero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/pasajeros.txt", true))) {
            // Formato: ID|Nombre|ApellidoP|ApellidoM|DNI|FechaNacimiento|Nacionalidad|Telefono|Maletas
            String linea = String.format("%d|%s|%s|%s|%s|%s|%s|%s|%d%n",
                    pasajero.getIdPasajero(),
                    pasajero.getNombre(),
                    pasajero.getApellidoP(),
                    pasajero.getApellidoM(),
                    pasajero.getDni(),
                    pasajero.getFechaNacimiento().toString(),
                    pasajero.getNacionalidad(),
                    pasajero.getTelefono(),
                    pasajero.getMaleta());
            
            writer.write(linea);
            writer.flush();
            
            JOptionPane.showMessageDialog(this, 
                "Pasajero registrado exitosamente con ID: " + pasajero.getIdPasajero(), 
                "Registro Exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar el pasajero: " + e.getMessage(), 
                "Error de Archivo", 
                JOptionPane.ERROR_MESSAGE);
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

        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        RegistarPasajero = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField8 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        Cerrar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Ingrese la Nacionalidad");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField1MousePressed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 132, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Apellido Paterno");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 166, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Nombre del Pasajero");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 25, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Apellido Materno");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 93, 163, -1));

        jTextField2.setForeground(new java.awt.Color(204, 204, 204));
        jTextField2.setText("Ingrese el Nombre");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField2MousePressed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 59, 163, -1));

        jTextField3.setForeground(new java.awt.Color(204, 204, 204));
        jTextField3.setText("Ingrese el Apellido");
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField3MousePressed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 194, 163, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("DNI");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 234, -1, -1));

        jTextField4.setForeground(new java.awt.Color(204, 204, 204));
        jTextField4.setText("Ingrese el DNI");
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField4MousePressed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 262, 163, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setText("Fecha De Nacimiento");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 25, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel8.setText("Nacionalidad");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 93, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel9.setText("Telefono");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 166, -1, -1));

        jTextField5.setForeground(new java.awt.Color(204, 204, 204));
        jTextField5.setText("Ingrese el Apellido");
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField5MousePressed(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 132, 163, -1));

        jTextField6.setForeground(new java.awt.Color(204, 204, 204));
        jTextField6.setText("Ingrese el Telefono");
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField6MousePressed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 194, 138, -1));

        RegistarPasajero.setBackground(new java.awt.Color(18, 90, 173));
        RegistarPasajero.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RegistarPasajero.setForeground(new java.awt.Color(255, 255, 255));
        RegistarPasajero.setText("Registrar Pasajero");
        RegistarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistarPasajeroActionPerformed(evt);
            }
        });
        getContentPane().add(RegistarPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 275, -1, 41));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel10.setText("Cantidad de Maletas (Max: 4)");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 302, -1, -1));
        getContentPane().add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 59, 138, -1));

        jTextField8.setForeground(new java.awt.Color(204, 204, 204));
        jTextField8.setText("Ingrese la Cantidad");
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField8MousePressed(evt);
            }
        });
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 330, 163, -1));

        jPanel1.setBackground(new java.awt.Color(171, 186, 238));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cerrar.setBackground(new java.awt.Color(255, 51, 51));
        Cerrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Cerrar.setForeground(new java.awt.Color(255, 255, 255));
        Cerrar.setText("X");
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel1.add(Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 3, 30, 30));

        jSeparator1.setForeground(new java.awt.Color(51, 0, 51));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(200, 10));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 50, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 510, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistarPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistarPasajeroActionPerformed
        // Validar todos los campos
        if (!validarCampos()) {
            return;
        }
        
        try {
            // Obtener los datos del formulario
            String nombre = jTextField2.getText().trim();
            String apellidoP = jTextField5.getText().trim();
            String apellidoM = jTextField3.getText().trim();
            String dni = jTextField4.getText().trim();
            String nacionalidad = jTextField1.getText().trim();
            String telefono = jTextField6.getText().trim();
            int maletas = Integer.parseInt(jTextField8.getText().trim());
            
            // Convertir fecha
            Date fechaSeleccionada = jDateChooser2.getDate();
            LocalDate fechaNacimiento = fechaSeleccionada.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            
            // Crear objeto Pasajero (sin boleto por ahora)
            Pasajero nuevoPasajero = new Pasajero(
                    contadorId++,
                    nombre,
                    apellidoP,
                    apellidoM,
                    dni,
                    fechaNacimiento,
                    nacionalidad,
                    telefono,
                    null, // boleto se asignará después
                    maletas
            );
            
            // Guardar en archivo
            guardarPasajeroEnArchivo(nuevoPasajero);
            
            // Limpiar campos para nuevo registro
             if (owner != null) {
            // Llama al nuevo método en ReservaBoleto
            owner.agregarPasajeroAReserva(nuevoPasajero); // <-- MODIFICAR ESTA LLAMADA
             }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al registrar pasajero: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_RegistarPasajeroActionPerformed

    private void jTextField2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MousePressed
        if (jTextField2.getText().equals("Ingrese el Nombre")) {
            jTextField2.setText("");
            jTextField2.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField2MousePressed

    private void jTextField5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MousePressed
        if (jTextField5.getText().equals("Ingrese el Apellido")) {
            jTextField5.setText("");
            jTextField5.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField5MousePressed

    private void jTextField4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MousePressed
        if (jTextField4.getText().equals("Ingrese el DNI")) {
            jTextField4.setText("");
            jTextField4.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField4MousePressed

    private void jTextField3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MousePressed
        if (jTextField3.getText().equals("Ingrese el Apellido")) {
            jTextField3.setText("");
            jTextField3.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField3MousePressed

    private void jTextField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MousePressed
        if (jTextField1.getText().equals("Ingrese la Nacionalidad")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField1MousePressed

    private void jTextField6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MousePressed
        if (jTextField6.getText().equals("Ingrese el Telefono")) {
            jTextField6.setText("");
            jTextField6.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField6MousePressed

    private void jTextField8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MousePressed
        if (jTextField8.getText().equals("Ingrese la Cantidad")) {
            jTextField8.setText("");
            jTextField8.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField8MousePressed

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_CerrarActionPerformed

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
            java.util.logging.Logger.getLogger(CrearPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearPasajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearPasajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton RegistarPasajero;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}