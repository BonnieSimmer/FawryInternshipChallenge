package All_Products;

import java.time.LocalDate;

public class Biscuits extends Products implements Expirable, Shippable{
    private String flavour; // ex for when i start testing: chocolate, vanilla, strawberry
    private LocalDate expiryDate;
    private double weight; // in grams
    public Biscuits(String name, double price, double weight, String flavour) {
        super(name, price);
        this.weight = weight;
        this.flavour = flavour;
        expiryDate = LocalDate.now().plusMonths(6);
    }

    public String getFlavour() {
        return flavour;
    }


    @Override
    public boolean expire() {
        return expiryDate.isBefore(LocalDate.now());

    }

    @Override
    public LocalDate getExpirationDate() {
        return expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
