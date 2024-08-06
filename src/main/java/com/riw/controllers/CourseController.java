package com.riw.controllers;

import com.riw.entities.Course;
import com.riw.entities.Student;
import com.riw.models.CouseModelImplement;
import com.riw.persistence.imodel.ICourseModel;

public class CourseController {
    // se llama la interfaz que tiene el crud
    ICourseModel courseModel;

    public CourseController(){
        //Se instancia un nuevo modelo en el constructor de la interfaz  para llamar los metodos
        this.courseModel = new CouseModelImplement();

    }

    //Creamos el metodo para inyectar el crear
    public Course createCourse(Course request){
        //Se retorna el metodo para crear la entidad
        return  courseModel.create(request);
    }

    public Course updateCourse(int idSearch, String newName, String description){
        //Creamos una entidad
        Course course = new  Course();
        //id del curso para actualizar
        course.setIdCourse(idSearch);

        //Se le asignan los nuevos valores
        course.setNameCourse(newName);
        course.setDescriptionCourse(description);



        return courseModel.update(course);
    }


    public  Course getCourse(String name){
        return courseModel.getEntity(name);
    }

    public boolean deleteCourse(Integer id){
        courseModel.delete(id);
        return true;
    }

}
