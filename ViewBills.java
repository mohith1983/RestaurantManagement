import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewBills extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewBills() {

        setTitle("Restaurant Bills");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("Customer");
        model.addColumn("Food");
        model.addColumn("Quantity");
        model.addColumn("Subtotal");
        model.addColumn("GST");
        model.addColumn("Total");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadBills();

        setVisible(true);
    }

    private void loadBills() {

        MongoDBConnection db = new MongoDBConnection();

        ArrayList<Order> orders = db.getAllOrders();

        model.setRowCount(0);

        for (Order order : orders) {

            model.addRow(new Object[]{
                    order.getCustomerName(),
                    order.getItemName(),
                    order.getQuantity(),
                    order.getSubtotal(),
                    order.getGST(),
                    order.getTotal()
            });

        }
    }
}