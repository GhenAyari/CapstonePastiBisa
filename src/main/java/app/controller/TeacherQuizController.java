package app.controller;

import app.model.QuestionItem;
import app.util.Database;

import java.sql.SQLException;
import java.util.List;

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
}
