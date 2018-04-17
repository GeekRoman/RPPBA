package GUI.Tasks;

import GUI.Product_Information_Management;
import server.Cell;
import server.Nomenclature;
import server.Storage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.*;

public class Set_products extends JFrame{
    private JTextField textFieldQuantity;
    private JComboBox comboBoxStorage;
    private JComboBox comboBoxCell;
    private JTextField textFieldDate;
    private JButton ButtonBack;
    private JButton ButtonAdd;
    private JComboBox comboBoxNameProduct;
    private JPanel PanelSetProduct;

    public Set_products() throws Exception{
        super("Получение продукции");
        setSize(600, 400);
        setContentPane(PanelSetProduct);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);

        initForm();
    }

    private void initForm() throws Exception{
        ArrayList<Nomenclature> nameProduct = new ArrayList<Nomenclature>(getNomenclatureComboBoxNameProduct());
        ArrayList<Storage> storages = new ArrayList<>(getStorageComboBoxStorage());

        String storage[] = new String[storages.size()];
        String product[] = new String[nameProduct.size()];

        for(int i=0;i<storage.length;i++){
            product[i] = nameProduct.get(i).getItemId() + " " + nameProduct.get(i).getName();
        }

        for(int i=0;i<product.length;i++){
            storage[i] = storages.get(i).getStorageId() + " " + storages.get(i).getStatus();
        }

        comboBoxStorage.setModel(new DefaultComboBoxModel(storage));
        comboBoxNameProduct.setModel(new DefaultComboBoxModel(product));


        textFieldDate.setText(addDate());

        comboBoxStorage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenercomboBoxStorage();
                }
                catch (Exception ex) {

                }
            }
        });

        ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonAdd();
                }
                catch (Exception ex) {

                }
            }
        });

        ButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonBack();
                }
                catch (Exception ex) {

                }
            }
        });
    }

    // Список ящиков в зависимости от выбора склада
    private void addActionListenercomboBoxStorage() throws Exception{
        String storage = "";
        storage = (String) comboBoxStorage.getSelectedItem();
        char first = storage.charAt(0);
        String firsrId = String.valueOf(first);
        ArrayList<Cell> cells = new ArrayList<>(getCellComboBoxCell(firsrId));
        String cell[] = new String[cells.size()];

        for(int i=0;i<cell.length;i++){
            cell[i] = cells.get(i).getCellId() + " " + cells.get(i).getType()+ " " + cells.get(i).getStatus();
        }

        comboBoxCell.setModel(new DefaultComboBoxModel(cell));
    }

    // Нажатие кнопки "Назад"
    private void addActionListenerButtonBack() throws Exception{
        setVisible(false);
    }

    // Нажатие кнопки "Добавить"
    private void addActionListenerButtonAdd() throws Exception{
        // Для log_availability
        String ItemId = IdcharToString((String) comboBoxNameProduct.getSelectedItem());
        String Cell = IdcharToString((String) comboBoxCell.getSelectedItem());
        String Quantity = textFieldQuantity.getText();
        String answer = addItemAvailability(ItemId,Cell,Quantity);

        String Store = IdcharToString((String) comboBoxStorage.getSelectedItem());
        String Date = textFieldDate.getText();

        String message = "";
        if(answer == "Ошибка") {
            message = "Ошибка в добавлении!";
        } else {
            message = "Данные добавлены!";
        }

        JOptionPane.showMessageDialog(null,message);

    }

    // Получение первого элемента
    private String IdcharToString(String Id){
        char first = Id.charAt(0);
        Id = String.valueOf(first);
        return Id;
    }

    // Получение текущей даты
    private String addDate(){
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd");

        String currentTime = sdf.format(dt);
        return currentTime;
    }

}
