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

    public ArrayList<Availability> getAllAvailabilityInList() throws ClassNotFoundException {
        try (Connection connection = getConnection()) {
            int i = 1;
            ArrayList<Availability> list3 = new ArrayList<Availability>();
            String  ItemId, Name, Length,
                    Height, Wight, Config,
                    Color, Provider, Quantity,
                    OrderQuantity, StorageId, CellId;

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_nomenclature;");

            while (rs.next()) {
                ItemId = rs.getString("ItemId");
                Name = rs.getString("Name");
                Length = rs.getString("Length");
                Height = rs.getString("Height");
                Wight = rs.getString("Wight");
                Config = rs.getString("Config");
                Color = rs.getString("Color");
                Provider = rs.getString("Provider");
                Quantity = rs.getString("Quantity");
                OrderQuantity = rs.getString("OrderQuantity");
                StorageId = rs.getString("StorageId");
                CellId = rs.getString("CellId");

                Availability myavailability = new Availability(ItemId, Name, Length,
                        Height, Wight, Config,
                        Color, Provider, Quantity,
                        OrderQuantity, StorageId, CellId);

                list3.add(myavailability);

            }

            return list3;

        } catch (Exception e){
            System.out.println("Ошибка получения всех сотрудников");
            return null;
        }
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
    public ArrayList<Cell> getAllCellInList() throws ClassNotFoundException {
        try (Connection connection = getConnection()) {
            int i = 1;
            ArrayList<Cell> list2 = new ArrayList<Cell>();

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_cell;");

            String cellId, storageId,length,height,widht, type, status;

            while (rs.next()) {
                cellId = rs.getString("CellId");
                storageId = rs.getString("StorageId");
                length = rs.getString("Lenght");
                height = rs.getString("Height");
                widht = rs.getString("Widht");
                type = rs.getString("Type");
                status = rs.getString("Status");

                Cell mycell = new Cell(cellId, storageId,length,height,widht, type, status);

                list2.add(mycell);

            }

            return list2;

        } catch (Exception e){
            System.out.println("Ошибка получения всех сотрудников");
            return null;
        }
    }


}
