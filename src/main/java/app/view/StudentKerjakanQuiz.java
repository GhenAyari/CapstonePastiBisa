/*
 * Created by JFormDesigner on Wed Oct 29 14:37:01 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Asus
 */
public class StudentKerjakanQuiz extends JFrame {
    public StudentKerjakanQuiz() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        SoaKerjakanQuiz = new JTextField();
        PilganStudentA = new JCheckBox();
        PilganStudentB = new JCheckBox();
        PilganStudentC = new JCheckBox();
        PilganStudentD = new JCheckBox();
        TombNextKerjakan = new JButton();
        TombKumpulkan = new JButton();
        TombKembali = new JButton();

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
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) )
            ; panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(SoaKerjakanQuiz);
            }

            //---- PilganStudentA ----
            PilganStudentA.setText("A");

            //---- PilganStudentB ----
            PilganStudentB.setText("B");

            //---- PilganStudentC ----
            PilganStudentC.setText("C");

            //---- PilganStudentD ----
            PilganStudentD.setText("D");

            //---- TombNextKerjakan ----
            TombNextKerjakan.setText("Next");

            //---- TombKumpulkan ----
            TombKumpulkan.setText("Kumpulkan");

            //---- TombKembali ----
            TombKembali.setText("Kembali");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(PilganStudentA)
                                .addGap(29, 29, 29)
                                .addComponent(PilganStudentB)
                                .addGap(39, 39, 39)
                                .addComponent(PilganStudentC)
                                .addGap(45, 45, 45)
                                .addComponent(PilganStudentD)
                                .addGap(42, 42, 42)
                                .addComponent(TombNextKerjakan)
                                .addGap(28, 28, 28)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TombKembali, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TombKumpulkan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(PilganStudentA)
                            .addComponent(PilganStudentB)
                            .addComponent(PilganStudentC)
                            .addComponent(PilganStudentD)
                            .addComponent(TombNextKerjakan)
                            .addComponent(TombKumpulkan))
                        .addGap(33, 33, 33)
                        .addComponent(TombKembali)
                        .addContainerGap(190, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, 597, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
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
    private JTextField SoaKerjakanQuiz;
    private JCheckBox PilganStudentA;
    private JCheckBox PilganStudentB;
    private JCheckBox PilganStudentC;
    private JCheckBox PilganStudentD;
    private JButton TombNextKerjakan;
    private JButton TombKumpulkan;
    private JButton TombKembali;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
