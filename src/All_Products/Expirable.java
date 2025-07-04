package All_Products;

import java.time.LocalDate;

public interface Expirable {
    boolean expire(); // checks if it is expired if it is the output is true
    LocalDate getExpirationDate();
}
