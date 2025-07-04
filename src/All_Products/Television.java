package All_Products;

public class Television extends Products implements Shippable{
    private boolean isSmartTV;
    private double weight; // in grams
    public Television(String name, double price, double weight, boolean isSmartTV) {
        super(name, price);
        this.weight = weight;
        this.isSmartTV = isSmartTV;
    }

    public boolean isSmartTV() {
        return isSmartTV;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
