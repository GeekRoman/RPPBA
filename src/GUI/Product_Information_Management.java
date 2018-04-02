package GUI;

import server.Storage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static client.Client.*;

public class Product_Information_Management extends JFrame implements ActionListener {
    private JTable table1;
    private JButton GetButton;
    private JButton SetButton;
    private JButton ExitButton;
    private JButton InventButton;
    private JPanel InventManagementForm;
    private JButton номенклатураButton;
    private JButton спискиЗаданийButton;
    private JComboBox comboBox1;
    private String []columnsHeader = {"ID", "Название", "Длина", "Высота",
            "Ширина", "Цвет", "Постовщик", "В наличии", "Заказанно", "Склад","Ячейка"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };


    public Product_Information_Management  () throws Exception{
        super("Управление продукцией ");
        setSize(900, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(InventManagementForm);
        this.setResizable(false);
        this.setVisible(true);
        InventButton.addActionListener(this);
        SetButton.addActionListener(this);
        GetButton.addActionListener(this);
        ExitButton.addActionListener(this);
        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        ArrayList<server.Availability> list;
       // list = new ArrayList<server.Availability>(getAllAvailabilityInList());


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
