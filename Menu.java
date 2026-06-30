public class Menu {

    private int itemId;
    private String itemName;
    private double price;

    // Constructor
    public Menu(int itemId, String itemName, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    // Getter for Item ID
    public int getItemId() {
        return itemId;
    }


    // Getter for Item Name
    public String getItemName() {
        return itemName;
    }

    // Getter for Price
    public double getPrice() {
        return price;
    }

    // Display Menu Item
    public void displayMenu() {
        System.out.println(itemId + ". " + itemName + " - ₹" + price);
    }
}