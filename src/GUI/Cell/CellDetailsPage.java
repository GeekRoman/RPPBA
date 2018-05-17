package GUI.Cell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class CellDetailsPage extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton enterButton;
    private JPanel detailsForm;
    private JButton exitButton;

    public CellDetailsPage(String CellId, String Lenght,
                           String Height, String Width, String Type,
                           String Status) throws Exception {
        super("Форма детализации");

        setSize(550, 300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(detailsForm);
        this.setResizable(false);
        this.setVisible(true);

        textField1.setText(CellId);
        textField2.setText(Lenght);
        textField3.setText(Height);
        textField4.setText(Width);


        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


}