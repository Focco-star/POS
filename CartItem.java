package POS;

// CartItem
public class CartItem {
    private final Product product; // Product
    private int quantity; // Qty

    // Create
    public CartItem(Product product, int quantity) {
        this.product = product;
        if (quantity < 1) quantity = 1;
        this.quantity = quantity;
    }

    // GetProduct
    public Product getProduct() {
        return product;
    }

    // GetQty
    public int getQuantity() {
        return quantity;
    }

    // SetQty
    public void setQuantity(int quantity) {
        if (quantity < 0) quantity = 0;
        this.quantity = quantity;
    }

    // Increment
    public void incrementQuantity() {
        quantity = quantity + 1;
    }

    // TotalPrice
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    // Quick test runner so this file can be run directly for learning.
    public static void main(String[] args) {
        // Create a sample product and cart item
        Product p = new FoodItem("Sample Burger", 99.50);
        CartItem ci = new CartItem(p, 2);
        // Print a simple line showing name, qty and total
        System.out.println(ci.getProduct().getName() + " x" + ci.getQuantity() + " = " + String.format("₱%.2f", ci.getTotalPrice()));
    }
}
