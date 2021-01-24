package zad1;

import java.util.Comparator;

public class SortByNames implements Comparator<Purchase> {

    public static final String SORT_BY_NAMES = "Nazwiska";

    @Override
    public int compare(Purchase p1, Purchase p2) {
        return p1.getClient().compareTo(p2.getClient());
    }
}