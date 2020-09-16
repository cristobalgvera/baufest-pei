package cl.crisgvera.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {

    private boolean promotionEnabled;
    private Customer customer;
    private LocalDate localDate;
    private Map<Product, Integer> products = new HashMap<>();

    public ShoppingCart() {
    }

    public int getTotal() {
        AtomicInteger totalCost = new AtomicInteger();
        products.forEach((product, quantity) -> totalCost.addAndGet(product.getCost() * quantity));
        return totalCost.get();
    }

    public int getTotalWithPromotion() {
        AtomicInteger totalCost = new AtomicInteger();
        products.forEach((product, quantity) -> {
            int totalByProduct = product.getCost() * quantity;

            if (quantity > 10) totalByProduct = (int) (totalByProduct * 0.9);
            else totalByProduct = (int) (totalByProduct * 0.95);

            totalCost.addAndGet(totalByProduct);
        });

        return totalCost.get();
    }

    public int getTotalByProduct(Product product) {
        return product.getCost() * products.get(product);
    }

    public boolean buy(Customer customer, Store store) {
        if (customer.canBuyShoppingCart(this)) {
            localDate = LocalDate.now();
            this.customer = customer;
            customer.addShoppingCart(this);
            store.addShoppingCart(this);
            if (store.getCustomers().indexOf(customer) == -1)
                store.addCustomer(customer);
            return true;
        } else {
            System.out.println("Cliente " + customer + " no puede comprar el carrito " + this);
            return false;
        }
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void addProduct(Product product, int quantity) {
        products.putIfAbsent(product, quantity);
        products.computeIfPresent(product, (k, v) -> v + quantity);
    }

    public void deleteProduct(Product product) {
        products.computeIfPresent(product, (k, v) -> products.remove(k));
    }

    public boolean isPromotionEnabled() {
        return promotionEnabled;
    }

    public void setPromotionEnabled(boolean promotionEnabled) {
        this.promotionEnabled = promotionEnabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "promotionEnabled=" + promotionEnabled +
                ", customer=" + customer +
                ", localDate=" + localDate +
                ", total=" + getTotal() +
                ", products=" + products +
                '}';
    }
}
