import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;


public class MongoDBConnection {


    private MongoCollection<Document> collection;


    public MongoDBConnection() {


        MongoClient client =
        MongoClients.create("mongodb://localhost:27017");


        MongoDatabase database =
        client.getDatabase("RestaurantDB");


        collection =
        database.getCollection("Orders");


    }



    public void saveOrder(Order order) {


        Document doc = new Document();


        doc.append("customerName",
        order.getCustomerName());


        doc.append("itemName",
        order.getItemName());


        doc.append("quantity",
        order.getQuantity());


        doc.append("subtotal",
        order.getSubtotal());


        doc.append("gst",
        order.getGST());


        doc.append("total",
        order.getTotal());



        collection.insertOne(doc);


    }





    public ArrayList<Order> getAllOrders() {


        ArrayList<Order> list =
        new ArrayList<>();


        MongoCursor<Document> cursor =
        collection.find().iterator();



        while(cursor.hasNext()){


            Document doc = cursor.next();



            Order order = new Order(


                    doc.getString("customerName"),


                    doc.getString("itemName"),


                    doc.getInteger("quantity"),


                    doc.getDouble("subtotal"),


                    doc.getDouble("gst"),


                    doc.getDouble("total")

            );



            list.add(order);


        }



        return list;


    }


}