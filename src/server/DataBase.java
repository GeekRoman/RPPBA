package server;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.text.*;


public class DataBase {
    private static Connection connection;

    private Connection getConnection() throws Exception {
        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/logistics";
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }


}
