package cl.crisgvera.model;

import cl.crisgvera.model.abstractClass.Persona;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer extends Persona {

    private String address, email;
    private int totalMoney;
    private Map<ShoppingCart, Integer> shoppingCarts = new HashMap<>();

    public Customer() {
    }

    public Customer(String name, String address, String email, int totalMoney) {
        super(name);
        this.address = address;
        this.email = email;
        this.totalMoney = totalMoney;
    }

    public boolean canBuyProduct(Product product, int quantity) {
        if (quantity < 1) return false;
        return totalMoney >= product.getCost() * quantity;
    }

    public boolean canBuyProduct(Product product) {
        return totalMoney >= product.getCost();
    }

    public boolean canBuyShoppingCart(ShoppingCart shoppingCart) {
        return totalMoney >= shoppingCart.getTotal();
    }

    public int getTotalPurchases() {
        AtomicInteger totalPurchases = new AtomicInteger();
        shoppingCarts.forEach((shoppingCart, integer) -> totalPurchases.addAndGet(integer));
        return totalPurchases.get();
    }

    public Map<ShoppingCart, Integer> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Map<ShoppingCart, Integer> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void addShoppingCart(ShoppingCart shoppingCart) {
        int cost = shoppingCart.getTotal();
        if (shoppingCart.isPromotionEnabled())
            cost = shoppingCart.getTotalWithPromotion();

        shoppingCarts.put(shoppingCart, cost);
        totalMoney -= cost;
    }

    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.remove(shoppingCart);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", totalMoney=" + totalMoney +
                ", name='" + name + '\'' +
                '}';
    }
}
