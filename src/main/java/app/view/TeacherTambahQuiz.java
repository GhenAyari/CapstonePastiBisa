/*
 * Created by JFormDesigner on Wed Oct 29 13:55:22 SGT 2025
 */

package app.view;
// imports yang dibutuhkan
import app.controller.TeacherQuizController;
import app.model.QuestionItem;
import app.view.TeacherDashboard;
import app.util.Database; // tidak dipakai langsung di file ini, tapi aman

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Asus
 */
public class TeacherTambahQuiz extends JFrame {

    private int teacherId;

    // Buffer soal: key = nomor soal, value = isi soal
    private final Map<Integer, QuestionItem> buffer = new LinkedHashMap<>();

    // Constructor default (dipakai JFormDesigner untuk preview)
    public TeacherTambahQuiz() {
        initComponents();
        commonInit();
    }

    // Constructor saat dibuka dari TeacherDashboard
    public TeacherTambahQuiz(int teacherId) {
        this.teacherId = teacherId;
        initComponents();
        setTitle("Tambah Quiz - Teacher ID: " + teacherId);
        commonInit();
    }

    // Inisialisasi umum
    private void commonInit() {
        setLocationRelativeTo(null);
        if (SoalKeBerapa != null) SoalKeBerapa.setValue(1);
        wireEvents();
    }

    // ================== EVENT WIRING ==================
    private void wireEvents() {
        // Kembali ke dashboard
        TombolKembaliTambahQuiz.addActionListener(e -> {
            new TeacherDashboard(teacherId).setVisible(true);
            dispose();
        });

        // Simpan soal saat ini dan lanjut ke soal berikutnya
        SimpanNext.addActionListener(e -> onSaveNext());

        // Simpan semua soal di buffer ke DB
        Simpan.addActionListener(e -> onSaveAll());

        // Kembali ke soal sebelumnya (variable name tombol di form: "button3")
        button3.addActionListener(e -> onBackQuestion());

        // Jika spinner diubah manual, sinkronkan tampilan
        SoalKeBerapa.addChangeListener(e -> {
            int nomor = (Integer) SoalKeBerapa.getValue();
            loadFromBufferOrClear(nomor);
        });
    }

    // ================== ACTIONS ==================

    // Simpan/replace soal di nomor saat ini, lalu pindah ke nomor berikutnya
    private void onSaveNext() {
        String quizTitle = MasukkanNamaQuiz.getText().trim();
        String soal = MasukkanSoal.getText().trim();
        Object sel = PilganBenar.getSelectedItem();
        int nomor = (Integer) SoalKeBerapa.getValue();

        // Validasi
        if (quizTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama quiz harus diisi.");
            MasukkanNamaQuiz.requestFocus();
            return;
        }
        if (soal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teks soal harus diisi.");
            MasukkanSoal.requestFocus();
            return;
        }
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Pilih jawaban benar (A/B/C/D).");
            return;
        }

        char correct = sel.toString().trim().toUpperCase().charAt(0);

        // Kunci nama quiz setelah soal pertama
        MasukkanNamaQuiz.setEditable(false);

        // Replace jika nomor ini sudah ada (edit-friendly)
        buffer.put(nomor, new QuestionItem(nomor, soal, correct));

        // Lanjut ke nomor berikutnya
        int next = nomor + 1;
        loadFromBufferOrClear(next);

