package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.unibl.etf.beans.AdminBean;

public class AdminDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_BY_USERNAME = "SELECT * FROM technical_support WHERE username=?";

	
	public AdminDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static AdminBean getAdminByUsername(String username) {
        AdminBean admin = null;
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {username};
		try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
                    SELECT_BY_USERNAME, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()){
                admin = new AdminBean(rs.getString("name"), rs.getString("surname"), username, rs.getString("password"));
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
