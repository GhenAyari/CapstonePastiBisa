package app.controller;
import app.util.Database;
import java.sql.*;

public class AuthController {
    public static void register(String role, String username, String password) throws Exception {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        String sql = "INSERT INTO " + table + " (username, password, is_verified) VALUES (?,?,FALSE)";
        try { Database.execUpdate(sql, username, password); }
        catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("duplicate")) throw new Exception("Username sudah dipakai");
            throw e;
        }
    }

    public static int loginAdmin(String username, String password) throws Exception {
        String sql = "SELECT id_admin FROM admin WHERE username=? AND password=?";
        try (Connection c = Database.get(); ResultSet rs = Database.execQuery(c, sql, username, password)) {
            if (rs.next()) return rs.getInt(1);
        }
        throw new Exception("Admin tidak ditemukan / password salah");
    }

    public static int loginUser(String role, String username, String password) throws Exception {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        String sql = "SELECT users_id, is_verified FROM " + table + " WHERE username=? AND password=?";
        try (Connection c = Database.get(); ResultSet rs = Database.execQuery(c, sql, username, password)) {
            if (rs.next()) {
                if (!rs.getBoolean("is_verified")) throw new Exception("Akun belum diverifikasi admin");
                return rs.getInt("users_id");
            }
        }
        throw new Exception(role + " tidak ditemukan / password salah");
    }
}
