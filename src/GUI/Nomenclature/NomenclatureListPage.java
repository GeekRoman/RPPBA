package GUI.Nomenclature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NomenclatureListPage extends JFrame {
    private JTable table1;
    private JButton exitButton;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel formListPage;


    public NomenclatureListPage() throws Exception {
        super("Номенклатура");
        setSize(900, 600);
        setContentPane(formListPage);
        setLocationRelativeTo(null);
        setResizable(true);
        initForm();
    }

    public void initForm() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
