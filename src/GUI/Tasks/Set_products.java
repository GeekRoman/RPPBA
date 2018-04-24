package GUI.Tasks;

import server.Cell;
import server.Nomenclature;
import server.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static client.Client.*;

public class Set_products extends JFrame{
    private JTextField textFieldQuantity;
    private JComboBox comboBoxStorage;
    private JComboBox comboBoxCell;
    private JTextField textFieldDate;
    private JButton buttonBack;
    private JButton buttonAdd;
    private JComboBox comboBoxNameProduct;
    private JPanel PanelSetProduct;
    private JLabel labelAvailability;
    private JButton buttonInfo;

    public Set_products() throws Exception{
        super("Получение продукции");
        setSize(600, 400);
        setContentPane(PanelSetProduct);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);
        buttonAdd.setEnabled(false);
        labelAvailability.setVisible(false);
        initForm();
    }

    private void initForm() throws Exception{
        ArrayList<Nomenclature> nameProduct = new ArrayList<Nomenclature>(getNomenclatureComboBoxNameProduct());
        ArrayList<Storage> storages = new ArrayList<>(getStorageComboBoxStorage());

        String storage[] = new String[storages.size()];
        String product[] = new String[nameProduct.size()];

        for(int i=0;i<product.length;i++){
            product[i] = "Номер: " + nameProduct.get(i).getItemId() + " Тип: " + nameProduct.get(i).getType();
        }

        for(int i=0;i<storage.length;i++){
            storage[i] = storages.get(i).getStorageId() + " " + storages.get(i).getStatus();
        }

        comboBoxStorage.setModel(new DefaultComboBoxModel(storage));
        comboBoxNameProduct.setModel(new DefaultComboBoxModel(product));

        textFieldDate.setText(addDate());
        textFieldDate.setEditable(false);

        comboBoxNameProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerComboBoxNameProduct();
                } catch (Exception e1) {
                    System.out.println("Ошибка нажатии в списке продукции");
                }
            }
        });

        comboBoxStorage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerComboBoxStorage();
                }
                catch (Exception ex) {
                    System.out.println("Ошибка нажатии в списке складов");
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonAdd();
                }
                catch (Exception ex) {
                    System.out.println("Ошибка нажатии добавлении");
                }
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonBack();
                }
                catch (Exception ex) {
                    System.out.println("Ошибка нажатии закрытии окна");
                }
            }
        });

        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonInfo();
                } catch (Exception e1){
                    System.out.println("Ошибка нажатии закрытии окна");
                }
            }
        });
    }

    // Список ящиков в зависимости от выбора склада
    private void addActionListenerComboBoxStorage() throws Exception{
        String itemId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());
        String storage = (String) comboBoxStorage.getSelectedItem();
        char first = storage.charAt(0);
        String firsrId = String.valueOf(first);
        ArrayList<Cell> cells = new ArrayList<>(getCellComboBoxCell(firsrId,itemId));
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

    // Нажатие кнопки "Информация"
    private void addActionListenerButtonInfo() throws Exception{
        String ItemId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());
        Nomenclature nomenclature = getInfoNomenclature(ItemId);
        String message = nomenclature.getItemId() + " " + nomenclature.getType()+ " " + nomenclature.getLength()+ " " + nomenclature.getHeight()+ " "
                + nomenclature.getWidth()+ " " + nomenclature.getConfig()+ " " + nomenclature.getProvider();
        JOptionPane.showMessageDialog(null,message);
    }

    // Нажатие кнопки "Добавить"
    private void addActionListenerButtonAdd() throws Exception{
        String answerAvailability = "";
        String answerTask = "";
        String answerTransit = "";
        String Cell = "";
        String ItemId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());

        if(comboBoxCell.isVisible() == false) {
            Cell = "";
        } else {
            Cell = IdCharToString((String) comboBoxCell.getSelectedItem());
        }

        String Quantity = textFieldQuantity.getText();

        // Для log_task,log_transit
        String Storage = IdCharToString((String) comboBoxStorage.getSelectedItem());
        String Date = textFieldDate.getText();
        String Type = "Получение";

        if(notStringQuantity(Quantity) == false){
            JOptionPane.showMessageDialog(null,"Некорректная запись в поле 'Количество'");
        } else {
            answerAvailability = addItemAvailability(ItemId,Cell,Quantity);
            answerTransit = addTransit(Storage,Type);
            answerTask = addTask(Date);
        }

        // 1 transit 2 task
        System.out.println("Ответ наличия: " + answerAvailability +";" + " " + "Ответ перемещения: " + answerTransit + ";" + " " + "Ответ задачи: " +answerTask + ";");
        String message = "";
        if(answerAvailability.equals("Ошибка") || answerTask.equals("Ошибка") || answerTransit.equals("Ошибка")) {
            message = "Ошибка в добавлении!";
        } else {
            message = "Данные добавлены!";
        }

        JOptionPane.showMessageDialog(null,message);
    }

    // Нажатие списка продукции
    private void addActionListenerComboBoxNameProduct() throws Exception{
        buttonAdd.setEnabled(true);

        String ItemId = IdCharToString((String) comboBoxNameProduct.getSelectedItem());
        String status = getComboBoxNameProduct(ItemId);

        if(status.equals("Есть")){
            comboBoxStorage.setVisible(false);
            comboBoxCell.setVisible(false);
            labelAvailability.setVisible(true);
        } else {
            comboBoxCell.setVisible(true);
            comboBoxStorage.setVisible(true);
            labelAvailability.setVisible(false);
        }
    }

    // Получение первого элемента в строке
    private String IdCharToString(String Id){
        char first = Id.charAt(7);
        Id = String.valueOf(first);
        return Id;
    }

    // Получение текущей даты
    private String addDate(){
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(dt);
    }

    // Проверка ввода кол-ва
    private boolean notStringQuantity(String Quantity){
        try {
            Integer.parseInt(Quantity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
