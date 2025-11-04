package app.view;

import app.utilities.data.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

/**
 * Kerjakan Quiz (navigasi Next/Back, submit ke rapor)
 */
public class StudentKerjakanQuiz extends JFrame {

    private final int studentId;
    private final String quizTitle;

    /** Struktur 1 soal pada tabel quiz */
    private static class Q {
        int number;           // question_number
        String text;          // question_text
        String a, b, c, d;    // option_a..d
        char correct;         // 'A'..'D'
    }

    private final java.util.List<Q> soal = new ArrayList<>();
    private final Map<Integer, Character> jawaban = new HashMap<>(); // nomor -> A/B/C/D yang dipilih
    private int idx = 0; // index soal aktif

    // ===== Komponen UI =====
    private JTextField HalloTeacher;
    private JPanel panel1;
    private JScrollPane scrollPane1;

    private JTextArea SoaKerjakanQuiz;
    private JRadioButton PilganStudentA, PilganStudentB, PilganStudentC, PilganStudentD;
    private JButton TombNextKerjakan, TombKembali, TombKumpulkan;
    private ButtonGroup group;

    public StudentKerjakanQuiz(int studentId, String quizTitle) {
        this.studentId = studentId;
        this.quizTitle = quizTitle;

        initComponents();
        setTitle("Kerjakan: " + quizTitle);
        setLocationRelativeTo(null);

        // group radio
        group = new ButtonGroup();
        group.add(PilganStudentA);
        group.add(PilganStudentB);
        group.add(PilganStudentC);
        group.add(PilganStudentD);

        // events
        TombNextKerjakan.addActionListener(this::onNext);
        TombKembali.addActionListener(this::onBack);
        TombKumpulkan.addActionListener(this::onSubmit);

        // load semua soal
        loadQuestions();

        // tampilkan soal pertama / disable jika kosong
        if (!soal.isEmpty()) {
            idx = 0;
            render();
        } else {
            JOptionPane.showMessageDialog(this, "Belum ada soal untuk quiz ini.");
            TombNextKerjakan.setEnabled(false);
            TombKembali.setEnabled(false);
            TombKumpulkan.setEnabled(false);
        }
    }

    /** Ambil daftar soal untuk quizTitle ini */
    private void loadQuestions() {
        final String sql =
                // pakai COALESCE supaya kalau question_text kosong, ambil 'soal'
                "SELECT question_number, COALESCE(question_text, soal) AS teks, correct_answer " +
                        "FROM quiz WHERE quiz_title = ? ORDER BY question_number";

        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, quizTitle);
            try (ResultSet rs = ps.executeQuery()) {
                soal.clear();
                while (rs.next()) {
                    Q q = new Q();
                    q.number  = rs.getInt("question_number");
                    q.text    = rs.getString("teks");       // <— ambil dari alias 'teks'
                    q.a = q.b = q.c = q.d = null;           // opsi tidak dipakai lagi
                    String corr = rs.getString("correct_answer");
                    q.correct = (corr != null && !corr.isEmpty())
                            ? Character.toUpperCase(corr.charAt(0)) : 'A';
                    soal.add(q);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal load soal", JOptionPane.ERROR_MESSAGE);
        }
    }



    /** Render 1 soal ke UI berdasarkan index aktif */
    private void render() {
        Q q = soal.get(idx);

        // header
        HalloTeacher.setText("Hallo, Student — Quiz: " + quizTitle);

        // isi area soal
        SoaKerjakanQuiz.setText("Soal #" + q.number + "\n\n" + (q.text == null ? "" : q.text));

// radio cukup huruf A/B/C/D (tanpa deskripsi opsi)
        PilganStudentA.setText("A");
        PilganStudentB.setText("B");
        PilganStudentC.setText("C");
        PilganStudentD.setText("D");

        // restore jawaban jika sudah ada
        group.clearSelection();
        Character chosen = jawaban.get(q.number);
        if (chosen != null) {
            switch (Character.toUpperCase(chosen)) {
                case 'A' -> PilganStudentA.setSelected(true);
                case 'B' -> PilganStudentB.setSelected(true);
                case 'C' -> PilganStudentC.setSelected(true);
                case 'D' -> PilganStudentD.setSelected(true);
            }
        }

        // tombol navigasi
        TombKembali.setEnabled(idx > 0);
        boolean last = (idx == soal.size() - 1);
        TombNextKerjakan.setVisible(!last);
        TombKumpulkan.setVisible(last);
    }

