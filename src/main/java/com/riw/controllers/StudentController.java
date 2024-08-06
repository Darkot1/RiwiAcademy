package com.riw.controllers;

import com.riw.entities.Student;
import com.riw.models.StudentModelImplement;
import com.riw.persistence.imodel.IStudentModel;

import java.util.ArrayList;

public class StudentController {

    // se llama la interfaz que tiene el crud
    IStudentModel studentModel;

    public StudentController(){
        //Se instancia un nuevo modelo en el constructor de la interfaz  para llamar los metodos
        this.studentModel = new StudentModelImplement();
    }

    //Creamos el metodo para inyectar el crear
    public Student createStudent(Student request){
        //Se retorna el metodo para crear la entidad
        return  studentModel.create(request);
    }

    //Creamos el metodo para leer todas la entidades

    public ArrayList<Student> getStudents(Student request){

        return  studentModel.getAll(request);
    }

    public  Student getStudent(String request){
        return  studentModel.getEntity(request);
    }

    public  Student updateStudent(Boolean update,String email){
        //Creamos una entidad
        Student student = new Student();
        //Se le asignan lso nuevos valores
        student.setStatus(update);

        //Email del studiante para actualizar
        student.setEmail(email);
        return studentModel.update(student);
    }

}
