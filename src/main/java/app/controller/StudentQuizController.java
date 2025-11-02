package app.controller;

import app.utilities.data.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Ringkasan quiz untuk Student.
 * Satu baris hasil = satu judul quiz, dengan jumlah soal-nya.
 */
public class StudentQuizController {

    public static ResultSet listQuizSummary() throws Exception {
        // MIN(question_id) dipakai sebagai "quiz_id" representatif
        String sql = """
            SELECT MIN(question_id) AS quiz_id,
                   quiz_title,
                   COUNT(*)       AS jumlah_soal
            FROM quiz
            GROUP BY quiz_title
            ORDER BY quiz_title
        """;

        Connection c = DatabaseConnection.get();
        PreparedStatement ps = c.prepareStatement(sql);
        // Kembalikan ResultSet; penutupannya dilakukan di View setelah dipakai.
        return ps.executeQuery();
    }
}
