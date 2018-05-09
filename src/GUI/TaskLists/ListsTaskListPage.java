package GUI.TaskLists;

import server.Storage;
import server.TaskList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.getAllStorageInList;
import static client.Client.getAllTaskLiatInList;

public class ListsTaskListPage extends JFrame{
    private JTable table1;
    private JButton exitButton;
    private JPanel ListsTaskListPage;
    private String []columnsHeader = {"Идентификатор", "Название", "Статус"};
    DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };


    public ListsTaskListPage() throws Exception {
        super("Списки заданий");
        setSize(900, 600);
        setContentPane(ListsTaskListPage);
        setLocationRelativeTo(null);
        this.setVisible(true);
        setResizable(true);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        initForm();
    }
    public void initForm() throws Exception{
        ArrayList<TaskList> list = new ArrayList<TaskList>(getAllTaskLiatInList());
        System.out.println(list.get(0).getName());

        for (String col: columnsHeader){
            tableModel.addColumn(col);
        }
        table1.setModel(tableModel);

        for (int i = 0; i < list.size(); i++) {
            tableModel.addRow(new String[]{
                    list.get(i).getTaskListId(),
                    list.get(i).getName(),
                    list.get(i).getStatus(),
            });
        };

    }
}
