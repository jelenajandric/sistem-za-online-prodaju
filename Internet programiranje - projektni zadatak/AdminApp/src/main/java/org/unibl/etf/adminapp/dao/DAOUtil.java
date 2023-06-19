package org.unibl.etf.adminapp.dao;

import java.sql.*;

public class DAOUtil {
    public static PreparedStatement prepareStatement(Connection connection,
                                                     String sql, boolean returnGeneratedKeys, Object... values)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS
                        : Statement.NO_GENERATED_KEYS);
        setValues(preparedStatement, values);
        return preparedStatement;
    }

    public static void setValues(PreparedStatement preparedStatement,
                                 Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    public static boolean doStatement(Object[] values, String method, ConnectionPool connectionPool) {
        Connection connection = null;
        ResultSet rs;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, method, false, values);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
            return false;
        } finally {
            connectionPool.checkIn(connection);
        }
        return true;
    }

    static int findIdFromName(String name, ConnectionPool connectionPool, String query) {
        int id = -1;
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {name};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, query, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return id;
    }
}
