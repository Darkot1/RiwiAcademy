package com.riw.entities;

public class Score {

    private int idScore;
    private String descriptionScore;
    private int idStudent;
    private int idCourse;
    private double score;

    public Score(){}

    public Score(int idScore, String descriptionScore, int idStudent, int idCourse, double score) {
        this.idScore = idScore;
        this.descriptionScore = descriptionScore;
        this.idStudent = idStudent;
        this.idCourse = idCourse;
        this.score = score;
    }

    public int getIdScore() {
        return this.idScore;
    }

    public String getDescriptionScore() {
        return this.descriptionScore;
    }

    public int getIdStudent() {
        return this.idStudent;
    }

    public int getIdCourse() {
        return this.idCourse;
    }

    public double getScore() {
        return this.score;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public void setDescriptionScore(String descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score " +
                "idScore = " + this.idScore +
                ", descriptionScore = '" + this.descriptionScore + '\'' +
                ", idStudent = " + this.idStudent +
                ", idCourse = " + this.idCourse +
                ", score = " + this.score;
    }
}
