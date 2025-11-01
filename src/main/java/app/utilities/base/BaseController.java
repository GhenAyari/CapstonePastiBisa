package app.utilities.base;

import app.utilities.data.DatabaseConnection;

import java.sql.*;

public class BaseController {
    public Connection dbConnection;

    public BaseController() {
        try {
            dbConnection = DatabaseConnection.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int execUpdate(String sql, Object... params) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        bind(ps, params);
        int affected = ps.executeUpdate();
        if (affected > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return affected;
    }

    public ResultSet execPreparedStatement(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public ResultSet execQuery(String sql, Object... params) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement(sql);
        bind(ps, params);
        return ps.executeQuery();
    }

    private void bind(PreparedStatement ps, Object... params) throws SQLException {
        for (int index = 0; index < params.length; index++)
            ps.setObject(index + 1, params[index]);
    }
}
