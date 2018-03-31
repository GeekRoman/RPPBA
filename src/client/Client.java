package client;
import GUI.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import server.Storage;

public class Client {
    private static ObjectInputStream cois;
    private static ObjectOutputStream coos;
    private static Socket clientSocket;
    private static String answer;
    private static String answer2;
    private static String currentUserLogin;

    public static String getCurrentUserLogin() {
        return currentUserLogin;
    }

    public static Socket getClientSocket() {
        return clientSocket;
    }

    public ObjectOutputStream getOutS() {
        return coos;
    }

    public ObjectInputStream getInS() {
        return cois;
    }

    public static void main(String[] args) throws IOException {
        try {

            clientSocket = new Socket("127.0.0.1", 2525);
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
            cois = new ObjectInputStream(clientSocket.getInputStream());

            Menu newMenu = new Menu();

        } catch (Exception e) {
            System.err.println("Не удалось подключиться к серверу!");
            try {
                clientSocket.close();
                coos.close();
                cois.close();
            } catch (Exception e1) {

            }

        }
    }
    //QUIT
    public static void quit() throws IOException {
        coos.writeObject("quit");
        currentUserLogin = "";

        clientSocket.close();
        coos.close();
        cois.close();
    }
    //ADD
    public static String addStorage(String id, String address, String status) throws Exception{
        String str = "addStorage " + id + " "+ address + " " + status;
        System.out.println(str);
        coos.writeObject(str);
        answer = (String) cois.readObject();

        return answer;

    }
    //DELETE
    public static String delStorage (String storageId) throws Exception{
        coos.writeObject("delStorage");
        coos.writeObject(storageId);
        answer = (String) cois.readObject();
        return answer;
    }
    //GET
    public static String getAllStorageId() throws Exception {
        coos.writeObject("getAllstorageId");
        answer = (String) cois.readObject();
        return answer;
    }
    public static ArrayList<server.Storage> getAllStorageInList() throws Exception {
        coos.writeObject("getAllStorageInList");
        ArrayList<server.Storage> storages = (ArrayList<server.Storage>) cois.readObject();
        return storages;
    }

    public static ArrayList<server.Cell> getAllCellInList() throws Exception {
        coos.writeObject("getAllCellInList");
        ArrayList<server.Cell> Cells = (ArrayList<server.Cell>) cois.readObject();
        return Cells;
    }

    public static ArrayList<server.Cell> getAllCellInListbyId(String stringId) throws Exception {
        coos.writeObject("getAllCellInList " + stringId);
        ArrayList<server.Cell> Cells = (ArrayList<server.Cell>) cois.readObject();
        return Cells;
    }

    public static ArrayList<server.Availability> getAllAvailabilityInList() throws Exception {
        coos.writeObject("getAllAvailabilityStorageInList");
        ArrayList<server.Availability> Availability = (ArrayList<server.Availability>) cois.readObject();
        return Availability;
    }
}
