/**
 *
 *  @author Stryszawski Emil S20607
 *
 */

package zad1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersPurchaseSortFind {

    private final List<Purchase> purchases;

    public CustomersPurchaseSortFind() {
        purchases = new ArrayList<>();
    }

    public void readFile(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fname)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(";");
                Client client = new Client(split[0], split[1]);
                Product product = new Product(split[2], Double.valueOf(split[3]));
                Purchase purchase = new Purchase(client, product, Double.valueOf(split[4]));
                purchases.add(purchase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String sortMethod) {
        System.out.println("\n" + sortMethod);
        switch (sortMethod) {
            case SortByNames.SORT_BY_NAMES :
                purchases.stream()
                        .sorted(new SortByNames())
                        .forEach(System.out::println);
                break;
            case SortByCost.SORT_BY_COST : {
                purchases.stream()
                        .sorted(new SortByCost())
                        .forEach(p -> System.out.println(p + " (koszt: " + p.getCost() + ")"));
            }
        }

    }

    public void showPurchaseFor(String id) {
        purchases.stream()
                .filter(p -> p.getClient().getId().equals(id))
                .limit(1)
                .forEach(p -> System.out.println("\nKlient " + p.getClient().getId()));
        purchases.stream()
                .filter(p -> p.getClient().getId().equals(id))
                .forEach(System.out::println);
    }
}
