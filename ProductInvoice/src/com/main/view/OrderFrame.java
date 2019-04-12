/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.view;

import com.main.dto.OrderProductDto;
import com.main.dto.ProductDto;
import com.main.model.Order;
import com.main.model.OrderDetails;
import com.main.refector.OrderService;
import com.main.service.EmailService;
import com.main.service.ProductService;
import com.main.table.OrderTableModel;
import com.main.table.ProductTableModel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author duong
 */
public class OrderFrame extends javax.swing.JFrame {

    private ProductService productService;
    private EmailService emailService;
    private OrderService orderService;
    private ArrayList<OrderProductDto> orderProductDtos;

    /**
     * Creates new form Order
     */
    public OrderFrame() {
        initComponents();
        orderProductDtos = new ArrayList<OrderProductDto>();
        productService = new ProductService();
        emailService = new EmailService();
        orderService = new OrderService();
        GetProductTable(null, null);
        GetOrderProductTable();
    }

    private void GetProductTable(String search, String type) {
        tableProduct.setModel(new ProductTableModel(productService.GetProducts(search, type)));

    }

    private void GetOrderProductTable() {
        jTable1.setModel(new OrderTableModel(orderProductDtos));
        Double total = 0.0;
        for (OrderProductDto orderProductDto : orderProductDtos) {
            Double temp = (Double) (orderProductDto.getQuantity() * orderProductDto.getProductDto().getPrice());
            total = total + temp;
        }
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        lbTotal.setText(df.format(total));
    }

    private void AddOrUpdate(Object source) {
        JFrame frame = new JFrame("Input Quantity");
        String quantity = JOptionPane.showInputDialog(
                frame,
                "Enter the quantity",
                "",
                JOptionPane.WARNING_MESSAGE
        );
        JTable target = (JTable) source;
        int row = target.getSelectedRow();
        ProductTableModel model = (ProductTableModel) target.getModel();
        ProductDto pd = model.getSelectedObject(row);
        Boolean newProduct = true;
        try {
            for (int i = 0; i < orderProductDtos.size(); i++) {
                OrderProductDto order = orderProductDtos.get(i);
                if (order.getProductDto().getId() == pd.getId()) {
                    order.setQuantity(order.getQuantity() + Integer.parseInt(quantity));
                    orderProductDtos.set(i, order);
                    newProduct = false;
                }
            }
            if (newProduct) {
                OrderProductDto opd = new OrderProductDto(pd, Integer.parseInt(quantity));
                orderProductDtos.add(opd);
            }
            GetOrderProductTable();
        } catch (Exception e) {
        }
    }

    private boolean Valid() {
        int result = 0;
        Border defaultBorder = txtMail.getBorder();
        if (txtMail.getText().trim().length() == 0) {
            txtMail.setBorder(new LineBorder(Color.RED, 2));
            result++;
        } else {
            txtMail.setBorder(new JTextField().getBorder());
        }
        if (txtName.getText().trim().length() == 0) {
            txtName.setBorder(new LineBorder(Color.RED, 2));
            result++;
        } else {
            txtName.setBorder(new JTextField().getBorder());
        }
        if (txtPhone.getText().trim().length() == 0) {
            txtPhone.setBorder(new LineBorder(Color.RED, 2));
            result++;
        } else {
            txtPhone.setBorder(new JTextField().getBorder());
        }
        return result == 0;
    }

    private void SearchProduct() {
        String search = txtSearch.getText();
        String type = (String) jComboBox1.getSelectedItem();
        GetProductTable(search, type);
    }

    private ArrayList<OrderDetails> GetOrderDetails() {
        ArrayList<OrderDetails> ods = new ArrayList<OrderDetails>();
        for (OrderProductDto opd : orderProductDtos) {
            OrderDetails details = new OrderDetails(0, opd.getProductDto().getId(), 0, opd.getQuantity(), opd.getProductDto().getPrice());
            ods.add(details);
        }
        return ods;
    }

    private String GetMsgMail() {
        String msg = "Order Detail\n";
        for (OrderProductDto orderProductDto : orderProductDtos) {
            msg += orderProductDto.getProductDto().getName()
                    + "   " + orderProductDto.getQuantity()
                    + "   " + new DecimalFormat("###,###,###.##").format(orderProductDto.getProductDto().getPrice())
                    + "   " + new DecimalFormat("###,###,###.##").format(orderProductDto.getQuantity() * orderProductDto.getProductDto().getPrice()) + "\n";
        }
        msg += "Total: " + lbTotal.getText();
        return msg;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        tableProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableProductKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduct);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSearchMouseReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Name" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Total");

        lbTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTotal.setText("0");

        jLabel2.setText("Name");

        jLabel3.setText("Email");

        jLabel4.setText("Phone");

        jButton1.setText("Create Order");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName)
                            .addComponent(txtMail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(58, 58, 58)
                                .addComponent(lbTotal))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lbTotal))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMail, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseReleased
        SearchProduct();
    }//GEN-LAST:event_btnSearchMouseReleased

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SearchProduct();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseClicked
        if (evt.getClickCount() == 2) {
            AddOrUpdate(evt.getSource());
        }
    }//GEN-LAST:event_tableProductMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            JFrame frame = new JFrame("Input Quantity");
            String quantity = JOptionPane.showInputDialog(
                    frame,
                    "Enter the quantity",
                    "",
                    JOptionPane.WARNING_MESSAGE
            );
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            OrderTableModel model = (OrderTableModel) target.getModel();
            OrderProductDto opd = model.getSelectedObject(row);
            try {
                OrderProductDto order = orderProductDtos.get(row);
                if (order.getProductDto().getId() == opd.getProductDto().getId()) {
                    order.setQuantity(Integer.parseInt(quantity));
                    orderProductDtos.set(row, order);
                }
                GetOrderProductTable();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (jTable1.getSelectedRow() > -1) {
                int[] selectedrows = jTable1.getSelectedRows();
                OrderTableModel model = (OrderTableModel) jTable1.getModel();
                ArrayList<OrderProductDto> temp = new ArrayList<OrderProductDto>();
                for (int i = 0; i < selectedrows.length; i++) {
                    OrderProductDto opd = model.getSelectedObject(i);
                    temp.add(opd);
                }
                orderProductDtos.removeAll(temp);
                GetOrderProductTable();
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void tableProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProductKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AddOrUpdate(evt.getSource());
        }
    }//GEN-LAST:event_tableProductKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (orderProductDtos.size()==0) {
            JOptionPane.showMessageDialog(null, "Please choice product!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Valid()) {
            return;
        }
        String name = txtName.getText();
        String mail = txtMail.getText();
        String phone = txtPhone.getText();
        Order order = new Order(0, name, phone, mail, null);
        ArrayList<OrderDetails> ods = GetOrderDetails();
        if (orderService.CreateOrder(order, ods)) {
            new Thread(new Runnable() {
                public void run() {
                    emailService.SendMail(mail, "test", GetMsgMail());
                }
            }).start();
            JFrame frame = new JFrame("Input Quantity");
            JOptionPane.showMessageDialog(
                    frame,
                    "Create Order Sụccess"
            );
            txtName.setText("");
            txtMail.setText("");
            txtPhone.setText("");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(OrderFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
