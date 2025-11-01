package app.controller;

import app.model.QuestionItem;
import app.utilities.base.BaseController;
import app.utilities.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherQuizController extends BaseController {

    public void saveBatch(int teacherId, String quizTitle, List<QuestionItem> items) throws SQLException {
        // versi minimal agar cocok dengan tabel kamu sekarang
        final String sql =
                "INSERT INTO quiz (teacher_id, quiz_title, soal, correct_answer, question_number) " +
                        "VALUES (?, ?, ?, ?, ?)";

        for (QuestionItem it : items) {
            execUpdate(sql,
                    teacherId,
                    quizTitle,
                    it.getQuestion(),                 // soal (pertanyaan)
                    String.valueOf(it.getCorrect()),  // 'A'/'B'/'C'/'D'
                    it.getNumber()
            );
        }
    }
    // Ringkasan quiz per guru: satu baris per quiz_title
    public ResultSet listQuizSummary(int teacherId) throws SQLException {
        String sql =
                "SELECT MIN(question_id) AS quiz_id, quiz_title, COUNT(*) AS jumlah_soal " +
                        "FROM quiz " +
                        "WHERE teacher_id=? " +
                        "GROUP BY quiz_title " +
                        "ORDER BY quiz_title";

        // gunakan pola yang sama seperti AdminController: kembalikan ResultSet,
        // nanti koneksinya ditutup dari pemanggil setelah selesai dipakai
        PreparedStatement ps = dbConnection.prepareStatement(sql);
        ps.setInt(1, teacherId);
        return execPreparedStatement(ps);
    }
    public int deleteQuizByTitle(int teacherId, String quizTitle) throws Exception {
        String delRapor =
                "DELETE FROM rapor WHERE quiz_id IN (" +
                        "   SELECT q.question_id FROM quiz q WHERE q.teacher_id=? AND q.quiz_title=?" +
                        ")";

        String delQuiz =
                "DELETE FROM quiz WHERE teacher_id=? AND quiz_title=?";

        try (Connection conn = DatabaseConnection.get()) {
            conn.setAutoCommit(false);
            int deletedRapor, deletedQuiz;

            try (var ps1 = conn.prepareStatement(delRapor);
                 var ps2 = conn.prepareStatement(delQuiz)) {

                // hapus rapor terkait
                ps1.setInt(1, teacherId);
                ps1.setString(2, quizTitle);
                deletedRapor = ps1.executeUpdate();

                // hapus baris quiz (semua soal di judul tersebut)
                ps2.setInt(1, teacherId);
                ps2.setString(2, quizTitle);
                deletedQuiz = ps2.executeUpdate();

                conn.commit();
            } catch (Exception e) {
                try { conn.rollback(); } catch (Exception ignore) {}
                throw e;
            } finally {
                try { conn.setAutoCommit(true); } catch (Exception ignore) {}
            }

            // kembalikan berapa baris quiz yang terhapus (bukan rapor)
            return deletedQuiz;
        }
    }
}
