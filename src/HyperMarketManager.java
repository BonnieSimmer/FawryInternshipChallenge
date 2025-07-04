import All_Products.Products;
import All_Products.Shippable;

import java.util.ArrayList;

public class HyperMarketManager {
    private ArrayList<Products> totalProducts;
    private ArrayList<Integer> totalProduct_quantities;
    static ArrayList<Shippable> tobeShipped;
    static ArrayList<Integer> tobeShipped_quantities;
    private static double sales; // Total HyperMarkets sales (static)

    public HyperMarketManager(ArrayList<Products> totalProducts, ArrayList<Integer> totalProduct_quantities) throws IllegalArgumentException {
        if (totalProducts.size() != totalProduct_quantities.size()) {
            throw new IllegalArgumentException("Number of Products and Quantity Do not Match");
        }
        this.totalProducts = totalProducts;
        this.totalProduct_quantities = totalProduct_quantities;
        sales = 0.0;
    }

    public ArrayList<Products> getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(ArrayList<Products> totalProducts) {
        this.totalProducts = totalProducts;
    }

    public ArrayList<Integer> getTotalProduct_quantities() {
        return totalProduct_quantities;
    }

    public void setTotalProduct_quantities(ArrayList<Integer> totalProduct_quantities) {
        this.totalProduct_quantities = totalProduct_quantities;
    }

    public double getSales() {
        return sales;
    }

    static void checkout(Customer customer, Cart cart) throws RuntimeException {
        ArrayList<Products> cartProducts = cart.getProducts();
        ArrayList<Integer> cartProduct_quantities = cart.getProduct_quantities();
        double shipping_cost = 0.0, total;
        if (cart.isShippingRequired()) {
            tobeShipped = new ArrayList<>();
            tobeShipped_quantities = new ArrayList<>();
            int total_grams = 0;
            System.out.println("** Shipment Notice **");
            for (int i = 0; i < cartProducts.size(); i++) {
                if (cartProducts.get(i) instanceof Shippable) {
                    tobeShipped.add((Shippable) cartProducts.get(i));
                    tobeShipped_quantities.add(cartProduct_quantities.get(i));
                    System.out.printf("%dx %s\t\t%.2fg\n", cartProduct_quantities.get(i), cartProducts.get(i).getName(),
                            ((Shippable) cartProducts.get(i)).getWeight() * cartProduct_quantities.get(i));
                    total_grams += ((Shippable) cartProducts.get(i)).getWeight() * cartProduct_quantities.get(i);
                }
            }
            if (total_grams >= 1000) {
                System.out.printf("Total Package weight: %.2fkg\n", (float) total_grams / 1000);
                shipping_cost = 30;
            } else {
                System.out.printf("Total Package weight : %dg\n", total_grams);
                shipping_cost = 20;
            }
            System.out.println();
        }
        System.out.println("** Checkout receipt **");
        for (int i = 0; i < cartProducts.size(); i++) {
            System.out.printf("%dx %s\t\t%.2f\n", cartProduct_quantities.get(i), cartProducts.get(i).getName(),
                    cartProducts.get(i).getPrice() * cartProduct_quantities.get(i));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal \t\t" + cart.getTotal());
        System.out.println("Shipping \t\t" + shipping_cost);
        total = cart.getTotal() + shipping_cost;
        System.out.println("Amount    \t\t" + total);
        if (customer.getBalance() >= total) {
            customer.setBalance(customer.getBalance() - total);
            sales += total;
            System.out.println("Successful Payment! Customer's current balance is: " + customer.getBalance());
            if (cart.isShippingRequired()) {
                System.out.println();
                ShippingService fedEx = new ShippingService(tobeShipped, tobeShipped_quantities, shipping_cost);
                fedEx.deliver();
            }
        } else {
            throw new RuntimeException("Insufficient Balance! The customer's current balance is: " + customer.getBalance());

        }
    }
}

