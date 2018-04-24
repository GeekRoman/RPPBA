package server;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.text.*;


public class DataBase {
    private static Connection connection;

    private Connection getConnection() throws Exception {
        String username = "root";
        String password = "sasha";
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
            String sql = "SELECT * FROM log_cell WHERE StorageId = \"" + stringId + "\";";
            rs = statement.executeQuery(sql);

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


    ////////////////////////////////////////////////////////////////////////
    // Set_products,Get_products

    private String setAvailabilityId = "";
    private String moveQuantity = "";
    private String transitId = "";
    private String type = "";


    // Set_products,comboBoxNameProduct // Работает
    public ArrayList<Nomenclature> comboBoxNameProduct () throws Exception {
        ArrayList<Nomenclature> nomenclatures = new ArrayList<Nomenclature>();
        String itemId;String typeNomenclature = "";

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT ItemId,Type FROM log_nomenclature");

            while (rs.next()){
                itemId = rs.getString("ItemId");
                typeNomenclature = rs.getString("Type");
                nomenclatures.add(new Nomenclature(itemId,typeNomenclature));
            }

        } catch (Exception e){
            System.out.println("Ошибка выборки продуктов");
        }

        return nomenclatures;
    }

    // Set_products,comboBoxStorage // Работает
    public ArrayList<Storage> getStorageComboBoxStorage () throws Exception{
        ArrayList<Storage> storages = new ArrayList<Storage>();
        String StorageId,Status;
        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT StorageId,Status FROM log_storage");

            while (rs.next()){
                StorageId = rs.getString("StorageId");
                Status = rs.getString("Status");
                storages.add(new Storage(StorageId,Status));
            }

        } catch (Exception e){
            System.out.println("Ошибка выборки складов");
        }

