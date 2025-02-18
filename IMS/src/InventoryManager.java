/**
 * Problem: Inventory Management System
 *
 * We want to build an inventory management system that can store different types 
 * of products in a warehouse. Each product has some common characteristics, but 
 * different types of products may have specific attributes.
 * 
 * Part A: Define a Product Class
 * --------------------------------
 * Create a `Product` class that represents a generic product. 
 * Each product should have:
 *   - A `String name` representing the product’s name.
 *   - A `double price` representing the price per unit.
 *   - An `int quantity` representing the number of units in stock.
 * 
 * The class should also include:
 *   - A constructor to initialize all attributes.
 *   - Getter methods for all attributes.
 *   - A method:
 *       double getTotalValue()
 *     This method should return the total value of the stock for that product, 
 *     which depends on the product type.
 * 
 * Part B: Implement Specific Product Types
 * -----------------------------------------
 * Extend the functionality of `Product` to support different types of products:
 * 
 * 1. **PerishableProduct**
 *    - Has an additional attribute: `int shelfLife`, representing the number of days 
 *      remaining before expiration.
 *    - Overrides `getTotalValue()` so that if the product has at least three days 
 *      until expiration, its total value is reduced by 20%.
 * 
 * 2. **ElectronicProduct**
 *    - Has an additional attribute: `int warrantyPeriod`, representing the warranty 
 *      length in months.
 *    - Uses the standard `getTotalValue()` calculation without any discounts.
 * 
 * Part C: Inventory Processing
 * ----------------------------
 * Create a class `InventoryManager` that contains a method:
 * 
 *    static void printInventorySummary(Product[] products)
 * 
 * The method should:
 *   - Iterate through the array of products.
 *   - Call `getTotalValue()` for each product.
 *   - Print the product’s name and its total stock value.
 *   - If the array is empty or null, print `"No products in inventory."`
 */

public class InventoryManager {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
