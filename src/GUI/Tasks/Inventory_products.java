package GUI.Tasks;

import server.Availability;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.*;

public class Inventory_products extends JFrame{

    private JButton buttonInventory;
    private JButton buttonInfo;
    private JButton buttonCancel;
    private JPanel PanelInventory;
    private JLabel labelQuantity;
    private JLabel labelDate;

    public Inventory_products(){
        super("Инвентаризация");
        setSize(600, 400);
        setContentPane(PanelInventory);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);
        buttonInfo.setEnabled(false);
        initForm();
    }

    private void initForm() {
        labelDate.setText(addDate());

        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonInfo();
                } catch (Exception e1) {
                    System.out.println("Ошибка нажатии Информации");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    addActionListenerButtonCancel();
                } catch (Exception e1) {
                    System.out.println("Ошибка нажатии Назад");
                }
            }
        });

        buttonInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonInventory();
                } catch (Exception e1) {
                    System.out.println("Ошибка нажатии Инветаризации");
                }
            }
        });
    }

    private void addActionListenerButtonInfo() throws Exception{
        ArrayList<Availability> availabilities = new ArrayList<>(getProductsForAvailability());
        String message [] = new String[availabilities.size()];
        for(int i=0;i<availabilities.size();i++){
            message[i] = "Номер: " + availabilities.get(i).getItemId() + " Кол-во: " + availabilities.get(i).getOrderQuantity() + " Ящик:" + availabilities.get(i).getCellId();
        }

        JOptionPane.showMessageDialog(null,message);
    }

    private void addActionListenerButtonCancel() {
        setVisible(false);
    }

    private void addActionListenerButtonInventory() throws Exception{
        buttonInfo.setEnabled(true);

        String answerTask;
        String answerTransit;

        String StorageIn = "";
        String Type = "Инвентаризация";
        String quantity = totalQuantityAvailability();

        answerTransit = addTransit(StorageIn,Type);
        answerTask = addTask(labelDate.getText());

        labelQuantity.setText(quantity);

        System.out.println("Инветаризация: " + "Ответ перемещения: " + answerTransit + ";" + " " + "Ответ задачи: " +answerTask + ";");
        String message = "";


        if(answerTask.equals("Ошибка") || answerTransit.equals("Ошибка")) {
            message = "Ошибка в добавлении!";
        } else if(answerTask.equals("Добавлены") || answerTransit.equals("Добавлены")){
            message = "Данные добавлены!";
        }

        if(message.equals("")){
            JOptionPane.showMessageDialog(null,"Ошибка в добавлении!");
        } else
            JOptionPane.showMessageDialog(null,message);
    }
}