        return storages;
    }

    // Set_product,comboBoxCell // Работает
    public ArrayList<Cell> getCellComboBoxCell (String storageId,String itemIdNomenclature) throws Exception{
        ArrayList<Cell> cells = new ArrayList<Cell>();
        String CellId,Type,Status,typeNomenclature = "";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Type FROM log_nomenclature WHERE ItemId = " + itemIdNomenclature);

            while (resultSet.next()){
                typeNomenclature = resultSet.getString("Type");
            }

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT CellId,Type,Status FROM log_cell WHERE StorageId = "+"'"+storageId+"'" + " AND Type = " + "'" + typeNomenclature + "'");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                CellId = rs.getString("CellId");
                Type = rs.getString("Type");
                Status = rs.getString("Status");
                cells.add(new Cell(CellId,Type,Status));
            }

        } catch (Exception e){
            System.out.println("Ошибка выборки ящиков");
        }

        return cells;
    }

    // Set_product,comboBoxNameProduct // Работает
    public String getComboBoxNameProduct(String ItemId) throws Exception{
        String status = ""; String ItemIdStr = "";
        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ItemId FROM log_availability WHERE ItemId = " + ItemId);

             while (resultSet.next()){
                 ItemIdStr = resultSet.getString("ItemId");
             }

             if(ItemIdStr.equals("")){
                 status = "Нету";
             } else {
                 status = "Есть";
             }

        } catch (Exception e) {
            System.out.println("Ошибка выборки ящиков");
        }
        return status;
    }

    // Set_product,Добавление в log_availability // Работает
    public boolean addItemAvailability(String ItemId,String CellId,String Quantity) throws Exception{
        boolean addStatus = false;String AvailabilityId = "",resultItemId ="";
        int Id = 0;
        moveQuantity = Quantity;
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT AvailabilityId,ItemId FROM log_availability WHERE ItemId = " + "'" + ItemId + "'");

            while (rs.next()){
                setAvailabilityId = rs.getString("AvailabilityId");
                resultItemId = rs.getString("ItemId");
            }

            if(resultItemId.equals("")){
                String sqlNull = "select AvailabilityId from log_availability order by AvailabilityId desc limit 1;";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlNull);
                ResultSet resultSet1 = preparedStatement.executeQuery(sqlNull);

                while (resultSet1.next()){
                    AvailabilityId = resultSet1.getString("AvailabilityId");
                }

                setAvailabilityId = idPlus(AvailabilityId);

                String sql = "INSERT INTO log_availability(AvailabilityId,ItemId,CellId,OrderQuantity) " +
                        "VALUES (" + setAvailabilityId + "," + ItemId + "," + CellId + "," + Quantity + ");";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.executeUpdate(sql);
            } else {
                String sql = "update log_availability set OrderQuantity = OrderQuantity +" + Quantity +" where ItemId = " + ItemId + ";";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate(sql);
            }

            addStatus = true;

        } catch (Exception e){
            System.out.println("Ошибка добавления продукта");
        }
        return addStatus;
    }

    // Set_product,Get_product Добавление перемещения в табл log_transit // Работает
    public boolean addTransit(String Storage,String Type) throws Exception{
        boolean addStatus = false;String Status = "Не выполнен";
        type = Type;

        try(Connection connection = getConnection()) {

            if(Type.equals("Получение")) {

                String sqlSet = "select TransitId from log_transit order by TransitId desc limit 1;";
                Statement statementSet = connection.createStatement();
                ResultSet resultSet = statementSet.executeQuery(sqlSet);

                while (resultSet.next()) {
                    transitId = resultSet.getString("TransitId");
                }

                transitId = idPlus(transitId);

                String sqlAddTransitSet = "INSERT INTO log_transit(TransitId, AvailabilityId, Move_Quantity, In_Storage, Status) " +
                        "VALUES (" + transitId + "," + setAvailabilityId + "," + moveQuantity + "," + Storage + "," +"'" + Status + "'" +");";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlAddTransitSet);
                preparedStatement.executeUpdate(sqlAddTransitSet);

            } else if(Type.equals("Отгрузка")){

                String sqlGet = "select TransitId from log_transit order by TransitId desc limit 1;";
                Statement statementGet = connection.createStatement();
                ResultSet resultSet = statementGet.executeQuery(sqlGet);

                while (resultSet.next()) {
                    transitId = resultSet.getString("TransitId");
                }

                transitId = idPlus(transitId);

                String sqlAddTransitGet = "INSERT INTO log_transit(TransitId, AvailabilityId, Move_Quantity, Out_Storage, Status) " +
                        "VALUES (" + transitId + "," + setAvailabilityId + "," + moveQuantity + "," + Storage + "," +"'" + Status + "'" +");";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlAddTransitGet);
                preparedStatement.executeUpdate(sqlAddTransitGet);
            }

            addStatus = true;

        } catch (Exception e) {
            System.out.println("Ошибка добавления перемещения");
        }
        return addStatus;
    }

    // Set_product,Get_product Добавление задачи в табл log_task // Работает
    public boolean addTask(String Date) throws Exception{
        boolean addStatus = false;String TaskId = "";String Status = "Не выполнен";
        System.out.println(type + " " + transitId);
        try(Connection connection = getConnection()){

            Statement statement = connection.createStatement();
            String sqlTask = "select TaskId from log_task order by TaskId desc limit 1;";
            ResultSet resultSet = statement.executeQuery(sqlTask);

            while (resultSet.next()){
                TaskId = resultSet.getString("TaskId");
            }

            String sqlInsertTask = "INSERT INTO log_task(TaskId, TransitId, Type,Date,Status) " +
                    "VALUES (" + idPlus(TaskId) + "," + transitId + "," + "'" + type + "'" + "," + "'" + Date + "'" + "," + "'" + Status + "'" + ");";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertTask);
            preparedStatement.executeUpdate(sqlInsertTask);

            addStatus = true;

        } catch (Exception e) {
            System.out.println("Ошибка добавления задачи");
        }

        return addStatus;
    }

    // Get_products,comboBoxProductAvailability //
    public ArrayList<Availability> getProductsForAvailability() throws Exception {
        ArrayList<Availability> availabilities = new ArrayList<Availability>();
        String itemId,orderQuantity = "";

        try (Connection connection = getConnection()) {
            Statement statementAvailability = connection.createStatement();
            ResultSet resultSet = statementAvailability.executeQuery("SELECT ItemId,OrderQuantity FROM log_availability");

            while (resultSet.next()){
                itemId = resultSet.getString("ItemId");
                orderQuantity = resultSet.getString("OrderQuantity");
                availabilities.add(new Availability(itemId,orderQuantity));
            }

        } catch (Exception e){
            System.out.println("Ошибка выборки продуктов");
        }

        return availabilities;
    }

    // Get_products,labelCell labelStorage // Работает
    public ArrayList getCellAndStorage(String itemId) throws Exception{
        String CellId = "",Type =  "",StorageId = "",Address = "";ArrayList storageCell = new ArrayList();

        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CellId FROM log_availability WHERE ItemId = " + itemId);

            while (resultSet.next()){
                CellId = resultSet.getString("CellId");
            }

            String queryCell = "SELECT StorageId,Type FROM log_cell WHERE CellId = " + CellId;
            PreparedStatement preparedStatement = connection.prepareStatement(queryCell);
            ResultSet resultSetCell = preparedStatement.executeQuery();

            while (resultSetCell.next()){
                StorageId = resultSetCell.getString("StorageId");
                Type = resultSetCell.getString("Type");
            }

            storageCell.add(new Cell(CellId,Type));

            String queryStorage = "SELECT Address FROM log_storage WHERE StorageId = " + StorageId;
            PreparedStatement preparedStatementStorage = connection.prepareStatement(queryStorage);
            ResultSet resultSetStorage = preparedStatementStorage.executeQuery();

            while (resultSetStorage.next()){
                Address = resultSetStorage.getString("Address");
            }

            storageCell.add(new Storage(StorageId,Address));

        } catch (Exception e){
            System.out.println("Ошибка выборки ящиков и складов");
        }

        return storageCell;
    }

    // Get_product,Отгрузка в log_availability // Работает
    public boolean getQuantityAvailability(String ItemId,String OrderQuantity) throws Exception{
        boolean addStatus = false;
        moveQuantity = OrderQuantity;
        try (Connection connection = getConnection()) {
            Statement statementSelect = connection.createStatement();
            ResultSet resultSet = statementSelect.executeQuery("SELECT AvailabilityId FROM log_availability WHERE ItemId = " + ItemId);

            while (resultSet.next()){
                setAvailabilityId = resultSet.getString("AvailabilityId");
            }

            String prepared = "update log_availability set OrderQuantity = OrderQuantity - " + OrderQuantity + " where ItemId = " + ItemId;
            PreparedStatement preparedStatement = connection.prepareStatement(prepared);
            preparedStatement.executeUpdate();

            addStatus = true;

        } catch (Exception e){
            System.out.println("Ошибка отгрузки склада");
        }
        return addStatus;
    }

    public String quantityAvailability(String ItemId) throws Exception{
        String Quantity = "";
        try(Connection connection = getConnection()){

            Statement statementQuantity = connection.createStatement();
            ResultSet resultSet = statementQuantity.executeQuery("SELECT OrderQuantity FROM log_availability WHERE ItemId = " + ItemId);

            while (resultSet.next()){
                Quantity = resultSet.getString("OrderQuantity");
            }

        } catch (Exception e) {
            System.out.println("Ошибка выборки кол-ва");
        }

        return Quantity;
    }

    // Get_product,Set_product,информация о продукте // Работает
    public Nomenclature getInfoNomenclature(String ItemId) throws Exception{
        Nomenclature nomenclature = new Nomenclature();
        String itemId,Type,Length,Height,Width,Config,Provider;
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ItemId,Type,Lenght,Height,Width,Config,Provider FROM log_nomenclature WHERE ItemId = " + ItemId);

            while (resultSet.next()){
                itemId = resultSet.getString("ItemId");
                Type = resultSet.getString("Type");
                Length = resultSet.getString("Lenght");
                Height = resultSet.getString("Height");
                Width = resultSet.getString("Width");
                Config = resultSet.getString("Config");
                Provider = resultSet.getString("Provider");
                nomenclature = new Nomenclature(itemId,Type,Length,Height,Width,Config,Provider);
            }
        } catch (Exception e){
            System.out.println("Ошибка получение информации");
        }
        return nomenclature;
    }

    // Увелечение iD на 1
    private String idPlus(String id){
        int Id =0;
        if(id.equals("")) {
            Id = 1;
            id = String.valueOf(Id);
        } else {
            Id = Integer.parseInt(id) + 1;
            id = String.valueOf(Id);
        }
        return id;
    }
}
