package GUI.Tasks;

import javax.swing.*;

public class Transfer_products extends JFrame{


    private JPanel PanelGetProduct;
    private JFormattedTextField textFieldQuantity;
    private JButton buttonTransfer;
    private JButton buttonBack;
    private JComboBox comboBoxNameProduct;
    private JComboBox comboBoxStorageIn;
    private JPanel panelTransferProduct;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;

    public Transfer_products() throws Exception{
        super("Отгрузка продукции");
        setSize(600, 400);
        setContentPane(panelTransferProduct);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);

        initForm();

    }

    private void initForm(){

    }
}
