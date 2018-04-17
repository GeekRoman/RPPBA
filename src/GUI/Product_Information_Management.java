package GUI;

import GUI.Tasks.Get_products;
import GUI.Tasks.Inventory_products;
import GUI.Tasks.Set_products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Product_Information_Management extends JFrame{
    private JTable table1;
    private JButton ButtonSetProduct;
    private JButton ButtonGetProduct;
    private JButton ExitButton;
    private JButton ButtonInventarization;
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
        ButtonSetProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    openSet_Products();
                } catch (Exception e1){

                }
            }
        });

        ButtonGetProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openGet_Products();
                } catch (Exception e1){

                }
            }
        });

        ButtonInventarization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openInventory_Products();
                } catch (Exception e1){

                }
            }
        });
    }

    // Открытие формы Получение продукции
    private void openSet_Products() throws Exception{
        new Set_products().setVisible(true);
    }

    // Открытие формы Отгрузка продукции
    private void openGet_Products() throws Exception{
        new Get_products().setVisible(true);
    }

    // Открытие формы Инветаризация склада
    private void openInventory_Products() throws Exception{
        new Inventory_products().setVisible(true);
    }
}
