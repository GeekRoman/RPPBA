package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;
import java.util.ArrayList;
import server.*;

import static client.Client.*;

public class StorageForm extends JFrame implements ActionListener {
    private JTextField idField;
    private JTable table1;
    private JTextField addressField;
    private JComboBox comboBox1;
    private JButton ExitButton;
    private JButton AddButton;
    private JButton CellButton;
    private JComboBox comboBox2;
    private JButton DeleteButton;
    private JPanel StorageForm;
    private JComboBox comboBox3;
    private String []columnsHeader = {"ID склада", "Адрес", "Статус"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };


    public StorageForm() throws Exception {
        super();
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(StorageForm);
        this.setResizable(false);
        this.setVisible(true);
        DeleteButton.addActionListener(this);
        AddButton.addActionListener(this);
        CellButton.addActionListener(this);
        ExitButton.addActionListener(this);

        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        ArrayList <Storage> list = new ArrayList<Storage>(getAllStorageInList());

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getStorageId(),
                    list.get(i).getAddress(),
                    list.get(i).getStatus(),
            });
        };

        String all_storage = getAllStorageId();
        String[] statusBox = {"Пустой", "Заполненыый"};
        String[] mas = all_storage.split("\n");
        for (int i = 0; i < mas.length; i++){
            comboBox2.addItem(mas[i]);
            comboBox3.addItem(mas[i]);
        }
        for (int j = 0; j<statusBox.length; j++){
            comboBox1.addItem(statusBox[j]);
        }

    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Добавить": {
                if (idField.getText().equals("") || addressField.getText().equals("")){
                    JOptionPane.showMessageDialog(StorageForm, "Необходимо заполнить все поля!");
                } else {
                    String storageId = this.idField.getText().trim();
                    String address = this.addressField.getText().trim();
                    String status = this.comboBox1.getSelectedItem().toString();

                    try {
                            if (addStorage(storageId,address,status).equals("false")){
                                JOptionPane.showMessageDialog(StorageForm, "Такой склад уже существует!");
                            } else {
                                JOptionPane.showMessageDialog(StorageForm, "Склад упешно добавлен!");
                                DefaultTableModel tableModel2 = new DefaultTableModel() {
                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return false;
                                    }
                                };
                                for (String col: columnsHeader){
                                    tableModel2.addColumn(col);
                                }
                                table1.setModel(tableModel2);
                                ArrayList <Storage> list = new ArrayList<Storage>(getAllStorageInList());

                                for (int i = 0; i < list.size(); i++) {
                                    tableModel2.addRow(new String[]{
                                            list.get(i).getStorageId(),
                                            list.get(i).getAddress(),
                                            list.get(i).getStatus(),
                                    });
                                };
                                comboBox2.removeAllItems();
                                comboBox3.removeAllItems();
                                String all_storage = getAllStorageId();
                                String[] mas = all_storage.split("\n");
                                for (int i = 0; i < mas.length; i++){
                                    comboBox2.addItem(mas[i]);
                                    comboBox3.addItem(mas[i]);
                                }

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }


                break;
            }

            case "Удалить":{
                String storageId = comboBox2.getSelectedItem().toString();

                try {
                    if (delStorage(storageId).equals("true")){
                        try {
                            comboBox2.removeItem(storageId);
                            comboBox3.removeItem(storageId);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(StorageForm, "Склад " + storageId + " удален!");
                        DefaultTableModel tableModel2 = new DefaultTableModel() {
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return false;
                            }
                        };
                        for (String col: columnsHeader){
                            tableModel2.addColumn(col);
                        }
                        table1.setModel(tableModel2);
                        ArrayList <Storage> list = new ArrayList<Storage>(getAllStorageInList());

                        for (int i = 0; i < list.size(); i++) {
                            tableModel2.addRow(new String[]{
                                    list.get(i).getStorageId(),
                                    list.get(i).getAddress(),
                                    list.get(i).getStatus(),
                            });
                        };
                        comboBox2.removeAllItems();
                        comboBox3.removeAllItems();



                    } else {
                        JOptionPane.showMessageDialog(StorageForm, "Ошибка при удалении!");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }

            case "Управление ячейками":{
                this.dispose();
                String stid = comboBox3.getSelectedItem().toString();
                Cell cellform = null;
                try {
                    cellform = new Cell();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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
