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

import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.lang.Runnable;

public class Gato extends javax.swing.JFrame implements Runnable {
   
    /*Atributos de nuestro gato.*/
    /*Modelo de juego.*/
    Modelo modelo;
    
    /*Jugadores.*/
    Jugador jugador1, jugador2;
    ComputadoraIA computadora;
    boolean jugando, terminado;
    public final int HOMBREvsHOMBRE = 1;
    public final int HOMBREvsCOMPUTADORA = 2;
    public final int JUGADOR1 = 1;
    public final int JUGADOR2 = 2;
    public boolean PENSANDO = false;
    
    /*Turno de jugador.*/
    int turno = 0;
    int turnoGeneral = 0;
    
    /*Matriz que representa el juego.*/
    int[] tablero = new int[9];

    /*Tablero en componentes.*/
    JLabel fichas[];
    
    /*Estadisticas.*/
    Estadisticas estadisticas = new Estadisticas();
    
    /*Configuracion.*/
    Configurar config = new Configurar();
    
    /*Sonidos.*/
    Sonidos sonido = new Sonidos();
    
    /*Construir un nuevo Gato*/
    public Gato() {

        /*LLenamos nuestro tablero de 0, vacío.*/
        Arrays.fill(tablero,0);
           
        /*Iniciamos los componentes de nuestra ventana*/
        initComponents();
        iniciarComponentes();
          
    }
    public void run(){
        
    }
    /*Método que ejecuta las operaciones al poner una ficha.*/
    public void movimiento( JLabel ficha ){
        /*Colocamos la ficha.*/
        if ( jugando ){
            
            /*Reproducimos el sonido.*/
            sonido.sonidoFicha();
            
            if( !PENSANDO )
                ponerFicha( ficha );
            
            
            if ( this.modelo.tipo_juego == HOMBREvsCOMPUTADORA && this.turno == JUGADOR2 ){
                PENSANDO = true;
                ponerFichaCPU(computadora.movimiento( this.tablero ));
                PENSANDO = false;
                
                /*Reproducimos el sonido.*/
                sonido.sonidoFicha();
            }

        }
        /*Si se va a comenzar un juego nuevo*/
        if( terminado ){
            reiniciarJuego();
            return;
        }
        
        /*Preguntamos si el juego termino o alguien ganó.*/
        if ( terminado() != 0){
            
            /*Sonido*/
            sonido.sonidoGano();
            
            /*Asignamos resultados.*/
            if ( terminado() == 1 ){
                jugador1.gano();
                jugador2.perdio();
                mensaje(jugador1.nombre + " ganó!");
            }else{
                jugador2.gano();
                jugador1.perdio();
                mensaje(jugador2.nombre + " ganó!");
            }
            
            /*Mostramos la información.*/
            mostrarInformacion();
            
            /*Detenemos el juego actual.*/
            jugando = false;
            terminado = true;
            
            
        } else if ( lleno() ){
            /*Asignamos resultados.*/
            jugador1.empato();
            jugador2.empato();
            mensaje("Empate!");
            
            /*Mostramos la información.*/
            mostrarInformacion();
            
            /*Detenemos el juego actual.*/
            jugando = false;
            terminado = true;
        }
        
        /*Movemos el foco al otro jugador.*/
        cambiarFoco();
    }
    
    /*Método que pone una ficha por la computadora.*/
    public void ponerFichaCPU( int indice ){
        
        if( indice == -1 ) return;
        
        switch ( indice ){
            case 0: this.f1.setIcon( jugador2.obtenFicha() ); break;
            case 1: this.f2.setIcon( jugador2.obtenFicha() ); break;
            case 2: this.f3.setIcon( jugador2.obtenFicha() ); break;
            case 3: this.f4.setIcon( jugador2.obtenFicha() ); break;
            case 4: this.f5.setIcon( jugador2.obtenFicha() ); break;
            case 5: this.f6.setIcon( jugador2.obtenFicha() ); break;
            case 6: this.f7.setIcon( jugador2.obtenFicha() ); break;
            case 7: this.f8.setIcon( jugador2.obtenFicha() ); break;
            case 8: this.f9.setIcon( jugador2.obtenFicha() ); break;        
        }
        
        this.tablero[indice] = 2;
        
        /*Cambiamos el turno.*/
        turno = ( turno == JUGADOR1 ) ? JUGADOR2 : JUGADOR1;
                
    }
 
