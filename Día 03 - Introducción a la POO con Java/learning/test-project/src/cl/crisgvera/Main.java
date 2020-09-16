package cl.crisgvera;

import cl.crisgvera.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
//        List<Customer> customers = setCustomerList(10);
//        List<Product> products = setProductsList(5);

        exerciseOne();
        exerciseTwo();
        exerciseThreeAndSectionC();

    }

    private static void exerciseThreeAndSectionC() {
        Store store = new Store("Tiendita de prueba");
        List<Product> products = setProductsList(10);
        List<Customer> customers = setCustomerList(5);

        System.out.println("\nClientes que no pudieron comprar por falta de dinero:");
        customers.forEach(customer -> {
            do {
                ShoppingCart shoppingCart = new ShoppingCart();
                if (Math.random() > 0.5) shoppingCart.setPromotionEnabled(true);
                products.forEach(product -> {
                    if (Math.random() > 0.5)
                        shoppingCart.addProduct(product, (int) (Math.random() * 3) + 1);
                });

                if (Math.random() > 0.5)
                    shoppingCart.buy(customer, store);

            } while (customer.getTotalMoney() >= 200 && Math.random() > 0.3);
        });

        System.out.println("\nCompras de la tienda:\n" + store.getShoppingCarts());
        System.out.println("Número de compras: " + store.getShoppingCarts().stream().count());

        // Section C
        // C1
        System.out.println("\nCliente que más compró: ");
        System.out.println(store.getMostActiveCustomer());

        // C2
        System.out.println("\nCarritos más caros:");
        System.out.println(store.getMostExpensivesShoppingCarts(20000));

        // C3
        System.out.println("\nTodos los clientes compraron: " + store.allCustomerBought());

        // C4
        System.out.println("\nClientes que no compraron más de una vez:");
        System.out.println(store.whoDoNotBoughtTwice());
        System.out.println("Número de clientes: " + store.whoDoNotBoughtTwice().stream().count());

        // C5
        System.out.println("\nTotal ganado por la tienda hasta el momento:");
        System.out.println(store.getTotalSales());

        // C6
        System.out.println("\nClientes ordenados desde el que más compró");
        System.out.println(store.orderCustomersByTotalBought());
        store.getCustomers().forEach(customer -> System.out.println(customer.getName() + " compró: " + customer.getTotalPurchases()));

    }

    private static void exerciseTwo() {
        WholesaleCustomer wholesaleCustomer = new WholesaleCustomer("Boro", "Broadway 1, 14th Floor, Cambridge, Boston", "tomas@boro.com", -1);
        Product product = new Product("Botines", "Nike", 2000);

        System.out.println(wholesaleCustomer.canBuyProduct(product));
    }

    private static void exerciseOne() {
        Customer customer = new Customer("Gonzalo", "Roosvelt 1655", "gonza_diablito86@independiente.com", 1000);
        Product product = new Product("Remera", "Independiente FC", 1500);

        stringBuilder.setLength(0);
        stringBuilder.append(customer.getName());

        if (!customer.canBuyProduct(product)) stringBuilder.append(" NO");
        stringBuilder.append(" puede comprar " + product.getName() + " " + product.getDescription());

        System.out.println(stringBuilder);
    }

    private static List<Product> setProductsList(int productTotalCount) {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= productTotalCount; i++) {
            products.add(new Product("Producto " + i, "Descripción producto " + i, (int) (Math.random() * 800) + 200));
        }

        return products;
    }

    private static List<Customer> setCustomerList(int customerTotalCount) {
        List<Customer> customers = new ArrayList<>();

        for (int i = 1; i <= customerTotalCount; i++) {
            customers.add(new Customer("Cliente " + i, "Dirección cliente " + i, "Correo cliente " + i, (int) (Math.random() * 50000) + 10000));
        }

        return customers;
    }

}
