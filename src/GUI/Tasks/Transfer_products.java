package GUI.Tasks;

import server.Availability;
import server.Cell;
import server.Nomenclature;
import server.Storage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.*;

public class Transfer_products extends JFrame{

    private JFormattedTextField textFieldQuantity;
    private JButton buttonTransfer;
    private JButton buttonBack;
    private JComboBox comboBoxNameProduct;
    private JComboBox comboBoxStorageIn;
    private JPanel panelTransferProduct;
    private JComboBox comboBoxCellIn;
    private JButton buttonInfo;
    private JTextField textFieldDate;
    private JLabel labelStorageOut;
    private JLabel labelCellOut;
    private JLabel labelDate;

    public Transfer_products() throws Exception{
        super("Отгрузка продукции");
        setSize(700, 500);
        setContentPane(panelTransferProduct);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);
        buttonTransfer.setEnabled(false);
        initForm();
    }

    private void initForm() throws Exception{
        ArrayList<Availability> availabilities = new ArrayList<>(getProductsForAvailability());
        ArrayList<Storage> storages = new ArrayList<>(getStorageComboBoxStorage());

        String storage[] = new String[storages.size()];
        String product[] = new String[availabilities.size()];

        for(int i=0;i<product.length;i++){
            product[i] = "Номер: " + availabilities.get(i).getItemId() + "Ящик: " + availabilities.get(i).getCellId();
        }

        for(int i=0;i<storage.length;i++){
            storage[i] = "Номер: " + storages.get(i).getStorageId() + " Тип: " + storages.get(i).getStatus();
        }

        comboBoxNameProduct.setModel(new DefaultComboBoxModel(product));
        comboBoxStorageIn.setModel(new DefaultComboBoxModel(storage));

        labelDate.setText(addDate());

        comboBoxNameProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerComboBoxNameProduct();
                } catch (Exception e1) {

                }
            }
        });

        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonInfo();
                } catch (Exception e1){

                }
            }
        });

        buttonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    addActionListenerButtonTransfer();
                } catch (Exception e1) {

                }
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    addActionListenerButtonBack();
                } catch (Exception e1){

                }
            }
        });

        comboBoxStorageIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    addActionListenerComboBoxStorageIn();
                } catch (Exception e1) {

                }
            }
        });
    }

    // Нажатие кнопки "Информация" // Получение информации о продукте
    private void addActionListenerButtonInfo() throws Exception{
        String ItemId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());
        Nomenclature nomenclature = getInfoNomenclature(ItemId);
        String message ="Номер: " + nomenclature.getItemId() + " " + "Тип: " + nomenclature.getType()+ " " + "Размеры: " + nomenclature.getLength()+ " " + nomenclature.getHeight()+ " "
                + nomenclature.getWidth()+ " " + "Конфигурация: " + nomenclature.getConfig()+ " " + nomenclature.getProvider();
        JOptionPane.showMessageDialog(null,message);
    }

    // Нажатие кнопки "Переместить" // Перемещение продукта в другой ящмик
    private void addActionListenerButtonTransfer() throws Exception{

//        System.out.println("Ответ наличия: " + answerAvailability +";" + " " + "Ответ перемещения: " + answerTransit + ";" + " " + "Ответ задачи: " +answerTask + ";");
    }

    // Нажатие по комбинированному списку продуктов
    private void addActionListenerComboBoxNameProduct() throws Exception{
        buttonTransfer.setEnabled(true);

        String ItemId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());
        ArrayList storageCell = getCellAndStorage(ItemId);
        Cell cellOb = (Cell) storageCell.get(0);
        Storage storageOb = (Storage) storageCell.get(1);

        labelStorageOut.setText("Адрес: " + storageOb.getAddress());
        labelCellOut.setText("Номер: " + cellOb.getCellId() + "Тип: " + cellOb.getType());
    }

    private void addActionListenerComboBoxStorageIn() throws Exception{
        String nomenclatureId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());
        String storageFirstId = IdCharToString((String) comboBoxStorageIn.getSelectedItem());
        ArrayList<Cell> cells = new ArrayList<>(getCellComboBoxCell(storageFirstId,nomenclatureId));
        String cell[] = new String[cells.size()];

        for(int i=0;i<cell.length;i++){
            cell[i] = "Номер: " + cells.get(i).getCellId() + " Тип: " + cells.get(i).getType()+ " Статус: " + cells.get(i).getStatus();
        }

        comboBoxCellIn.setModel(new DefaultComboBoxModel(cell));
    }

    // Нажатие кнопки "Назад"
    private void addActionListenerButtonBack(){
        setVisible(false);
    }

    // Получение номера из строки // Работает
    private String IdCharToString(String Id){
        char first = Id.charAt(7);
        Id = String.valueOf(first);
        return Id;
    }

    // Получение текущей даты // Работает
    private String addDate(){
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(dt);
    }

    // Проверка на ввод кол-ва // Работает
    private boolean notStringQuantity(String Quantity){
        try {
            Integer.parseInt(Quantity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
