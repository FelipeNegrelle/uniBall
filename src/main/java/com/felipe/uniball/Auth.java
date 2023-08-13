package com.felipe.uniball;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.sql.ResultSet;

public class Auth {
    public static void register(String username, String password) {
        final String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        final String query = "INSERT INTO users (user, password) VALUES (?, ?)";
        try {
            Database db = new Database();

            db.execute(query, new Object[]{
                    username,
                    hashed_password
            }, true);

            db.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void login(String username, String password) {
        final String query = "SELECT * FROM users WHERE user = ?";
        try {
            Database db = new Database();
            ResultSet rs = db.execute(query, new Object[]{
                    username
            }, false);

            if (rs.next()) {
                final String hashed_password = rs.getString("password");

                if (BCrypt.checkpw(password, hashed_password)) {
                    JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(dialog, "Login successful");

                    System.out.println("Login successful");
                } else {
                    JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(dialog, "Login failed");
                    System.out.println("Login failed");
                }
            } else {
                System.out.println("Login failed");
            }

            db.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
