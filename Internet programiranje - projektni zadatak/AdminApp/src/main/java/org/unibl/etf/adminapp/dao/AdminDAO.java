package org.unibl.etf.adminapp.dao;

import org.unibl.etf.adminapp.dto.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_BY_USERNAME = "SELECT * FROM admin WHERE username=?";

    public static Admin getAdminByUsername(String username) {
        Admin admin = null;
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {username};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
                    SELECT_ALL_BY_USERNAME, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = new Admin(rs.getString("name"), rs.getString("surname"), username, rs.getString("password"));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return admin;
    }
}
