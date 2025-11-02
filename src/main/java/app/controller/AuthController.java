package app.controller;

import app.utilities.authentication.registration.StudentAuthentication;
import app.utilities.authentication.registration.TeacherAuthentication;
import app.utilities.base.BaseController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController extends BaseController {
    public void register(String role, String username, String password) throws Exception {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        String sql = "INSERT INTO " + table + " (username, password, is_verified) VALUES (?,?,FALSE)";
        try {
            execUpdate(sql, username, password);
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("duplicate")) throw new Exception("Username sudah dipakai");
            else throw e;
        }
    }

    public void registerTeacher(String name, String username, String password) throws Exception {
        String query = new TeacherAuthentication().registrationQuery();
        try {
            execUpdate(query, name, username, password);
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("duplicate")) {
                throw new Exception("Username sudah dipakai");
            } else throw e;
        }
    }

    public void registerStudent(String name, String username, String password) throws Exception {
        String query = new StudentAuthentication().registrationQuery();
        try {
            execUpdate(query, name, username, password);
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("duplicate")) {
                throw new Exception("Username sudah dipakai");
            } else throw e;
        }
    }

    public int loginAdmin(String username, String password) throws Exception {
        final String sql =
                "SELECT id_admin FROM admin WHERE username=? AND password=? LIMIT 1";

        try (var rs = execQuery(sql, username, password)) {
            if (rs.next()) {
                return rs.getInt("id_admin");
            }
            throw new Exception("Username / password admin salah.");
        } catch (SQLException e) {
            // biar pesan user-friendly, tapi akar error tetap kelihatan di log
            throw new Exception("Gagal login admin: " + e.getMessage());
        }
    }

    public int loginUser(String role, String username, String password) throws Exception {
        // Normalisasi & validasi role
        final String r = role == null ? "" : role.trim().toUpperCase();
        if (!r.equals("TEACHER") && !r.equals("STUDENT")) {
            throw new Exception("Role tidak valid.");
        }

        final String table = r.equals("TEACHER") ? "teacher" : "student";
        // is_verified=1 supaya yang pending tidak bisa login
        final String sql =
                "SELECT users_id FROM " + table + " " +
                        "WHERE username=? AND password=? AND is_verified=1 " +
                        "LIMIT 1";

        try (var rs = execQuery(sql, username, password)) {
            if (rs.next()) {
                return rs.getInt("users_id");
            }
            throw new Exception("Username / password salah atau akun belum diverifikasi.");
        } catch (SQLException e) {
            throw new Exception("Gagal login " + r.toLowerCase() + ": " + e.getMessage());
        }
    }
}
