import All_Products.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private HyperMarketManager manager;
    private ArrayList<Products> testProducts;
    private ArrayList<Integer> testQuantities;
    private Cheese cheese;
    private Biscuits biscuits;
    private Television tv;
    private MobilePhones phone;

    @BeforeEach
    void setUp() {
        testProducts = new ArrayList<>();
        testQuantities = new ArrayList<>();

        // Initialize test products
        cheese = new Cheese("Mozzarella", 50.0, 250, "Italy");
        biscuits = new Biscuits("ChocoBiscuits", 15.0, 300, "Chocolate");
        tv = new Television("Toshiba", 1000.0, 5000, true);
        phone = new MobilePhones("iPhone", 800.0, 200, true);

        testProducts.add(cheese);
        testProducts.add(biscuits);
        testProducts.add(tv);
        testProducts.add(phone);

        testQuantities.add(10);
        testQuantities.add(20);
        testQuantities.add(5);
        testQuantities.add(8);

        manager = new HyperMarketManager(testProducts, testQuantities);
    }

    @Test
    void testProductCreation() {
        assertEquals("Mozzarella", cheese.getName());
        assertEquals(50.0, cheese.getPrice());
        assertEquals("Italy", cheese.getCountryOfOrigin());
        assertEquals(250, cheese.getWeight());

        assertEquals("ChocoBiscuits", biscuits.getName());
        assertEquals("Chocolate", biscuits.getFlavour());

        assertTrue(tv.isSmartTV());
        assertEquals(5000, tv.getWeight());

        assertTrue(phone.isSupporting5G());
    }

    @Test
    void testInvalidProductPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cheese("Invalid", -10, 200, "France");
        });
    }

    @Test
    void testCartOperations() {
        Cart cart = new Cart(manager);
        cart.add(cheese, 2);
        cart.add(biscuits, 3);

        assertEquals(2, cart.getProducts().size());
        assertEquals(145.0, cart.getTotal()); // (50.0 * 2) + (15.0 * 3)

        cart.remove(cheese,1);
        assertEquals(1,cart.getProduct_quantities().get(0) );
        cart.remove(cheese,1);
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    void testCartIsEmpty() {
        Cart cart = new Cart(manager);
        Customer customer = new Customer(500.0);

        assertThrows(IllegalArgumentException.class, () -> {
            HyperMarketManager.checkout(customer, cart); // Cart is empty
        });
    }

    @Test
    void testCartExceedsAvailableQuantity() {
        Cart cart = new Cart(manager);
        assertThrows(IllegalArgumentException.class, () -> {
            cart.add(cheese, 11); // Only 10 available in stock
        });
    }

    @Test
    void testSuccessfulCheckout() {
        Cart cart = new Cart(manager);
        cart.add(cheese, 1);
        Customer customer = new Customer(1000.0);

        HyperMarketManager.checkout(customer, cart);
        assertTrue(customer.getBalance() < 1000.0); // Balance should be reduced
    }

    @Test
    void testInsufficientBalance() {
        Cart cart = new Cart(manager);
        cart.add(tv, 1); // TV costs 1000.0
        Customer customer = new Customer(500.0);

        assertThrows(RuntimeException.class, () -> {
            HyperMarketManager.checkout(customer, cart);
        });
    }

    @Test
    void testExpirationDates() {
        assertFalse(cheese.expire()); // Should not be expired yet
        assertFalse(biscuits.expire()); // Should not be expired yet

        assertTrue(cheese.getExpirationDate().isAfter(LocalDate.now()));
        assertTrue(biscuits.getExpirationDate().isAfter(LocalDate.now()));
    }

    @Test
    void testShippableProducts() {
        Cart cart = new Cart(manager);
        cart.add(tv, 1);
        cart.add(phone, 1);

        assertTrue(cart.isShippingRequired());
        // Total weight should be over 1kg (5000g + 200g)
        assertEquals(5200, ((Shippable)tv).getWeight() + ((Shippable)phone).getWeight());
    }

    @Test
    void testHyperMarketManagerInitialization() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayList<Products> products = new ArrayList<>();
            ArrayList<Integer> quantities = new ArrayList<>();
            products.add(cheese);
            // Don't add quantity - should throw exception
            new HyperMarketManager(products, quantities);
        });
    }
}
