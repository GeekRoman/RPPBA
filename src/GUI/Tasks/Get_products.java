package GUI.Tasks;

import server.Availability;
import server.Cell;
import server.Nomenclature;
import server.Storage;

import static client.Client.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Get_products extends JFrame{
    private JPanel PanelGetProduct;
    private JComboBox comboBoxProductAvailability;
    private JButton buttonGet;
    private JTextField textFieldQuantity;
    private JButton buttonBack;
    private JLabel labelCell;
    private JLabel labelStorage;
    private JButton buttonInfo;
    private JLabel labelDate;

    public Get_products() throws Exception{
        super("Отгрузка продукции");
        setSize(600, 400);
        setContentPane(PanelGetProduct);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);
        buttonGet.setEnabled(false);
        initForm();
    }

    private void initForm() throws Exception{
        ArrayList<Availability> availabilities = new ArrayList<>(getProductsForAvailability());

        String product[] = new String[availabilities.size()];

        for(int i=0;i<product.length;i++){
            product[i] = "Номер: " + availabilities.get(i).getItemId() + " Кол-во: " + availabilities.get(i).getOrderQuantity();
        }

        comboBoxProductAvailability.setModel(new DefaultComboBoxModel(product));

        labelDate.setText(addDate());

        comboBoxProductAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerСomboBoxProductAvailability();
                } catch (Exception e1) {
                    System.out.println("Ошибка нажатии наличия продукта");
                }
            }
        });

        buttonGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonGet();
                }
                catch (Exception ex) {
                    System.out.println("Ошибка нажатии отгрузки");
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

    // Нажатие кнопки "Назад"
    private void addActionListenerButtonBack() throws Exception{
        setVisible(false);
    }

    // Нажатие кнопки "Отгрузить"
    private void addActionListenerButtonGet() throws Exception{
        String answerAvailability = "";
        String answerTask = "";
        String answerTransit = "";

        String ItemId = IdCharToString((String) comboBoxProductAvailability.getSelectedItem());
        String Quantity = textFieldQuantity.getText();
        String Type = "Отгрузка";
        String Storage = IdCharToString(labelStorage.getText());

        if(notStringQuantity(Quantity) == false || minusQuantity(Quantity,ItemId) == false){
            JOptionPane.showMessageDialog(null,"Некорректная запись в поле 'Количество'");
        } else {
            answerAvailability = getQuantityAvailability(ItemId,Quantity);
            answerTransit = addTransit(Storage,Type);
            answerTask = addTask(labelDate.getText());
        }

        System.out.println("Ответ наличия: " + answerAvailability +";" + " " + "Ответ перемещения: " + answerTransit + ";" + " " + "Ответ задачи: " +answerTask + ";");
        String message = "";
        if(answerAvailability.equals("Ошибка") || answerTask.equals("Ошибка") || answerTransit.equals("Ошибка")) {
            message = "Ошибка в добавлении!";
        } else if(answerAvailability.equals("Добавлены") || answerTask.equals("Добавлены") || answerTransit.equals("Добавлены")){
            message = "Данные добавлены!";
        }

        if(message.equals("")){
            JOptionPane.showMessageDialog(null,"Ошибка в добавлении!");
        } else
            JOptionPane.showMessageDialog(null,message);
    }

    // Нажатие списка продукции // Работает
    private void addActionListenerСomboBoxProductAvailability() throws Exception{
        buttonGet.setEnabled(true);

        String ItemId = IdCharToString((String) comboBoxProductAvailability.getSelectedItem());
        ArrayList storageCell = getCellAndStorage(ItemId);
        Cell cellOb = (Cell) storageCell.get(0);
        Storage storageOb = (Storage) storageCell.get(1);

        labelCell.setText("Номер: " + cellOb.getCellId() + " " + "Тип: " + cellOb.getType());

        // getStatus = Address
        labelStorage.setText("Номер: " + storageOb.getStorageId() + " " + "Адрес: " + storageOb.getStatus());
    }

    // Нажатие кнопки "Информация" // Работает
    private void addActionListenerButtonInfo() throws Exception{
        String ItemId = IdCharToString((String) comboBoxProductAvailability.getSelectedItem());
        Nomenclature nomenclature = getInfoNomenclature(ItemId);
        String message ="Номер: " + nomenclature.getItemId() + " " + "Тип: " + nomenclature.getType()+ " " + "Размеры: " + nomenclature.getLength()+ " " + nomenclature.getHeight()+ " "
                + nomenclature.getWidth()+ " " + "Конфигурация: " + nomenclature.getConfig()+ " " + nomenclature.getProvider();
        JOptionPane.showMessageDialog(null,message);
    }
}
