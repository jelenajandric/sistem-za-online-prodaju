package org.unibl.etf.adminapp.dao;

import org.unibl.etf.adminapp.dto.Statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatisticDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ALL = "SELECT * FROM statistic";

    public static ArrayList<Statistic> getAll() {
        ArrayList<Statistic> statistics = new ArrayList<Statistic>();
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                statistics.add(new Statistic(rs.getInt("id"), rs.getString("client_username"),
                        rs.getString("what_is_happened"), rs.getInt("returned_id"), rs.getTimestamp("date")));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return statistics;
    }
}
