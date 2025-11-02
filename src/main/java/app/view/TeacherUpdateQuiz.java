package app.view;/*
 * Created by JFormDesigner on Wed Oct 29 14:05:52 SGT 2025
 */

import app.utilities.data.DatabaseConnection;
import app.view.TeacherDashboard;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class TeacherUpdateQuiz extends JFrame {

    // ---------- FIELD ----------
    private int teacherId;
    private int quizId;          // hanya untuk display
    private String quizTitle;
    private int maxSoal = 1;     // jumlah soal pada quiz ini

//    // ---------- KOMPONEN (pastikan namanya sama dengan di formmu) ----------
//    private JButton TombKembaliUpdateQuiz;
//    private JButton TombolSimpan;            // tombol simpan
//    private JSpinner PilihSoalKe;            // spinner pilih soal
//    private JTextArea InputSoalArea;         // area teks soal
//    private JTextField FieldIdQuiz;          // menampilkan ID quiz (readonly)

    // ---------- CONSTRUCTOR ----------
    public TeacherUpdateQuiz(int teacherId, int quizId, String quizTitle) {
        this.teacherId = teacherId;
        this.quizId = quizId;          // display
        this.quizTitle = quizTitle;    // kunci query bareng teacherId + question_number

        initComponents(); // (punya JFormDesigner)
        setTitle("Update Quiz: " + quizTitle + " (ID " + quizId + ")");
        setLocationRelativeTo(null);

        if (FieldIdQuiz != null) {
            FieldIdQuiz.setText(String.valueOf(quizId));
            FieldIdQuiz.setEditable(false);
        }

        // tombol kembali -> balik ke dashboard
        TombKembaliUpdateQuiz.addActionListener(e -> {
            new TeacherDashboard(teacherId).setVisible(true);
            dispose();
        });

        // hitung total soal untuk set batas spinner
        maxSoal = hitungJumlahSoal();
        if (maxSoal < 1) maxSoal = 1;

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, maxSoal, 1);
        PilihSoalKe.setModel(model);

        // load soal pertama
        muatSoal(1);

        // ganti soal saat spinner berubah
        PilihSoalKe.addChangeListener(ev -> {
            int nomor = (Integer) PilihSoalKe.getValue();
            muatSoal(nomor);
        });

        // simpan perubahan
        TombolSimpan.addActionListener(e -> simpanSoalAktif());
    }

    // ========== HELPER #3: hitung jumlah soal ==========
    private int hitungJumlahSoal() {
        final String sql = "SELECT COUNT(*) FROM quiz WHERE teacher_id=? AND quiz_title=?";
        try (var c = DatabaseConnection.get();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ps.setString(2, quizTitle);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return 1;
        }
    }

    // ========== HELPER #4: load soal berdasarkan nomor ==========
    private void muatSoal(int nomor) {
        final String sql = "SELECT soal FROM quiz " +
                "WHERE teacher_id=? AND quiz_title=? AND question_number=?";
        try (var c = DatabaseConnection.get();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ps.setString(2, quizTitle);
            ps.setInt(3, nomor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String teks = rs.getString("soal");
                    InputSoalArea.setText(teks != null ? teks : "");
                } else {
                    InputSoalArea.setText("");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ========== HELPER #5: simpan/update soal aktif ==========
    private void simpanSoalAktif() {
        int nomor = (Integer) PilihSoalKe.getValue();
        String teks = InputSoalArea.getText().trim();

        if (teks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi soal tidak boleh kosong.");
            InputSoalArea.requestFocus();
            return;
        }

        final String sql = "UPDATE quiz SET soal=? " +
                "WHERE teacher_id=? AND quiz_title=? AND question_number=?";
        try (var c = DatabaseConnection.get();
             var ps = c.prepareStatement(sql)) {
            ps.setString(1, teks);
            ps.setInt(2, teacherId);
            ps.setString(3, quizTitle);
            ps.setInt(4, nomor);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Soal #" + nomor + " berhasil disimpan.");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Baris soal tidak ditemukan. Pastikan nomor soal valid.",
                        "Tidak ditemukan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal menyimpan", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        label1 = new JLabel();
        FieldIdQuiz = new JTextField();
        InputSoalArea = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        TombolSimpan = new JButton();
        PilihSoalKe = new JSpinner();
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
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );

            //---- label1 ----
            label1.setText("Anda berada di quiz ");
            label1.setForeground(Color.black);

            //---- label2 ----
            label2.setText("Soal");
            label2.setForeground(Color.black);

            //---- label3 ----
            label3.setText("Pilih Soal Ke");
            label3.setForeground(Color.black);

            //---- TombolSimpan ----
            TombolSimpan.setText("Simpan");

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
                                        .addComponent(FieldIdQuiz, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(InputSoalArea, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(TombolSimpan, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PilihSoalKe, GroupLayout.Alignment.LEADING))
                                    .addComponent(TombKembaliUpdateQuiz))
                                .addGap(30, 30, 30))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(FieldIdQuiz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(TombKembaliUpdateQuiz))
                        .addGap(18, 18, 18)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PilihSoalKe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(TombolSimpan))
                            .addComponent(InputSoalArea, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
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
    private JTextField FieldIdQuiz;
    private JTextField InputSoalArea;
    private JLabel label2;
    private JLabel label3;
    private JButton TombolSimpan;
    private JSpinner PilihSoalKe;
    private JButton TombKembaliUpdateQuiz;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
