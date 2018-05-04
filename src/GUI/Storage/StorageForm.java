package GUI.Storage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;
import java.util.ArrayList;

import GUI.Cell.Cell;
import GUI.Menu;
import GUI.Storage.StorageAdd;

import server.*;

import static client.Client.*;

public class StorageForm extends JFrame implements ActionListener {

    private JTextField idField;
    public JTable table1;
    private JTextField addressField;
    private JComboBox comboBox1;
    private JButton ExitButton;
    private JButton AddButton;
    private JButton CellButton;
    private JButton DeleteButton;
    private JPanel StorageForm;
    private JComboBox comboBox3;
    private JToolBar ToolBar;

    private String []columnsHeader = {"ID склада", "Адрес", "Статус"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };


    public StorageForm() throws Exception {
        super("Управление складом");

        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(StorageForm);
        this.setResizable(false);
        this.setVisible(true);
        DeleteButton.addActionListener(this);
        AddButton.addActionListener(this);


        ExitButton.addActionListener(this);
        ArrayList <Storage> list = new ArrayList<Storage>(getAllStorageInList());

        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getStorageId(),
                    list.get(i).getAddress(),
                    list.get(i).getStatus(),
            });
        };

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2){
                    try {
                        int column0 = 0;
                        int column1 = 1;
                        int column2 = 2;
                        int row = table1.getSelectedRow();
                        String storageId = table1.getModel().getValueAt(row, column0).toString();
                        String address = table1.getModel().getValueAt(row, column1).toString();
                        String status = table1.getModel().getValueAt(row, column2).toString();
                        new StorageDetailsPage(storageId, address, status).setVisible(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table1.getSelectedRow();
                String value = table1.getModel().getValueAt(row, column).toString();
                try {
                    if (delStorage(value).equals("true")) {
                        JOptionPane.showMessageDialog(StorageForm, "Склад " + value + " удален!");
                        DefaultTableModel tableModel = new DefaultTableModel() {
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return false;
                            }
                        };
                        ArrayList <Storage> list = new ArrayList<Storage>(getAllStorageInList());
                        for (String col: columnsHeader){
                            tableModel.addColumn(col);
                        }
                        table1.setModel(tableModel);

                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new String[]{
                                    list.get(i).getStorageId(),
                                    list.get(i).getAddress(),
                                    list.get(i).getStatus(),
                            });
                        };



                    } else {
                        JOptionPane.showMessageDialog(StorageForm, "Ошибка при удалении!");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        CellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int column = 0;
                    int row = table1.getSelectedRow();
                    String value = table1.getModel().getValueAt(row, column).toString();
                    new Cell(value).setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Добавить": {
                this.dispose();
                StorageAdd addstorage = null;
                try {
                    addstorage = new StorageAdd();
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
