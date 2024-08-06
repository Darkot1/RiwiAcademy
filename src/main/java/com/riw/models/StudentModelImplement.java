package com.riw.models;

import com.riw.entities.Student;
import com.riw.persistence.configDB.MysqlConfig;
import com.riw.persistence.imodel.IStudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentModelImplement implements IStudentModel {
    @Override
    public Student create(Student request) {
        //Creamos la entidad  para retornarla
        Student student = null;

        //Creammos la conexion pasandole el metodo de abrir conexion a la base de datos
        Connection connection = MysqlConfig.openConnection();

        //Generamos el query para insertar valores
        String sqlQuery = "INSERT INTO student (name, last_name,email) VALUES (?,?,?)";

        try {
            //Preparamos el query
            PreparedStatement statement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Remplazamos las ? con los get de la entidad
            statement.setString(1,request.getName());
            statement.setString(2,request.getLastName());
            statement.setString(3, request.getEmail());

            //Ejecutamos la Query y la guardamos en un int ya que nos devuelve la cantidad de columnas afectas
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0){
                //Recuperamos los datos generados con un resultSet
                ResultSet resultSet = statement.getGeneratedKeys();
                //Validamos que haya algo recuperado con el next()
                if (resultSet.next()){
                    //Asignamos el id generado a la entidad y recuperamos el id del resultSet que se encuentra en la columna 1
                    request.setIdStudent(resultSet.getInt(1));
                }

            }

            //Le pasamos todos los datos a la nueva entidad generado al principio
            student = request;
            //Cerramos el PreparetStatement
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        //Cerramos la conexion al final de la consulta
        MysqlConfig.closeConnection();

        //Retornamos la entidad creada
        return student;
    }

    @Override
    public ArrayList<Student> getAll(Student request) {
// Crear una lista para almacenar las Entidades
        ArrayList<Student> students = new ArrayList<>();

        // Establecer la conexión a la base de datos
        Connection connection = MysqlConfig.openConnection();

        // Crear la consulta SQL para leer todas las entidades
        String sqlQuery = "SELECT * FROM student WHERE status = ?";

        try {
            // Preparar la consulta
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            // Configurar el parametro para la busqueda
            if (request != null) {
                statement.setBoolean(1, request.getStatus());
            }
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Procesar los resultados
            while (resultSet.next()) {
                  Student student = new Student(
                           resultSet.getInt("id_student"),
                           resultSet.getString("name"),
                           resultSet.getString("last_name"),
                           resultSet.getString("email"),
                           resultSet.getBoolean("status")
                   );

                // Agregar la entidad de la base de datos al array
                students.add(student);
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
        return students;
    }

    @Override
    public Student update(Student request) {

        Connection connection = MysqlConfig.openConnection();

        String sqlQuery = "UPDATE student SET status = ? WHERE email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setBoolean(1, request.getStatus());
            statement.setString(2, request.getEmail());


            int rowsAffected = statement.executeUpdate();


            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        MysqlConfig.closeConnection();

        return request;
    }

    @Override
    public Student getEntity(String email) {
        Connection connection = MysqlConfig.openConnection();

        //Se crea una nueva entidad
        Student student = null;

        try {
            //Se genera la querya para buscar la entidad
            String sqlQuery = "SELECT * FROM student WHERE email = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //Se remplaza ? con el dato que pide el metodo
            preparedStatement.setString(1, email);

            preparedStatement.execute();

            //Se obtiene la tabla
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                //se asigna los datos a la nueva entidad
                student = new Student(
                        resultSet.getInt("id_student"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("status")
                );
            }

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MysqlConfig.closeConnection();
        }

        //Se retorna la entidad
        return student;
    }
}
