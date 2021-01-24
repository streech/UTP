package zad1;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;

public class DataReader {

    private final String path;
    private final int rows;

    public DataReader(String path) throws IOException {
        this.path = path;
        rows = countRow()-1;
    }

    public String[] getColumnNames() throws IOException {
        String[] columnName = new String[5];
        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        if ((line = bufferedReader.readLine()) != null) {
            columnName = line.split("\\t+");
        }
        bufferedReader.close();
        return columnName;
    }

    public int countRow() throws IOException {
        String line;
        int count = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        while((line = bufferedReader.readLine()) != null) {
            count++;
        }
        return count;
    }

    public LocalDateTime[] getDates() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        LocalDateTime[] dates = new LocalDateTime[rows];
        int i = 0;
        bufferedReader.readLine(); // skip first line (headers)
        String line;
        while((line = bufferedReader.readLine()) != null) {
            // 17.10.2020 12:00:00

            String date = line.split("\\t+")[4];
            int day = Integer.parseInt(date.split("\\.+")[0]);
            int month = Integer.parseInt(date.split("\\.+")[1]);
            String year = date.split("\\.+")[2];
            int year2 = Integer.parseInt(year.split("\\s+")[0]);
            year = year.substring(5);
            System.out.println(year);
            int hour = Integer.parseInt(year.split(":+")[0]);
            System.out.println(hour);
            int minute = Integer.parseInt(year.split(":+")[1]);
            int second = Integer.parseInt(year.split(":+")[2]);
            dates[i] = LocalDateTime.of(year2, month, day, hour, minute, second);
            i++;
        }
        bufferedReader.close();
        return dates;
    }

    public ImageIcon[] getFlags() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        ImageIcon[] flags = new ImageIcon[rows];
        int i = 0;
        bufferedReader.readLine(); // skip first line (headers)
        String line;
        while((line = bufferedReader.readLine()) != null) {
            flags[i] = new ImageIcon(new ImageIcon(line.split("\\t+")[3]).getImage());
            i++;
        }
        bufferedReader.close();
        return flags;
    }

    public int[] getPopulations() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        int[] populations = new int[rows];
        int i = 0;
        bufferedReader.readLine(); // skip first line (headers)
        String line;
        while((line = bufferedReader.readLine()) != null) {
            populations[i] = Integer.parseInt(line.split("\\t+")[2]);
            i++;
        }
        bufferedReader.close();
        return populations;
    }

    public String[] getCapitals() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        String[] capitals = new String[rows];
        int i = 0;
        bufferedReader.readLine(); // skip first line (headers)
        String line;
        while((line = bufferedReader.readLine()) != null) {
            capitals[i] = line.split("\\t+")[1];
            i++;
        }
        bufferedReader.close();
        return capitals;
    }

    public String[] getNames() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        String[] names = new String[rows];
        int i = 0;
        bufferedReader.readLine(); // skip first line (headers)
        String line;
        while((line = bufferedReader.readLine()) != null) {
            names[i] = line.split("\\t+")[0];
            i++;
        }
        bufferedReader.close();
        return names;
    }
}
