package GUI.Storage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;
import java.util.ArrayList;

import GUI.Cell.Cell;
import GUI.Menu;
import GUI.Storage.StorageAdd;
import GUI.Storage.StorageDelete;
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

            case "Удалить":{
                this.dispose();
                StorageDelete deletestorage = null;
                try {
                    deletestorage = new StorageDelete();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }

            case "Управление ячейками":{
                this.dispose();
                String stringId = "";

                GUI.Cell.Cell cellform = null;
                try {
                    cellform = new Cell(stringId);
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
