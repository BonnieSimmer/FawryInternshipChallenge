import All_Products.Shippable;

import java.util.ArrayList;

public class ShippingService {
    private ArrayList<Shippable> cargo;
    private ArrayList<Integer> cargo_quantities;
    private double shipping_cost;
    public ShippingService(ArrayList<Shippable> cargo, ArrayList<Integer> cargo_quantities, double shipping_cost) {
        this.cargo = cargo;
        this.cargo_quantities = cargo_quantities;
        this.shipping_cost = shipping_cost;
    }

    public void deliver() {
        System.out.println("** Delivering ordered goods through shipping service **");
        for (int i = 0; i<cargo.size(); i++) {
            System.out.printf("%dx %s\n", cargo_quantities.get(i), cargo.get(i).getName());
        }
        System.out.println("----------------------");
        System.out.println("Finished delivering!");
    }
}
