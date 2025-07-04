package All_Products;

import java.time.LocalDate;

public class Cheese extends Products implements Expirable, Shippable{
    private String countryOfOrigin;
    private LocalDate expiryDate;
    private double weight; // in grams
    public  Cheese(String name, double Price, double weight, String country) {
        super(name, Price);
        this.weight = weight;
        countryOfOrigin = country;
        expiryDate = LocalDate.now().plusDays(3); // Will remove the plus days to test
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
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
