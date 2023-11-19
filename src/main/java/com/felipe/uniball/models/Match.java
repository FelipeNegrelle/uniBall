package com.felipe.uniball.models;

public class Match {
    private int id;
    private String dateTimeMatch;
    private int scoreA;
    private int scoreB;
    private String winner;
    private String bestPlayer;
    private String playerBeautifulScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTimeMatch() {
        return dateTimeMatch;
    }

    public void setDateTimeMatch(String dateTimeMatch) {
        this.dateTimeMatch = dateTimeMatch;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getBestPlayer() {
        return bestPlayer;
    }

    public void setBestPlayer(String bestPlayer) {
        this.bestPlayer = bestPlayer;
    }

    public String getPlayerBeautifulScore() {
        return playerBeautifulScore;
    }

    public void setPlayerBeautifulScore(String playerBeautifulScore) {
        this.playerBeautifulScore = playerBeautifulScore;
    }
}
