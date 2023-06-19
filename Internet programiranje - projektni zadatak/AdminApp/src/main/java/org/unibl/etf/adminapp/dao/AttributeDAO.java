package org.unibl.etf.adminapp.dao;


import org.unibl.etf.adminapp.dto.Attribute;
import org.unibl.etf.adminapp.dto.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttributeDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ALL_ATTRIBUTES_FOR_SPECIFIC_CATEGORY =
            "SELECT attribute.id, attribute.name FROM attribute " +
                    "JOIN category ON category.id = attribute.category_id " +
                    "WHERE category.name = ? ";
    private static final String SELECT_ATTRIBUTE_BY_NAME = "SELECT * FROM attribute WHERE name=?";
    private static final String SELECT_ATTRIBUTE_ID = "SELECT id FROM attribute WHERE name=?";
    private static final String CREATE_NEW_ATTRIBUTE = "INSERT INTO attribute (name, category_id) VALUES (?,?)";
    private static final String UPDATE_ATTRIBUTE = "UPDATE attribute SET name=? WHERE id=?";
    private static final String DELETE_ATTRIBUTE_WHERE_NAME = "DELETE FROM attribute WHERE name = ?";
    private static final String DELETE_ATTRIBUTE_WHERE_CATEGORY_ID = "DELETE FROM attribute WHERE category_id = ?";

    public static ArrayList<Attribute> getAllAttributesForSpecificCategory(String categoryName) {
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {categoryName};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_ATTRIBUTES_FOR_SPECIFIC_CATEGORY, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                attributes.add(new Attribute(rs.getInt("id"), rs.getString("name")));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return attributes;
    }

    public static Attribute findByName(String name) {
        Attribute attribute = null;
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {name};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ATTRIBUTE_BY_NAME, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                attribute = new Attribute(rs.getInt("id"), rs.getString("name"));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return attribute;
    }

    public static int findAttributeId(String name) {
        return DAOUtil.findIdFromName(name, connectionPool, SELECT_ATTRIBUTE_ID);
    }

    public static boolean createNewAttribute(String name, int categoryId) {
        Object[] values = {name, categoryId};
        return doStatement(values, CREATE_NEW_ATTRIBUTE);
    }

    public static boolean updateAttribute(String newName, int id) {
        Object[] values = {newName, id};
        return doStatement(values, UPDATE_ATTRIBUTE);
    }

    public static boolean deleteAttributeWithSpecificName(String name) {
        Object[] values = {name};
        return doStatement(values, DELETE_ATTRIBUTE_WHERE_NAME);
    }

    public static boolean deleteAttributeWithSpecificCategoryId(int categoryId) {
        Object[] values = {categoryId};
        return doStatement(values, DELETE_ATTRIBUTE_WHERE_CATEGORY_ID);
    }

    private static boolean doStatement(Object[] values, String method) {
        return DAOUtil.doStatement(values, method, connectionPool);
    }

}
