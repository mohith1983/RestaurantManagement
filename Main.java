import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MongoDBConnection db = new MongoDBConnection();

        Menu[] menu = {
                new Menu(1, "Burger", 120),
                new Menu(2, "Pizza", 250),
                new Menu(3, "Coffee", 80),
                new Menu(4, "Fries", 100),
                new Menu(5, "Sandwich", 150)
        };

        System.out.println("==================================");
        System.out.println("     RESTAURANT BILLING SYSTEM");
        System.out.println("==================================");

        System.out.print("Enter Customer Name: ");
        String customer = sc.nextLine();

        System.out.println("\nMENU");

        for (Menu item : menu) {
            System.out.println(item.getItemId() + ". " +
                    item.getItemName() +
                    " - ₹" + item.getPrice());
        }

        System.out.print("\nEnter Item Number: ");
        int choice = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        Menu selected = menu[choice - 1];

        double subtotal = selected.getPrice() * qty;
        double gst = subtotal * 0.05;
        double total = subtotal + gst;

        System.out.println("\n========== RECEIPT ==========");
        System.out.println("Customer : " + customer);
        System.out.println("Item     : " + selected.getItemName());
        System.out.println("Price    : ₹" + selected.getPrice());
        System.out.println("Quantity : " + qty);
        System.out.println("Subtotal : ₹" + subtotal);
        System.out.println("GST (5%) : ₹" + gst);
        System.out.println("-----------------------------");
        System.out.println("Total    : ₹" + total);
        System.out.println("=============================");

        Order order = new Order(
                customer,
                selected.getItemName(),
                qty,
                subtotal,
                gst,
                total
        );

        db.saveOrder(order);

        sc.close();
    }
}
