package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;
import java.util.ArrayList;
import server.*;

import static client.Client.*;


public class StorageAdd extends JFrame implements ActionListener{
    private JTextField idField;
    private JTextField addressField;
    private JComboBox comboBox1;

    private JPanel storageAdd;
    private JButton button1;
    private JButton button2;


    public StorageAdd() throws Exception {
        super("Добавление склада");
        setSize(650, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(storageAdd);
        this.setResizable(false);
        this.setVisible(true);
        button1.addActionListener(this);
        button2.addActionListener(this);


        String[] statusBox = {"Пустой", "Заполненыый"};
        for (int j = 0; j<statusBox.length; j++){
            comboBox1.addItem(statusBox[j]);
        }
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Добавить": {
                if (idField.getText().equals("") || addressField.getText().equals("")){
                    JOptionPane.showMessageDialog(storageAdd, "Необходимо заполнить все поля!");
                } else {
                    String storageId = this.idField.getText().trim();
                    String address = this.addressField.getText().trim();
                    String status = this.comboBox1.getSelectedItem().toString();

                    try {
                        if (addStorage(storageId,address,status).equals("false")){
                            JOptionPane.showMessageDialog(storageAdd, "Такой склад уже существует!");
                        } else {
                            JOptionPane.showMessageDialog(storageAdd, "Склад упешно добавлен!");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    this.dispose();
                    StorageForm newStorage = null;
                    try
                    {
                        newStorage = new StorageForm();
                        newStorage.setResizable(false);
                        newStorage.setVisible(true);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }


                break;
            }
            case "Назад":{
                this.dispose();
                StorageForm newStorage = null;
                try
                {
                    newStorage = new StorageForm();
                    newStorage.setResizable(false);
                    newStorage.setVisible(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }


        }
    }
}
