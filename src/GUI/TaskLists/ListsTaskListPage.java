package GUI.TaskLists;


import server.TaskList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static client.Client.*;

public class ListsTaskListPage extends JFrame{
    private JTable table1;
    private JButton exitButton;
    private JPanel ListsTaskListPage;

    private String []columnsHeader = {"Идентификатор", "Название", "Статус"," "};
    DefaultTableModel tableModel2 = new DefaultTableModel() {
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

        ArrayList<TaskList> mylist = new ArrayList<>(getAllTaskListInList());

        for (int i = 0; i < mylist.size(); i++) {
            System.out.println(mylist.get(i).getTaskListId());

        };
        for (String col: columnsHeader){
            tableModel2.addColumn(col);
        }
        table1.setModel(tableModel2);

        for (int i = 0; i < mylist.size(); i++) {
            tableModel2.addRow(new String[]{
                    mylist.get(i).getTaskListId(),
                    mylist.get(i).getName(),
                    mylist.get(i).getStatus(),
            });
        };

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

}
