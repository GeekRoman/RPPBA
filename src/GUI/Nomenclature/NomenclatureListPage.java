package GUI.Nomenclature;

import server.Nomenclature;
import server.Storage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.delNomenclature;
import static client.Client.getAllNomenclatureInList;
import static client.Client.getAllStorageInList;

public class NomenclatureListPage extends JFrame {
    private JTable table1;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel formListPage;

    private String []columnsHeader = {"ID", "Тип", "Длина", "Высота",
            "Ширина", "Цвет", "Конфигурация", "Постовщик"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };


    public NomenclatureListPage() throws Exception {
        super("Номенклатура");
        setSize(900, 600);
        setContentPane(formListPage);
        setLocationRelativeTo(null);
        this.setVisible(true);
        setResizable(true);
        initForm();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NomenclatureAdd addnomenclature= null;
                try {
                    addnomenclature = new NomenclatureAdd();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void initForm() throws Exception{
        ArrayList<Nomenclature> list = new ArrayList<Nomenclature>(getAllNomenclatureInList());

        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getItemId(),
                    list.get(i).getType(),
                    list.get(i).getLength(),
                    list.get(i).getHeight(),
                    list.get(i).getWidth(),
                    list.get(i).getColor(),
                    list.get(i).getConfig(),
                    list.get(i).getProvider()
            });
        };

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table1.getSelectedRow();
                String value = table1.getModel().getValueAt(row, column).toString();
                try {
                    if (delNomenclature(value).equals("true")) {
                        JOptionPane.showMessageDialog(formListPage, "Номенклатура " + value + " удалена!");
                        DefaultTableModel tableModel = new DefaultTableModel() {
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return false;
                            }
                        };
                        ArrayList<Nomenclature> list = new ArrayList<Nomenclature>(getAllNomenclatureInList());

                        for (String col: columnsHeader){
                            tableModel.addColumn(col);
                        }
                        table1.setModel(tableModel);

                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new String[]{
                                    list.get(i).getItemId(),
                                    list.get(i).getType(),
                                    list.get(i).getLength(),
                                    list.get(i).getHeight(),
                                    list.get(i).getWidth(),
                                    list.get(i).getColor(),
                                    list.get(i).getConfig(),
                                    list.get(i).getProvider()
                            });
                        };


                    } else {
                        JOptionPane.showMessageDialog(formListPage, "Ошибка при удалении!");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}
