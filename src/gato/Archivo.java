/**********************************************************************/
/* Autor: Francisco I. Leyva
 * Página web: http://www.panchosoft.com
 * Correo electrónico: yagami_2@hotmail.com
 *
 * Clase que permite crear y modificar archivos.
 *
/**********************************************************************/
package gato;

import java.io.*;
import javax.swing.*;
import java.util.*;

/* Clase para leer y escribir archivos con el formato dado. */

public class Archivo {
    
    /* Variables */
    private File archivo;
    public String contenido, ruta;
    private JFileChooser selector = new JFileChooser();
    private JOptionPane alerta = new JOptionPane();
    private StringTokenizer divisor;
    private FileReader lector;
    private BufferedReader buffer_lector;
    private BufferedWriter buffer_escritor;
    private FileWriter escritor;
    private PrintWriter escribir;
    public int indice = 0;
    
    /* Constructor manual. */
    public Archivo( String ruta ) {
        
        /* Creamos la referencia. */
        archivo = new File( ruta );
        
        /* Comprobamos si existe, si no enviamos alerta. */
        if ( this.existe() )
            this.ruta = ruta;

    }

    /* Método que comprueba si existe el archivo. */
    public boolean existe(){
        return ( archivo.exists() );
    }
    
    /* Método que devuelve el contenido en una string */
    public String LeerContenido(){
        
        try{
            /* Creamos los lectores. */
            lector = new FileReader( this.archivo );
            buffer_lector = new BufferedReader( lector );
            String ctemp;

             /* Almacenamos el contenido.*/
             this.contenido = "";
           
            while ( (ctemp = buffer_lector.readLine() ) != null)
                this.contenido += ctemp + "\r\n";

             return this.contenido;
        
          } catch ( IOException ex ){
                alerta.showMessageDialog( null, ex.getMessage() );
          }
        
         return null;
        
    }
    
   /* Método que devuelve el contenido en un array de strings. */
    public String[] LeerLineas(){
        
        String[] lineas = new String[ NumeroLineas() ];
        int c = 0;
        try{
            
            divisor = new StringTokenizer ( LeerContenido(), "\r\n" );
            
            lineas[c] = divisor.nextToken();
            
            while ( divisor.hasMoreTokens() ){
                c ++;
                lineas[c] = divisor.nextToken();
            }
            
            return lineas;
            
          } catch ( Exception ex ){
                alerta.showMessageDialog( null, ex.getMessage() );
          }
        
         return null;
        
    }

    /* Método que escribe un contenido a un archivo.*/
    public boolean EscribirContenido( String cadena ){
        
        try{
    
            /*Creamos las variables.*/
            escritor = new FileWriter ( archivo );
            buffer_escritor = new BufferedWriter ( escritor );
            escribir = new PrintWriter( buffer_escritor );
               
            /*Escribimos al archivo */
            escribir.write( cadena );

            escribir.close();
            
            return true;
        
        } catch ( IOException ex ){
            alerta.showMessageDialog( null, ex.getMessage() );
        }
        
        return false;
        
    }
    
    /* Método que escribe en una línea nueva. */
    public boolean EscribirLinea( String cadena ){
        
        try{
    
            /*Creamos las variables.*/
            String ctemp = LeerContenido() + cadena;
            escritor = new FileWriter ( archivo );
            buffer_escritor = new BufferedWriter ( escritor );
            escribir = new PrintWriter( buffer_escritor );
               
            /*Escribimos al archivo */
            escribir.println( ctemp );

            escribir.close();
            
            return true;
        
        } catch ( IOException ex ){
            alerta.showMessageDialog( null, ex.getMessage() );
        }
        
        return false;
        
    }
    
    /* Método que devuelve el número de líneas */
    public int NumeroLineas(){
        
         try{
            /* Creamos los lectores. */
            lector = new FileReader( this.archivo );
            buffer_lector = new BufferedReader( lector );
            int c = 0;
           
            //Contamos.
            while ( buffer_lector.readLine() != null)
               c ++;
            

            
            return c;
        
          } catch ( IOException ex ){
                alerta.showMessageDialog( null, ex.getMessage() );
          }
        
         return 0;
        
    }
   
    
}
