public class PerishableProduct extends Product {
    private int shelfLife;

    /**
     * Constructs a new instance of the perishable product class
     * @param name The name of the product
     * @param price The price of the product
     * @param quantity The number of units of the product in stock
     * @param shelfLife The number of days remaining until the product expires
     */
    public PerishableProduct(
        String name,
        double price,
        int quantity,
        int shelfLife
    ) {
        super(name, price, quantity);
        this.shelfLife = shelfLife;
    }

    /**
     * Gets the total value of the perishable products remaining in stock,
     * applying a discount if the product will expire within three days
     * @return The total value of the perishable products in stock
     */
    @Override
    public double getTotalValue() {
        double total = super.getTotalValue();
        if (this.shelfLife <= 3) total *= 0.8;
        return total;
    }
}
