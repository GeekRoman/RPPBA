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
