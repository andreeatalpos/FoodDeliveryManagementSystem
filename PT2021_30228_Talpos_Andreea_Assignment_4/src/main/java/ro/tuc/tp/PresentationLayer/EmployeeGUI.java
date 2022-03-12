package ro.tuc.tp.PresentationLayer;

import ro.tuc.tp.BLL.DeliveryService;
import ro.tuc.tp.BLL.MenuItem;
import ro.tuc.tp.BLL.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends JFrame implements Observer {
    private JLabel titleLabel;

    private DeliveryService service;
    private JTextArea result;
    private JScrollPane scroll;
    private MainGUI main;
    public EmployeeGUI(DeliveryService service) {

        this.service = service;
        this.setBounds(500,150,400,400);
        this.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(217,148,95));
        this.getContentPane().setLayout(null);

        Font hugeFont= new Font("Serif",Font.BOLD,30);
        Font bigFont= new Font("Serif",Font.PLAIN,22);

        titleLabel = new JLabel("Employee interface");
        titleLabel.setFont(hugeFont);
        titleLabel.setBounds(80, 35, 450, 50);
        getContentPane().add(titleLabel);
        result = new JTextArea();
        result.setEditable(false);
        scroll = new JScrollPane(result);
        scroll.setBounds(40,100,300,200);
        scroll.setViewportView(result);
        getContentPane().add(scroll);
    }
    @Override
    public void update(Observable observable, Object object) {
        int x = JOptionPane.showConfirmDialog(null,"Ready to cook!","Notification !", 2);
        if (x == 0) {
            System.out.println("Chef will cook!");
            this.setVisible(false);
        } else {
            System.out.println("Chef is busy, but will still cook later!");
            this.setVisible(false);
        }
        String res="";
        float price=0;
        DeliveryService service = (DeliveryService) observable;
        Order order = (Order) object;
        ArrayList<MenuItem> orderedProducts = service.getOrdersInfo().get(order);
        res+="New order!!" +"\n";
        res+="Order ID: " +order.getOrderID()+"\n";
        res+="Client ID: " +order.getClientID()+"\n";
        res+="Date: "+ order.getDate()+"\n";
        res+="Products: " + "\n";
        System.out.println(orderedProducts);
        for(MenuItem item: service.getOrdersInfo().get(order)){
            res+="Title: " + item.getTitle()+ ", price: "+ item.computePrice()+"\n";
            price+=item.computePrice();
        }
        res+="\n";

        res+="TOTAL: " +price;
        result.setText(res);
        this.setVisible(true);
    }

}
