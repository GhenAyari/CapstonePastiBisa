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
            }else throw e;
        }
    }

    public int loginAdmin(String username, String password) throws Exception {
        String sql = "SELECT id_admin FROM admin WHERE username=? AND password=?";
        try  {
            ResultSet rs = execQuery(sql, username, password);
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            throw new Exception("Admin tidak ditemukan / password salah");
        }
        return -1;
    }

    public int loginUser(String role, String username, String password) throws Exception {
        String table = role.equalsIgnoreCase("TEACHER") ? "teacher" : "student";
        String sql = "SELECT users_id, is_verified FROM " + table + " WHERE username=? AND password=?";
        try {
            ResultSet rs = execQuery(sql, username, password);
            if (rs.next()) {
                if (!rs.getBoolean("is_verified")) throw new Exception("Akun belum diverifikasi admin");
                return rs.getInt("users_id");
            }
        } catch (Exception e) {
            throw new Exception(role + " tidak ditemukan / password salah");
        }
        return -1;
    }
}
