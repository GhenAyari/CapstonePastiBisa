/*
 * Created by JFormDesigner on Sun Nov 02 20:54:13 SGT 2025
 */

package app.view;

import app.utilities.data.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;



/**
 * @author Asus
 */
public class TeacherFeedback extends JFrame {

    private final int teacherId;

    // komponen dari form (pastikan namanya sama dengan di JFormDesigner)
//    private JTable TableSiswa;
//    private JTextArea IsiPesan;
//    private JButton TombolKembaliTeacherFeedback, TombolKirimTeacher;

    public TeacherFeedback(int teacherId) {
        this.teacherId = teacherId;
        initComponents();                 // <-- panggil form JFD-mu
        setTitle("Kirim Pesan / Feedback");
        setLocationRelativeTo(null);

        // wire events
        TombolKembaliTeacherFeedback.addActionListener(e -> {
            new TeacherDashboard(teacherId).setVisible(true);
            dispose();
        });

        TombolKirimTeacher.addActionListener(e -> {
            int row = TableSiswa.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Pilih siswa di tabel dulu.");
                return;
            }
            String body = IsiPesan.getText().trim();
            if (body.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi pesan tidak boleh kosong.");
                return;
            }

            int studentId = Integer.parseInt(TableSiswa.getValueAt(row, 0).toString()); // kolom 0 = users_id siswa
            long ts = System.currentTimeMillis();

            // >>>> SELIPKAN METADATA DI AWAL PESAN <<<<
            String payload = String.format("[FD|t=%d|s=%d|ts=%d]\n%s", teacherId, studentId, ts, body);

            final String sql = "INSERT INTO feedback(message) VALUES (?)";
            try (var c = app.utilities.data.DatabaseConnection.get();
                 var ps = c.prepareStatement(sql)) {
                ps.setString(1, payload);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Pesan terkirim!");
                IsiPesan.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal mengirim", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadStudents();
    }

    /** Ambil daftar siswa terverifikasi dan tampilkan di tabel */
    private void loadStudents() {
        final String sql =
                "SELECT users_id, username, name FROM student " +
                        "WHERE is_verified = TRUE ORDER BY username";

        DefaultTableModel m = new DefaultTableModel(
                new Object[]{"ID", "Username Siswa", "Nama Siswa"}, 0
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                m.addRow(new Object[]{
                        rs.getInt("users_id"),
                        rs.getString("username"),
                        rs.getString("name")
                });
            }
            TableSiswa.setModel(m);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal load siswa",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Ambil username guru (untuk metadata pesan) */
    private String fetchTeacherUsername() {
        final String sql = "SELECT username FROM teacher WHERE users_id = ?";
        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString(1);
            }
        } catch (Exception ignore) {}
        return "unknown";
    }

    /** Kirim pesan -> INSERT ke feedback(message) saja */
    private void onSend() {
        int row = TableSiswa.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih siswa dulu dari tabel.");
            return;
        }
        String studentUsername = String.valueOf(TableSiswa.getValueAt(row, 1));
        String studentName     = String.valueOf(TableSiswa.getValueAt(row, 2));

        String body = IsiPesan.getText().trim();
        if (body.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi pesan tidak boleh kosong.");
            IsiPesan.requestFocus();
            return;
        }

        // bentuk payload + metadata (tanpa ubah tabel)
        String teacherUsername = fetchTeacherUsername();
        String header = String.format("[From: %s → To: %s (%s) @%tF %<tT]",
                teacherUsername, studentUsername, studentName, new Date());
        String payload = header + System.lineSeparator() + body;

        final String insert = "INSERT INTO feedback (message) VALUES (?)";
        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(insert)) {
            ps.setString(1, payload);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pesan terkirim.");
            IsiPesan.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal mengirim",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloAdmin = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        TableSiswa = new JTable();
        scrollPane2 = new JScrollPane();
        IsiPesan = new JTextField();
        label1 = new JLabel();
        TombolKembaliTeacherFeedback = new JButton();
        TombolKirimTeacher = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- HalloAdmin ----
        HalloAdmin.setText("Hallo Teacher");
        HalloAdmin.setBackground(new Color(0x0099cc));
        HalloAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        HalloAdmin.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        HalloAdmin.setForeground(new Color(0x333333));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x006666));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //======== scrollPane1 ========
            {

                //---- TableSiswa ----
                TableSiswa.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null},
                        {null, null},
                    },
                    new String[] {
                        "Username Siswa", "Nama Siswa"
                    }
                ));
                scrollPane1.setViewportView(TableSiswa);
            }

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(IsiPesan);
            }

            //---- label1 ----
            label1.setText("Isi pesan dibawah:");

            //---- TombolKembaliTeacherFeedback ----
            TombolKembaliTeacherFeedback.setText("Kembali");

            //---- TombolKirimTeacher ----
            TombolKirimTeacher.setText("Kirim");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TombolKirimTeacher, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(TombolKembaliTeacherFeedback, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(217, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(TombolKembaliTeacherFeedback)
                                .addGap(40, 40, 40)
                                .addComponent(TombolKirimTeacher))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(HalloAdmin, GroupLayout.PREFERRED_SIZE, 869, GroupLayout.PREFERRED_SIZE)))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(HalloAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
    private JTable TableSiswa;
    private JScrollPane scrollPane2;
    private JTextField IsiPesan;
    private JLabel label1;
    private JButton TombolKembaliTeacherFeedback;
    private JButton TombolKirimTeacher;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
