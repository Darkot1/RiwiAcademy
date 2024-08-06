package com.riw.persistence.configDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {

    public  static Connection connection = null;


    public static Connection openConnection(){

        String url = "jdbc:mysql://localhost:3306/riwiAcademyDB";
        String user = "root";
        String password = "Rlwl2023.";

        try {

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection ON");

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return  connection;
    }

    public static void closeConnection(){

        if (connection != null){
            try {

                connection.close();
                connection = null;
                System.out.println("Connection OFF");
            }catch (SQLException e){

                throw new RuntimeException(e.getMessage());
            }

        }

    }

}
