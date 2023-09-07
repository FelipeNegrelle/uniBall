package com.felipe.uniball.models;

public class Player {
    private final int idPlayer;
    private int idTeam;
    private String name;
    private String position;
    private int number;
    private int score;

    public Player(int idPlayer, int idTeam, String name, String position, int number, int score) {
        this.idPlayer = idPlayer;
        this.idTeam = idTeam;
        this.name = name;
        this.position = position;
        this.number = number;
        this.score = score;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
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
}
