package com.riw.models;

import com.riw.entities.Course;
import com.riw.persistence.configDB.MysqlConfig;
import com.riw.persistence.imodel.ICourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CouseModelImplement implements ICourseModel {
    @Override
    public Course create(Course request) {
        //Creamos la entidad  para retornarla
        Course course = null;

        //Creammos la conexion pasandole el metodo de abrir conexion a la base de datos
        Connection connection = MysqlConfig.openConnection();

        //Generamos el query para insertar valores
        String sqlQuery = "INSERT INTO course (name_course, description) VALUES (?,?)";

        try {
            //Preparamos el query
            PreparedStatement statement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Remplazamos las ? con los get de la entidad
            statement.setString(1,request.getNameCourse());
            statement.setString(2,request.getDescriptionCourse());

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
            course = request;
            //Cerramos el PreparetStatement
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        //Cerramos la conexion al final de la consulta
        MysqlConfig.closeConnection();

        //Retornamos la entidad creada
        return course;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = MysqlConfig.openConnection();

        String sqlQuery = "DELETE FROM course WHERE id_course = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Course getEntity(String name) {
        Connection connection = MysqlConfig.openConnection();

        //Se crea una nueva entidad
        Course course = null;

        try {
            //Se genera la querya para buscar la entidad
            String sqlQuery = "SELECT * FROM course WHERE name_course = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //Se remplaza ? con el dato que pide el metodo
            preparedStatement.setString(1, name);

            preparedStatement.execute();

            //Se obtiene la tabla
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                //se asigna los datos a la nueva entidad
                 course = new Course(
                        resultSet.getInt("id_course"),
                        resultSet.getString("name_course"),
                        resultSet.getString("description")
                );
            }

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MysqlConfig.closeConnection();
        }

        //Se retorna la entidad
        return course;
    }

    @Override
    public Course update(Course request) {
        Connection connection = MysqlConfig.openConnection();

        String sqlQuery = "UPDATE course SET name_course = ?,description = ? WHERE id_course = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setString(1, request.getNameCourse());
            statement.setString(2, request.getDescriptionCourse());
            statement.setInt(3,request.getIdCourse());


            int rowsAffected = statement.executeUpdate();


            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        MysqlConfig.closeConnection();

        return request;
    }
}