    private void captureCurrentChoice() {
        Q q = soal.get(idx);
        if (PilganStudentA.isSelected())      jawaban.put(q.number, 'A');
        else if (PilganStudentB.isSelected()) jawaban.put(q.number, 'B');
        else if (PilganStudentC.isSelected()) jawaban.put(q.number, 'C');
        else if (PilganStudentD.isSelected()) jawaban.put(q.number, 'D');
        // jika tidak memilih, dianggap salah saat submit (tidak disimpan)
    }

    private void onNext(ActionEvent e) {
        captureCurrentChoice();
        if (idx < soal.size() - 1) {
            idx++;
            render();
        }
    }

    private void onBack(ActionEvent e) {
        captureCurrentChoice();
        if (idx > 0) {
            idx--;
            render();
        }
    }

    private void onSubmit(ActionEvent e) {
        captureCurrentChoice();

        int benar = 0;
        for (Q q : soal) {
            char my = Character.toUpperCase(jawaban.getOrDefault(q.number, ' '));
            if (my == q.correct) benar++;
        }
        int total = soal.size();
        int salah = total - benar;

// hitung nilai 0–100 proporsional
        int nilai = (int) Math.round((benar * 100.0) / total);

        // quiz_id representatif = MIN(question_id) untuk quiz_title ini
        int quizId = fetchRepresentativeQuizId();

        // simpan ke rapor
        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO rapor (student_id, quiz_id, is_incorrect, is_correct, total_scores) " +
                             "VALUES (?,?,?,?,?)")) {
            ps.setInt(1, studentId);
            ps.setInt(2, quizId);
            ps.setInt(3, salah);
            ps.setInt(4, benar);
            ps.setInt(5, nilai); // total_scores = jumlah benar
            ps.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal simpan nilai", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Terkumpul!\nBenar: " + benar + "\nSalah: " + salah + "\nNilai akhir: " + nilai);

        new StudentDashboard(studentId).setVisible(true);
        dispose();
    }

    private int fetchRepresentativeQuizId() {
        final String sql = "SELECT MIN(question_id) AS quiz_id FROM quiz WHERE quiz_title = ?";
        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(

                    1, quizTitle);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("quiz_id");
            }
        } catch (Exception ignore) {}
        return 0; // fallback
    }

    // ===== UI builder (tanpa artefak JFormDesigner yang bikin error) =====
    private void initComponents() {
        var contentPane = getContentPane();

        HalloTeacher = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();

        SoaKerjakanQuiz = new JTextArea();
        PilganStudentA = new JRadioButton("A");
        PilganStudentB = new JRadioButton("B");
        PilganStudentC = new JRadioButton("C");
        PilganStudentD = new JRadioButton("D");

        TombNextKerjakan = new JButton("Next");
        TombKumpulkan   = new JButton("Kumpulkan");
        TombKembali     = new JButton("Kembali");

        // header
        HalloTeacher.setText("Hallo, Student");
        HalloTeacher.setBackground(new Color(0x0099cc));
        HalloTeacher.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        HalloTeacher.setForeground(new Color(0x333333));

        // panel
        panel1.setBackground(new Color(0x009999));

        // area soal
        SoaKerjakanQuiz.setLineWrap(true);
        SoaKerjakanQuiz.setWrapStyleWord(true);
        SoaKerjakanQuiz.setEditable(false);
        scrollPane1.setViewportView(SoaKerjakanQuiz);

        // layout panel1
        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(16)
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(24)
                                                .addComponent(PilganStudentA)
                                                .addGap(24)
                                                .addComponent(PilganStudentB)
                                                .addGap(24)
                                                .addComponent(PilganStudentC)
                                                .addGap(24)
                                                .addComponent(PilganStudentD)
                                                .addGap(30)
                                                .addComponent(TombNextKerjakan)
                                                .addGap(12)
                                                .addGroup(panel1Layout.createParallelGroup()
                                                        .addComponent(TombKumpulkan, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(TombKembali, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addGap(24)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(PilganStudentA)
                                        .addComponent(PilganStudentB)
                                        .addComponent(PilganStudentC)
                                        .addComponent(PilganStudentD)
                                        .addComponent(TombNextKerjakan)
                                        .addComponent(TombKumpulkan))
                                .addGap(18)
                                .addComponent(TombKembali)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        // layout root
        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(HalloTeacher)
                        .addComponent(panel1)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(HalloTeacher, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel1)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(getOwner());
    }
}
