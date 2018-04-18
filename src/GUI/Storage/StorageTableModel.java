package GUI.Storage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StorageTableModel extends AbstractTableModel{

    private int columnCount = 3;
    private ArrayList<String []>dataArrayList;
    public StorageTableModel(){
        dataArrayList = new ArrayList<String[]>();
        for( int i = 0; i< dataArrayList.size(); i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }
    @Override
    public int getRowCount(){
        return dataArrayList.size();
    }
    @Override
    public int getColumnCount(){
        return columnCount;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        String []rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    @Override
    public String getColumnName (int columnIndex){
        switch (columnIndex){
            case 0: return "Id";
            case 1: return "Адрес";
            case 2: return "Статус";
        }
        return "";
    }
    public void addData(String []row){
        String []rowTable =  new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

}
