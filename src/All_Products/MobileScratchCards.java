package All_Products;

public class MobileScratchCards extends Products{
    private double rechargeAmount;
    public MobileScratchCards(String name, double price, double rechargeAmount) throws IllegalArgumentException {
        super(name, price);
        if (rechargeAmount < 0 || rechargeAmount < price) {
            throw new IllegalArgumentException("Invalid recharge amount");
        }
        this.rechargeAmount = rechargeAmount;
    }

    public double getRechargeAmount() {
        return rechargeAmount;
    }
}
