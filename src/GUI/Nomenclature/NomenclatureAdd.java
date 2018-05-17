package GUI.Nomenclature;

import server.Nomenclature;
import static client.Client.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class NomenclatureAdd extends JFrame {
    private JButton addButton;
    private JTextField idField;
    private JComboBox typeComboBox;
    private JComboBox configComboBox;
    private JPanel addForm;
    private JTextField lengthField;
    private JTextField heightField;
    private JTextField widthField;
    private JTextField colorField;
    private JTextField providerField;


    public NomenclatureAdd() throws Exception {
        super("Добавление склада");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(addForm);
        this.setResizable(true);
        this.setVisible(true);

        String[] typeBox = {"Ручки", "Карандаши"};
        String[] configBoxForPen = {"Пластмассовые","Алюминиевые", "Латунные"};
        String[] configBoxForRencil = {"Цанговые","Механические"};

        for (int j = 0; j < typeBox.length; j++){
            typeComboBox.addItem(typeBox[j]);
        }



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().equals("") || lengthField.getText().equals("") ||
                        heightField.getText().equals("") || widthField.getText().equals("") ||
                        colorField.getText().equals("") || providerField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(addForm, "Необходимо заполнить все поля!");
                } else if (idField.getText().length()<2)
                {
                    JOptionPane.showMessageDialog(addForm, "Идентификатор слишком короткий!");
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
                        if (addNomenclature(itemId, mytype, length,
                                height, width, color, config, provider).equals("false")){
                            JOptionPane.showMessageDialog(addForm, "Номенклатура с таким идентификатором" +
                                    " уже существует!");
                        } else {
                            JOptionPane.showMessageDialog(addForm, "Номенклатура упешно добавлена!");

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                    NomenclatureListPage newNnomenclature = null;
                    try
                    {
                        newNnomenclature = new NomenclatureListPage();
                        newNnomenclature.setResizable(false);
                        newNnomenclature.setVisible(true);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = typeComboBox.getSelectedIndex();
                configComboBox.removeAllItems();
                if (selectedIndex == 0)
                {
                    for (int j = 0; j < configBoxForPen.length; j++){
                        configComboBox.addItem(configBoxForPen[j]);
                    }
                } else
                {
                    for (int j = 0; j < configBoxForRencil.length; j++){
                        configComboBox.addItem(configBoxForRencil[j]);
                    }
                }
            }
        });
    }
    }
