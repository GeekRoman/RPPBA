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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;


    public StorageAdd() throws Exception {
        super("Добавление склада");
        setSize(650, 450);
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
                            String along  = this.textField1.getText().trim();
                            String across  = this.textField2.getText().trim();
                            String levels  = this.textField3.getText().trim();
                            String lengthP  = this.textField4.getText().trim();

                            String[] typeBox = {"Верхняя", "Средняя", "Нижняя"};
                            int q = 0, w =0;
                            int volume2int = Integer.parseInt(volume);
                            int along2int = Integer.parseInt(along);
                            int across2int = Integer.parseInt(across);
                            int levels2int = Integer.parseInt(levels);
                            int length2int = Integer.parseInt(lengthP);

                            int sectionCount, cellCountInSection, cellAll;

                            if (along2int == across2int){
                                sectionCount = (across2int + 1) * (across2int + 1);
                            } else  sectionCount = along2int *across2int * 2;

                            int lhw = volume2int/3;
                            cellCountInSection = (length2int /lhw)* levels2int;

                            cellAll = sectionCount*cellCountInSection;
                            String cellId = "";


                            for(int i = 0; i < cellAll; i++){
                                cellId = this.idField.getText().trim();

                                cellId += "-";
                                cellId += (i);
                                cellId += "-";


                                if (i > cellCountInSection) {
                                    w++;
                                }
                                cellId += (w);

                                String IdStorage = this.idField.getText().trim();
                                String length = Integer.toString(lhw);
                                String height = Integer.toString(lhw);
                                String width = Integer.toString(lhw);
                                String type = typeBox[q];
                                String statusCell = "Пустая";
                                addCell(cellId,IdStorage, length, height, width, type, statusCell);
                                q++;
                                if (q>=3) q = 0;




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
