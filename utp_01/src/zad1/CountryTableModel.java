package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.time.LocalDateTime;

public class CountryTableModel extends AbstractTableModel {
    private static String[] columnNames;
    private static String[] names;
    private static String[] capitals;
    private static int[] populations;
    private static ImageIcon[] flags;
    private static LocalDateTime[] modificationDates;

    public CountryTableModel(String path) throws IOException {
        DataReader dataReader = new DataReader(path);

        columnNames = dataReader.getColumnNames();
        names = dataReader.getNames();
        capitals = dataReader.getCapitals();
        populations = dataReader.getPopulations();
        flags = dataReader.getFlags();
        modificationDates = dataReader.getDates();
        for (LocalDateTime modificationDate : modificationDates) {
            System.out.println(modificationDate);
        }
    }

    @Override
    public int getRowCount() {
        return names.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;

        switch (columnIndex) {
            case 0: o = names[rowIndex]; break;
            case 1: o = capitals[rowIndex]; break;
            case 2: o = populations[rowIndex]; break;
            case 3: o = flags[rowIndex]; break;
            case 4: o = modificationDates[rowIndex]; break;
        }
        return o;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        populations[rowIndex] = (Integer) value;
        modificationDates[rowIndex] = LocalDateTime.now();
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }
}
