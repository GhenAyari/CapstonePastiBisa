package app.controller;

import app.util.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    // Ambil semua yang pending (teacher & student)
    public static ResultSet listPendingAll() throws SQLException {
        String sql =
                "SELECT users_id AS id, name, username, 'TEACHER' AS role, is_verified FROM teacher WHERE is_verified=FALSE " +
                        "UNION ALL " +
                        "SELECT users_id AS id, name, username, 'STUDENT' AS role, is_verified FROM student WHERE is_verified=FALSE " +
                        "ORDER BY role, id";
        Connection c = Database.get();
        return Database.execQuery(c, sql);
    }


    // Approve = set verified true, Reject = hapus baris
    public static void verify(String role, int userId, boolean approve) throws SQLException {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        if (approve) {
            Database.execUpdate("UPDATE " + table + " SET is_verified=TRUE WHERE users_id=?", userId);
        } else {
            Database.execUpdate("DELETE FROM " + table + " WHERE users_id=?", userId);
        }
    }
}
