package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JFrame implements ActionListener {
    private JTable table1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JButton AddButton;
    private JButton ExitButton;
    private JComboBox comboBox3;
    private JButton DeleteButton;
    private JPanel CellForm;

    public Cell (){
        super();
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(CellForm);
        this.setResizable(false);
        this.setVisible(true);
        DeleteButton.addActionListener(this);
        AddButton.addActionListener(this);
        ExitButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(), answer;

        switch(str){
            case "Добавить": {
                break;
            }

            case "Удалить":{
                break;

            }

            case "Назад":{
                this.dispose();
                StorageForm storageform = null;
                try {
                    storageform = new StorageForm();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }
}
