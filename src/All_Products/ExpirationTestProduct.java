package All_Products;

import java.time.LocalDate;

public class ExpirationTestProduct extends Product implements Expirable, Shippable{
    /* This is not really an actual product this like just to test what happens
       to an expired product for ex think of this class as expired cheese
     */
    private String countryOfOrigin;
    private LocalDate expiryDate;
    private double weight; // in grams
    public  ExpirationTestProduct(String name, double Price, double weight, String country) {
        super(name, Price);
        this.weight = weight;
        countryOfOrigin = country;
        expiryDate = LocalDate.now().minusDays(3); // to check without time passing
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
