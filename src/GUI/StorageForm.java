package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;
import java.util.ArrayList;
import server.*;

import static client.Client.getAllStorageInList;

public class StorageForm extends JFrame implements ActionListener {
    private JTextField textField1;
    private JTable table1;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton ExitButton;
    private JButton AddButton;
    private JButton CellButton;
    private JComboBox comboBox2;
    private JButton DeleteButton;
    private JPanel StorageForm;
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

        ArrayList <Storage> list;
       /*list = new ArrayList<Storage>(getAllStorageInList());

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getStorageId(),
                    list.get(i).getAddress(),
                    list.get(i).getStatus(),
            });
        };*/

    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Добавить": {
                break;
            }

            case "Удалить":{
                break;

            }

            case "Управление ячейками":{
                this.dispose();
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
