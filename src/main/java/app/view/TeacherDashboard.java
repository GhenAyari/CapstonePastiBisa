/*
 * Created by JFormDesigner on Wed Oct 29 13:45:19 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Asus
 */
public class TeacherDashboard extends JFrame {
    private int teacherId;
    public TeacherDashboard() {
        initComponents();
    }

    public TeacherDashboard(int teacherId) {
        this.teacherId = teacherId;
        initComponents();
        setTitle("Teacher Dashboard - ID: " + teacherId);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        DaftarQuizTeacher = new JTextField();
        scrollPane1 = new JScrollPane();
        TableTeacherDashboard = new JTable();
        TombTambahQuiz = new JButton();
        TombUpdateQuiz = new JButton();
        TombHapusQuiz = new JButton();

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
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
            (0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border
            .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
            .Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
            propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
            ;}});

            //---- DaftarQuizTeacher ----
            DaftarQuizTeacher.setText("Daftar Quiz");
            DaftarQuizTeacher.setBackground(new Color(0xcccccc));
            DaftarQuizTeacher.setForeground(Color.black);

            //======== scrollPane1 ========
            {

                //---- TableTeacherDashboard ----
                TableTeacherDashboard.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null},
                        {null, null, null},
                    },
                    new String[] {
                        "ID Quiz", "Nama Quiz", "Jumlah Soal"
                    }
                ));
                scrollPane1.setViewportView(TableTeacherDashboard);
            }

            //---- TombTambahQuiz ----
            TombTambahQuiz.setText("Tambah Quiz");

            //---- TombUpdateQuiz ----
            TombUpdateQuiz.setText("Update Quiz");

            //---- TombHapusQuiz ----
            TombHapusQuiz.setText("Hapus Quiz");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(DaftarQuizTeacher, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TombTambahQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TombUpdateQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TombHapusQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(26, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(DaftarQuizTeacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(TombTambahQuiz)
                                .addGap(35, 35, 35)
                                .addComponent(TombUpdateQuiz)
                                .addGap(32, 32, 32)
                                .addComponent(TombHapusQuiz)))
                        .addContainerGap(21, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, 597, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
    private JTextField DaftarQuizTeacher;
    private JScrollPane scrollPane1;
    private JTable TableTeacherDashboard;
    private JButton TombTambahQuiz;
    private JButton TombUpdateQuiz;
    private JButton TombHapusQuiz;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
