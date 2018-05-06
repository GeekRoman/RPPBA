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
    public ArrayList<Nomenclature> getAllNomenclatureInList() throws ClassNotFoundException {
        try (Connection connection = getConnection()) {

            ArrayList<Nomenclature> list = new ArrayList<Nomenclature>();

            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_nomenclature;");

            String itemId, mytype, length, height, width, color, config, provider;

            while (rs.next()) {
                itemId = rs.getString("ItemId");
                mytype = rs.getString("ItemId");
                length = rs.getString("Length");
                height = rs.getString("Height");
                width = rs.getString("Width");
                color = rs.getString("Color");
                config = rs.getString("Config");
                provider = rs.getString("Provider");

                Nomenclature oneNomenclature = new Nomenclature(itemId, mytype, length,
                        height, width, color, config, provider);
                list.add(oneNomenclature);

            }

            return list;

        } catch (Exception e){
            System.out.println("Ошибка получения всей номенклатуры");
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

            String cellId, storageId,length,height,width, type, status;

            while (rs.next()) {
                cellId = rs.getString("CellId");
                storageId = rs.getString("StorageId");
                length = rs.getString("Length");
                height = rs.getString("Height");
                width = rs.getString("Width");
                type = rs.getString("Type");
                status = rs.getString("Status");

                Cell mycell = new Cell(cellId, storageId,length,height,width, type, status);

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
            System.out.println("Будет удален склад: " + storageId + " - " + sql);
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

    public boolean delNomenclature(String itemId){
        try (Connection connection = getConnection()) {

            String sql = "DELETE FROM log_nomenclature WHERE ItemId = \"" + itemId + "\";";
            System.out.println("Будет удалена номенклатура: " + itemId + " - " + sql);
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
            st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
            return true;

        } catch (Exception e){
            System.out.println("Ошибка удаления номенклатуры***");
            return false;
        }
    }

    public boolean delCell(String cellId){
        try (Connection connection = getConnection()) {

            String sql = "DELETE FROM log_cell WHERE CellId = \"" + cellId + "\";";
            System.out.println("Будет удалена ячейка: " + cellId + " - " + sql);
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
            st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
            return true;

        } catch (Exception e){
            System.out.println("Ошибка удаления ячейки!!!");
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

    public boolean addCell (String cellId, String storageId, String Length, String Height, String Width,
                            String Type, String Status) throws Exception {

        boolean addstatus = false;
        //int Length2int = Integer.parseInt(Length);
        //int Height2int = Integer.parseInt(Height);
        //int Width2int = Integer.parseInt(Width);
        try (Connection connection = getConnection()) {

            System.out.println("Проверка на свободный идентификатор...");
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_cell;");
            while (rs.next()){
                if (cellId.equals(rs.getString("CellId"))){
                    return addstatus=false;
                }
            }

            String sql = "INSERT INTO log_cell(CellId,StorageId,Length,Height,Width,Type,Status)" +
                    " VALUES (\"" + cellId + "\",\"" + storageId + "\", \"" + Length + "\", \"" + Height + "\", \"" + Width + "\", \"" + Type + "\", \"" + Status + "\");";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
            addstatus=true;

        } catch (Exception e){
            System.out.println("Ошибка добавления ячейки");
        }

        return addstatus;
    }

    public boolean addNomenclature (String itemId,String mytype, String length,String height,
                                    String width,String color,String config,String provider) throws Exception {

        boolean addstatus = false;
        try (Connection connection = getConnection()) {

            System.out.println("Проверка на свободный идентификатор...");
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM log_nomenclature;");
            while (rs.next()){
                if (itemId.equals(rs.getString("ItemId"))){
                    return addstatus=false;
                }
            }

            String sql = "INSERT INTO log_nomenclature(ItemId, Type, Length, Height, Width, Config, Color, Provider)" +
                    " VALUES (\"" + itemId + "\",\"" + mytype + "\", \"" + length + "\", \"" + height + "\", \"" + width + "\", \"" + config + "\", \"" + color + "\", \"" + provider + "\");";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
            addstatus=true;

        } catch (Exception e){
            System.out.println("Ошибка добавления ячейки");
        }

        return addstatus;
    }

    //UPDATE

    public boolean NomenclatureUpdate (String itemId,String mytype, String length,String height,
                                    String width,String color,String config,String provider) throws Exception {

        boolean addstatus = false;
        try (Connection connection = getConnection()) {
            Statement st = connection.createStatement();
            String sql = "UPDATE log_nomenclature SET ItemId = \""+ itemId +"\"," +
                   " Type = \"" + mytype + "\", Length = \"" + length + "\"," +
                    " Height = \"" + height + "\", Width = \"" + width + "\", Config = \"" + config + "\"," +
                   " Color = \"" + color + "\", Provider = \"" + provider + "\";";
            System.out.println(sql);

            st.executeUpdate(sql);
            return true;

        } catch (Exception e){
            System.out.println("Ошибка обновления");
        }

        return addstatus;
    }
    ////////////////////////////////////////////////////////////////////////
    // Set_products,Get_products,Transfer_products,Inventory_products

    private String setAvailabilityId = ""; // Id log_availability для log_transit
    private String moveQuantity = ""; // Кол-во продукции для log_availability, log_transit
    private String transitId = ""; // Id log_transit для log_task
    private String type = ""; // Тип задания для log_task, log_transit


    // Set_products,comboBoxNameProduct // Список продукции
    public ArrayList<Nomenclature> comboBoxNameProduct (){
        ArrayList<Nomenclature> nomenclatures = new ArrayList<Nomenclature>();
        String itemId;String typeNomenclature;

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

    // Set_products,comboBoxStorage // Список складов
    public ArrayList<Storage> getStorageComboBoxStorage(){
        ArrayList<Storage> storages = new ArrayList<>();
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

    // Set_products,comboBoxCell // Список ящиков в зависимости от склада и типа продукции
    public ArrayList<Cell> getCellComboBoxCell (String storageId,String itemIdNomenclature){
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

    // Set_products,comboBoxNameProduct // Наличие продукции
    public String getComboBoxNameProduct(String ItemId){
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

    // Set_products // Добавление продукции в log_availability
    public boolean addItemAvailability(String ItemId,String CellId,String Quantity){
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

    // Set_product,Get_product,Transfer_products Добавление перемещения в табл log_transit
    public boolean addTransit(String Storage,String Type){
        boolean addStatus = false;String Status = "Не выполнен";
        type = Type;

        try(Connection connection = getConnection()) {
            switch (Type)
            {
                case "Получение": {
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
                    break;
                }

                case "Отгрузка": {
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
                    break;
                }

                case "Перемещение" :{
                    System.out.println("Ошибка1");
                    String sqlGet = "select TransitId from log_transit order by TransitId desc limit 1;";
                    Statement statementGet = connection.createStatement();
                    ResultSet resultSet = statementGet.executeQuery(sqlGet);
                    System.out.println("Ошибка2");
                    while (resultSet.next()) {
                        transitId = resultSet.getString("TransitId");
                    }
                    System.out.println("Ошибка3");
                    transitId = idPlus(transitId);
                    char storageOut = Storage.charAt(0);
                    char storageIn = Storage.charAt(2);
                    String storageInStr = String.valueOf(storageIn);
                    String storageOutStr = String.valueOf(storageOut);
                    System.out.println("Ошибка4");
                    String sqlAddTransitGet = "INSERT INTO log_transit(TransitId, AvailabilityId, Move_Quantity,In_Storage, Out_Storage, Status) " +
                            "VALUES (" + transitId + "," + setAvailabilityId + "," + moveQuantity + "," + storageInStr + "," + storageOutStr + "," +"'" + Status + "'" +");";
                    System.out.println("Ошибка5");
                    System.out.println(sqlAddTransitGet);
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlAddTransitGet);
                    preparedStatement.executeUpdate(sqlAddTransitGet);
                    System.out.println("Ошибка6");
                    break;
                }

                case "Инвентаризация" : {
                    String sqlTransitId = "select TransitId from log_transit order by TransitId desc limit 1;";
                    Statement statementGet = connection.createStatement();
                    ResultSet resultSet = statementGet.executeQuery(sqlTransitId);

                    while (resultSet.next()) {
                        transitId = resultSet.getString("TransitId");
                    }

                    transitId = idPlus(transitId);

                    String sqlAddTransitGet = "INSERT INTO log_transit(TransitId,Status) " +
                            "VALUES (" + transitId + "," +"'" + Status + "'" +");";
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlAddTransitGet);
                    preparedStatement.executeUpdate(sqlAddTransitGet);
                    break;
                }
            }

            addStatus = true;

        } catch (Exception e) {
            System.out.println("Ошибка добавления перемещения");
        }

        return addStatus;
    }

    // Set_product,Get_product,Transfer_products // Добавление задачи в табл log_task
    public boolean addTask(String Date){
        boolean addStatus = false;String TaskId = "";String Status = "Не выполнен";
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
    public ArrayList<Availability> getProductsForAvailability(){
        ArrayList<Availability> availabilities = new ArrayList<Availability>();
        String itemId,orderQuantity,cellID;

        try (Connection connection = getConnection()) {
            Statement statementAvailability = connection.createStatement();
            ResultSet resultSet = statementAvailability.executeQuery("SELECT ItemId,OrderQuantity,CellId FROM log_availability");

            while (resultSet.next()){
                itemId = resultSet.getString("ItemId");
                orderQuantity = resultSet.getString("OrderQuantity");
                cellID = resultSet.getString("CellId");
                availabilities.add(new Availability(itemId,orderQuantity,cellID));
            }

        } catch (Exception e){
            System.out.println("Ошибка выборки продуктов");
        }

        return availabilities;
    }

    // Get_products,labelCell labelStorage
    public ArrayList getCellAndStorage(String itemId){
        String CellId = "",Type =  "",StorageId = "",Address = "",Status = "";ArrayList storageCell = new ArrayList();
        System.out.println(itemId);
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

            String queryStorage = "SELECT Address,Status FROM log_storage WHERE StorageId = " + StorageId;
            PreparedStatement preparedStatementStorage = connection.prepareStatement(queryStorage);
            ResultSet resultSetStorage = preparedStatementStorage.executeQuery();

            while (resultSetStorage.next()){
                Address = resultSetStorage.getString("Address");
                Status = resultSetStorage.getString("Status");
            }
            System.out.println(Address);
            storageCell.add(new Storage(StorageId,Address,Status));

        } catch (Exception e){
            System.out.println("Ошибка выборки ящиков и складов");
        }

        return storageCell;
    }

    // Get_product,Отгрузка в log_availability
    public boolean getQuantityAvailability(String ItemId,String OrderQuantity){
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

    // Получение кол-ва продукта в наличии
    public String quantityAvailability(String ItemId){
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

    // Get_products,Set_products,Transfer_products // Информация о продукте
    public Nomenclature getInfoNomenclature(String ItemId){
        Nomenclature nomenclature = new Nomenclature();
        String itemId,Type,Length,Height,Width,Config,Provider, Color;
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ItemId,Type,Lenght,Height,Width,Config,Provider FROM log_nomenclature WHERE ItemId = " + ItemId);

            while (resultSet.next()){
                itemId = resultSet.getString("ItemId");
                Type = resultSet.getString("Type");
                Length = resultSet.getString("Lenght");
                Height = resultSet.getString("Height");
                Width = resultSet.getString("Width");
                Color = resultSet.getString("Color");
                Config = resultSet.getString("Config");
                Provider = resultSet.getString("Provider");
                nomenclature = new Nomenclature(itemId,Type,Length,Height,Width,Color,Config,Provider);
            }
        } catch (Exception e){
            System.out.println("Ошибка получение информации");
        }
        return nomenclature;
    }

    // Transfer_products // Перемещение продукции с одного ящика в другой
    public boolean transferCellAvailability(String ItemId,String Quantity,String CellIn){
        try(Connection connection = getConnection()){
            moveQuantity = Quantity;
            System.out.println("ОшибкаНал1");
            Statement statementUpdateItemId = connection.createStatement();
            statementUpdateItemId.executeUpdate("UPDATE log_availability SET OrderQuantity = OrderQuantity -" + Quantity +
                    " WHERE ItemId = " + ItemId);
            System.out.println("ОшибкаНал1");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select AvailabilityId from log_availability order by AvailabilityId desc limit 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("ОшибкаНал2");
            while (resultSet.next()){
                setAvailabilityId = resultSet.getString("AvailabilityId");
            }

            setAvailabilityId = idPlus(setAvailabilityId);


            PreparedStatement preparedStatementInsert = connection.prepareStatement("INSERT INTO log_availability(AvailabilityId, ItemId, CellId,OrderQuantity) " +
                    "VALUES (" + setAvailabilityId + "," + ItemId + "," + CellIn + "," + Quantity + ")");
            preparedStatementInsert.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка перемещения продукта");
            return false;
        }
    }

    // Inventory_products // Получение кол-ва суммы всех продуктов
    public String getQuantityAvailability(){
        String total = "";
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT sum(OrderQuantity) AS 'total' FROM log_availability");

            while (resultSet.next()) {
                total = resultSet.getString("total");
            }

        } catch (Exception e) {
            System.out.println("Ошибка получения кол-ва наличия");
        }

        return total;
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
