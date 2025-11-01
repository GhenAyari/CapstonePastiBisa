/*
 * Created by JFormDesigner on Sat Nov 01 11:47:05 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Asus
 */
public class StudentRapor extends JFrame {
    public StudentRapor() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        TableRapor = new JTable();
        TombolKembaliRapor = new JButton();

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
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );

            //======== scrollPane1 ========
            {

                //---- TableRapor ----
                TableRapor.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "Id Rapor", "Quiz ID", "Is Incorrect", "Is Correct", "Total Scores", "Date"
                    }
                ));
                scrollPane1.setViewportView(TableRapor);
            }

            //---- TombolKembaliRapor ----
            TombolKembaliRapor.setText("Kembali");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(TombolKembaliRapor, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(115, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                            .addComponent(TombolKembaliRapor))
                        .addContainerGap(61, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(panel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(HalloTeacher, GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
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
    private JTable TableRapor;
    private JButton TombolKembaliRapor;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
