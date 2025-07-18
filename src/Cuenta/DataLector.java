    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cuenta;



import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Map;

/**
 *
 * @author win12
 */
public class DataLector {

    
  public static Usuario[] getUsuarios() {

        // Variable para contar el número de usuarios en el archivo
        int cantUsuarios = 0;

        // Primer bloque try-with-resources para contar el número de líneas (usuarios) en el archivo
        try (FileReader fr = new FileReader("src/usuarios.txt")) {

            // BufferedReader para leer el archivo
            BufferedReader br = new BufferedReader(fr);
            

            // Leer cada línea del archivo y contar el número de líneas
            while(br.readLine() != null) cantUsuarios++;
            

        } catch(Exception e) {
            // Manejo de excepciones en caso de error al leer el archivo
            e.printStackTrace(); 
        }

        // Crear un arreglo de Usuario con el tamaño determinado anteriormente
        Usuario[] usuarios = new Usuario[cantUsuarios];

        // Reiniciar el contador de usuarios
        cantUsuarios = 0;

        // Segundo bloque try-with-resources para leer las líneas y crear objetos Usuario
        try (FileReader fr = new FileReader("src/usuarios.txt")) {

            // BufferedReader para leer el archivo
            BufferedReader br = new BufferedReader(fr);

            String linea;
            // Leer cada línea del archivo y crear un nuevo objeto Usuario para cada línea
            while((linea = br.readLine()) != null) {
                // Separar la línea por el delimitador "|" y crear un nuevo objeto Usuario
                usuarios[cantUsuarios++] = new Usuario(linea.split("\\|")[2], linea.split("\\|")[3]);
            }

        } catch(Exception e) {
            // Manejo de excepciones en caso de error al leer el archivo
            e.printStackTrace();
        }

        // Retornar el arreglo de usuarios
        return usuarios;
    }


  
   

    
 
}

    













    
   



    
