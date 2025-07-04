import All_Products.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> test_products = new ArrayList<>();
        Cheese cheese = new Cheese("cheese", 100, 200, "Egypt");
        test_products.add(cheese);
        Biscuits biscuits = new Biscuits("biscuits", 150, 700, "Vanilla");
        test_products.add(biscuits);
        Television tv = new Television("Samsung", 10000, 5000, true);
        test_products.add(tv);
        MobileScratchCards tenPounds = new MobileScratchCards("Vodafone", 15, 10);
        test_products.add(tenPounds);
        ArrayList<Integer> test_quantity = new ArrayList<>();
        test_quantity.add(20);
        test_quantity.add(15);
        test_quantity.add(10);
        test_quantity.add(5);
        try {
            HyperMarketManager manager = new HyperMarketManager(test_products, test_quantity);
            Customer customer = new Customer(15000); // No error
            //Customer customer = new Customer(200); // will result in "RuntimeException: Insufficient Balance! The customer's current balance is: 20.0"
            Cart cart = new Cart(manager);
            cart.add(cheese, 5); // No error
            // cart.add(cheese, 30); // will result in "IllegalArgumentException: The product quantity exceeds the quantity in the hypermarket"
            cart.add(biscuits, 3);
            cart.add(tv, 1);
            cart.add(tenPounds, 2);
            HyperMarketManager.checkout(customer, cart);
        } catch (Exception e) {
            System.out.printf("%s: %s\n",e.getClass().getName() ,e.getMessage());
        }
    }
}
