package com.felipe.uniball.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.sql.ResultSet;

public class Auth {
    public static boolean register(String name, String number, String position, String username, String password, String secretPhrase, String secretAnswer) {
        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        final String queryRegister = "INSERT INTO players (name, number, position, user, password, secret_phrase, secret_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        final String queryCheck = "SELECT user FROM players WHERE user = ?";
        try {
            ResultSet rs = Database.execute(queryCheck, new Object[]{username}, false);

            if (rs.next()) {
                JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                final String user = rs.getString("user");
                JOptionPane.showMessageDialog(dialog, "Usuário " + user + " ja existe, escolhe um novo nome.");
                return false;
            }

            Database.execute(queryRegister, new Object[]{
                    name,
                    number,
                    position,
                    username,
                    hashedPassword,
                    secretPhrase,
                    secretAnswer.trim().toLowerCase()
            }, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    public static boolean login(String username, String password) {
        final String query = "SELECT user, password FROM players WHERE user = ?";

        try {
            ResultSet rs = Database.execute(query, new Object[]{
                    username
            }, false);

            if (rs.next()) {
                final String hashed_password = rs.getString("password");
                return BCrypt.checkpw(password, hashed_password);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(username + password);
        return false;
    }

    public static boolean edit(int id, String name, String number, String position) {
        final String query = "UPDATE players SET name = ?, number = ?, position = ? WHERE id_player = ?";
        try {
            Database.getConnection();
            Database.execute(query, new Object[]{
                    name,
                    number,
                    position,
                    id
            }, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    public static boolean checkSecretAnswer(String username, String secretAnswer) {
        final String query = "SELECT secret_answer FROM players WHERE user = ?";
        try {
            Database.getConnection();
            ResultSet rs = Database.execute(query, new Object[]{username}, false);
            if (rs.next()) {
                return rs.getString("secret_answer").equals(secretAnswer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public static boolean forgotPassword(String username, String newPassword) {
        final String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        final String query = "UPDATE players SET password = ? WHERE user = ?";
        try {
            Database.getConnection();
            Database.execute(query, new Object[]{
                    hashedPassword,
                    username,
            }, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }
}
