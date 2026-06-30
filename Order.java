public class Order {

    private String customerName;
    private String itemName;
    private int quantity;
    private double subtotal;
    private double gst;
    private double total;

    public Order(String customerName, String itemName, int quantity,
                 double subtotal, double gst, double total) {
        this.customerName = customerName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.gst = gst;
        this.total = total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getGST() {
        return gst;
    }

    public double getTotal() {
        return total;
    }
}