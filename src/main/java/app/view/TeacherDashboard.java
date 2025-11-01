/*
 * Created by JFormDesigner on Wed Oct 29 13:45:19 SGT 2025
 */

package app.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


/**
 * @author Asus
 */
public class TeacherDashboard extends JFrame {
    private int teacherId;
    public TeacherDashboard() {
        initComponents();
    }

    public TeacherDashboard(int teacherId) {   // konstruktor yang dipakai saat login
        this.teacherId = teacherId;
        initComponents();
        setTitle("Teacher Dashboard - ID: " + teacherId);
        setLocationRelativeTo(null);
        loadProfile();// tampilkan nama guru
        loadQuizList();
//        onDeleteQuiz();
        // === Event tombol Tambah Quiz ===
        TombTambahQuiz.addActionListener(e -> {
            new TeacherTambahQuiz(teacherId).setVisible(true); // buka form tambah quiz
            dispose(); // tutup halaman dashboard
        });

        TombolKembaliDashboard.addActionListener(e -> {
            new Login().setVisible(true); // buka form login
            dispose(); // tutup halaman dashboard
        });
        TombHapusQuiz.addActionListener(e -> onDeleteQuiz());

    }


    private void loadQuizList() {
        try (ResultSet rs = app.controller.TeacherQuizController.listQuizSummary(teacherId)) {
            DefaultTableModel m = new DefaultTableModel(
                    new Object[]{"ID Quiz", "Nama Quiz", "Jumlah Soal"}, 0
            ) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };

            while (rs.next()) {
                int id = rs.getInt("quiz_id");
                String title = rs.getString("quiz_title");
                int count = rs.getInt("jumlah_soal");
                m.addRow(new Object[]{id, title, count});
            }
            TableTeacherDashboard.setModel(m);

            // rapikan resource dari ResultSet yang kembali
            try {
                var st = rs.getStatement();
                if (st != null) {
                    var conn = st.getConnection();
                    st.close();
                    if (conn != null) conn.close();
                }
            } catch (Exception ignore) {}

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void loadProfile() {
        try (var c = app.util.Database.get();
             var ps = c.prepareStatement("SELECT name, username FROM teacher WHERE users_id=?")) {
            ps.setInt(1, teacherId);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String uname = rs.getString("username");
                    setTitle("Teacher: " + name + " (" + uname + ")");
                    // kalau kamu punya JLabel, bisa juga begini:
                    // lblTeacherInfo.setText("Selamat datang, " + name);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===================== HAPUS QUIZ ======================
    private void onDeleteQuiz() {
        int row = TableTeacherDashboard.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih satu quiz di tabel dulu.");
            return;
        }

        // kolom: 0=ID Quiz, 1=Nama Quiz, 2=Jumlah Soal
        String quizTitle = TableTeacherDashboard.getValueAt(row, 1).toString();
        int count        = Integer.parseInt(TableTeacherDashboard.getValueAt(row, 2).toString());

        int ok = JOptionPane.showConfirmDialog(
                this,
                "Hapus quiz \"" + quizTitle + "\" beserta " + count + " soal?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );
        if (ok != JOptionPane.YES_OPTION) return;

        try {
            int deleted = app.controller.TeacherQuizController
                    .deleteQuizByTitle(teacherId, quizTitle);
            loadQuizList(); // refresh tabel
            JOptionPane.showMessageDialog(this,
                    "Terhapus: " + deleted + " baris soal untuk \"" + quizTitle + "\".");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal hapus", JOptionPane.ERROR_MESSAGE);
        }
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
        TombolKembaliDashboard = new JButton();

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
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
            ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
            .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
            propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );

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

            //---- TombolKembaliDashboard ----
            TombolKembaliDashboard.setText("Kembali");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
                            .addComponent(DaftarQuizTeacher, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(TombTambahQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombUpdateQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombHapusQuiz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TombolKembaliDashboard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(26, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(DaftarQuizTeacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(TombolKembaliDashboard)))
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
    private JButton TombolKembaliDashboard;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
