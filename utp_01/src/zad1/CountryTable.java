package zad1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CountryTable {

    private final String countriesFileName;

    public CountryTable(String countriesFileName) {
        this.countriesFileName = countriesFileName;
    }

    public JTable create() {
        JTable cTab = null;
        try {
            cTab = new JTable(new CountryTableModel(countriesFileName));
            cTab.getColumn("Population").setCellRenderer(new PopulationCellRenderer(countriesFileName));
            cTab.getColumn("Flag").setCellRenderer(new FlagCellRenderer(countriesFileName));
            cTab.getColumn("Flag").setPreferredWidth(96);
            cTab.getColumn("Flag").setMinWidth(96);
            cTab.getColumn("Flag").setMaxWidth(96);
            cTab.setRowHeight(64);
            cTab.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cTab;
    }
}
