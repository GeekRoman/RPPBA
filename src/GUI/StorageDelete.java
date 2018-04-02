package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;
import java.util.ArrayList;
import server.*;

import static client.Client.*;


public class StorageDelete extends JFrame implements ActionListener{

    private JComboBox comboBox2;

    private JPanel DeleteForm;
    private JButton button1;
    private JButton button2;


    public StorageDelete() throws Exception {
        super("Удаление");
        setSize(500, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(DeleteForm);
        this.setResizable(false);
        this.setVisible(true);
        button1.addActionListener(this);
        button2.addActionListener(this);
        String all_storage = getAllStorageId();

        String[] mas = all_storage.split("\n");
        for (int i = 0; i < mas.length; i++){
            comboBox2.addItem(mas[i]);
        }


    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch (str) {
            case "Удалить": {
                String storageId = comboBox2.getSelectedItem().toString();

                try {
                    if (delStorage(storageId).equals("true")) {
                        try {
                            comboBox2.removeItem(storageId);

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                        comboBox2.removeAllItems();

                        String all_storage = getAllStorageId();
                        String[] mas = all_storage.split("\n");
                        for (int i = 0; i < mas.length; i++) {
                            comboBox2.addItem(mas[i]);
                        }


                    } else {
                        JOptionPane.showMessageDialog(DeleteForm, "Ошибка при удалении!");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //------------------------
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

            case "Назад": {
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
        }
    }}




