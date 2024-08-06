package com.riw.entities;

public class Course {

    //Atributos
    private  int idCourse;
    private String nameCourse;
    private String descriptionCourse;


    public Course(){}

    public Course(int idCourse, String nameCourse, String descriptionCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.descriptionCourse = descriptionCourse;
    }

    public int getIdCourse() {
        return this.idCourse;
    }

    public String getNameCourse() {
        return this.nameCourse;
    }

    public String getDescriptionCourse() {
        return this.descriptionCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }




    @Override
    public String toString() {
        return "Course " +
                "idCourse = " + this.idCourse +
                ", nameCourse = '" + this.nameCourse + '\'' +
                ", descriptionCourse = '" + this.descriptionCourse;
    }
}
