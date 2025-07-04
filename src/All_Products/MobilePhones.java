package All_Products;

public class MobilePhones extends Products implements Shippable {
    boolean supports5G;
    private double weight; // in grams
    public MobilePhones(String name, double price, double weight, boolean supports5G) {
        super(name, price);
        this.weight = weight;
        this.supports5G = supports5G;
    }

    public boolean isSupporting5G() {
        return supports5G;
    }


    @Override
    public double getWeight() {
        return weight;
    }
}
