package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static client.Client.quit;

public class Product_Information_Management extends JFrame implements ActionListener {
    private JTable table1;
    private JButton GetButton;
    private JButton SetButton;
    private JButton ExitButton;
    private JButton InventButton;
    private JPanel InventManagementForm;


    public Product_Information_Management  (){
        super();
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(InventManagementForm);
        this.setResizable(false);
        this.setVisible(true);
        InventButton.addActionListener(this);
        SetButton.addActionListener(this);
        GetButton.addActionListener(this);
        ExitButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Получение продукции": {
                break;
            }
            case "Отгрузку продукции": {
                break;
            }

            case "Плановую инвентаризацию":{
                break;

            }
            case "Назад":{
                this.dispose();
                Menu menuform = null;
                try {
                    menuform = new Menu();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }
}
