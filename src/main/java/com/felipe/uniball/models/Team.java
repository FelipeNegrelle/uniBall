package com.felipe.uniball.models;

public class Team {
    private final int idTeam;
    private String name;
    private int victories;

    public Team(int idTeam, String name, int victories) {
        this.idTeam = idTeam;
        this.name = name;
        this.victories = victories;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
}
