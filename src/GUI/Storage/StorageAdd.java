package GUI.Storage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import  java.lang.*;

import static client.Client.*;


public class StorageAdd extends JFrame implements ActionListener{
    private JTextField idField;
    private JTextField addressField;
    private JComboBox comboBox1;
    private JPanel storageAdd;
    private JButton button1;
    private JTextField textField;


    public StorageAdd() throws Exception {
        super("Добавление склада");
        setSize(650, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(storageAdd);
        this.setResizable(true);
        this.setVisible(true);
        button1.addActionListener(this);


        String[] statusBox = {"Действующий", "Резервный"};
        for (int j = 0; j<statusBox.length; j++){
            comboBox1.addItem(statusBox[j]);
        }
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Добавить": {
                if (idField.getText().equals("") || addressField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(storageAdd, "Необходимо заполнить все поля!");
                } else if (idField.getText().length()<2)
                {
                    JOptionPane.showMessageDialog(storageAdd, "Идентификатор слишком короткий!");
                }
                else
                {
                    String storageId = this.idField.getText().trim();
                    String address = this.addressField.getText().trim();
                    String status = this.comboBox1.getSelectedItem().toString();

                    try {
                        if (addStorage(storageId,address,status).equals("false")){
                            JOptionPane.showMessageDialog(storageAdd, "Такой склад уже существует!");
                        } else {
                            JOptionPane.showMessageDialog(storageAdd, "Склад упешно добавлен!");


                            String volume  = this.textField.getText().trim();


                            String[] typeBox = {"Верхняя", "Средняя", "Нижняя"};
                            int j = 0;
                            int volume2int = Integer.parseInt(volume);
                            for (int i = 0; i < volume2int; i++){
                                String cellId = this.idField.getText().trim();
                                cellId += "-";
                                cellId += Integer.toString(i);
                                String IdStorage = this.idField.getText().trim();
                                String length = "10";
                                String height = "10";
                                String width = "10";
                                String type = typeBox[j];
                                String statusCell = "Пустая";
                                addCell(cellId,IdStorage, length, height, width, type, statusCell);
                                j++;
                                if (j>=3) j = 0;

                            }

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    this.dispose();
                    StorageForm newStorage = null;
                    try
                    {
                        newStorage = new StorageForm();
                        newStorage.setResizable(false);
                        newStorage.setVisible(true);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }


                break;
            }
        }
    }
}
