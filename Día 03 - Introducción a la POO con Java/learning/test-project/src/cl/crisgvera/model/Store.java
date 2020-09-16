package cl.crisgvera.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        return customers.stream()
                .max(Comparator.comparingInt(Customer::getTotalPurchases))
                .orElse(null);
    }

    public List<ShoppingCart> getMostExpensivesShoppingCarts(int amount) {
        return shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getTotal() > amount)
                .collect(Collectors.toList());
    }

    public boolean allCustomerBought() {
        return customers.stream()
                .allMatch(customer -> !getShoppingCarts().isEmpty());
    }

    public List<Customer> whoDoNotBoughtTwice() {
        return customers.stream()
                .filter(customer -> customer.getShoppingCarts().size() == 1)
                .collect(Collectors.toList());
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
