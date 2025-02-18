import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class InventoryManagementSystemTest {

    // Since Product is abstract, we create an anonymous subclass for testing its getters.
    @Test
    public void testProductGetters() {
        Product product = new Product("Generic", 10.0, 5) {};
        assertEquals("Generic", product.getName(), "getName() should return the product's name.");
        assertEquals(10.0, product.getPrice(), "getPrice() should return the product's price.");
        assertEquals(5, product.getQuantity(), "getQuantity() should return the product's quantity.");
        assertEquals(50.0, product.getTotalValue(), 0.001, 
            "getTotalValue() should return price * quantity for a generic product.");
    }

    @Test
    public void testPerishableProductDiscountApplied() {
        // For a perishable product with shelfLife <= 3, a 20% discount should be applied.
        PerishableProduct milk = new PerishableProduct("Milk", 2.0, 10, 3);
        double expectedValue = 2.0 * 10 * 0.8; // discount applied: 20 * 0.8 = 16.0
        assertEquals(expectedValue, milk.getTotalValue(), 0.001, 
            "PerishableProduct with shelfLife <= 3 should apply a 20% discount.");
    }

    @Test
    public void testPerishableProductNoDiscount() {
        // For a perishable product with shelfLife > 3, no discount should be applied.
        PerishableProduct cheese = new PerishableProduct("Cheese", 5.0, 4, 5);
        double expectedValue = 5.0 * 4; // no discount: 20.0
        assertEquals(expectedValue, cheese.getTotalValue(), 0.001, 
            "PerishableProduct with shelfLife > 3 should not apply any discount.");
    }

    @Test
    public void testElectronicProductWarrantyStatusValid() {
        ElectronicProduct laptop = new ElectronicProduct("Laptop", 1000.0, 2, 24);
        assertEquals("Warranty valid for 24 months", laptop.getWarrantyStatus(), 
            "ElectronicProduct with a positive warrantyPeriod should report a valid warranty.");
    }

    @Test
    public void testElectronicProductWarrantyStatusExpired() {
        ElectronicProduct oldPhone = new ElectronicProduct("Old Phone", 200.0, 3, 0);
        assertEquals("Warranty expired", oldPhone.getWarrantyStatus(), 
            "ElectronicProduct with warrantyPeriod <= 0 should report warranty expired.");
    }

    @Test
    public void testElectronicProductTotalValue() {
        ElectronicProduct tv = new ElectronicProduct("TV", 500.0, 3, 12);
        double expectedValue = 500.0 * 3; // 1500.0
        assertEquals(expectedValue, tv.getTotalValue(), 0.001, 
            "ElectronicProduct's total value should equal price multiplied by quantity.");
    }

    @Test
    public void testInventoryManagerPrintSummary() {
        // Create a mixed array of products.
        Product[] products = new Product[] {
            // For "Yogurt": shelfLife=2 (<=3) so discount applies: 1.0 * 10 * 0.8 = 8.00
            new PerishableProduct("Yogurt", 1.0, 10, 2),
            // For "Bread": shelfLife=5 (>3) so no discount: 2.0 * 5 = 10.00
            new PerishableProduct("Bread", 2.0, 5, 5),
            // For "Speaker": ElectronicProduct uses standard totalValue: 50.0 * 2 = 100.00
            new ElectronicProduct("Speaker", 50.0, 2, 6)
        };

        String expectedOutput = 
            "Product: Yogurt | Total Value 8.00\n" +
            "Product: Bread | Total Value 10.00\n" +
            "Product: Speaker | Total Value 100.00\n";

        // Capture the output of InventoryManager.printInventorySummary.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            // Set Locale to US for consistent decimal formatting
            System.setOut(new PrintStream(outContent));
            Locale.setDefault(Locale.US);
            InventoryManager.printInventorySummary(products);
        } finally {
            System.setOut(originalOut);
        }
        
        // Strip leading/trailing whitespace and compare
        assertEquals(expectedOutput.trim(), outContent.toString().trim(), 
            "InventoryManager.printInventorySummary should correctly print product names and total values.");

        // Test with an empty array.
        outContent.reset();
        try {
            System.setOut(new PrintStream(outContent));
            InventoryManager.printInventorySummary(new Product[0]);
        } finally {
            System.setOut(originalOut);
        }
        String expectedEmptyOutput = "No products in inventory";
        assertEquals(expectedEmptyOutput.trim(), outContent.toString().trim(), 
            "InventoryManager.printInventorySummary should handle an empty array correctly.");

        // Test with a null array.
        outContent.reset();
        try {
            System.setOut(new PrintStream(outContent));
            InventoryManager.printInventorySummary(null);
        } finally {
            System.setOut(originalOut);
        }
        assertEquals(expectedEmptyOutput.trim(), outContent.toString().trim(), 
            "InventoryManager.printInventorySummary should handle a null array correctly.");
    }
}
