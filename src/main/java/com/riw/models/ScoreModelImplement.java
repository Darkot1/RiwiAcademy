package com.riw.models;

import com.riw.entities.Score;
import com.riw.persistence.configDB.MysqlConfig;
import com.riw.persistence.imodel.IScoreModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreModelImplement implements IScoreModel {
    @Override
    public Score create(Score request) {
        //Creamos la entidad  para retornarla
        Score score = null;

        //Creammos la conexion pasandole el metodo de abrir conexion a la base de datos
        Connection connection = MysqlConfig.openConnection();

        //Generamos el query para insertar valores
        String sqlQuery = "INSERT INTO score (description, fk_student_id, fk_course_id, score) VALUES (?,?,?,?);";

        try {
            //Preparamos el query
            PreparedStatement statement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            //Remplazamos las ? con los get de la entidad
            statement.setString(1,request.getDescriptionScore());
            statement.setInt(2,request.getIdStudent());
            statement.setInt(3,request.getIdCourse());
            statement.setDouble(4,request.getScore());

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
            score = request;
            //Cerramos el PreparetStatement
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        //Cerramos la conexion al final de la consulta
        MysqlConfig.closeConnection();

        //Retornamos la entidad creada
        return score;
    }

    @Override
    public Score update(Score request) {
        Connection connection = MysqlConfig.openConnection();

        String sqlQuery = "UPDATE score SET score = ? WHERE fk_student_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            statement.setDouble(1, request.getScore());
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

