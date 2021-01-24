package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.IOException;

public class FlagCellRenderer extends DefaultTableCellRenderer {
    private JLabel label;
    private ImageIcon[] flags;

    public FlagCellRenderer(String countriesFileName) {
        try {
            DataReader dataReader = new DataReader(countriesFileName);
            flags = dataReader.getFlags();
            label = new JLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { ;
        label.setIcon(flags[row]);
        return label;
    }
}
