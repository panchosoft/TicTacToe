/**********************************************************************/
/* Autor: Francisco I. Leyva
 * Página web: http://www.panchosoft.com
 * Correo electrónico: yagami_2@hotmail.com
 *
 *
/**********************************************************************/
package gato;
import javax.swing.*;

public class Modelo extends javax.swing.JFrame {
    
    /*Juego del gato donde se aplicará este modelo.*/
    private Gato gato;
    private JOptionPane mensaje;
    /*Datos del modelo.*/
    public final int HOMBREvsHOMBRE = 1;
    public final int HOMBREvsCOMPUTADORA = 2;
    public int tipo_juego = 0;
    public String nombre1, nombre2;
    
    /** Crea un nuevo Modelo */
    public Modelo( Gato gato ) {
        /*Iniciamos componentes visuales.*/
        initComponents();
        setVisible(true);
        mensaje = new JOptionPane();
        
        /*Asignamos el gato.*/
        this.gato = gato;
        
    }
    
    /*Método que recoje los datos.*/
    public boolean recojer(){

        /*Comprobamos que los campos estén llenos.*/
        if( this.txtJugador1.getText().equals("") ){
            mensaje.showMessageDialog(this,"Llene el nombre del jugador 1 por favor.","[X] Error:",JOptionPane.ERROR_MESSAGE);
            return false;   
        }
        if( this.txtJugador2.getText().equals("") && this.hvsh.isSelected() ){
            mensaje.showMessageDialog(this,"Llene el nombre del jugador 2 por favor.","[X] Error:",JOptionPane.ERROR_MESSAGE);
            return false;   
        }
        if( this.txtJugador1.getText().equals( this.txtJugador2.getText() )){
            mensaje.showMessageDialog(this,"Escriba nombres diferentes para los jugadores.","[X] Error:",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        /*Recojemos los valores.*/
        this.tipo_juego = ( this.hvsh.isSelected() ) ? HOMBREvsHOMBRE : HOMBREvsCOMPUTADORA;
        this.nombre1 = this.txtJugador1.getText();
        this.nombre2 = this.txtJugador2.getText();
        
        return true;
    }
    
    /*Método que envía los datos ( modelo ) al gato.*/
    public void enviarModelo(){
        gato.recojerModelo();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        Grupo = new javax.swing.ButtonGroup();
        opcionUno = new javax.swing.JLabel();
        hvsh = new javax.swing.JRadioButton();
        hvpc = new javax.swing.JRadioButton();
        img1 = new javax.swing.JLabel();
        img2 = new javax.swing.JLabel();
        img3 = new javax.swing.JLabel();
        opcionDos = new javax.swing.JLabel();
        jugador1 = new javax.swing.JLabel();
        txtJugador1 = new javax.swing.JTextField();
        jugador2 = new javax.swing.JLabel();
        txtJugador2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modo de juego");
        setFont(new java.awt.Font("Tahoma", 1, 12));
        setIconImage(new ImageIcon(this.getClass().getResource("/gato/images/gdm.png")).getImage());
        setLocationByPlatform(true);
        setUndecorated(true);
        opcionUno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/rating_star.png")));
        opcionUno.setText("Estilo de juego:");

        Grupo.add(hvsh);
        hvsh.setSelected(true);
        hvsh.setText("Persona a Persona");
        hvsh.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        hvsh.setMargin(new java.awt.Insets(0, 0, 0, 0));
        hvsh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hvshActionPerformed(evt);
            }
        });

        Grupo.add(hvpc);
        hvpc.setText("Persona contra Computadora");
        hvpc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        hvpc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        hvpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hvpcActionPerformed(evt);
            }
        });

        img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/pvsp.png")));

        img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/pvspc.png")));

        img3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/persona.png")));

        opcionDos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/rating_star.png")));
        opcionDos.setText("Nombre(s) de jugador(es):");

        jugador1.setText("Jugador 1:");

        jugador2.setText("Jugador 2:");

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/checkin.png")));
        btnAceptar.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/cancelar.png")));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(47, 47, 47)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(hvsh)
                            .add(layout.createSequentialGroup()
                                .add(31, 31, 31)
                                .add(img1)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(52, 52, 52)
                                .add(hvpc))
                            .add(layout.createSequentialGroup()
                                .add(84, 84, 84)
                                .add(img3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(img2))))
                    .add(layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(opcionUno)
                            .add(layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jugador1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtJugador1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(35, 35, 35)
                                .add(jugador2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtJugador2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 114, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(opcionDos)
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                            .add(jSeparator1))))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(209, Short.MAX_VALUE)
                .add(btnAceptar)
                .add(21, 21, 21)
                .add(btnCancelar)
                .add(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(opcionUno)
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(img1)
                    .add(img3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(img2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(hvsh)
                    .add(hvpc))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19)
                .add(opcionDos)
                .add(19, 19, 19)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jugador1)
                    .add(jugador2)
                    .add(txtJugador2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtJugador1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(27, 27, 27)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnAceptar)
                    .add(btnCancelar))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        /*Recojemos los datos de los campos.*/
        if( recojer() ){
            /*Los enviamos al gato.*/
            enviarModelo();
            /*Cerramos esta ventana.*/
            dispose();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void hvshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hvshActionPerformed
        txtJugador2.setEnabled(true);
        jugador2.setEnabled(true);
    }//GEN-LAST:event_hvshActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void hvpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hvpcActionPerformed
        txtJugador2.setEnabled(false);
        jugador2.setEnabled(false);
    }//GEN-LAST:event_hvpcActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JRadioButton hvpc;
    private javax.swing.JRadioButton hvsh;
    private javax.swing.JLabel img1;
    private javax.swing.JLabel img2;
    private javax.swing.JLabel img3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jugador1;
    private javax.swing.JLabel jugador2;
    private javax.swing.JLabel opcionDos;
    private javax.swing.JLabel opcionUno;
    private javax.swing.JTextField txtJugador1;
    private javax.swing.JTextField txtJugador2;
    // End of variables declaration//GEN-END:variables
    
}
