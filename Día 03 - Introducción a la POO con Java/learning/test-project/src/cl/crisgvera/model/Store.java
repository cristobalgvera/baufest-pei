package cl.crisgvera.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Store {

    private String name;
    private List<Customer> customers = new ArrayList<>();
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public Store() {
    }

    public Store(String name) {
        this.name = name;
    }

    public Customer getMostActiveCustomer() {
        Customer mostActiveCustomer = new Customer();

        for (Customer customer : customers) {
            if (customer.getTotalPurchases() > mostActiveCustomer.getTotalPurchases())
                mostActiveCustomer = customer;
        }

        return mostActiveCustomer;
    }

    public List<ShoppingCart> getMostExpensivesShoppingCarts(int amount) {
        List<ShoppingCart> mostExpensivesShoppingCarts = new ArrayList<>();
        shoppingCarts.forEach(shoppingCart -> {
            if (shoppingCart.getTotal() > amount)
                mostExpensivesShoppingCarts.add(shoppingCart);
        });

        return mostExpensivesShoppingCarts;
    }

    public boolean allCustomerBought() {
        for (Customer customer : customers)
            if (customer.getShoppingCarts().isEmpty()) return false;

        return true;
    }

    public List<Customer> whoDoNotBoughtTwice() {
        List<Customer> customerWhoDoNotBoughtTwice = new ArrayList<>();
        for (Customer customer : customers) {
            AtomicInteger i = new AtomicInteger();
            customer.getShoppingCarts().forEach((k, v) -> {
                i.addAndGet(1);
            });
            if (i.get() < 2) customerWhoDoNotBoughtTwice.add(customer);
        }

        return customerWhoDoNotBoughtTwice;
    }

    public int getTotalSales() {
        return shoppingCarts.stream().mapToInt(ShoppingCart::getTotal).sum();
    }

    public List<Customer> orderCustomersByTotalBought() {
        return customers.stream()
                .sorted(Comparator.comparingInt(Customer::getTotalPurchases).reversed())
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.add(shoppingCart);
    }

    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.remove(shoppingCart);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                ", shoppingCarts=" + shoppingCarts +
                '}';
    }
}