    /*Método que "pone una ficha" en el tablero.*/
    public void ponerFicha( JLabel ficha ){

        /*Obtenemos la casilla.*/
        int casilla = Integer.parseInt(""+ficha.getName().charAt(1)) - 1;
        
        /*Comprobamos si la casilla no estaba ocupada.*/
        if ( estaOcupada(casilla ) )
            return;
        
        /*Elegimos la ficha según el turno*/
        if ( turno == JUGADOR1 )
            ficha.setIcon( jugador1.obtenFicha() );
        else
            ficha.setIcon( jugador2.obtenFicha() );
        
        /*Guardamos la representación en el tablero*/
        tablero[casilla] = turno;
        
        /*Cambiamos el turno.*/
        turno = ( turno == JUGADOR1 ) ? JUGADOR2 : JUGADOR1;
        
    }

    /*Método que dice si el juego está terminado.*/
    /*Regresa 0 si nadie gana, 1 si gana jugador 1 y 2 si gana jugador 2*/
    public int terminado(){
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
    
    /*Método que nos dice si el tablero se llenó.*/
    public boolean lleno(){
        boolean res = true;
        for ( int i = 0; i < tablero.length; i ++ )
            if ( tablero[i] == 0 )
                res = false;
        
        return res;
    }
    
    /*Método que nos dice si una casilla está ocupada.*/
    public boolean estaOcupada( int casilla ){
        return ( tablero[casilla] != 0 );
    }
    
    /*Método que inicia los componentes del Gato.*/
    private void iniciarComponentes(){
        /*Icono del formulario.*/
        //this.setIconImage( Image );
        
        /*Hacemos visible el formulario.*/
        this.setVisible(true);
        
        
        /*Referenciamos todas las etiquetas.*/
        fichas = new JLabel[9];
        fichas[0] = f1; fichas[1] = f2; fichas[2] = f3;
        fichas[3] = f4; fichas[4] = f5; fichas[5] = f6;
        fichas[6] = f7; fichas[7] = f8; fichas[8] = f9;
        
        /*Mostramos el panel con estadisticas.*/
        this.mostrarEstadisticas();
        
        /*Cursor para los componentes.*/
        for ( int i = 0; i < 9; i ++ )
            fichas[i].setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        
        /*Cursor para el sonido.*/
        this.lblSonido.setCursor( new Cursor( Cursor.HAND_CURSOR ));
    }
    
    /*Método que recupera la información del jugador y la usa en el panel de usuario.*/
    public void mostrarInformacion(){
        
        /*Establecemos el título.*/
        panelJ1.setTitle( jugador1.nombre );
        panelJ2.setTitle( jugador2.nombre );
        
        /*Establecemos las estadísticas del jugador.*/
        this.lblGanados.setText("Ganados: " + jugador1.GANADOS );
        this.lblPerdidos.setText("Perdidos: " + jugador1.PERDIDOS );
        this.lblEmpatados.setText("Empatados: " + jugador1.EMPATADOS );
        this.lblIcono.setIcon( jugador1.obtenFicha() );
        
        this.lblGanados2.setText("Ganados: " + jugador2.GANADOS );
        this.lblPerdidos2.setText("Perdidos: " + jugador2.PERDIDOS );
        this.lblEmpatados2.setText("Empatados: " + jugador2.EMPATADOS );
        this.lblIcono2.setIcon( jugador2.obtenFicha() );
        
        this.panelJ1.setVisible(true);
        if( this.modelo.tipo_juego != HOMBREvsCOMPUTADORA)
            this.panelJ2.setVisible(true);
    }
    
    /*Método que muestra las estadisticas.*/
    public void mostrarEstadisticas(){
        this.estadisticas.guardarJugador( new Jugador("","") );
        String esta = this.estadisticas.leerDatos();
        this.txtEsta.setText(esta);
        this.panelEstadisticas.setVisible(true);
    }
    
    /*Método que establece el texto de la etiqueta de descripción.*/
    public void mensaje(String mensaje){
        this.lblEstado.setText(mensaje);
    }
    
    /*Cambiar foco*/
    public void cambiarFoco(){
        
        /*Si estamos jugando.*/
        if ( !jugando )
            return;
        
        /*Si es turno del primer jugador..*/
        if ( turno == JUGADOR1 ){
            /*Seleccionamos su ventana.*/
            try{this.panelJ1.setSelected(true);
            this.panelJ2.setSelected(false);}
            catch(Exception ex){}
            
            mensaje("Turno de " + jugador1.nombre );
            
        } else {
            try{this.panelJ1.setSelected(false);
            this.panelJ2.setSelected(true);}
            catch(Exception ex){}
            
            mensaje("Turno de " + jugador2.nombre );
        }
        
            
    }
    
    /*Método que recoje el modelo y nos avisa que está listo.*/
    public void recojerModelo(){
        /*Iniciamos los componentes del juego.*/
        iniciarJuego();
    }
    
    /*Método que inicia el juego una vez obtenido el modelo.*/
    public void iniciarJuego(){
        /*Creamos los jugadores según el tipo de juego.*/
        if ( modelo.tipo_juego == HOMBREvsHOMBRE ){
            this.jugador1 = new Jugador( modelo.nombre1, config.fichas[0] );
            this.jugador2 = new Jugador( modelo.nombre2, config.fichas[1] );
            
            /*Mostramos su información, asignamos los nombres de jugador al panel.*/
            mostrarInformacion();
        } else {
            /*Jugadores*/
            this.jugador1 = new Jugador( modelo.nombre1, config.fichas[0] );
            this.jugador2 = new Jugador ( "Computadora", config.fichas[1] );
            this.panelJ2.setVisible(false);
            
            /*Creamos la instancia para la computadora.*/
            computadora = new ComputadoraIA();
            
            /*Mostramos su información, asignamos los nombres de jugador al panel.*/
            mostrarInformacion();
        }
        
        /*Iniciamos el turno en jugador 1*/
        this.turno = 1;
        this.turnoGeneral = JUGADOR1;
        
        /*Variables de juego.*/
        jugando = true;
        terminado = false;
        
        /*Deshabilitamos el menú nuevo juego.*/
        this.mnuIniciar.setEnabled(false);
        this.mnuSuspender.setEnabled(true);
        this.panelEstadisticas.setVisible(false);
        
        /*Movemos el foco.*/
        cambiarFoco();
        
    }
    
    /*Método que inicia un nuevo juego.*/
    public void reiniciarJuego(){
        
        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero,0);
        
        /*Borramos los iconos.*/
        for ( int i = 0; i < 9; i ++ )
            fichas[i].setIcon(null);
        
        /*Quitamos selecciones.*/
        try{this.panelJ1.setSelected(false);
        this.panelJ2.setSelected(true);}
        catch(Exception ex){}
        
        /*Cambiamos el turno General.*/
        if ( this.modelo.tipo_juego == HOMBREvsCOMPUTADORA )
            turnoGeneral = JUGADOR1;
        else
            turnoGeneral = ( turnoGeneral == JUGADOR1 ) ? JUGADOR2 : JUGADOR1;
        
        turno = turnoGeneral;
        
        /*Jugando.*/
        if ( turno == JUGADOR1 )
            mensaje( "Turno de " +jugador1.nombre);
        else
            mensaje( "Turno de " +jugador2.nombre);
        
        /*Mostramos su información, asignamos los nombres de jugador al panel.*/
        mostrarInformacion();
        
        jugando = true;
        terminado = false;
    }
    
