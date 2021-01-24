package zad1;

import java.util.Comparator;

public class SortByCost implements Comparator<Purchase> {

    public static final String SORT_BY_COST = "Koszty";

    @Override
    public int compare(Purchase p1, Purchase p2) {
        return p1.compareTo(p2);
    }
}
