package org.unibl.etf.adminapp.dao;

import org.unibl.etf.adminapp.dto.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM client";
    private static final String SELECT_ONE_CLIENT = "SELECT * FROM client WHERE username = ?";
    private static final String CREATE_NEW_CLIENT = "INSERT INTO client (name, surname, username, password, city, email) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_CLIENT = "UPDATE client SET name=?, surname=?, password=?, city=?, email=? WHERE username=?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE username = ?";

    public static ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = new ArrayList<Client>();
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_CLIENTS, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getString("name"), rs.getString("surname"), rs.getString("username"),
                        rs.getString("password"), rs.getString("city"), rs.getString("email"), rs.getInt("is_activated") == 1));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return clients;
    }

    public static Client getClientWithUsername(String username) {
        Client client = null;
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {username};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ONE_CLIENT, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getString("name"), rs.getString("surname"), rs.getString("username"),
                        rs.getString("password"), rs.getString("city"), rs.getString("email"), rs.getInt("is_activated") == 1);
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return client;
    }

    public static boolean createNewClient(Client client) {
        Object[] values = {client.getName(), client.getSurname(), client.getUsername(), client.getPassword(), client.getCity(), client.getEmail()};
        return doStatement(values, CREATE_NEW_CLIENT);
    }

    public static boolean updateClient(Client client) {
        Object[] values = {client.getName(), client.getSurname(), client.getPassword(), client.getCity(), client.getEmail(), client.getUsername()};
        return doStatement(values, UPDATE_CLIENT);
    }

    public static boolean deleteClient(String username) {
        Object[] values = {username};
        return doStatement(values, DELETE_CLIENT);
    }

    private static boolean doStatement(Object[] values, String method) {
        return DAOUtil.doStatement(values, method, connectionPool);
    }
}
