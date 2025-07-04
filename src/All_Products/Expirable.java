package All_Products;

import java.time.LocalDate;

public interface Expirable {
    public boolean expire(); // checks if it is expired if it is the output is true
    public LocalDate getExpirationDate();
}
