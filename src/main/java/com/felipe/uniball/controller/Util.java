package com.felipe.uniball.controller;

import com.felipe.uniball.models.Player;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Player> getPlayers(String campo, String ordem) {
        final String query = "SELECT * FROM players ORDER BY " + campo + " " + ordem;
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
                p.setUser(rs.getString("user"));
                p.setScore(rs.getInt("score"));
                p.setBestPlayerFromDb(rs.getInt("best_player"));
                p.setBeautifulScoreFromDb(rs.getInt("beautiful_score"));
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

    public static void updatePlayers(DefaultTableModel model, List<Player> playerList) {
        model.setRowCount(0);

        for (Player player : playerList) {
            model.addRow(new Object[]
                    {
                            player.getName(),
                            player.getScore(),
                            player.getTeam()
                    });
        }
    }

    public static String[] getPlayersName(List<Player> players) {
        String[] playersName = new String[players.size()];
        for (int i = 0; i < players.size(); i++) {
            playersName[i] = players.get(i).getName();
        }
        return playersName;
    }

    public static int saveMatch(String dateTimeMatch, String teamAScore, String teamBScore) {
        final String queryInsert = "INSERT INTO matches (date_time_match, team_a_score, team_b_score, winner) VALUES (?, ?, ?, ?)";
        final String querySelect = "SELECT id_match FROM matches WHERE date_time_match = ? AND team_a_score = ? AND team_b_score = ?";
        try {
            Database.execute(queryInsert, new Object[]{
                    dateTimeMatch,
                    teamAScore,
                    teamBScore,
                    getWinner(teamAScore, teamBScore)
            }, true);

            ResultSet rs = Database.execute(querySelect, new Object[]{
                    dateTimeMatch,
                    teamAScore,
                    teamBScore
            }, false);

            if (rs.next()) {
                return rs.getInt("id_match");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return -1;
    }

    public static void relateMatchPlayer(int idMatch, int idPlayer) {
        final String query = "INSERT INTO matches_players (id_match, id_player) VALUES (?, ?)";
        try {
            Database.execute(query, new Object[]{
                    idMatch,
                    idPlayer
            }, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static char getWinner(String teamAScore, String teamBScore) {
        if (Integer.parseInt(teamAScore) > Integer.parseInt(teamBScore)) {
            return 'A';
        } else if (Integer.parseInt(teamAScore) < Integer.parseInt(teamBScore)) {
            return 'B';
        } else {
            return 'E';
        }
    }
}