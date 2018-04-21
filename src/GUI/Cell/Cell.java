package GUI.Cell;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.addCell;
import static client.Client.delCell;
import static client.Client.getAllCellInListbyId;

public class Cell extends JFrame implements ActionListener {
    private JTable table1;
    private JTextField LengthField;
    private JComboBox TypeBox;
    private JTextField Id;
    private JComboBox StatusBox;
    private JButton AddButton;
    private JButton DeleteButton;
    private JPanel CellForm;
    private JTextField Height;
    private JTextField Width;
    private String []columnsHeader = {"ID ячейки", "Длина", "Высота", "Ширина", "Тип", "Статус"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    public Cell (String stringId) throws Exception{
        super("Управление складом.");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(CellForm);
        this.setResizable(true);
        this.setVisible(true);
        DeleteButton.addActionListener(this);
        AddButton.addActionListener(this);


        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        ArrayList<server.Cell> list;
        list = new ArrayList<server.Cell>(getAllCellInListbyId(stringId));

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getCellId(),
                    list.get(i).getType(),
                    list.get(i).getLength(),
                    list.get(i).getHeight(),
                    list.get(i).getWight(),
                    list.get(i).getStatus(),
            });
        };
        String[] typeBox = {"Верхняя", "Нижняя", "Средняя"};
        for (int j = 0; j < typeBox.length; j++){
            TypeBox.addItem(typeBox[j]);
        }
        String[] statusBox = {"Пустая", "Полная"};
        for (int j = 0; j < statusBox.length; j++){
            StatusBox.addItem(statusBox[j]);
        }
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table1.getSelectedRow();
                String value = table1.getModel().getValueAt(row, column).toString();
                try {
                    if (delCell(value).equals("true")) {
                        JOptionPane.showMessageDialog(CellForm, "Ячейка " + value + " удалена!");
                        DefaultTableModel tableModel = new DefaultTableModel() {
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return false;
                            }
                        };

                        for (String col: columnsHeader){
                            tableModel.addColumn(col);
                        }
                        table1.setModel(tableModel);
                        ArrayList<server.Cell> list;
                        list = new ArrayList<server.Cell>(getAllCellInListbyId(stringId));

                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new String[]{
                                    list.get(i).getCellId(),
                                    list.get(i).getType(),
                                    list.get(i).getLength(),
                                    list.get(i).getHeight(),
                                    list.get(i).getWight(),
                                    list.get(i).getStatus(),
                            });
                        };

                    } else {
                        JOptionPane.showMessageDialog(CellForm, "Ошибка при удалении ячейки!");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Id.getText().equals("") || LengthField.getText().equals("")
                        || Height.getText().equals("") || Width.getText().equals("")){
                    JOptionPane.showMessageDialog(CellForm, "Необходимо заполнить все поля!");
                } else {
                    String cellId = Id.getText().trim();
                    String storageId = stringId;
                    String length = LengthField.getText().trim();
                    String height = Height.getText().trim();
                    String width = Width.getText().trim();
                    String type = TypeBox.getSelectedItem().toString();
                    String status = StatusBox.getSelectedItem().toString();

                    try {
                        if (addCell(cellId,storageId, length, height, width, type, status).equals("false")){
                            JOptionPane.showMessageDialog(CellForm, "Такая ячейка уже существует!");
                        } else {
                            JOptionPane.showMessageDialog(CellForm, "Ячейка упешно добавлена!");
                            DefaultTableModel tableModel = new DefaultTableModel() {
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return false;
                                }
                            };

                            for (String col: columnsHeader){
                                tableModel.addColumn(col);
                            }
                            table1.setModel(tableModel);
                            ArrayList<server.Cell> list;
                            list = new ArrayList<server.Cell>(getAllCellInListbyId(stringId));

                            for (int i = 0; i < list.size(); i++) {
                                tableModel.addRow(new String[]{
                                        list.get(i).getCellId(),
                                        list.get(i).getType(),
                                        list.get(i).getLength(),
                                        list.get(i).getHeight(),
                                        list.get(i).getWight(),
                                        list.get(i).getStatus(),
                                });
                            };


                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;


    }
}
