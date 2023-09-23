package com.felipe.uniball.controller;

import com.felipe.uniball.models.Player;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Player> getPlayers() {
        final String query = "SELECT id_player, name, number, position, score FROM players ORDER BY id_player ASC";
        try {
            Database.getConnection();
            ResultSet rs = Database.execute(query, new Object[]{}, false);
            List<Player> players = new ArrayList<>();
            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("id_player"));
                p.setName(rs.getString("name"));
                p.setNumber(rs.getInt("number"));
                p.setPosition(rs.getString("position"));
                p.setScore(rs.getInt("score"));
                players.add(p);
            }
            return players;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static Player getPlayer(int id) {
        final String query = "SELECT id_player, name, number, position, score FROM players WHERE id_player = ?";
        try {
            Database.getConnection();
            ResultSet rs = Database.execute(query, new Object[]{id}, false);

            Player p = new Player();
            while (rs.next()) {
                p.setId(rs.getInt("id_player"));
                p.setName(rs.getString("name"));
                p.setNumber(rs.getInt("number"));
                p.setPosition(rs.getString("position"));
                p.setScore(rs.getInt("score"));
            }
            return p;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void deletePlayer(int id) {
        final String query = "DELETE FROM players WHERE id_player = ?";
        try {
            Database.execute(query, new Object[]{id}, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSecretPhrase(String username) {
        final String query = "SELECT secret_phrase FROM players WHERE user = ?";
        try {
            Database.getConnection();
            ResultSet rs = Database.execute(query, new Object[]{username}, false);
            if (rs.next()) {
                return rs.getString("secret_phrase");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static boolean checkPlayerExistence(String username) {
        final String query = "SELECT user FROM players WHERE user = ?";
        try {
            Database.getConnection();
            ResultSet rs = Database.execute(query, new Object[]{username}, false);
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}