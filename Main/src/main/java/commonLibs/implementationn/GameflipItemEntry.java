package commonLibs.implementationn;

public class GameflipItemEntry {
    private String itemName;
    private double price;
    private int quantity;

    public GameflipItemEntry(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = 1; // Initialize quantity to 1
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
