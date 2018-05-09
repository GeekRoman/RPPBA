package server;

import client.Client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread {
    private static DataBase db;
    private Socket clientSocket;
    private ObjectInputStream sois;
    private ObjectOutputStream soos;
    private String answer;
    private File reportFile = new File("reportsfiles/report.txt");


    public Server(Socket s) throws IOException {
        clientSocket = s;
        sois = new ObjectInputStream(clientSocket.getInputStream());
        soos = new ObjectOutputStream(clientSocket.getOutputStream());
        start();
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(2525);
        System.out.println("Сервер запущен...");

        try {
            while (true) {
                Socket newSocket = serverSocket.accept();
                System.out.println("Новый клиент подключен");

                try {
                    new Server(newSocket);
                } catch (Exception e) {
                    newSocket.close();
                }
            }

        } finally {
            serverSocket.close();
        }
    }


    public void run() {
        DataBase db = new DataBase();

        try{
            while(true) {
                System.out.println("Ожидание ввода");

                String clientMessageRecieved = (String)sois.readObject();
                System.out.println("Сообщение получено: " + clientMessageRecieved);
                String mas[] = clientMessageRecieved.split(" ");
                String mess =  mas[0];
                if (mess.equals("quit")){
                    break;
                }

                switch (mess){
                    case "quit": {
                        System.out.println("Клиент отсоединился");
                        clientSocket.close();
                        break;
                    }
                    //Add
                    case "addStorage": {
                        String id = mas[1];
                        String address = mas[2];
                        String status = mas[3];

                        if (db.addStorage(id, address, status)==false){
                            answer = "false";
                        } else {
                            answer = "true";
                        }

                        soos.writeObject(answer);

                        break;
                    }

                    case "addCell": {
                        String Id = mas[1];
                        String StorageId = mas[2];
                        String Length = mas[3];
                        String Heigth = mas[4];
                        String Width = mas[5];
                        String Type = mas[6];
                        String Status = mas[7];


                        if (db.addCell(Id, StorageId, Length,Heigth,Width,Type,Status)==false){
                            answer = "false";
                        } else {
                            answer = "true";
                        }

                        soos.writeObject(answer);

                        break;
                    }

                    case "addNomenclature": {
                        String itemId = mas[1];
                        String mytype = mas[2];
                        String length = mas[3];
                        String height = mas[4];
                        String width = mas[5];
                        String color = mas[6];
                        String config = mas[7];
                        String provider = mas[8];

                        if (db.addNomenclature(itemId, mytype, length,
                                height, width, color, config, provider)==false){
                            answer = "false";
                        } else {
                            answer = "true";
                        }
                        soos.writeObject(answer);
                        break;
                    }

                    //UpDate
                    case "NomenclatureUpdate": {
                        String itemId = mas[1];
                        String mytype = mas[2];
                        String length = mas[3];
                        String height = mas[4];
                        String width = mas[5];
                        String color = mas[6];
                        String config = mas[7];
                        String provider = mas[8];

                        if (db.NomenclatureUpdate(itemId, mytype, length,
                                height, width, color, config, provider)==false){
                            answer = "false";
                        } else {
                            answer = "true";
                        }
                        soos.writeObject(answer);
                        break;
                    }
                    case "StorageUpdate": {
                        String storageId = mas[1];
                        String adsress = mas[2];
                        String status = mas[3];

                        if (db.StorageUpdate(storageId, adsress, status)==false){
                            answer = "false";
                        } else {
                            answer = "true";
                        }
                        soos.writeObject(answer);
                        break;
                    }



                    //DELETE
                    case "delStorage": {
                        String storageId = mas[1];
                        if (db.delStorage(storageId)){
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        soos.writeObject(answer);
                        break;
                    }

                    case "delNomenclature": {
                        String itemId = mas[1];
                        if (db.delNomenclature(itemId)){
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        soos.writeObject(answer);
                        break;
                    }

                    case "delCell": {
                        String cellId = mas[1];
                        if (db.delCell(cellId)){
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        soos.writeObject(answer);
                        break;
                    }
                    //GET
                    case "getAllstorageId": {
                        String allStorages = db.getAllStorageId();
                        soos.writeObject(allStorages);
                        break;
                    }
                    case "getAllStorageInList": {
                        ArrayList<Storage> storages = new ArrayList<>(db.getAllStoragesInList());
                        soos.writeObject(storages);
                        break;
                    }
                    case "getAllNomenclatureInList": {
                        ArrayList<Nomenclature> nomenclatures = new ArrayList<>(db.getAllNomenclatureInList());
                        soos.writeObject(nomenclatures);
                        break;
                    }
                    case "getAllCellInList": {
                        ArrayList<Cell> cells = new ArrayList<>(db.getAllCellInList());
                        soos.writeObject(cells);
                        break;
                    }
                    case "getAllAvailabilityInList": {
                        ArrayList<Availability> availability = new ArrayList<>(db.getAllAvailabilityInList());
                        soos.writeObject(availability);
                        break;
                    }
                    case "getAllCellInListbyId": {
                        String storageId = mas[1];
                        ArrayList<Cell> cells = new ArrayList<>(db.getAllCellInListbyId(storageId));
                        soos.writeObject(cells);
                        break;
                    }
                    case "getAllTaskLiatInList": {
                        ArrayList<TaskList> taskLists = new ArrayList<>(db.getAllTaskLiatInList());
                        soos.writeObject(taskLists);
                        break;
                    }

                    // Set_products,comboBoxProductName
                    case "getNomenclatureComboBoxProductName" : {
                        ArrayList<Nomenclature> nomenclatures = new ArrayList<>(db.comboBoxNameProduct());
                        soos.writeObject(nomenclatures);
                        break;
                    }

                    // Set_products,comboBoxStorage
                    case "getStorageComboBoxStorage" : {
                        ArrayList<Storage> storages = new ArrayList<>(db.getStorageComboBoxStorage());
                        soos.writeObject(storages);
                        break;
                    }

                    // Set_products,comboBoxCell
                    case "getCellComboBoxCell" : {
                        String storageId = mas[1];
                        String itemId = mas[2];
                        ArrayList<Cell> cells = new ArrayList<>(db.getCellComboBoxCell(storageId,itemId));
                        soos.writeObject(cells);
                        break;
                    }

                    // Set_products,comboBoxNameProduct
                    case "getComboBoxNameProduct" : {
                        String productId = mas[1];
                        String status = db.getComboBoxNameProduct(productId);
                        soos.writeObject(status);
                        break;
                    }

                    // Set_products,ButtonAdd
                    case  "addItemAvailability" : {
                        String ItemId = mas[1];
                        String CellId = mas[2];
                        String Quantity = mas[3];

                        String status = "";
                        if(db.addItemAvailability(ItemId,CellId,Quantity) == false) {
                            status = "Ошибка";
                            soos.writeObject(status);
                        } else {
                            status = "Добавлены";
                            soos.writeObject(status);
                        }
                        break;
                    }

                    // Set_products,ButtonAdd
                    case "addTask" :{
                        String Date = mas[1];

                        String status = "";
                        if(db.addTask(Date) == false) {
                            status = "Ошибка";
                            soos.writeObject(status);
                        } else {
                            status = "Добавлены";
                            soos.writeObject(status);
                        }
                        break;
                    }

                    // Set_products,ButtonAdd
                    case "addTransit" :{
                        String Storage = mas[1];
                        String Type = mas[2];

                        String status = "";
                        if(db.addTransit(Storage,Type) == false) {
                            status = "Ошибка";
                            soos.writeObject(status);
                        } else {
                            status = "Добавлены";
                            soos.writeObject(status);
                        }
                        break;
                    }

                    // Set_products,ButtonAdd
                    case "addTransitTransfer" :{
                        String StorageOut = mas[1];
                        String StorageIn = mas[2];
                        String Storage = StorageOut + " " + StorageIn;
                        String Type = mas[3];

                        String status = "";
                        if(db.addTransit(Storage,Type) == false) {
                            status = "Ошибка";
                            soos.writeObject(status);
                        } else {
                            status = "Добавлены";
                            soos.writeObject(status);
                        }
                        break;
                    }

                    // Get_products,ButtonGet
                    case  "getQuantityAvailability" : {
                        String ItemId = mas[1];
                        String Quantity = mas[2];

                        String status = "";
                        if(db.getQuantityAvailability(ItemId,Quantity) == false) {
                            status = "Ошибка";
                            soos.writeObject(status);
                        } else {
                            status = "Добавлены";
                            soos.writeObject(status);
                        }
                        break;
                    }

                    // Get_products,ComboBoxProductAvailability
                    case  "getProductsForAvailability" : {
                        ArrayList<Availability> availabilities = new ArrayList<>(db.getProductsForAvailability());
                        soos.writeObject(availabilities);
                        break;
                    }

                    // Get_products,ButtonInfo
                   /* case  "getInfoNomenclature" : {
                        String itemID = mas[1];
                        Nomenclature nomenclature = db.getInfoNomenclature(itemID);
                        soos.writeObject(nomenclature);
                        break;
                    }*/

                    // Get_products,labelStorage,labelCell
                    case  "getCellAndStorage" : {
                        String itemId = mas[1];
                        ArrayList storageCell = db.getCellAndStorage(itemId);
                        soos.writeObject(storageCell);
                        break;
                    }

                    // Get_products,
                    case  "quantityAvailability" : {
                        String itemId = mas[1];
                        String storageCell = db.quantityAvailability(itemId);
                        soos.writeObject(storageCell);
                        break;
                    }

                    // Transfer_products,
                    case  "transferCellAvailability" : {
                        String itemId = mas[1];
                        String quantity = mas[2];
                        String cellIn = mas[3];
                        String status;
                        if (db.transferCellAvailability(itemId,quantity,cellIn) == false) {
                            status = "Ошибка";
                            soos.writeObject(status);
                        } else {
                            status = "Добавлены";
                            soos.writeObject(status);
                        }
                        break;
                    }

                    // Inventory_products,
                    case  "totalQuantityAvailability" : {
                        String totalQuantity = db.getQuantityAvailability();
                        soos.writeObject(totalQuantity);
                        break;
                    }

                    default: {
                        System.out.println("Команда не определена");
                    }
                }
            }
        } catch (Exception e){
            System.err.println("IO Exception ska");
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e){
                System.err.println("Socket not closed");
            }
        }
    }

}
