package org.unibl.etf.adminapp.dao;

import org.unibl.etf.adminapp.dto.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
    private static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
    private static final String CREATE_NEW_CATEGORY = "INSERT INTO category (name) VALUES (?)";
    private static final String UPDATE_CATEGORY = "UPDATE category SET name=? WHERE id=?";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE name = ?";
    private static final String SELECT_CATEGORY_ID = "SELECT id FROM category WHERE name = ?";

    public static ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_CATEGORIES, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return categories;
    }

    public static Category findByName(String name) {
        Category category = null;
        Connection connection = null;
        ResultSet rs = null;
        Object[] values = {name};
        try {
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_CATEGORY_BY_NAME, false, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }
            pstmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return category;
    }

    public static boolean createNewCategory(String name) {
        Object[] values = {name};
        return doStatement(values, CREATE_NEW_CATEGORY);
    }

    public static boolean updateCategory(String newName, int id) {
        Object[] values = {newName, id};
        return doStatement(values, UPDATE_CATEGORY);
    }

    public static boolean deleteCategory(String name) {
        Object[] values = {name};
        return doStatement(values, DELETE_CATEGORY);
    }

    public static int findCategoryId(String name) {
        return DAOUtil.findIdFromName(name, connectionPool, SELECT_CATEGORY_ID);
    }

    private static boolean doStatement(Object[] values, String method) {
        return DAOUtil.doStatement(values, method, connectionPool);
    }
}
