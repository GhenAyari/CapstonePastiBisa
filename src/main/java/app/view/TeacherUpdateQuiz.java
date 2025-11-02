/*
 * Created by JFormDesigner on Wed Oct 29 14:05:52 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Asus
 */
public class TeacherUpdateQuiz extends JFrame {
    private int teacherId;
    private int quizId;
    private String quizTitle;

    public TeacherUpdateQuiz() {                    // diperlukan JFormDesigner
        initComponents();
    }

    public TeacherUpdateQuiz(int teacherId, int quizId, String quizTitle) {
        this.teacherId = teacherId;
        this.quizId = quizId;
        this.quizTitle = quizTitle;

        initComponents();
        setTitle("Update Quiz: " + quizTitle + " (ID " + quizId + ")");
        setLocationRelativeTo(null);

        TombKembaliUpdateQuiz.addActionListener(e -> {
            new TeacherDashboard(teacherId).setVisible(true);
            dispose(); // tutup form update
        });

        // TODO: nanti di sini kamu load semua soal utk quizTitle & tampilkan ke UI
        // loadQuestions();

        // (opsional) kalau kamu punya tombol kembali di form update:
        // TombolKembaliUpdate.addActionListener(e -> {
        //     new TeacherDashboard(teacherId).setVisible(true);
        //     dispose();
        // });
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        label1 = new JLabel();
        beradaDiSoal = new JTextField();
        TextSoal = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();
        spinner1 = new JSpinner();
        TombKembaliUpdateQuiz = new JButton();

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
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder
            ( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );

            //---- label1 ----
            label1.setText("Anda berada di quiz ");
            label1.setForeground(Color.black);

            //---- label2 ----
            label2.setText("Soal");
            label2.setForeground(Color.black);

            //---- label3 ----
            label3.setText("Pilih Soal Ke");
            label3.setForeground(Color.black);

            //---- button1 ----
            button1.setText("Simpan");

            //---- TombKembaliUpdateQuiz ----
            TombKembaliUpdateQuiz.setText("Kembali");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(beradaDiSoal, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(TextSoal, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(spinner1, GroupLayout.Alignment.LEADING))
                                    .addComponent(TombKembaliUpdateQuiz))
                                .addGap(30, 30, 30))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(beradaDiSoal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(TombKembaliUpdateQuiz))
                        .addGap(18, 18, 18)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(button1))
                            .addComponent(TextSoal, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(130, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addComponent(HalloTeacher, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
    private JLabel label1;
    private JTextField beradaDiSoal;
    private JTextField TextSoal;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    private JSpinner spinner1;
    private JButton TombKembaliUpdateQuiz;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
