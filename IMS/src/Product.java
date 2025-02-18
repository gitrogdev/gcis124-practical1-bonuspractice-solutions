public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new instance of the Product superclass
     * @param name The name of the product
     * @param price The price of the product
     * @param quantity The number of units of the product in stock
     */
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the name of the product
     * @return The name of the prodct
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the price of the product
     * @return The price of the product
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the quantity of the product
     * @return The quantity of the product
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets the total value of the products in stock
     * @return The total value of the products in stock
     */
    public double getTotalValue() {
        return this.price * this.quantity;
    }
}
