public class ElectronicProduct extends Product {
    private int warrantyPeriod;

    /**
     * Constructs a new instance of the electronic product class
     * @param name The name of the product
     * @param price The price of the product
     * @param quantity The number of units of the product in stock
     * @param warrantyPeriod The number of months remaining in the warranty
     */
    public ElectronicProduct (
        String name,
        double price,
        int quantity,
        int warrantyPeriod
    ) {
        super(name, price, quantity);
        this.warrantyPeriod = warrantyPeriod;
    }

    /**
     * Writes the number of months remaining in the warranty to a String, or if
     * the warranty has expired
     * @return A string containing either "Warranty expired" or "Warranty valid
     * for X months"
     */
    public String getWarrantyStatus() {
        if (this.warrantyPeriod <= 0) return "Warranty expired";
        else return String.format(
            "Warranty valid for %d months",
            this.warrantyPeriod
        );
    }
}
