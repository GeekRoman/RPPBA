package GUI.Tasks;

import GUI.Product_Information_Management;
import server.Cell;
import server.Nomenclature;
import server.Storage;
import static client.Client.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Get_products extends JFrame{
    private JPanel PanelGetProduct;
    private JComboBox comboBoxNameProduct;
    private JComboBox comboBoxStorage;
    private JButton ButtonAdd;
    private JTextField textFieldQuantity;
    private JButton ButtonBack;
    private JTextField textFieldDate;
    private JComboBox comboBoxCell;

    public Get_products() throws Exception{
        super("Отгрузка продукции");
        setSize(600, 400);
        setContentPane(PanelGetProduct);
        setLocationRelativeTo(null); // Появляется по центру экрана
        setResizable(true);
        this.setVisible(true);
        initForm();
    }

    public void initForm() throws Exception{
        ArrayList<Nomenclature> nameProduct = new ArrayList<Nomenclature>(getNomenclatureComboBoxNameProduct());
        ArrayList<Storage> storages = new ArrayList<>(getStorageComboBoxStorage());

        String storage[] = new String[storages.size()];
        String product[] = new String[nameProduct.size()];

        for(int i=0;i<storage.length;i++){
            product[i] = nameProduct.get(i).getItemId() + " " + nameProduct.get(i).getName();
        }

        for(int i=0;i<product.length;i++){
            storage[i] = storages.get(i).getStorageId() + " " + storages.get(i).getStatus();
        }

        comboBoxStorage.setModel(new DefaultComboBoxModel(storage));
        comboBoxNameProduct.setModel(new DefaultComboBoxModel(product));

        textFieldDate.setText(addDate());
        comboBoxStorage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenercomboBoxStorage();
                }
                catch (Exception ex) {

                }
            }
        });

        ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonAdd();
                }
                catch (Exception ex) {

                }
            }
        });

        ButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActionListenerButtonBack();
                }
                catch (Exception ex) {

                }
            }
        });
    }

    private void addActionListenercomboBoxStorage() throws Exception{
        String storage = "";
        storage = (String) comboBoxStorage.getSelectedItem();
        char first = storage.charAt(0);
        String firsrId = String.valueOf(first);
        ArrayList<Cell> cells = new ArrayList<>(getCellComboBoxCell(firsrId));
        String cell[] = new String[cells.size()];

        for(int i=0;i<cell.length;i++){
            cell[i] = cells.get(i).getCellId() + " " + cells.get(i).getType()+ " " + cells.get(i).getStatus();
        }

        comboBoxCell.setModel(new DefaultComboBoxModel(cell));
    }

    private void addActionListenerButtonBack() throws Exception{
        setVisible(false);
    }


    private String addDate(){
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd");

        String currentTime = sdf.format(dt);
        return currentTime;
    }
    private void addActionListenerButtonAdd() throws Exception{
        String nameProduct ="";
        String Quantity = "";
        String Storage ="";
        String Cell="";


    }
}
