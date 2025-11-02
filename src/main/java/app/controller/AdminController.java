package app.controller;

import app.utilities.base.BaseController;
import app.utilities.data.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AdminController extends BaseController {

    // Ambil semua yang pending (teacher & student)
    public ResultSet listPendingAll() throws SQLException {
        String sql = "SELECT users_id AS id, name, username, 'TEACHER' AS role, is_verified FROM teacher WHERE is_verified=FALSE " +
                        "UNION ALL " +
                        "SELECT users_id AS id, name, username, 'STUDENT' AS role, is_verified FROM student WHERE is_verified=FALSE " +
                        "ORDER BY role, id";
        return execQuery(sql);
    }

    // Approve = set verified true, Reject = hapus baris
    public void verify(String role, int userId, boolean approve) throws Exception {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        String sql;

        if (approve)
            sql = "UPDATE " + table + " SET is_verified = TRUE WHERE users_id = ?";
        else
            sql = "DELETE FROM " + table + " WHERE users_id = ?";

        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    public int rejectUser(String role, int userId) throws Exception {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        String sql = "DELETE FROM " + table + " WHERE users_id = ?";
        try (Connection c = DatabaseConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate();
        }
    }
}
