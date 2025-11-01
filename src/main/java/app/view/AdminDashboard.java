/*
 * Created by JFormDesigner on Wed Oct 29 13:37:42 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
import app.controller.AuthController;
import app.view.AdminDashboard;
import app.view.Login;
import app.controller.AdminController;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;



/**
 * @author Asus
 */
public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        initComponents();
        setTitle("Hallo Admin");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        TombolKembaliAdmin.addActionListener(e -> {
            new Login().setVisible(true); // buka halaman login
            dispose(); // tutup halaman admin
        });
        TombolVerifikasiAdmin.addActionListener(e -> verifySelected(true));
        TombolTolakAdmin.addActionListener(e -> verifySelected(false));
        loadPending();
    }

    private void loadPending() {
        try (var rs = AdminController.listPendingAll()) {
            var m = new javax.swing.table.DefaultTableModel(
                    new Object[]{"Nama", "Username", "ID", "Status"}, 0
            ){ @Override public boolean isCellEditable(int r,int c){ return false; }};

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");      // <-- pakai name
                String uname = rs.getString("username"); // <-- pakai username
                String role = rs.getString("role");
                m.addRow(new Object[]{name, uname, id, role});
            }
            TableAdmin.setModel(m);
            try { var st=rs.getStatement(); if(st!=null){ var c=st.getConnection(); st.close(); if(c!=null)c.close(); } } catch(Exception ignore){}
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // ambil baris terpilih lalu approve/reject
    private void verifySelected(boolean approve) {
        int row = TableAdmin.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu.");
            return;
        }
        int id = (int) TableAdmin.getValueAt(row, 2);           // kolom ID
        String role = TableAdmin.getValueAt(row, 3).toString();  // kolom Status berisi role

        try {
            AdminController.verify(role, id, approve);
            loadPending(); // refresh tabel
            JOptionPane.showMessageDialog(this,
                    approve ? "Berhasil diverifikasi." : "Berhasil ditolak/hapus.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloAdmin = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        TableAdmin = new JTable();
        TombolKembaliAdmin = new JButton();
        TombolVerifikasiAdmin = new JButton();
        TombolTolakAdmin = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- HalloAdmin ----
        HalloAdmin.setText("Hallo Admin");
        HalloAdmin.setBackground(new Color(0x0099cc));
        HalloAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        HalloAdmin.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        HalloAdmin.setForeground(new Color(0x333333));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x009999));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.
            red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}});

            //======== scrollPane1 ========
            {

                //---- TableAdmin ----
                TableAdmin.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                    },
                    new String[] {
                        "Nama", "Username", "ID", "Status"
                    }
                ));
                TableAdmin.setForeground(new Color(0x999999));
                TableAdmin.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
                scrollPane1.setViewportView(TableAdmin);
            }

            //---- TombolKembaliAdmin ----
            TombolKembaliAdmin.setText("Kembali");

            //---- TombolVerifikasiAdmin ----
            TombolVerifikasiAdmin.setText("Verifikasi");

            //---- TombolTolakAdmin ----
            TombolTolakAdmin.setText("Tolak");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(TombolVerifikasiAdmin, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombolTolakAdmin, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombolKembaliAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TombolKembaliAdmin)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(TombolVerifikasiAdmin)
                                .addGap(48, 48, 48)
                                .addComponent(TombolTolakAdmin))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(48, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(HalloAdmin, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(HalloAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Ghendida
    private JTextField HalloAdmin;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable TableAdmin;
    private JButton TombolKembaliAdmin;
    private JButton TombolVerifikasiAdmin;
    private JButton TombolTolakAdmin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
