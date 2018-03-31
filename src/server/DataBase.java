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
            System.out.println("Ошибка получения всех складов");
            return null;
        }
    }
    public ArrayList<Cell> getAllCellInList() throws ClassNotFoundException {
        try (Connection connection = getConnection()) {

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
            System.out.println("Ошибка получения всех ячеек");
            return null;
        }
    }
    public ArrayList<Cell> getAllCellInListbyId(String stringId) throws ClassNotFoundException {
        try (Connection connection = getConnection()) {

            ArrayList<Cell> list = new ArrayList<Cell>();

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_cell WHERE StorageId = \"" + stringId + "\";");

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

                list.add(mycell);

            }

            return list;

        } catch (Exception e){
            System.out.println("Ошибка получения всех ячеек");
            return null;
        }
    }
    public String getAllStorageId() throws ClassNotFoundException {

        try (Connection connection = getConnection()) {
            int i = 0;
            String str ="";

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_storage;");
            String name = new String();

            while (rs.next()) {
                name = rs.getString("StorageId");
                str += name.trim() + "\n";
                i++;
            }

            return str;

        } catch (Exception e){
            System.out.println("Ошибка получения StorageId");
            return null;
        }

    }
    //DELETE
    public boolean delStorage(String storageId){
        try (Connection connection = getConnection()) {

            String sql = "DELETE FROM log_storage WHERE StorageId = \"" + storageId + "\";";
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
            st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
            return true;

        } catch (Exception e){
            System.out.println("Ошибка удаления склада***");
            return false;
        }
    }
    //ADD
    public boolean addStorage (String mystorageId, String myaddress, String mystatus) throws Exception {
        boolean addstatus = false;
        try (Connection connection = getConnection()) {

            System.out.println("Проверка на свободный идентификатор...");
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_storage;");
            while (rs.next()){
                if (mystorageId.equals(rs.getString("StorageId"))){
                    return addstatus=false;
                }
            }

            String sql = "INSERT INTO log_storage(StorageId,Address,Status) VALUES (\"" + mystorageId + "\",\"" + myaddress + "\", \"" + mystatus + "\");";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
            addstatus=true;

        } catch (Exception e){
            System.out.println("Ошибка добавления склада");
        }

        return addstatus;
    }



}
