package com.felipe.uniball.controller;

import com.felipe.uniball.models.Match;
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

    public static List<Match> getMatches() {
        final String query = "SELECT * FROM matches ORDER BY id_match ASC";
        try {
            Database.getConnection();

            ResultSet rs = Database.execute(query, new Object[]{}, false);

            List<Match> matches = new ArrayList<>();

            while (rs.next()) {
                Match m = new Match();

                String[] bests = getBests(rs.getInt("id_best_player"), rs.getInt("id_beautiful_score"));

                m.setId(rs.getInt("id_match"));
                m.setDateTimeMatch(rs.getString("date_time_match"));
                m.setScoreA(rs.getInt("team_a_score"));
                m.setScoreB(rs.getInt("team_b_score"));
                m.setWinner(rs.getString("winner"));
                m.setBestPlayer(bests[0]);
                m.setPlayerBeautifulScore(bests[1]);

                matches.add(m);
            }

            return matches;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static String[] getBests(int idBestPlayer, int idBeautifulScore) {
        final String query = "SELECT id_player, name FROM players WHERE id_player IN (?, ?) ORDER BY id_player ASC";

        try {
            Database.getConnection();

            ResultSet rs = Database.execute(query, new Object[]{
                    idBestPlayer,
                    idBeautifulScore
            }, false);

            String[] bests = new String[2];

            while (rs.next()) {
                if (idBestPlayer == rs.getInt("id_player")) {
                    bests[0] = rs.getString("name");
                } else {
                    bests[1] = rs.getString("name");
                }
            }

            return bests;
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
            model.addRow(new Object[]{
                    player.getName(),
                    player.getScore(),
                    player.getTeam()
            });
        }
    }

    public static String[] getPlayersWithScore(List<Player> players) {
        String[] playersName = new String[players.size()];

        for (int i = 0; i < players.size(); i++) {
            playersName[i] = players.get(i).getName();
        }

        return playersName;
    }

    public static ResultSet saveMatch(String dateTimeMatch, String teamAScore, String teamBScore,
                                      int idBeautifulScore, int idBestPlayer) {
        final String queryInsert = "INSERT INTO matches (date_time_match, team_a_score, team_b_score, winner, id_best_player, id_beautiful_score) VALUES (?, ?, ?, ?, ?, ?)";
        final String querySelect = "SELECT id_match, id_beautiful_score, id_best_player FROM matches WHERE date_time_match = ? AND team_a_score = ? AND team_b_score = ?";
        try {
            Database.execute(queryInsert, new Object[]{
                    dateTimeMatch,
                    teamAScore,
                    teamBScore,
                    getWinner(teamAScore, teamBScore),
                    idBestPlayer,
                    idBeautifulScore
            }, true);

            return Database.execute(querySelect, new Object[]{
                    dateTimeMatch,
                    teamAScore,
                    teamBScore
            }, false);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public static void relateMatchPlayer(int idMatch, int idPlayer, int score) {
        final String query = "INSERT INTO matches_players (id_match, id_player, player_match_score) VALUES (?, ?, ?)";
        try {
            Database.execute(query, new Object[]{
                    idMatch,
                    idPlayer,
                    score
            }, true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateScores(int idPlayer, int idMatch) {
        final String query = "UPDATE players SET score = score + (SELECT player_match_score FROM matches_players WHERE id_player = ? AND id_match = ?) WHERE id_player = ?";

        try {
            Database.execute(query, new Object[]{
                    idPlayer,
                    idMatch,
                    idPlayer
            }, true);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateBeautifulScoreBestPlayer(int idMatch) {
        final String querySelect = "SELECT id_beautiful_score, id_best_player FROM matches WHERE id_match = ?";
        final String queryUpdateBeautifulScore = "UPDATE players SET beautiful_score = beautiful_score + 1 WHERE id_player = ?";
        final String queryUpdateBestPlayer = "UPDATE players SET best_player = best_player + 1 WHERE id_player = ?";

        try {
            ResultSet rs = Database.execute(querySelect, new Object[]{
                    idMatch
            }, false);

            if (rs.next()) {
                Database.execute(queryUpdateBeautifulScore, new Object[]{
                        rs.getInt("id_beautiful_score"),
                }, true);

                Database.execute(queryUpdateBestPlayer, new Object[]{
                        rs.getInt("id_best_player"),
                }, true);
            }
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