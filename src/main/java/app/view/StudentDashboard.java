/*
 * Created by JFormDesigner on Wed Oct 29 14:33:00 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

import app.utilities.data.DatabaseConnection; // <--- ini juga penting (untuk Database.get())

/**
 * @author Asus
 */
public class StudentDashboard extends JFrame {
    private int studentId;

    public StudentDashboard() {
        initComponents();
    }

    public StudentDashboard(int studentId) {
        this.studentId = studentId;
        initComponents();
        setTitle("Student Dashboard - ID: " + studentId);
        setLocationRelativeTo(null);
        loadProfile();
        // === Tambahkan ini ===
        TombKembaliDashStudent.addActionListener(e -> {
            new Login().setVisible(true);  // buka halaman login lagi
            dispose();                     // tutup halaman student
        });
    }

    private void loadProfile() {
        try (var c = DatabaseConnection.get();
             var ps = c.prepareStatement("SELECT name, username FROM student WHERE users_id=?")) {
            ps.setInt(1, studentId);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String uname = rs.getString("username");
                    setTitle("Student: " + name + " (" + uname + ")");
                    // kalau punya JLabel info, set di sini
                    // lblStudentInfo.setText("Halo, " + name + " (" + uname + ")");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        TableDashStudent = new JTable();
        TombKerjakanQuiz = new JButton();
        TombLihatSkor = new JButton();
        TombKembaliDashStudent = new JButton();
        TombLihatFeedback = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //---- HalloTeacher ----
        HalloTeacher.setText("Hallo, Student");
        HalloTeacher.setBackground(new Color(0x0099cc));
        HalloTeacher.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        HalloTeacher.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        HalloTeacher.setForeground(new Color(0x333333));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x009999));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

            //======== scrollPane1 ========
            {

                //---- TableDashStudent ----
                TableDashStudent.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, ""},
                        {null, null},
                    },
                    new String[] {
                        "Nama Quiz", "Jumlah Soal"
                    }
                ));
                scrollPane1.setViewportView(TableDashStudent);
            }

            //---- TombKerjakanQuiz ----
            TombKerjakanQuiz.setText("Kerjakan Quiz");

            //---- TombLihatSkor ----
            TombLihatSkor.setText("Lihat Skor");

            //---- TombKembaliDashStudent ----
            TombKembaliDashStudent.setText("Kembali");

            //---- TombLihatFeedback ----
            TombLihatFeedback.setText("Lihat Pesan");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(TombKerjakanQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombLihatSkor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombKembaliDashStudent, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                            .addComponent(TombLihatFeedback, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(TombKembaliDashStudent)
                                .addGap(51, 51, 51)
                                .addComponent(TombKerjakanQuiz)
                                .addGap(18, 18, 18)
                                .addComponent(TombLihatSkor)
                                .addGap(18, 18, 18)
                                .addComponent(TombLihatFeedback))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(38, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(HalloTeacher, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Ghendida
    private JTextField HalloTeacher;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable TableDashStudent;
    private JButton TombKerjakanQuiz;
    private JButton TombLihatSkor;
    private JButton TombKembaliDashStudent;
    private JButton TombLihatFeedback;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
