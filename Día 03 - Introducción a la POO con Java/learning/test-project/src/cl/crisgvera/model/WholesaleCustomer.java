package cl.crisgvera.model;

public class WholesaleCustomer extends Customer {

    public WholesaleCustomer() {
    }

    public WholesaleCustomer(String name, String address, String email, int totalMoney) {
        super(name, address, email, totalMoney);
        setName(name.toUpperCase());
        if (!name.substring(name.length() - 3).equals(" FC")) setName(getName() + " FC");
    }

    @Override
    public boolean canBuyProduct(Product product, int quantity) {
        return true;
    }

    @Override
    public boolean canBuyProduct(Product product) {
        return true;
    }

    @Override
    public String toString() {
        return "wholesaleCustomer{" +
                super.toString() +
                "}";
    }
}
