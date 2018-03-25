package GUI;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import  java.lang.*;
import  server.Storage;

import static client.Client.quit;

public class Menu extends JFrame implements ActionListener {
    private JButton Button1;
    private JButton ExitButton;
    private JButton Button2;
    private JPanel FormMenu;

    public Menu () throws Exception {
        super();
        setSize(600, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(FormMenu);
        this.setResizable(false);
        this.setVisible(true);
        Button1.addActionListener(this);
        Button2.addActionListener(this);
        ExitButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Управление продукцией": {
                this.dispose();
                Product_Information_Management PIManagement = null;
                try {
                    PIManagement = new Product_Information_Management();
                    PIManagement.setResizable(false);
                    PIManagement.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }

            case "Управление складом":{
                this.dispose();
                StorageForm newStorage = null;
                try {
                    newStorage = new StorageForm();
                    newStorage.setResizable(false);
                    newStorage.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;

            }

            case "Выход":{
                try {
                    quit();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.dispose();
                break;
            }
        }
    }
}
