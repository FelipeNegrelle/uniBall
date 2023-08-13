package com.felipe.uniball;

import java.sql.*;

public class Database {
    private static Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/felipe/IdeaProjects/UniBall/db/database.db");
            System.out.println("Conexão realizada !!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet execute(String query, Object[] values, boolean isRegister) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            if (isRegister) {
                statement.executeUpdate();
                return null;
            } else {
                return statement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        return null;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
