package GUI.Cell;

import GUI.Storage.StorageForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.getAllCellInListbyId;

public class Cell extends JFrame implements ActionListener {
    private JTable table1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JButton AddButton;
    private JButton ExitButton;
    private JComboBox comboBox3;
    private JButton DeleteButton;
    private JPanel CellForm;
    private String []columnsHeader = {"ID ячейки", "Длина", "Высота", "Ширина", "Тип", "Статус"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    public Cell (String stringId) throws Exception{
        super();
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(CellForm);
        this.setResizable(false);
        this.setVisible(true);
        DeleteButton.addActionListener(this);
        AddButton.addActionListener(this);
        ExitButton.addActionListener(this);

        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        ArrayList<server.Cell> list;
        list = new ArrayList<server.Cell>(getAllCellInListbyId(stringId));

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getCellId(),
                    list.get(i).getLength(),
                    list.get(i).getHeight(),
                    list.get(i).getWight(),
                    list.get(i).getType(),
                    list.get(i).getStatus(),
            });
        };
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

            case "Назад":{
                this.dispose();
                StorageForm storageform = null;
                try {
                    storageform = new StorageForm();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }
}
