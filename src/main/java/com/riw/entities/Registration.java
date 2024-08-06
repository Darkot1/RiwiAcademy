package com.riw.entities;

import java.sql.Timestamp;

public class Registration {

    private int idRegistration;
    private int idStudent;
    private int idCourse;
    private Timestamp registrationDate;


    public Registration(){}

    public Registration(int idRegistration, int idStudent, int idCourse, Timestamp registrationDate) {
        this.idRegistration = idRegistration;
        this.idStudent = idStudent;
        this.idCourse = idCourse;
        this.registrationDate = registrationDate;
    }

    public Registration(int courseIdSearch) {
    }


    public int getIdRegistration() {
        return this.idRegistration;
    }

    public int getIdStudent() {
        return this.idStudent;
    }

    public int getIdCourse() {
        return this.idCourse;
    }

    public Timestamp getRegistrationDate() {
        return this.registrationDate;
    }

    public void setIdRegistration(int idRegistration) {
        this.idRegistration = idRegistration;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Registration " +
                "idRegistration = " + this.idRegistration +
                ", idStudent = " + this.idStudent +
                ", idCourse = " + this.idCourse +
                ", registrationDate = " + this.registrationDate;
    }
}
