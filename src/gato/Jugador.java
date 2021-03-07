/**********************************************************************/
/* Autor: Francisco I. Leyva
 * Página web: http://www.panchosoft.com
 * Correo electrónico: yagami_2@hotmail.com
 *
 *
/**********************************************************************/
package gato;
import javax.swing.*;
import java.awt.*;

public class Jugador {
    
    /*Atributos del jugador.*/
    String nombre;
    public int GANADOS, PERDIDOS, EMPATADOS;
    
    /*Imagen de la ficha del jugador.*/
    private ImageIcon ficha;
    
    /** Crea un nuevo Jugador. */
    public Jugador(String nombre, String ruta) {
        
        /*Nombre del jugador.*/
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        
        /*Imagen por defecto.*/
        miFicha( ruta );
    }
    
        
    /** Crea un nuevo Jugador. */
    public Jugador(String nombre, ImageIcon imagen ) {
        
        /*Nombre del jugador.*/
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        
        /*Imagen por defecto.*/
        this.ficha = imagen;
    }

    /*Método que incrementa los juegos ganados.*/
    public void gano(){
        GANADOS ++;
    }
    public void perdio(){
        PERDIDOS ++;
    }
    public void empato(){
        EMPATADOS ++;
    }
    
    /*Método que devuelve la imagen de la ficha.*/
    public ImageIcon obtenFicha(){
        return ficha;
    }
    
    /*Método que establace la ficha.*/
    public void miFicha(String ruta){
        this.ficha = new ImageIcon ( this.getClass().getResource(ruta) );
    }
    
}
