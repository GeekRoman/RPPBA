package GUI;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import  java.lang.*;

import static client.Client.quit;

public class Menu extends JFrame implements ActionListener {
    private JButton Button1;
    private JButton ExitButton;
    private JButton Button2;
    private JPanel FormMenu;

    public Menu (){
        super();
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Управление продукцией": {
                break;
            }

            case "Управление складом":{
                this.dispose();
                Storage newStorage = null;
                try {
                    newStorage = new Storage();
                   
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
