package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    static Connection  connection=null;
    public static void connectToDB(){

        String jdbcUrl="jdbc:mysql://localhost:3306/customer";
        String username="root";
        String password="";


        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);

        } catch (Exception exception){
                exception.printStackTrace();
            }


    }

    public static Connection getMyConnection(){
       return connection;
    }

    public static void dropDBConnection(){
        try {
            connection.commit();
           connection.close();
        System.out.println("Connection closed.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