        JOptionPane.showMessageDialog(this, "Soal #" + nomor + " disimpan. Lanjut soal #" + next + ".");
        MasukkanSoal.requestFocus();
    }

    // Kembali 1 nomor dan tampilkan isinya (kalau ada)
    private void onBackQuestion() {
        int nomor = (Integer) SoalKeBerapa.getValue();
        if (nomor <= 1) {
            JOptionPane.showMessageDialog(this, "Sudah di soal pertama.");
            return;
        }
        loadFromBufferOrClear(nomor - 1);
    }

    // Helper: load soal dari buffer atau kosongkan jika belum ada
    private void loadFromBufferOrClear(int nomor) {
        QuestionItem item = buffer.get(nomor);
        if (item != null) {
            MasukkanSoal.setText(item.getQuestion());
            PilganBenar.setSelectedItem(String.valueOf(item.getCorrect()));
        } else {
            MasukkanSoal.setText("");
            PilganBenar.setSelectedIndex(0);
        }
        SoalKeBerapa.setValue(nomor);
    }

    // Simpan semua soal dari buffer ke DB
    private void onSaveAll() {
        if (buffer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada soal yang ditambahkan.");
            return;
        }
        String quizTitle = MasukkanNamaQuiz.getText().trim();
        if (quizTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama quiz harus diisi.");
            MasukkanNamaQuiz.requestFocus();
            return;
        }

        try {
            // Convert map -> list, lalu urutkan berdasarkan nomor soal
            List<QuestionItem> items = new ArrayList<>(buffer.values());
            items.sort(java.util.Comparator.comparingInt(QuestionItem::getNumber));

            TeacherQuizController.saveBatch(teacherId, quizTitle, items);
            JOptionPane.showMessageDialog(this,
                    "Quiz \"" + quizTitle + "\" tersimpan dengan " + items.size() + " soal.");

            new TeacherDashboard(teacherId).setVisible(true);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal menyimpan", JOptionPane.ERROR_MESSAGE);
        }
    }


    // ======= di bawah ini biarkan JFormDesigner yang generate: initComponents + variables =======




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ghendida
        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        TombolKembaliTambahQuiz = new JButton();
        MasukkanNamaQuiz = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        MasukkanSoal = new JTextField();
        PilganBenar = new JComboBox<>();
        SimpanNext = new JButton();
        Simpan = new JButton();
        button3 = new JButton();
        SoalKeBerapa = new JSpinner();

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
            ( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );

            //---- TombolKembaliTambahQuiz ----
            TombolKembaliTambahQuiz.setText("Kembali");

            //---- label1 ----
            label1.setText("Masukkan Nama Quiz");
            label1.setBackground(new Color(0xcccccc));
            label1.setForeground(Color.black);

            //---- label2 ----
            label2.setText("Masukkan Soal");
            label2.setForeground(Color.black);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(MasukkanSoal);
            }

            //---- PilganBenar ----
            PilganBenar.setModel(new DefaultComboBoxModel<>(new String[] {
                "A",
                "B",
                "C",
                "D"
            }));

            //---- SimpanNext ----
            SimpanNext.setText("Simpan Next");

            //---- Simpan ----
            Simpan.setText("Simpan");

            //---- button3 ----
            button3.setText("Kembali Soal ");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(MasukkanNamaQuiz, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(413, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                                .addComponent(TombolKembaliTambahQuiz)
                                .addGap(62, 62, 62))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 479, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(SoalKeBerapa, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(PilganBenar, GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addComponent(SimpanNext)
                                        .addGap(18, 18, 18)
                                        .addComponent(Simpan)
                                        .addGap(27, 27, 27)
                                        .addComponent(button3)))
                                .addGap(0, 164, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(TombolKembaliTambahQuiz))
                        .addGap(4, 4, 4)
                        .addComponent(MasukkanNamaQuiz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(PilganBenar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(SimpanNext)
                            .addComponent(Simpan)
                            .addComponent(button3))
                        .addGap(28, 28, 28)
                        .addComponent(SoalKeBerapa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(209, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(HalloTeacher, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
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
    private JButton TombolKembaliTambahQuiz;
    private JTextField MasukkanNamaQuiz;
    private JLabel label1;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTextField MasukkanSoal;
    private JComboBox<String> PilganBenar;
    private JButton SimpanNext;
    private JButton Simpan;
    private JButton button3;
    private JSpinner SoalKeBerapa;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
