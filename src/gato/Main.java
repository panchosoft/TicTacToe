/**********************************************************************/
/* Autor: Francisco I. Leyva
 * Página web: http://www.panchosoft.com
 * Correo electrónico: yagami_2@hotmail.com
 *
 * Programa que permite jugar al tres en raya, gato, o tic tac toe contra otra
 * persona o contra la máquina. Implementando el algoritmo minimax, árboles,
 * recursión, etc.
 *
/**********************************************************************/
package gato;
import javax.swing.*;
public class Main {

    public Main() {
    }
    
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        /*Creamos una nueva instancia de nuestro gato.*/
        new Gato();
    }
    
}
