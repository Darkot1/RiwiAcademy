package com.riw.entities;


public class Student {
    //Creamos los atributos del estudiante
    private int idStudent;
    private String name;
    private String lastName;
    private String email;
    private Boolean status;

    //Creamos los constructores
    //Vacio
    public Student(){}
    //Completo

    public Student(int idStudent, String name, String lastName, String email, Boolean status) {
        this.idStudent = idStudent;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    public Student(Boolean statusStudents) {
        this.status = statusStudents;
    }

    //Creamos los Getters


    public int getIdStudent() {
        return this.idStudent;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Boolean getStatus() {
        return this.status;
    }


    //Creamos los Setters

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    //Creamos el ToString


    @Override
    public String toString() {
        return "Student" +'\n'+
                "idStudent = " + this.idStudent + '\n' +
                "name = " + this.name + '\n' +
                "lastName = " + this.lastName + '\n' +
                "email = " + this.email + '\n' +
                "status = " + this.status+ '\n';
    }


    public  String toString2() {
       return   "name = " + this.name + '\n' +
                "lastName = " + this.lastName + '\n' +
                "email = " + this.email + '\n';
    }
}
