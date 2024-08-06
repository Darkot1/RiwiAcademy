package com.riw.models;

import com.riw.entities.Registration;
import com.riw.persistence.configDB.MysqlConfig;
import com.riw.persistence.imodel.IRegistrationModel;

import java.sql.*;
import java.util.ArrayList;

public class RegistrationModelImplement implements IRegistrationModel {
    @Override
    public Registration create(Registration request) {
        //Creamos la entidad  para retornarla
        Registration registration = null;

        //Creammos la conexion pasandole el metodo de abrir conexion a la base de datos
        Connection connection = MysqlConfig.openConnection();

        //Generamos el query para insertar valores
        String sqlQuery = "INSERT INTO registration (fk_student_id, fk_course_id) VALUES (?,?)";

        try {
            //Preparamos el query
            PreparedStatement statement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Remplazamos las ? con los get de la entidad
            statement.setInt(1,request.getIdStudent());
            statement.setInt(2,request.getIdCourse());


            //Ejecutamos la Query y la guardamos en un int ya que nos devuelve la cantidad de columnas afectas
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0){
                //Recuperamos los datos generados con un resultSet
                ResultSet resultSet = statement.getGeneratedKeys();
                //Validamos que haya algo recuperado con el next()
                if (resultSet.next()){
                    //Asignamos el id generado a la entidad y recuperamos el id del resultSet que se encuentra en la columna 1
                    request.setIdCourse(resultSet.getInt(1));
                }

            }

            //Le pasamos todos los datos a la nueva entidad generado al principio
            registration = request;
            //Cerramos el PreparetStatement
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        //Cerramos la conexion al final de la consulta
        MysqlConfig.closeConnection();

        //Retornamos la entidad creada
        return registration;
    }

    @Override
    public ArrayList<Registration> getAll(Registration request) {
        // Crear una lista para almacenar las Entidades
        ArrayList<Registration> registrations = new ArrayList<>();

        // Establecer la conexión a la base de datos
        Connection connection = MysqlConfig.openConnection();

        // Crear la consulta SQL para leer todas las entidades
        String sqlQuery = "SELECT * FROM registration WHERE fk_course_id = ?";

        try {
            // Preparar la consulta
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            // Configurar el parametro para la busqueda
            if (request != null) {
                statement.setInt(1, request.getIdCourse());
            }
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Procesar los resultados
            while (resultSet.next()) {
                Registration registration = new Registration(
                        resultSet.getInt("id_registration"),
                        resultSet.getInt("fk_student_id"),
                        resultSet.getInt("fk_course_id"),
                        resultSet.getTimestamp("Registration_date")
                );

                // Agregar la entidad de la base de datos al array
                registrations.add(registration);
            }

            // Cerrar el prepare statement y resultset
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        // Cerrar conexion
        MysqlConfig.closeConnection();

        //Retornamos el Arrays con las entidades
        return registrations;
    }

    @Override
    public Registration update(Registration request) {
        Connection connection = MysqlConfig.openConnection();

        String sqlQuery = "UPDATE registration SET fk_course_id = ? WHERE fk_student_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setInt(1, request.getIdCourse());
            statement.setInt(2, request.getIdStudent());


            int rowsAffected = statement.executeUpdate();


            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        MysqlConfig.closeConnection();

        return request;
    }
}
