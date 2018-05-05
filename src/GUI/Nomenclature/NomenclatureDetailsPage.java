package GUI.Nomenclature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static client.Client.NomenclatureUpdate;
import static client.Client.addNomenclature;

public class NomenclatureDetailsPage extends JFrame{
    private JTextField idField;
    private JTextField lengthField;
    private JTextField heightField;
    private JTextField widthField;
    private JTextField providerField;
    private JTextField colorField;
    private JComboBox configComboBox;
    private JComboBox typeComboBox;
    private JButton changeButton;
    private JPanel DetailsPage;

    public NomenclatureDetailsPage(String ItemId,String Type, String Length,String Height,
                                   String Width,String Color,String Config,String Provider) throws Exception {
        super("Форма детализации");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(DetailsPage);
        this.setResizable(true);
        this.setVisible(true);

        idField.setText(ItemId);
        lengthField.setText(Length);
        heightField.setText(Height);
        widthField.setText(Width);
        colorField.setText(Color);
        providerField.setText(Provider);

        String[] typeBox = {"Ручки", "Карандаши"};
        String[] configBox = {"10x2.3", "10x2.5"};
        for (int j = 0; j < typeBox.length; j++){
            typeComboBox.addItem(typeBox[j]);
        }

        for (int j = 0; j < configBox.length; j++){
            configComboBox.addItem(configBox[j]);
        }

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().equals("") || lengthField.getText().equals("") ||
                        heightField.getText().equals("") || widthField.getText().equals("") ||
                        colorField.getText().equals("") || providerField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(DetailsPage, "Необходимо заполнить все поля!");
                } else if (idField.getText().length()<2)
                {
                    JOptionPane.showMessageDialog(DetailsPage, "Идентификатор слишком короткий!");
                }
                else
                {
                    String itemId = idField.getText().trim();
                    String length = lengthField.getText().trim();
                    String height = heightField.getText().trim();
                    String width = widthField.getText().trim();
                    String color = colorField.getText().trim();
                    String provider = providerField.getText().trim();
                    String mytype = typeComboBox.getSelectedItem().toString();
                    String config = configComboBox.getSelectedItem().toString();

                    try {
                        if (NomenclatureUpdate(itemId, mytype, length,
                                height, width, color, config, provider).equals("false")){
                            JOptionPane.showMessageDialog(DetailsPage, "Номенклатура с таким идентификатором" +
                                    " уже существует!");
                        } else {
                            JOptionPane.showMessageDialog(DetailsPage, "Номенклатура упешно изменена!");

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    dispose();

                }
            }
        });
    }
}
