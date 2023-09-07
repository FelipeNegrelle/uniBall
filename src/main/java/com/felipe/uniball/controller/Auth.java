package com.felipe.uniball.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.sql.ResultSet;

public class Auth {
    public static boolean register(String name, String number, String position, String username, String password) {
        final String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        final String queryRegister = "INSERT INTO players (name, number, position, score, user, password) VALUES (?, ?, ?, ?, ?, ?)";
        final String queryCheck = "SELECT user FROM players WHERE user = ?";
        try {
            Database db = new Database();

            ResultSet count = db.execute(queryCheck, new Object[]{ username }, false);

            if (count.next()) {
                JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                final String user = count.getString("user");
                JOptionPane.showMessageDialog(dialog, "Username " + user + " already exists.");
                db.close();
                return false;
            }

            db.execute(queryRegister, new Object[]{
                    name,
                    number,
                    position,
                    0,
                    username,
                    hashed_password
            }, true);

            db.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    public static boolean login(String username, String password) {
        final String query = "SELECT user, password FROM players WHERE user = ?";

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
                    return true;
                }
            } else {
                JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "Login failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(username + password);
        return false;
    }

}
