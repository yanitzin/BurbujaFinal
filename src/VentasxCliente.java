import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class VentasxCliente extends javax.swing.JFrame {
    String servidor="localhost", usuario="sa", contra="123";
    
    public VentasxCliente() {
        initComponents();
        AutoCompleteDecorator.decorate(ItemClientes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ItemClientes = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ventasxcliente = new javax.swing.JTable();
        RegresarMenudeventaxemp = new javax.swing.JButton();
        btnBuscarvC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        ItemClientes.setEditable(true);

        jLabel23.setText("Buscar:");

        tabla_ventasxcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Servicio", "Total de servicio", "fecha", "fecha de entrega"
            }
        ));
        jScrollPane4.setViewportView(tabla_ventasxcliente);

        RegresarMenudeventaxemp.setText("Regresar");
        RegresarMenudeventaxemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarMenudeventaxempActionPerformed(evt);
            }
        });

        btnBuscarvC.setText("Buscar");
        btnBuscarvC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarvCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ItemClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarvC))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RegresarMenudeventaxemp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(ItemClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarvC))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RegresarMenudeventaxemp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        ItemClientes.removeAllItems();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String conecctionUrl = "jdbc:sqlserver://"+this.servidor+";databaseName=burbuja2";
            java.sql.Connection cn = DriverManager.getConnection(conecctionUrl, this.usuario, this.contra);
            Statement sentencia = cn.createStatement();
            String sql="SELECT nombre_cliente FROM cliente";
            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
               ItemClientes.addItem(rs.getString("nombre_cliente"));  
            }
        }catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }  
    }//GEN-LAST:event_formWindowActivated

    private void btnBuscarvCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarvCActionPerformed
        String nombre= ItemClientes.getSelectedItem().toString();
        String sql="select s.tipo_servicio, v.total_venta,v.fecha,"
                + "v.fecha_entrega  from cliente c ,venta v, servicio s" +
                " where  c.id_cliente = v.id_cliente and "
                + "s.id_servicio= v.id_servicio and nombre_cliente ='"+nombre+"'";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String conecctionUrl = "jdbc:sqlserver://"+this.servidor+";databaseName=burbuja2";
            java.sql.Connection cn = DriverManager.getConnection(conecctionUrl, this.usuario, this.contra);
            Statement sentencia = (Statement) cn.createStatement();
            ResultSet rs = sentencia.executeQuery(sql);
            DefaultTableModel m1=(DefaultTableModel)tabla_ventasxcliente.getModel();
            m1.setRowCount(0);
            while (rs.next()) {
                Object []fila={nombre,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                m1.addRow(fila);
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarvCActionPerformed

    private void RegresarMenudeventaxempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarMenudeventaxempActionPerformed
        String v="";
        String te="";
        String nombreUsuario= VentanaPRINCIPAL.ItemUsuarios.getSelectedItem().toString();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentasxEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        String conecctionUrl = "jdbc:sqlserver://"+this.servidor+";databaseName=burbuja2";
        java.sql.Connection cn = null;
        try {
            cn = DriverManager.getConnection(conecctionUrl, this.usuario, this.contra);
        } catch (SQLException ex) {
            Logger.getLogger(VentasxEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
            Statement sentencia = null;
        try {
            sentencia = cn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VentasxEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
            ResultSet r = null;
        try {
            r = sentencia.executeQuery("select tipo_usuario from usuario where nombre_usuario='"+nombreUsuario+"'");
        } catch (SQLException ex) {
            Logger.getLogger(VentasxEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (r!=null){
            try {
                while(r.next())
                    te = r.getString("tipo_usuario");
            } catch (SQLException ex) {
                Logger.getLogger(VentasxEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (te.equals("A")){
            this.dispose();
        }else if (te.equals("E")){
            this.dispose();
        }
    }//GEN-LAST:event_RegresarMenudeventaxempActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentasxEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasxEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasxEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasxEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasxCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ItemClientes;
    private javax.swing.JButton RegresarMenudeventaxemp;
    private javax.swing.JButton btnBuscarvC;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabla_ventasxcliente;
    // End of variables declaration//GEN-END:variables
}
