package com.felipe.uniball.controller;

import java.sql.*;

public class Database {
    private static Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new Database();
        }
        return connection;
    }

    public static ResultSet execute(String query, Object[] values, boolean notHasResultSet) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            if (notHasResultSet) {
                statement.executeUpdate();
                return null;
            } else {
                return statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
