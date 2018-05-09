package GUI;

import GUI.Nomenclature.NomenclatureListPage;
import GUI.Tasks.Get_products;
import GUI.Tasks.Inventory_products;
import GUI.Tasks.Set_products;
import GUI.Tasks.Transfer_products;
import server.Availability;
import server.Nomenclature;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.getAllAvailabilityInList;
import static client.Client.getAllNomenclatureInList;

public class Product_Information_Management extends JFrame{
    private JTable table1;
    private JButton buttonSetProduct;
    private JButton buttonGetProduct;
    private JButton ExitButton;
    private JButton buttonInventarization;
    private JPanel InventManagementForm;
    private JButton nomenclatureListButton;
    private JButton listTasksButton;
    private JComboBox comboBox1;
    private JButton buttonTransfer;

    private String []columnsHeader = {"ID", "Тип","Длина", "Высота",
            "Ширина","Конфигурация", "Цвет", "Постовщик", "В наличии", "Заказанно"};
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


        initForm();

        /*ArrayList<Availability> list;
       list = new ArrayList<server.Availability>(getAllAvailabilityInList())*/;

        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
                Menu menuform = null;
                try {
                    menuform = new Menu();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        nomenclatureListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openNomenclature();
                } catch (Exception e1){

                }

            }
        });
    }

    // Обработка кнопок с заданиями
    private void initForm() throws Exception{
        ArrayList<Availability> list = new ArrayList<Availability>(getAllAvailabilityInList());
        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getItemId(),
                    list.get(i).getType(),
                    list.get(i).getLength(),
                    list.get(i).getHeight(),
                    list.get(i).getWigth(),
                    list.get(i).getColor(),
                    list.get(i).getConfig(),
                    list.get(i).getProvider(),
                    list.get(i).getQuantity(),
                    list.get(i).getOrderQuantity(),
            });
        };

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

    private void openNomenclature() throws Exception{
        new NomenclatureListPage().setVisible(true);
    }
}
