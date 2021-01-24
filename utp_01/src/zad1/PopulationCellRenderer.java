package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.IOException;

public class PopulationCellRenderer extends DefaultTableCellRenderer {
    private int[] populations;
    private JFormattedTextField intField;

    public PopulationCellRenderer(String countriesFileName) {
        try {
            DataReader dataReader = new DataReader(countriesFileName);
            populations = dataReader.getPopulations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        intField = new JFormattedTextField();
        intField.setValue(populations[row]);
            if (isSelected) {
                intField.setValue(value);
                if (value instanceof Integer) {
                    populations[row] = (int) value;
                }
            }
            if (populations[row] > 20000) {
                intField.setForeground(Color.RED);
            }
            return intField;
    }
}
