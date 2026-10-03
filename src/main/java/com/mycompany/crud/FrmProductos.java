/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author PC-16
 */
public class FrmProductos extends javax.swing.JInternalFrame {
    private Map<String, Integer> categorias =
    new HashMap<>();
    /**
     * Creates new form FrmProductos
     */
    public FrmProductos() {
        
        initComponents();
        try (Connection cn = Conexion.conectar()) {
        System.out.println("Conexión correcta");
        } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
}
    }
    private void cargarCategorias() {
        cboCategoria.removeAllItems();
        categorias.clear();
        String sql =
        "SELECT id_categoria, nombre " +
        "FROM categoria " +
        "ORDER BY nombre";
        try (Connection cn = Conexion.conectar();
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
        int id = rs.getInt("id_categoria");
        String nombre = rs.getString("nombre");
        cboCategoria.addItem(nombre);
        categorias.put(nombre, id);
        }
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(
    this,
    "Error al cargar categorías: " + e.getMessage());
    }
}
    private void listarProductos() {
        DefaultTableModel modelo = new DefaultTableModel(
        new Object[]{
        "ID", "Producto", "Categoría", "Precio", "Stock"
        }, 0
    ) {
    @Override
    public boolean isCellEditable(int row, int column) {
    return false;
    }
    };
    String sql =
    "SELECT p.id_producto, p.nombre, " +
    "c.nombre AS categoria, p.precio, p.stock " +
    "FROM producto p " +
    "INNER JOIN categoria c " +
    "ON p.id_categoria = c.id_categoria " +
    "ORDER BY p.id_producto";
    try (Connection cn = Conexion.conectar();
    PreparedStatement ps = cn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery()) {
    while (rs.next()) {
    Object[] fila = {
    rs.getInt("id_producto"),
    rs.getString("nombre"),
    rs.getString("categoria"),
    rs.getDouble("precio"),
    rs.getInt("stock")
    };
    modelo.addRow(fila);
 }
    tblProductos.setModel(modelo);
 } catch (SQLException e) {
    JOptionPane.showMessageDialog(
    this,
    "Error al listar productos: " + e.getMessage());
    }
}
   private boolean validarCampos() {
    if (txtNombre.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this,
    "Ingrese el nombre del producto");
    txtNombre.requestFocus();
    return false;
}
    if (cboCategoria.getSelectedItem() == null) {
    JOptionPane.showMessageDialog(this,
    "Seleccione una categoría");
    return false;
}
    if (txtPrecio.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this,
    "Ingrese el precio");
    txtPrecio.requestFocus();
    return false;
}
    if (txtStock.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this,
    "Ingrese el stock");
    txtStock.requestFocus();
    return false;
    }
    return true;
}
   private Double obtenerPrecioValido() {
        try {
        double precio = Double.parseDouble(
        txtPrecio.getText().trim());
    if (precio <= 0) {
    JOptionPane.showMessageDialog(this,
    "El precio debe ser mayor que cero");
    txtPrecio.requestFocus();
    return null;
    }
    return precio;
    } catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this,
    "Ingrese un precio válido");
    txtPrecio.requestFocus();
    return null;
    }
}
   private Integer obtenerStockValido() {
        try {
        int stock = Integer.parseInt(
        txtStock.getText().trim());
        if (stock < 0) {
        JOptionPane.showMessageDialog(this,
        "El stock no puede ser negativo");
        txtStock.requestFocus();
        return null;
        }
        return stock;
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
        "Ingrese un stock válido");
        txtStock.requestFocus();
            return null;
    }
}
   
   

    
    private void habilitarCampos(boolean estado) {
        txtNombre.setEnabled(estado);
        cboCategoria.setEnabled(estado);
        txtPrecio.setEnabled(estado);
        txtStock.setEnabled(estado);
    }
    private void estadoInicial() {
        habilitarCampos(false);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    private void estadoNuevo() {
        habilitarCampos(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(true);
    }
    private void estadoEdicion() {
        habilitarCampos(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        cboCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setTitle("GESTION DE PRODUCTOS                                                                               SENATI");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel2.setText("Producto");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel3.setText("Categoria");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCategoria.addActionListener(this::cboCategoriaActionPerformed);

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel4.setText("Precio");

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel5.setText("Stock");

        btnNuevo.setBackground(new java.awt.Color(0, 51, 204));
        btnNuevo.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("NUEVO");

        btnGuardar.setBackground(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");

        btnActualizar.setBackground(new java.awt.Color(255, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setBackground(new java.awt.Color(204, 0, 51));
        btnEliminar.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");

        btnCancelar.setBackground(new java.awt.Color(153, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar))
                            .addComponent(jLabel3))
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(txtPrecio))
                        .addGap(84, 84, 84))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        tblProductos.setBackground(new java.awt.Color(204, 204, 204));
        tblProductos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "Mouse Logitech", "Perifericos", "69.90", "20"},
                { new Integer(2), "Monitor LG 24", "Monitores", "649.90", "8"},
                { new Integer(3), "SSD Kingston 1 TB", "Almacenamiento", "289.90", "15"}
            },
            new String [] {
                "ID", "Producto", "Categoria", "Precio", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblProductos.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(tblProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCategoriaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
