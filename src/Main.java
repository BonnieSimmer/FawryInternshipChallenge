import All_Products.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Products> test_products = new ArrayList<>();
        Cheese cheese = new Cheese("cheese", 100, 200, "Egypt");
        test_products.add(cheese);
        Biscuits biscuits = new Biscuits("biscuits", 150, 700, "Vanilla");
        test_products.add(biscuits);
        ArrayList<Integer> test_quantity = new ArrayList<>();
        test_quantity.add(20);
        test_quantity.add(15);
        try {
            HyperMarketManager manager = new HyperMarketManager(test_products, test_quantity);
            Customer customer = new Customer(500); // No error
            //Customer customer = new Customer(200); // will result in "RuntimeException: Insufficient Balance! The customer's current balance is: 20.0"
            Cart cart = new Cart(manager);
            cart.add(cheese, 2); // No error
            // cart.add(cheese, 30); // will result in "IllegalArgumentException: The product quantity exceeds the quantity in the hypermarket"
            cart.add(biscuits, 1);
            HyperMarketManager.checkout(customer, cart);
        } catch (Exception e) {
            System.out.printf("%s: %s\n",e.getClass().getName() ,e.getMessage());
        }
    }
}
