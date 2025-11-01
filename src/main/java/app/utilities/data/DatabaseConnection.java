package app.utilities.data;
import java.sql.*;

public final class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_bisaaa?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Laragon default
    private static final String PASS = "";     // Laragon default kosong; sesuaikan

    private DatabaseConnection() {}

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static int execUpdate(String sql, Object... params) throws SQLException {
        try (Connection c = get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(ps, params);
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
            return affected;
        }
    }

    public static ResultSet execQuery(Connection c, String sql, Object... params) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        bind(ps, params);
        return ps.executeQuery(); // pemanggil yang menutup ps & c
    }

    private static void bind(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) ps.setObject(i + 1, params[i]);
    }
}
