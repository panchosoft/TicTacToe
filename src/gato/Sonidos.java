/**********************************************************************/
/* Autor: Francisco I. Leyva
 * P�gina web: http://www.panchosoft.com
 * Correo electr�nico: yagami_2@hotmail.com
 *
 *
/**********************************************************************/
package gato;

import java.applet.AudioClip;

public class Sonidos {
    /*Objeto audioclip*/
    AudioClip sonido;
    boolean silencio = false;
    
    /*Crea una nueva instancia de sonidos.*/
    public Sonidos() {
    }
    /*M�todo que imprime un sonido al error.*/
    public void sonidoFicha(){
        sonido = java.applet.Applet.newAudioClip( getClass().getResource("/gato/sonidos/error.wav") );
	if ( !silencio ) sonido.play();
    }
	
    /*M�todo que imprime un sonido al ganar.*/
    public void sonidoGano(){
	sonido = java.applet.Applet.newAudioClip( getClass().getResource("sonidos/victoria.wav" ) );
	if ( !silencio ) sonido.play();
    }
	
    /*M�todo que imprime un sonido al perder.*/
    public void sonidoPerdio(){
	sonido = java.applet.Applet.newAudioClip( getClass().getResource("sonidos/perdio.wav" ) );
	if ( !silencio ) sonido.play();
    }
}
