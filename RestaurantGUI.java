import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class RestaurantGUI extends JFrame {


    JLabel title,lblCustomer,lblFood,lblQuantity,lblPrice;
    JLabel lblSubtotal,lblGST,lblTotal;


    JTextField txtCustomer,txtQuantity;
    JTextField txtPrice,txtSubtotal,txtGST,txtTotal;


    JComboBox<String> cmbFood;


    JButton btnCalculate,btnSave,btnClear,btnExit;


    JTextArea receiptArea;


    MongoDBConnection mongo;



    public RestaurantGUI(){


        mongo = new MongoDBConnection();



        setTitle("Restaurant Billing Management System");


        setSize(1100,750);


        setLayout(null);


        setLocationRelativeTo(null);


        setDefaultCloseOperation(EXIT_ON_CLOSE);



        // ONLY BACKGROUND COLOUR CHANGE

        getContentPane()
        .setBackground(new Color(220,235,250));






        title = new JLabel(
        "RESTAURANT BILLING MANAGEMENT SYSTEM"
        );


        title.setBounds(250,30,700,50);


        title.setFont(
        new Font("Arial",Font.BOLD,32)
        );


        title.setForeground(
        new Color(50,50,50)
        );


        add(title);






        Font labelFont =
        new Font("Arial",Font.BOLD,18);





        lblCustomer =
        new JLabel("Customer Name");


        lblCustomer.setBounds(80,130,180,35);

        lblCustomer.setFont(labelFont);

        add(lblCustomer);



        txtCustomer =
        new JTextField();


        txtCustomer.setBounds(280,130,300,35);


        add(txtCustomer);







        lblFood =
        new JLabel("Food Item");


        lblFood.setBounds(80,200,180,35);


        lblFood.setFont(labelFont);


        add(lblFood);



        String food[] =
        {
            "Burger",
            "Pizza",
            "Coffee",
            "Fries",
            "Sandwich"
        };



        cmbFood =
        new JComboBox<>(food);


        cmbFood.setBounds(280,200,300,35);


        add(cmbFood);







        lblQuantity =
        new JLabel("Quantity");


        lblQuantity.setBounds(80,270,180,35);


        lblQuantity.setFont(labelFont);


        add(lblQuantity);



        txtQuantity =
        new JTextField();


        txtQuantity.setBounds(280,270,300,35);


        add(txtQuantity);







        lblPrice =
        new JLabel("Price");


        lblPrice.setBounds(80,340,180,35);


        lblPrice.setFont(labelFont);


        add(lblPrice);



        txtPrice =
        new JTextField();


        txtPrice.setBounds(280,340,300,35);


        txtPrice.setEditable(false);


        add(txtPrice);







        lblSubtotal =
        new JLabel("Subtotal");


        lblSubtotal.setBounds(80,410,180,35);


        lblSubtotal.setFont(labelFont);


        add(lblSubtotal);



        txtSubtotal =
        new JTextField();


        txtSubtotal.setBounds(280,410,300,35);


        txtSubtotal.setEditable(false);


        add(txtSubtotal);






        lblGST =
        new JLabel("GST");


        lblGST.setBounds(80,480,180,35);


        lblGST.setFont(labelFont);


        add(lblGST);



        txtGST =
        new JTextField();


        txtGST.setBounds(280,480,300,35);


        txtGST.setEditable(false);


        add(txtGST);







        lblTotal =
        new JLabel("Total Amount");


        lblTotal.setBounds(80,550,180,35);


        lblTotal.setFont(labelFont);


        add(lblTotal);



        txtTotal =
        new JTextField();


        txtTotal.setBounds(280,550,300,35);


        txtTotal.setEditable(false);


        add(txtTotal);








        btnCalculate =
        new JButton("Calculate");


        btnCalculate.setBounds(750,150,220,45);


        add(btnCalculate);




        btnSave =
        new JButton("Save Bill");


        btnSave.setBounds(750,230,220,45);


        add(btnSave);




        btnClear =
        new JButton("Clear");


        btnClear.setBounds(750,310,220,45);


        add(btnClear);




        btnExit =
        new JButton("Exit");


        btnExit.setBounds(750,390,220,45);


        add(btnExit);








        receiptArea =
        new JTextArea();



        receiptArea.setFont(
        new Font("Monospaced",Font.PLAIN,18)
        );


        receiptArea.setBorder(
        new TitledBorder("Generated Bill")
        );



        JScrollPane sp =
        new JScrollPane(receiptArea);



        sp.setBounds(620,500,380,150);



        add(sp);









        cmbFood.addActionListener(e->{


            switch(
            cmbFood.getSelectedItem().toString()
            ){


                case "Burger":
                txtPrice.setText("100");
                break;


                case "Pizza":
                txtPrice.setText("250");
                break;


                case "Coffee":
                txtPrice.setText("80");
                break;


                case "Fries":
                txtPrice.setText("120");
                break;


                case "Sandwich":
                txtPrice.setText("150");
                break;

            }


        });









        btnCalculate.addActionListener(e->{


            try{


                int qty =
                Integer.parseInt(
                txtQuantity.getText());



                double price =
                Double.parseDouble(
                txtPrice.getText());



                double subtotal =
                qty*price;



                double gst =
                subtotal*0.05;



                double total =
                subtotal+gst;



                txtSubtotal.setText(""+subtotal);

                txtGST.setText(""+gst);

                txtTotal.setText(""+total);



                receiptArea.setText(

                "----- RESTAURANT BILL -----\n\n"+
                "Customer : "+
                txtCustomer.getText()+"\n"+
                "Food     : "+
                cmbFood.getSelectedItem()+"\n"+
                "Quantity : "+qty+"\n"+
                "Price    : "+price+"\n"+
                "Subtotal : "+subtotal+"\n"+
                "GST      : "+gst+"\n"+
                "Total    : "+total

                );



            }
            catch(Exception ex){

                JOptionPane.showMessageDialog(null,
                "Enter Quantity");

            }



        });







        btnSave.addActionListener(e->{


            Order order =
            new Order(

            txtCustomer.getText(),

            cmbFood.getSelectedItem().toString(),

            Integer.parseInt(txtQuantity.getText()),

            Double.parseDouble(txtSubtotal.getText()),

            Double.parseDouble(txtGST.getText()),

            Double.parseDouble(txtTotal.getText())

            );



            mongo.saveOrder(order);



            JOptionPane.showMessageDialog(null,
            "Bill Saved Successfully");


        });







        btnClear.addActionListener(e->{


            txtCustomer.setText("");

            txtQuantity.setText("");

            txtPrice.setText("");

            txtSubtotal.setText("");

            txtGST.setText("");

            txtTotal.setText("");

            receiptArea.setText("");


        });





        btnExit.addActionListener(e->{


            System.exit(0);


        });




        setVisible(true);


    }




    public static void main(String args[]){

        new RestaurantGUI();

    }


}