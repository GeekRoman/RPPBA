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

    public ArrayList<Storage> getAllStoragesInList() throws ClassNotFoundException {
        try (Connection connection = getConnection()) {
            int i = 1;
            ArrayList<Storage> list = new ArrayList<Storage>();

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_storage;");

            String storageId, address,status;

            while (rs.next()) {
                storageId = rs.getString("StorageId");
                address = rs.getString("Address");
                status = rs.getString("Status");

                Storage mystorage = new Storage(storageId, address, status);

                list.add(mystorage);

            }

            return list;

        } catch (Exception e){
            System.out.println("Ошибка получения всех сотрудников");
            return null;
        }
    }


}
