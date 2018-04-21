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
                        ArrayList<Storage> storages = new ArrayList<Storage>(db.getAllStoragesInList());
                        soos.writeObject(storages);
                        break;
                    }
                    case "getAllCellInList": {
                        ArrayList<Cell> cells = new ArrayList<Cell>(db.getAllCellInList());
                        soos.writeObject(cells);
                        break;
                    }
                    case "getAllAvailabilityInList": {
                        ArrayList<Availability> availability = new ArrayList<Availability>(db.getAllAvailabilityInList());
                        soos.writeObject(availability);
                        break;
                    }
                    case "getAllCellInListbyId": {
                        String storageId = mas[1];
                        ArrayList<Cell> cells = new ArrayList<Cell>(db.getAllCellInListbyId(storageId));
                        soos.writeObject(cells);
                        break;
                    }

                    // comboBoxProductName
                    case "getNomenclatureComboBoxProductName" : {
                        ArrayList<Nomenclature> nomenclatures = new ArrayList<Nomenclature>(db.comboBoxNameProduct());
                        soos.writeObject(nomenclatures);
                        break;
                    }

                    // comboBoxStorage
                    case "getStorageComboBoxStorage" : {
                        ArrayList<Storage> storages = new ArrayList<>(db.getStorageComboBoxStorage());
                        soos.writeObject(storages);
                        break;
                    }

                    // comboBoxCell
                    case "getCellComboBoxCell" : {
                        String storageId = mas[1];
                        ArrayList<Cell> cells = new ArrayList<Cell>(db.getCellComboBoxCell(storageId));
                        soos.writeObject(cells);
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
