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
import java.lang.Runnable;
import java.util.Arrays;

public class ComputadoraIA{
    
    public static int tiradas = 0;
    
    /*Árboles para los movimientos del Gato.*/
    class NodoG{
        
        
        /*Mejor movimiento.*/
        int mejorMovimiento;
        /*Nodos hijos.*/
        NodoG nodos[];
        /*Tablero del juego.*/
        public int tablero[];
        /*Turno de la computadora.*/
        boolean miTurno = false;
        /*Indice de la pocision.*/
        int indice;
        /*Ganador.*/
        int ganador = 0;

        /*Constructor.*/
        NodoG(){
            
            /*Inicializamos las variables.*/
            tablero = new int[9];
               
        }
    }
    
    /*Raíz del árbol*/
    NodoG arbol = new NodoG();
    
    /*Atributos.*/
    int[] tablero;
    
    /*Mi ficha.*/
    public final int miFICHA = 2;
    
    /*Método que nos devuelve los espacios disponibles.*/
    public int movDisponibles( int[] tablero ){
        int mov = 0;
        
        for ( int i = 0; i < 9; i ++ )
            if ( tablero[i] == 0 )
                mov++;
        
        return mov;
    }
    
    /*Método que nos devuelve los indices del tablero de las pocisiones vacías.*/
    public int[] posVacias( int[] tablero ){
        /*Creamos el vector con los índices.*/
        int[] indices = new int[movDisponibles(tablero)];
        int indice = 0;
        
        /*Recorremos y guardamos los indices.*/
        for ( int i = 0; i < 9; i ++ ){
            if ( tablero[i] == 0 ){
                indices[indice] = i;
                indice++;
            }
        }
        return indices;
    }
    
    /*Clase que recibe el tablero actual.*/
    public int movimiento( int[] tablero ){
        /*Asignamos el tablero.*/
        this.tablero = tablero;
        tiradas ++;
        
        /*Copiamos el tablero a nuestro nodo raíz.*/
        for ( int i = 0; i < 9; i ++ )
            this.arbol.tablero[i] = this.tablero[i];
        
        /*Calculamos el mejor movimiento del árbol, desde las hojas hasta la raiz.*/
        movComputadora( arbol );
        
        /*Devolvemos el mejor movimiento.*/
        return arbol.mejorMovimiento;
    }

    /*Método recursivo, que genera los nodos con los movimientos.*/
    public void movComputadora( NodoG raiz ){
        
        /*Número de movimientos disponibles y sus indices en el tablero.*/
        int movimientos = movDisponibles(raiz.tablero);
        int indices[] = posVacias(raiz.tablero);
        int Max, Min;
        
        /*Inicializamos el nodo.*/
        raiz.nodos = new NodoG[movimientos];
        
        /*Verificamos si hay un ganador.*/
        int ganador = terminado(raiz.tablero);
        if ( ganador == 1 ) ganador = -1;
        else if ( ganador == 2 ) ganador = 1;
  
        if ( ganador!= 0 || movimientos == 0){
            raiz.ganador = ganador;
        }else{

            /*Creamos los datos de cada hijo.*/
            for( int i = 0; i < movimientos; i ++ ){
                
                /*Inicializamos los nodos hijos del árbol.*/
                raiz.nodos[i] = new NodoG();
                
                /*Les pasamos su tablero.*/
                for ( int j = 0; j < 9; j ++ )
                    raiz.nodos[i].tablero[j] = raiz.tablero[j];
                                
                /*Creamos los diferentes movimientos posibles.*/
                if ( raiz.miTurno )
                    raiz.nodos[i].tablero[indices[i]] = 1;
                else
                    raiz.nodos[i].tablero[indices[i]] = 2;
                
                /*Cambiamos el turno de los hijos*/
                raiz.nodos[i].miTurno = !raiz.miTurno;
                
                
                /*Guardamos el indice de su movimiento.*/
                raiz.nodos[i].indice = indices[i];
                    
                movComputadora(raiz.nodos[i]);
                                
            }

            /*Minimax*/
            if (!raiz.miTurno)
                raiz.ganador = Max(raiz);
            else
                raiz.ganador = Min(raiz);
            
       }    

    }
   
    /*Método que calcula el MÁXIMO de los nodos hijos de MIN*/
    public int Max( NodoG raiz ){
        int Max = -111;
        /*Máximo para la computadora, buscamos el valor donde gane.*/
        for (int i = 0; i < raiz.nodos.length; i++){
          /*Preguntamos por un nodo con un valor alto MAX*/
            if (raiz.nodos[i].ganador > Max){
                /*Lo asignamos y pasamos el mejor movimiento a la raíz.*/
                Max = raiz.nodos[i].ganador;
                raiz.mejorMovimiento = raiz.nodos[i].indice;
                /*Terminamos de buscar.*/
                if (Max == 1) break;
            }
         }
        
        /*Borramos los nodos.*/
        raiz.nodos = null;
        
        return Max;
    }
    
    /*Método que calcula el MÍNIMO de los nodos hijos de MAX.*/
    public int Min( NodoG raiz ){
        int Min = 111;
        /*Mínimo para el jugador*/
        for (int i = 0; i < raiz.nodos.length; i++)
          if (raiz.nodos[i].ganador < Min ){
            Min = raiz.nodos[i].ganador;
            raiz.mejorMovimiento = raiz.nodos[i].indice;
            if (Min == -1) break;
          }
        
        /*Borramos los nodos.*/
        raiz.nodos = null;
        
        return Min;
    }
                
    /*Método que dice si el juego está terminado.*/
    /*Regresa 0 si nadie gana, 1 si gana jugador 1 y 2 si gana jugador 2*/
    public int terminado( int[] tablero ){
        /*Comprobamos si el juego terminó.*/
        /*Filas*/
        if ( tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[3] == tablero[4] && tablero[3] == tablero[5]  && tablero[3] != 0  )
            return tablero[3];
        else if ( tablero[6] == tablero[7] && tablero[6]== tablero[8]  && tablero[6] != 0 )
            return tablero[6];
        /*Columnas*/
        else if( tablero[0] == tablero[3] && tablero[0] == tablero[6]  && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[1] == tablero[4] && tablero[1] == tablero[7]  && tablero[1] != 0  )
            return tablero[1];
        else if ( tablero[2] == tablero[5] && tablero[2] == tablero[8]  && tablero[2] != 0 )
            return tablero[2];
        /*Diagonales*/
        else if ( tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] !=0 )
            return tablero[0];
        else if ( tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0 )
            return tablero[2];
        
        return 0;
        
    }
    
    /*Método que nos dice si gana la computadora.*/
    public boolean puedoGanar(int[] tablero){
        return terminado(tablero) == 2;
    }
    
    /*Método que nos dice si pierde la computadora.*/
    public boolean puedoPerder(int[] tablero){
        return terminado(tablero) == 1;
    }
    
    /*Mëtodo que imprime un vector como un gato. xD*/
    public void imprime(int[] gato){
        for ( int i = 0; i < 9; i ++ ){
            System.out.print(gato[i]+"");
            if ( i == 2 || i == 5 )
                System.out.println();
        }
        
        System.out.println("\r\n");
    }
}
