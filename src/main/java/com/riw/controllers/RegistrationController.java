package com.riw.controllers;

import com.riw.entities.Registration;
import com.riw.entities.Student;
import com.riw.models.RegistrationModelImplement;
import com.riw.persistence.imodel.IRegistrationModel;

import java.util.ArrayList;

public class RegistrationController {
    // se llama la interfaz que tiene el crud

    IRegistrationModel registrationModel;

    public RegistrationController(){
        //Se instancia un nuevo modelo en el constructor de la interfaz  para llamar los metodos
        this.registrationModel = new RegistrationModelImplement();
    }


    //Creamos el metodo para inyectar el crear
    public Registration createRegistration(Registration request){
        return registrationModel.create(request);
    }

    public ArrayList<Registration> getRegistrationList(Registration request){
        return registrationModel.getAll(request);
    }

    public Registration updateRegistration(int idStudent, int idCourse){
        //Creamos una entidad
        Registration registration = new Registration();
        //Se le asignan lso nuevos valores
        registration.setIdStudent(idStudent);

        //Email del studiante para actualizar
        registration.setIdCourse(idCourse);
        return registrationModel.update(registration);
    }

}
