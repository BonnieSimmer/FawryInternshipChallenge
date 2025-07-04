import All_Products.*;

import java.util.ArrayList;

public class Cart {
    private HyperMarketManager manager; // which hypermarket this cart belongs to and has access to
    private boolean shippingRequired;
    private ArrayList<Product> products;
    private ArrayList<Integer> product_quantities;
    private double total;
    public Cart(HyperMarketManager manager) {
        this.manager = manager;
        products = new ArrayList<>();
        product_quantities = new ArrayList<>();
        shippingRequired = false;
        total = 0.0;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Integer> getProduct_quantities() {
        return product_quantities;
    }

    public boolean isShippingRequired() {
        return shippingRequired;
    }

    public double getTotal() {
        return total;
    }


    void add(Product product, int product_quantity ) throws IllegalArgumentException, IllegalStateException {
        if (manager.getTotalProducts().contains(product)) {
            int quantity_store = manager.getTotalProduct_quantities().get(manager.getTotalProducts().indexOf(product));
            if (product_quantity <= 0) {
                throw new IllegalArgumentException("Quantity can't be less than or equal zero!");
            } else if (product instanceof Expirable) {
                if ( ((Expirable)product).expire() ) {
                    throw new IllegalStateException("This product is already expired");
                }
            }
            if (product_quantity > quantity_store) {
                throw new IllegalArgumentException("The product quantity exceeds the quantity in the hypermarket");
            }
            if (product instanceof Shippable) {
                shippingRequired = true;
            }
            products.add(product);
            total += product.getPrice() * product_quantity;
            product_quantities.add(product_quantity);
        } else  {
            throw new IllegalArgumentException("The product is not available in this hypermarket");
        }

    }

    void remove(Product product, int quantity)  throws IllegalArgumentException {
        if (products.contains(product)) {
            int cart_quantity = product_quantities.get(products.indexOf(product));
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity can't be less than or equal zero!");
            } else if (quantity > cart_quantity) {
                throw new IllegalArgumentException("Removed quantity is too large!");
            } else if (quantity == cart_quantity ) {
                product_quantities.remove(products.indexOf(product));
                products.remove(product);
                total -= product.getPrice() * quantity;
            } else {
                total -= product.getPrice() * quantity;
                product_quantities.set(products.indexOf(product), cart_quantity-quantity);
            }

        } else {
            throw new IllegalArgumentException("This Product was not in the cart.");
        }

    }
}
