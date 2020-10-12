package de.qsmq.java11introduction.entity;

public class Competences {

    private int id;

    private String name;
    private String competences;

    public Competences() {
    }

    public Competences(int id, String name, String competences) {
        this.id = id;
        this.name = name;
        this.competences = competences;
    }

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

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

}
