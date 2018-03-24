package client;
import GUI.*;
import server.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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


    public static void quit() throws IOException {
        coos.writeObject("quit");
        currentUserLogin = "";

        clientSocket.close();
        coos.close();
        cois.close();
    }
}
