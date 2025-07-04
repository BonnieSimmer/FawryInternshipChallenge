package All_Products;

public abstract class Products {
    private String name;
    private double price;

    Products(String name, double price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price, must be greater than 0.");
        }
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
