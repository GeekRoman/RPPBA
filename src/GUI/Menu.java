package GUI;

import javax.swing.*;

import java.awt.event.*;
import  java.lang.*;
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
                //ss
                break;
            }

            case "Управление складом":{
                this.dispose();
                Storage newStorage = null;
                try {
                    newStorage = new Storage();
<<<<<<< HEAD


=======
                   
>>>>>>> roman
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;

            }

            case "Выход":{
                break;
            }


        }

    }
}
