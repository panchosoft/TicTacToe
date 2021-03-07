/**********************************************************************/
/* Autor: Francisco I. Leyva
 * Página web: http://www.panchosoft.com
 * Correo electrónico: yagami_2@hotmail.com
 *
 *
/**********************************************************************/
package gato;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Enumeration;


public class Estadisticas {
    
    /*Archivo donde guardamos estadísticas.*/
    Archivo archivo;
    String ruta;
    
    /*Formato.*/
    StringTokenizer cortador;
    
    /*Rango de ganados y perdidos.*/
    int mayor, menor;
    
    /*Estadisticas.*/
    String[][] estadisticas;
    Vector<String[]> tabla = new Vector<String[]>();
    
    
    /** Crear nuevas Estadisticas */
    public Estadisticas() {
        /*Archivo de estadisticas.*/
        this.ruta = "estadisticas.txt";
        archivo = new Archivo(ruta);
        
        /*Comprobamos si existe, si no lo creamos.*/
        if ( archivo.LeerContenido() == null )
            archivo.EscribirContenido("Miguel 5 3 3");
     
        /*Leemos las estadisticas.*/
        estadisticas = leerEstadisticas();
        
        
    }
    
    /*Leer las estadisticas, regresa un vector con el nombre[0], juegos ganados[1],
     juegos perdidos[2] y juegos empatados[3]*/
    public String[][] leerEstadisticas(){
        
        /*Leemos las líneas del archivo.*/
        String[] lineas = archivo.LeerLineas();
        String[][] datos = new String[archivo.NumeroLineas()][4];
        
        /*Cortamos las líneas del archivo.*/
        for ( int i = 0; i < lineas.length; i ++ ){
            cortador = new StringTokenizer( lineas[i] );
            
            datos[i][0] = cortador.nextToken();
            datos[i][1] = cortador.nextToken();
            datos[i][2] = cortador.nextToken();
            datos[i][3] = cortador.nextToken();
            
            
        }
        
        /*Recuperamos el mayor y el menor dato.*/
        mayor = Integer.parseInt(datos[0][1]);
        menor = Integer.parseInt(datos[datos.length - 1][1]);
        
        /*Regresar datos.*/
        return datos;
    }
    
    /*Método que guarda un nuevo jugador en las estadísticas.*/
    public boolean guardarJugador( Jugador jugador ){
        
        /*Volvemos a leer las estadisticas.*/
        estadisticas = leerEstadisticas();
        
        /*Creamos su representación en un String[]*/
        String[] player = {jugador.nombre,""+jugador.GANADOS,""+jugador.PERDIDOS,""+jugador.EMPATADOS };
        
        /*Preguntamos si entró al record de los mejores.*/
        if ( jugador.GANADOS >= menor ){
            
            /*Agregado.*/
            boolean agregado = false;
            /*Recorremos e insertamos.*/
            for ( int i = 0 ; i < estadisticas.length; i ++ ){
                
                if ( jugador.GANADOS > mayor && !agregado){
                    tabla.add(player);
                    tabla.add(estadisticas[i]);
                    agregado = true;
                    continue;
                }else if ( jugador.GANADOS <= Integer.parseInt(estadisticas[i][1]) && jugador.GANADOS > Integer.parseInt(estadisticas[(i!=estadisticas.length-1)?i+1:i][1])  && !agregado ){
                    tabla.add(player);
                    tabla.add(estadisticas[i]);
                    agregado = true;
                    continue;
                }
                   
                tabla.add(this.estadisticas[i]);
                
                
            }
            guardarArchivo();
            return true;
        }
        
        return false;
        
    }
    
    /*Método que recupera la tabla actual.*/
    public String imprimeTabla(){
        Enumeration lista = tabla.elements();
        String res = "";
        String[] tmp; int c = 0;
        while ( lista.hasMoreElements() ){
            tmp = (String[])lista.nextElement();
            res += tmp[0] + " " + tmp[1] + " " + tmp[2] + " " + tmp[3] + "\r\n";
            c++; if ( c == 10 ) break;
        }
        return res;
    }
    
    /*Método para leer las estadisticas del archivo directamente.*/
    public String leerDatos(){
        String[][] datos = this.leerEstadisticas();
        String res = "Nombre -  G  -  P  -  E\r\n";
        for( int i = 0; i < datos.length; i ++ )
            res += datos[i][0] + " - " + datos[i][1] + " - " +  datos[i][2] +  " - " + datos[i][3] + "\r\n";
      
        
        return res;
    }
    
    /*Método que guarda las estadisticas en el archivo.*/
    public void guardarArchivo(){
        
        /*Guardamos el contenido en el archivo.*/
        this.archivo.EscribirContenido( imprimeTabla() );
        
    }
    
    /*Método que limpia las variables.*/
    public void refrescar(){
        archivo = new Archivo(ruta);
        this.estadisticas = this.leerEstadisticas();
        this.tabla.clear();
        cortador = null;
        
    }
}
