package app;

import app.util.Database;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Menguji koneksi ke MySQL...");
        try (Connection c = Database.get()) {
            System.out.println("✅ Koneksi OK: " + c.getCatalog());

            // Cek versi server
            try (Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery("SELECT VERSION()")) {
                if (rs.next()) System.out.println("MySQL Version: " + rs.getString(1));
            }

            // Cek tabel 'admin' (yang sudah kamu isi admin/admin123)
            try (PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM admin");
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                System.out.println("Jumlah baris di tabel admin: " + rs.getInt(1));
            }

            // List semua tabel di database
            DatabaseMetaData md = c.getMetaData();
            try (ResultSet tables = md.getTables(c.getCatalog(), null, "%", new String[]{"TABLE"})) {
                System.out.println("Daftar tabel:");
                while (tables.next()) {
                    System.out.println(" - " + tables.getString("TABLE_NAME"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal konek: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
