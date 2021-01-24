/**
 *
 *  @author Stryszawski Emil S20607
 *
 */

package zad1;

public class Purchase implements Comparable<Purchase> {

    private Client client;
    private Product product;
    private Double quantity;
    private Double cost;

    public Purchase() {

    }

    public Purchase(Client client, Product product, Double quantity) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.cost = quantity * product.getPrice();
    }

    @Override
    public int compareTo(Purchase p) {
        int compare = -(this.cost.compareTo(p.cost));
        return compare == 0 ? this.client.getId().compareTo(p.client.getId()) : compare;
    }

    @Override
    public String toString() {
        return client + ";" + product + ";" + quantity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
