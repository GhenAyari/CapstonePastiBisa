/*
 * Created by JFormDesigner on Sun Nov 02 20:11:30 SGT 2025
 */

package app.view;

import app.utilities.data.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Asus
 */
public class TeacherLihatSkor extends JFrame {

    private final int teacherId;
    private final String quizTitle;

    // === UI (harus sama dengan yang ada di .jfd) ===
//    private JTextField HalloTeacher;
//    private JPanel panel1;
//    private JScrollPane scrollPane1;
//    private JTable table1;
//    private JButton TombolKembaliLihatSkor;
//    private JButton TombolHapusLihatSkor;

    public TeacherLihatSkor(int teacherId, String quizTitle) {
        this.teacherId = teacherId;
        this.quizTitle = quizTitle;

        initComponents();
        tuneColumns();
        setTitle("Skor: " + quizTitle);
        setLocationRelativeTo(null);

        HalloTeacher.setText("Hallo, Teacher — Skor untuk: " + quizTitle);

        TombolKembaliLihatSkor.addActionListener(e -> {
            new TeacherDashboard(teacherId).setVisible(true);
            dispose();
        });

        TombolHapusLihatSkor.addActionListener(e -> onDeleteScore());

        loadScores();
    }

    /** Ambil semua skor untuk quizTitle ini (hanya milik teacherId) */
    private void loadScores() {
        final String sql =
                "SELECT r.id_rapor, r.quiz_id, s.name AS student_name, " +
                        "       r.is_correct, r.is_incorrect, r.total_scores, r.attempt_date " +
                        "FROM rapor r " +
                        "JOIN quiz q      ON q.question_id = r.quiz_id " +           // r.quiz_id = MIN(question_id) yang kita simpan
                        "JOIN student s   ON s.users_id   = r.student_id " +
                        "WHERE q.teacher_id = ? AND q.quiz_title = ? " +
                        "ORDER BY r.attempt_date DESC";

        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, teacherId);
            ps.setString(2, quizTitle);

            try (ResultSet rs = ps.executeQuery()) {
                DefaultTableModel m = new DefaultTableModel(
                        new Object[]{"ID Rapor","ID Quiz","Nama Student","Is Correct","Incorrect","Score","Date"}, 0
                ) {
                    @Override public boolean isCellEditable(int r, int c) { return false; }
                };

                while (rs.next()) {
                    m.addRow(new Object[]{
                            rs.getInt("id_rapor"),
                            rs.getInt("quiz_id"),
                            rs.getString("student_name"),
                            rs.getInt("is_correct"),
                            rs.getInt("is_incorrect"),
                            rs.getInt("total_scores"),
                            rs.getTimestamp("attempt_date")
                    });
                }
                table1.setModel(m);
                tuneColumns();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal memuat skor", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Hapus 1 baris skor yang dipilih */
    private void onDeleteScore() {
        int row = table1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih satu baris skor di tabel.");
            return;
        }
        int idRapor = Integer.parseInt(String.valueOf(table1.getValueAt(row, 0)));

        int ok = JOptionPane.showConfirmDialog(this,
                "Hapus skor dengan ID Rapor = " + idRapor + " ?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) return;

        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM rapor WHERE id_rapor = ?")) {
            ps.setInt(1, idRapor);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Skor terhapus.");
            loadScores(); // refresh
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal menghapus", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        TombolKembaliLihatSkor = new JButton();
        TombolHapusLihatSkor = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- HalloTeacher ----
        HalloTeacher.setText("Hallo, Teacher");
        HalloTeacher.setBackground(new Color(0x0099cc));
        HalloTeacher.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        HalloTeacher.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        HalloTeacher.setForeground(new Color(0x333333));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x009999));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
            , 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
            panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, "", null, null, null},
                        {null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "ID Rapor", "ID Quiz", "Nama Student", "Is Correct", "Incorrect", "Score", "Date"
                    }
                ));
                scrollPane1.setViewportView(table1);

                // >>> atur perilaku resize dan lebar kolom
                table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // tabel tetap responsif

                javax.swing.table.TableColumnModel cm = table1.getColumnModel();
                cm.getColumn(0).setPreferredWidth(70);   // ID Rapor
                cm.getColumn(1).setPreferredWidth(60);   // ID Quiz
                cm.getColumn(2).setPreferredWidth(140);  // Nama Student
                cm.getColumn(3).setPreferredWidth(80);   // Is Correct
                cm.getColumn(4).setPreferredWidth(80);   // Incorrect
                cm.getColumn(5).setPreferredWidth(80);   // Score
                cm.getColumn(6).setPreferredWidth(220);  // Date - dibuat lebih lebar
// <<< akhir pengaturan kolom

            }

            //---- TombolKembaliLihatSkor ----
            TombolKembaliLihatSkor.setText("Kembali");

            //---- TombolHapusLihatSkor ----
            TombolHapusLihatSkor.setText("Hapus");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addGap(0, 548, Short.MAX_VALUE)
                                .addComponent(TombolHapusLihatSkor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(TombolKembaliLihatSkor)))
                        .addGap(29, 29, 29))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(TombolKembaliLihatSkor)
                            .addComponent(TombolHapusLihatSkor))
                        .addGap(37, 37, 37)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(HalloTeacher, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void tuneColumns() {
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        var cm = table1.getColumnModel();
        cm.getColumn(0).setPreferredWidth(70);
        cm.getColumn(1).setPreferredWidth(60);
        cm.getColumn(2).setPreferredWidth(140);
        cm.getColumn(3).setPreferredWidth(80);
        cm.getColumn(4).setPreferredWidth(80);
        cm.getColumn(5).setPreferredWidth(80);
        cm.getColumn(6).setPreferredWidth(220);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Ghendida
    private JTextField HalloTeacher;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton TombolKembaliLihatSkor;
    private JButton TombolHapusLihatSkor;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
