package app.controller;

import app.model.QuestionItem;
import app.util.Database;

import java.sql.*;
import java.util.List;
import app.util.Database;

public class TeacherQuizController {

    public static void saveBatch(int teacherId, String quizTitle, List<QuestionItem> items) throws SQLException {
        // versi minimal agar cocok dengan tabel kamu sekarang
        final String sql =
                "INSERT INTO quiz (teacher_id, quiz_title, soal, correct_answer, question_number) " +
                        "VALUES (?, ?, ?, ?, ?)";

        for (QuestionItem it : items) {
            Database.execUpdate(sql,
                    teacherId,
                    quizTitle,
                    it.getQuestion(),                 // soal (pertanyaan)
                    String.valueOf(it.getCorrect()),  // 'A'/'B'/'C'/'D'
                    it.getNumber()
            );
        }
    }
    // Ringkasan quiz per guru: satu baris per quiz_title
    public static ResultSet listQuizSummary(int teacherId) throws SQLException {
        String sql =
                "SELECT MIN(question_id) AS quiz_id, quiz_title, COUNT(*) AS jumlah_soal " +
                        "FROM quiz " +
                        "WHERE teacher_id=? " +
                        "GROUP BY quiz_title " +
                        "ORDER BY quiz_title";

        // gunakan pola yang sama seperti AdminController: kembalikan ResultSet,
        // nanti koneksinya ditutup dari pemanggil setelah selesai dipakai
        Connection c = Database.get();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, teacherId);
        return ps.executeQuery();
    }
}
