/*
 * Created by JFormDesigner on Sat Nov 01 11:47:19 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author Asus
 */
public class StudentFeedback extends JFrame {
    public StudentFeedback() {
        initComponents();
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        IsiPesan = new JTextField();
        TombolKembaliStudentFeedback = new JButton();
        scrollPane2 = new JScrollPane();
        TablePengrim = new JTable();
        label1 = new JLabel();
        NamaGuruPengirim = new JTextField();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x006666));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(IsiPesan);
            }

            //---- TombolKembaliStudentFeedback ----
            TombolKembaliStudentFeedback.setText("Kembali");

            //======== scrollPane2 ========
            {

                //---- TablePengrim ----
                TablePengrim.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null},
                        {null, null},
                    },
                    new String[] {
                        "Nama Teacher", "Id Feedback"
                    }
                ));
                scrollPane2.setViewportView(TablePengrim);
            }

            //---- label1 ----
            label1.setText("Isi pesan:");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addComponent(TombolKembaliStudentFeedback, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(295, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(TombolKembaliStudentFeedback))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
            );
        }

        //---- NamaGuruPengirim ----
        NamaGuruPengirim.setText("Hallo, Teacher");
        NamaGuruPengirim.setBackground(new Color(0x0099cc));
        NamaGuruPengirim.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        NamaGuruPengirim.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        NamaGuruPengirim.setForeground(new Color(0x333333));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(NamaGuruPengirim, GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NamaGuruPengirim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Ghendida
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextField IsiPesan;
    private JButton TombolKembaliStudentFeedback;
    private JScrollPane scrollPane2;
    private JTable TablePengrim;
    private JLabel label1;
    private JTextField NamaGuruPengirim;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
