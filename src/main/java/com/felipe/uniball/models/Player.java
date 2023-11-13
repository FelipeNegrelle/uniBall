package com.felipe.uniball.models;

public class Player {
    private int id;
    private String name;
    private String position;
    private int number;
    private int score;
    private int bestPlayer;
    private int bestScore;
    private String user;
    private char team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBestPlayer() {
        return bestPlayer;
    }

    public void setBestPlayer() {
        this.bestPlayer = getBestPlayer() + 1;
    }

    public void setBestPlayerFromDb(int bestPlayer) {
        this.bestPlayer = bestPlayer;
    }

    public int getBeautifulScore() {
        return bestScore;
    }

    public void setBeautifulScore() {
        this.bestScore = getBeautifulScore() + 1;
    }

    public void setBeautifulScoreFromDb(int bestScore) {
        this.bestScore = bestScore;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public char getTeam() {
        return team;
    }

    public void setTeam(char team) {
        this.team = team;
    }
}
