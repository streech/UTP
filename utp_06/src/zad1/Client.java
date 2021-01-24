package zad1;

import java.text.Collator;

public class Client implements Comparable<Client> {

    private String id;
    private String name;

    public Client() {

    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Client c) {
        int compare = Collator.getInstance().compare(this.name, c.name);
        return compare == 0 ? this.id.compareTo(c.id) : compare;
    }

    @Override
    public String toString() {
        return id + ";" + name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
