package GUI;

import GUI.Tasks.Get_products;
import GUI.Tasks.Inventory_products;
import GUI.Tasks.Set_products;
import GUI.Tasks.Transfer_products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Product_Information_Management extends JFrame{
    private JTable table1;
    private JButton buttonSetProduct;
    private JButton buttonGetProduct;
    private JButton ExitButton;
    private JButton buttonInventarization;
    private JPanel InventManagementForm;
    private JButton номенклатураButton;
    private JButton спискиЗаданийButton;
    private JComboBox comboBox1;
    private JButton buttonTransfer;
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
        setResizable(false);

        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        initForm();

        /*ArrayList<Availability> list;
       list = new ArrayList<server.Availability>(getAllAvailabilityInList())*/;


    }

    // Обработка кнопок с заданиями
    private void initForm(){
        buttonSetProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    openSet_Products();
                } catch (Exception e1){

                }
            }
        });

        buttonGetProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openGet_Products();
                } catch (Exception e1){

                }
            }
        });

        buttonInventarization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openInventory_Products();
                } catch (Exception e1){

                }
            }
        });



        buttonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openTransfer_Products();
                } catch (Exception e1){

                }
            }
        });
    }

    // Открытие формы Получение продукции
    private void openSet_Products() throws Exception{
        Set_products set_products =  new Set_products();
        set_products.setVisible(true);
    }

    // Открытие формы Отгрузка продукции
    private void openGet_Products() throws Exception{
        new Get_products().setVisible(true);
    }

    // Открытие формы Инветаризация склада
    private void openInventory_Products() throws Exception{
        new Inventory_products().setVisible(true);
    }

    // Открытие формы Перемещение продукции
    private void openTransfer_Products() throws Exception{
        new Transfer_products().setVisible(true);
    }
}
