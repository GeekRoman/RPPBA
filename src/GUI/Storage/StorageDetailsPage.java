package GUI.Storage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StorageDetailsPage extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton ExitButton;
    private JButton EnterButton;
    private JPanel Form;

    public StorageDetailsPage(String storageId, String Address, String Status) throws Exception {
        super("Форма детализации");

        setSize(500, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(Form);
        this.setResizable(false);
        this.setVisible(true);

        textField1.setText(storageId);
        textField2.setText(Address);
        textField3.setText(Status);

        EnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


}
