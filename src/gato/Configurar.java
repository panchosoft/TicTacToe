/**********************************************************************/
/* Autor: Francisco I. Leyva
 * Página web: http://www.panchosoft.com
 * Correo electrónico: yagami_2@hotmail.com
 *
 *
/**********************************************************************/
package gato;
import javax.swing.*;

public class Configurar extends javax.swing.JFrame {
    
    /*Creamos los vectores con las rutas a las imagenes.*/
    
    /*Predeterminado.*/
    public ImageIcon[] fichas = new ImageIcon[2];
    public ImageIcon[] fichas1 = new ImageIcon[2];
    public ImageIcon[] fichas2 = new ImageIcon[2];
    public ImageIcon[] fichas3 = new ImageIcon[2];
    public ImageIcon[] fichas4 = new ImageIcon[2];
    
    
    public Configurar() {
        
        /*Iniciamos variables.*/
        fichas1[0] = new ImageIcon(getClass().getResource("/gato/images/circulo4.png"));
        fichas1[1] = new ImageIcon(getClass().getResource("/gato/images/cruz.png"));
        
        fichas2[0] = new ImageIcon(getClass().getResource("/gato/images/radio1.png"));
        fichas2[1] = new ImageIcon(getClass().getResource("/gato/images/radio2.png"));

        fichas3[0] = new ImageIcon(getClass().getResource("/gato/images/disco1.png"));
        fichas3[1] = new ImageIcon(getClass().getResource("/gato/images/disco2.png"));

        fichas4[0] = new ImageIcon(getClass().getResource("/gato/images/cielo1.png"));
        fichas4[1] = new ImageIcon(getClass().getResource("/gato/images/cielo2.png"));
        
        fichas[0] = fichas1[0];
        fichas[1] = fichas1[1];
        
        /*Iniciamos componentes visuales.*/
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblFicha1 = new javax.swing.JLabel();
        lblFicha2 = new javax.swing.JLabel();
        lblInstrucciones = new javax.swing.JLabel();
        cmbElegir = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuraciones");
        lblFicha1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/circulo3.png")));

        lblFicha2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/cruz.png")));

        lblInstrucciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/rating_star.png")));
        lblInstrucciones.setText("Seleccione fichas:");

        cmbElegir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Predeterminado", "Radioactivo", "Discos", "Cielo" }));
        cmbElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbElegirActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gato/images/icon_arrow.gif")));
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(lblInstrucciones)
                        .add(19, 19, 19)
                        .add(cmbElegir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(42, 42, 42)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jButton1)
                            .add(layout.createSequentialGroup()
                                .add(lblFicha1)
                                .add(33, 33, 33)
                                .add(lblFicha2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(37, 37, 37)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbElegir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblInstrucciones))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblFicha1)
                    .add(lblFicha2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbElegirActionPerformed
        int indice = cmbElegir.getSelectedIndex();
        
        if ( indice == 0 ){
            fichas[0] = fichas1[0];
            fichas[1] = fichas1[1];
        }else if ( indice == 1 ){
            fichas[0] = fichas2[0];
            fichas[1] = fichas2[1];
        }else if ( indice == 2 ){
            fichas[0] = fichas3[0];
            fichas[1] = fichas3[1];
        }else if ( indice == 3 ){
            fichas[0] = fichas4[0];
            fichas[1] = fichas4[1];
        }
        
        lblFicha1.setIcon( fichas[0] );
        lblFicha2.setIcon( fichas[1] );
    }//GEN-LAST:event_cmbElegirActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbElegir;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFicha1;
    private javax.swing.JLabel lblFicha2;
    private javax.swing.JLabel lblInstrucciones;
    // End of variables declaration//GEN-END:variables
    
}
