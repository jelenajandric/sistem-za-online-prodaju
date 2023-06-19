package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.beans.MessageBean;

public class MessageDAO {
	
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL = "SELECT m.id, m.content, m.seen, c.name, c.surname, c.username FROM mail_to_technical_support m JOIN client c ON c.id=m.client_id";
    private static final String SET_SEEN_ON_TRUE = "UPDATE mail_to_technical_support SET seen='1' WHERE id=?";
    private static final String SELECT_CLIENTS_EMAIL = "SELECT c.email FROM mail_to_technical_support m JOIN client c ON c.id=m.client_id where m.id=?";
    
	public MessageDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<MessageBean> getMessages() {
        ArrayList<MessageBean> messages = new ArrayList<MessageBean>();
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {};
		try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
                    SELECT_ALL, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()){
                messages.add(new MessageBean(rs.getInt("id"), rs.getString("content"), rs.getString("name"), 
                		rs.getString("surname"), rs.getString("username"), rs.getBoolean("seen")));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return messages;
	}
	
	public static void makeAsRead(int id) {
		Connection connection = null;
        Object[] values = {id};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SET_SEEN_ON_TRUE, false, values);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
	}
	
	public static String findSendersEmail(int messageId) {
		String email = "";
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {messageId};
		try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
            		SELECT_CLIENTS_EMAIL, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()){
                email = rs.getString("email");
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return email;
	}
}