    /*Método que suspende un juego.*/
    public void suspenderJuego(){
                
        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero,0);
        
        /*Borramos los iconos.*/
        for ( int i = 0; i < 9; i ++ )
            fichas[i].setIcon(null);
        
        /*Quitamos selecciones.*/
        try{this.panelJ1.setSelected(false);
        this.panelJ2.setSelected(true);}
        catch(Exception ex){}
        
        /*Reinciamos el turno.*/
        turno = 1;
        jugando = false;
        terminado = false;
        
        /*Borramos jugadores.*/
        jugador1 = null;
        jugador2 = null;
        
        /*Habilitamos los menús.*/
        this.mnuIniciar.setEnabled(true);
        this.mnuSuspender.setEnabled(false);
        this.lblEstado.setText("Juega al gato!");
        
        /*Quitamos los paneles.*/
        this.panelJ1.setVisible(false);
        this.panelJ2.setVisible(false);
        
        
        
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        f1 = new javax.swing.JLabel();
        f2 = new javax.swing.JLabel();
        f3 = new javax.swing.JLabel();
        f4 = new javax.swing.JLabel();
        f5 = new javax.swing.JLabel();
        f6 = new javax.swing.JLabel();
        f7 = new javax.swing.JLabel();
        f8 = new javax.swing.JLabel();
        f9 = new javax.swing.JLabel();
        Tablero = new javax.swing.JLabel();
        Version = new javax.swing.JLabel();
        panelEstadisticas = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEsta = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelJ2 = new javax.swing.JInternalFrame();
        lblEmpatados2 = new javax.swing.JLabel();
        lblPerdidos2 = new javax.swing.JLabel();
        lblGanados2 = new javax.swing.JLabel();
        lblIcono2 = new javax.swing.JLabel();
        panelJ1 = new javax.swing.JInternalFrame();
        lblGanados = new javax.swing.JLabel();
        lblPerdidos = new javax.swing.JLabel();
        lblEmpatados = new javax.swing.JLabel();
        lblIcono = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblSonido = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        mnuJuego = new javax.swing.JMenu();
        mnuIniciar = new javax.swing.JMenuItem();
        mnuSuspender = new javax.swing.JMenuItem();
        mnuEstadisticas = new javax.swing.JMenuItem();
        mnuConfigurar = new javax.swing.JMenu();
        mnuAcerca = new javax.swing.JMenu();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gato - Panchosoft.com");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        f1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f1.setName("f1");
        f1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f1MouseClicked(evt);
            }
        });

        getContentPane().add(f1);
        f1.setBounds(270, 70, 100, 100);

        f2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f2.setName("f2");
        f2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f2MouseClicked(evt);
            }
        });

        getContentPane().add(f2);
        f2.setBounds(370, 70, 110, 100);

        f3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f3.setName("f3");
        f3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f3MouseClicked(evt);
            }
        });

        getContentPane().add(f3);
        f3.setBounds(480, 70, 100, 100);

        f4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f4.setName("f4");
        f4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f4MouseClicked(evt);
            }
        });

        getContentPane().add(f4);
        f4.setBounds(270, 170, 100, 100);

        f5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f5.setName("f5");
        f5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f5MouseClicked(evt);
            }
        });

        getContentPane().add(f5);
        f5.setBounds(370, 170, 110, 100);

        f6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f6.setName("f6");
        f6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f6MouseClicked(evt);
            }
        });

        getContentPane().add(f6);
        f6.setBounds(480, 170, 100, 100);

        f7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f7.setName("f7");
        f7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f7MouseClicked(evt);
            }
        });

        getContentPane().add(f7);
        f7.setBounds(270, 270, 100, 100);

        f8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f8.setName("f8");
        f8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f8MouseClicked(evt);
            }
        });

        getContentPane().add(f8);
        f8.setBounds(370, 270, 110, 100);

        f9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f9.setName("f9");
        f9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f9MouseClicked(evt);
            }
        });

        getContentPane().add(f9);
        f9.setBounds(480, 270, 100, 100);

        Tablero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/tablero.png")));
        getContentPane().add(Tablero);
        Tablero.setBounds(270, 20, 320, 400);

        Version.setFont(new java.awt.Font("Trebuchet MS", 0, 12));
        Version.setForeground(new java.awt.Color(102, 102, 102));
        Version.setText("Panchosoft.com");
        getContentPane().add(Version);
        Version.setBounds(20, 400, 170, 40);

        panelEstadisticas.getContentPane().setLayout(null);

        panelEstadisticas.setResizable(true);
        panelEstadisticas.setTitle("Estad\u00edsticas");
        panelEstadisticas.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/bluefish-icon.png")));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        txtEsta.setColumns(20);
        txtEsta.setEditable(false);
        txtEsta.setFont(new java.awt.Font("Verdana", 1, 12));
        txtEsta.setRows(5);
        jScrollPane1.setViewportView(txtEsta);

        panelEstadisticas.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 200, 190);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/SYSTEM ALERT NOTE ICON.png")));
        jLabel1.setToolTipText("Estad\u00edsticas");
        panelEstadisticas.getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 210, 128, 128);

        getContentPane().add(panelEstadisticas);
        panelEstadisticas.setBounds(10, 30, 230, 370);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(""));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel2);
        jLabel2.setBounds(250, 300, 110, 100);

        panelJ2.setResizable(true);
        panelJ2.setTitle("Jugador 2");
        panelJ2.setVisible(true);
        lblEmpatados2.setText("Empatados: 0");

        lblPerdidos2.setText("Perdidos: 0");

        lblGanados2.setText("Ganados: 0");

        org.jdesktop.layout.GroupLayout panelJ2Layout = new org.jdesktop.layout.GroupLayout(panelJ2.getContentPane());
        panelJ2.getContentPane().setLayout(panelJ2Layout);
        panelJ2Layout.setHorizontalGroup(
            panelJ2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelJ2Layout.createSequentialGroup()
                .addContainerGap()
                .add(panelJ2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblGanados2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblPerdidos2)
                    .add(panelJ2Layout.createSequentialGroup()
                        .add(lblEmpatados2)
                        .add(6, 6, 6)
                        .add(lblIcono2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJ2Layout.setVerticalGroup(
            panelJ2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelJ2Layout.createSequentialGroup()
                .addContainerGap()
                .add(lblGanados2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblPerdidos2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblEmpatados2)
                .addContainerGap(80, Short.MAX_VALUE))
            .add(panelJ2Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .add(lblIcono2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        getContentPane().add(panelJ2);
        panelJ2.setBounds(20, 220, 200, 180);

        panelJ1.setResizable(true);
        panelJ1.setTitle("Jugador 1");
        panelJ1.setVisible(true);
        lblGanados.setText("Ganados: 0");

        lblPerdidos.setText("Perdidos: 0");

        lblEmpatados.setText("Empatados: 0");

        org.jdesktop.layout.GroupLayout panelJ1Layout = new org.jdesktop.layout.GroupLayout(panelJ1.getContentPane());
        panelJ1.getContentPane().setLayout(panelJ1Layout);
        panelJ1Layout.setHorizontalGroup(
            panelJ1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelJ1Layout.createSequentialGroup()
                .addContainerGap()
                .add(panelJ1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblGanados, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(panelJ1Layout.createSequentialGroup()
                        .add(lblEmpatados)
                        .add(6, 6, 6)
                        .add(lblIcono, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .add(lblPerdidos))
                .addContainerGap())
        );
        panelJ1Layout.setVerticalGroup(
            panelJ1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelJ1Layout.createSequentialGroup()
                .addContainerGap()
                .add(lblGanados)
                .add(6, 6, 6)
                .add(lblPerdidos)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblEmpatados)
                .addContainerGap(80, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelJ1Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .add(lblIcono, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        getContentPane().add(panelJ1);
        panelJ1.setBounds(20, 30, 200, 180);

        lblEstado.setFont(new java.awt.Font("Trebuchet MS", 1, 24));
        lblEstado.setForeground(new java.awt.Color(153, 153, 153));
        lblEstado.setText("Juega al Gato!");
        getContentPane().add(lblEstado);
        lblEstado.setBounds(270, 10, 310, 40);

        lblSonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/sonido.png")));
        lblSonido.setToolTipText("Activar/Desactivar sonidos!");
        lblSonido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSonidoMouseClicked(evt);
            }
        });

        getContentPane().add(lblSonido);
        lblSonido.setBounds(590, 390, 60, 60);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/fondo.jpg")));
        fondo.setText("jLabel1");
        getContentPane().add(fondo);
        fondo.setBounds(0, 0, 838, 500);

        mnuJuego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/gdm.png")));
        mnuJuego.setText("Juego");
        mnuIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/gdm.png")));
        mnuIniciar.setText("Iniciar nuevo juego");
        mnuIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIniciarActionPerformed(evt);
            }
        });

        mnuJuego.add(mnuIniciar);

        mnuSuspender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/exit.gif")));
        mnuSuspender.setText("Suspender juego");
        mnuSuspender.setEnabled(false);
        mnuSuspender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSuspenderActionPerformed(evt);
            }
        });

        mnuJuego.add(mnuSuspender);

        mnuEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/checkin.png")));
        mnuEstadisticas.setText("Ver estad\u00edsticas");
        mnuEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEstadisticasActionPerformed(evt);
            }
        });

        mnuJuego.add(mnuEstadisticas);

        Menu.add(mnuJuego);

        mnuConfigurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/config.png")));
        mnuConfigurar.setText("Configurar");
        mnuConfigurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuConfigurarMouseClicked(evt);
            }
        });

        Menu.add(mnuConfigurar);

        mnuAcerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/tooltip.png")));
        mnuAcerca.setText("Acerca de...");
        mnuAcerca.setEnabled(false);
        Menu.add(mnuAcerca);

        setJMenuBar(Menu);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-664)/2, (screenSize.height-507)/2, 664, 507);
    }// </editor-fold>//GEN-END:initComponents

    private void lblSonidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSonidoMouseClicked
        this.sonido.silencio =  !sonido.silencio;
        if ( this.sonido.silencio )
            this.lblSonido.setEnabled(false);
        else
            this.lblSonido.setEnabled(true);
    }//GEN-LAST:event_lblSonidoMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void mnuConfigurarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuConfigurarMouseClicked
        this.config.setVisible(true);
    }//GEN-LAST:event_mnuConfigurarMouseClicked

    private void mnuEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEstadisticasActionPerformed
        this.mostrarEstadisticas();
    }//GEN-LAST:event_mnuEstadisticasActionPerformed

    private void mnuSuspenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSuspenderActionPerformed
        /*Guardamos records.*/
        estadisticas.guardarJugador(jugador2);
        estadisticas.refrescar();
        estadisticas.guardarJugador(jugador1);
        
        /*Suspendemos.*/
        suspenderJuego();
    }//GEN-LAST:event_mnuSuspenderActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if( jugando ){
            /*Guardamos records.*/
            estadisticas.guardarJugador(jugador2);
            estadisticas.refrescar();
            estadisticas.guardarJugador(jugador1);
        }
    }//GEN-LAST:event_formWindowClosing

    private void mnuIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIniciarActionPerformed
        /*Creamos el nuevo modelo de juego para nuestro Gato.*/
        modelo = new Modelo(this);
    }//GEN-LAST:event_mnuIniciarActionPerformed

    private void f9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f9MouseClicked
        movimiento(f9);
    }//GEN-LAST:event_f9MouseClicked

    private void f8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f8MouseClicked
        movimiento(f8);
    }//GEN-LAST:event_f8MouseClicked

    private void f7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f7MouseClicked
        movimiento(f7);
    }//GEN-LAST:event_f7MouseClicked

    private void f6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f6MouseClicked
        movimiento(f6);
    }//GEN-LAST:event_f6MouseClicked

    private void f5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f5MouseClicked
        movimiento(f5);
    }//GEN-LAST:event_f5MouseClicked

    private void f4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f4MouseClicked
        movimiento(f4);
    }//GEN-LAST:event_f4MouseClicked

    private void f3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f3MouseClicked
        movimiento(f3);
    }//GEN-LAST:event_f3MouseClicked

    private void f2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f2MouseClicked
        movimiento(f2);
    }//GEN-LAST:event_f2MouseClicked

    private void f1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f1MouseClicked
        movimiento(f1);
    }//GEN-LAST:event_f1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel Tablero;
    private javax.swing.JLabel Version;
    private javax.swing.JLabel f1;
    private javax.swing.JLabel f2;
    private javax.swing.JLabel f3;
    private javax.swing.JLabel f4;
    private javax.swing.JLabel f5;
    private javax.swing.JLabel f6;
    private javax.swing.JLabel f7;
    private javax.swing.JLabel f8;
    private javax.swing.JLabel f9;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpatados;
    private javax.swing.JLabel lblEmpatados2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblGanados;
    private javax.swing.JLabel lblGanados2;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblIcono2;
    private javax.swing.JLabel lblPerdidos;
    private javax.swing.JLabel lblPerdidos2;
    private javax.swing.JLabel lblSonido;
    private javax.swing.JMenu mnuAcerca;
    private javax.swing.JMenu mnuConfigurar;
    private javax.swing.JMenuItem mnuEstadisticas;
    private javax.swing.JMenuItem mnuIniciar;
    private javax.swing.JMenu mnuJuego;
    private javax.swing.JMenuItem mnuSuspender;
    private javax.swing.JInternalFrame panelEstadisticas;
    private javax.swing.JInternalFrame panelJ1;
    private javax.swing.JInternalFrame panelJ2;
    private javax.swing.JTextArea txtEsta;
    // End of variables declaration//GEN-END:variables
    
}
