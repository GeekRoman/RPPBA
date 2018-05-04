package client;
import GUI.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import server.Availability;
import server.Cell;
import server.Nomenclature;
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

    public static String addCell(String Id, String storageId, String Length, String Height, String Width,
                                    String Type, String Status) throws Exception{
        String str = "addCell " + Id + " "+ storageId + " " + Length + " " +
                Height + " " + Width + " " + Type + " " + Status;
        System.out.println(str);
        coos.writeObject(str);
        answer = (String) cois.readObject();
        return answer;
    }

    public static String addNomenclature(String ItemId,String Type, String Length,String Height,
                                         String Width,String Color,String Config,String Provider) throws Exception{
        String str = "addNomenclature " + ItemId + " "+ Type + " " + Length + " " +
                Height + " " + Width + " " + Color + " " + Config + " " +Provider;
        System.out.println(str);
        coos.writeObject(str);
        answer = (String) cois.readObject();
        return answer;
    }

    //DELETE
    public static String delStorage (String storageId) throws Exception{
        coos.writeObject("delStorage " + storageId);
        answer = (String) cois.readObject();
        return answer;
    }
    public static String delNomenclature (String itemId) throws Exception{
        coos.writeObject("delNomenclature " + itemId);
        answer = (String) cois.readObject();
        return answer;
    }

    public static String delCell (String cellId) throws Exception{
        coos.writeObject("delCell " + cellId);
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

    public static ArrayList<server.Nomenclature> getAllNomenclatureInList() throws Exception {
        coos.writeObject("getAllNomenclatureInList");
        ArrayList<server.Nomenclature> nomenclatures = (ArrayList<server.Nomenclature>) cois.readObject();
        return nomenclatures;
    }

    public static ArrayList<server.Cell> getAllCellInList() throws Exception {
        coos.writeObject("getAllCellInList");
        ArrayList<server.Cell> Cells = (ArrayList<server.Cell>) cois.readObject();
        return Cells;
    }

    public static ArrayList<server.Cell> getAllCellInListbyId(String stringId) throws Exception {
        coos.writeObject("getAllCellInListbyId " + stringId);
        ArrayList<server.Cell> Cells = (ArrayList<server.Cell>) cois.readObject();
        return Cells;
    }

    public static ArrayList<server.Availability> getAllAvailabilityInList() throws Exception {
        coos.writeObject("getAllAvailabilityStorageInList");
        ArrayList<server.Availability> Availability = (ArrayList<server.Availability>) cois.readObject();
        return Availability;
    }

    /////////////////////////////////////////////////////////////////////
    // Set_products, Get_products

    // Список продукции для comboBoxNameProduct
    public static ArrayList<server.Nomenclature> getNomenclatureComboBoxNameProduct() throws Exception{
        coos.writeObject("getNomenclatureComboBoxProductName");
        ArrayList<server.Nomenclature> nomenclatures = (ArrayList<server.Nomenclature>) cois.readObject();
        return nomenclatures;
    }

    // Список складов для comboBoxStorage
    public static ArrayList<server.Storage> getStorageComboBoxStorage() throws Exception {
        coos.writeObject("getStorageComboBoxStorage");
        ArrayList<server.Storage> storages = (ArrayList<server.Storage>) cois.readObject();
        return storages;
    }

    // Список ящиков для comboBoxCell
    public static ArrayList<server.Cell> getCellComboBoxCell(String storageId,String itemId) throws Exception {
        coos.writeObject("getCellComboBoxCell " + storageId + " " + itemId);
        ArrayList<server.Cell> cells = (ArrayList<server.Cell>) cois.readObject();
        return cells;
    }

    // Отключение списка складов и ящиков
    public static String getComboBoxNameProduct(String ItemId) throws Exception {
        coos.writeObject("getComboBoxNameProduct " + ItemId);
        return (String) cois.readObject();
    }

    // Добавление в табл log_availability
    public static String addItemAvailability(String ItemId,String CellId,String Quantity) throws Exception{
        coos.writeObject("addItemAvailability "+ItemId+ " " + CellId + " " + Quantity);
        String answer = (String) cois.readObject();
        return answer;
    }

    // Добавление в табл log_task
    public static String addTask(String Date) throws Exception{
        coos.writeObject("addTask " + Date);
        String answer = (String) cois.readObject();
        return answer;
    }

    // Добавление в табл log_transit
    public static String addTransit(String Storage,String Type) throws Exception{
        coos.writeObject("addTransit "+Storage + " " + Type);
        String answer = (String) cois.readObject();
        return answer;
    }

    // Get_products, Получение продуктов с табл log_availability
    public static ArrayList<Availability> getProductsForAvailability() throws Exception{
        coos.writeObject("getProductsForAvailability");
        ArrayList<Availability> availabilities = (ArrayList<Availability>) cois.readObject();
        return availabilities;
    }

    // Get_products, Отгрузка в табл log_availability
    public static String getQuantityAvailability(String ItemId,String Quantity) throws Exception{
        coos.writeObject("getQuantityAvailability " +  ItemId + " " + Quantity);
        String answer = (String) cois.readObject();
        return answer;
    }

    // Get_products,Set_products, Получение информации о продукте
    public static Nomenclature getInfoNomenclature(String ItemId) throws Exception{
        coos.writeObject("getInfoNomenclature" + " " + ItemId);
        Nomenclature nomenclature = (Nomenclature) cois.readObject();
        return nomenclature;
    }

    // Get_products, Ящик и склад
    public static ArrayList getCellAndStorage(String itemId) throws Exception{
        coos.writeObject("getCellAndStorage " + itemId);
        ArrayList storageCell = (ArrayList) cois.readObject();
        return storageCell;
    }

    // Get_products, Получение кол-ва продукта
    public static String quantityAvailability(String ItemId) throws Exception{
        coos.writeObject("quantityAvailability " + ItemId);
        String quantity = (String) cois.readObject();
        return quantity;
    }
}
