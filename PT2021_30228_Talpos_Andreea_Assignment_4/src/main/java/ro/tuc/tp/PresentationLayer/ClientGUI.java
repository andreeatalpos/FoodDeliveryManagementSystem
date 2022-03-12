package ro.tuc.tp.PresentationLayer;

import ro.tuc.tp.BLL.*;
import ro.tuc.tp.BLL.MenuItem;
import ro.tuc.tp.DataLayer.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class ClientGUI extends JFrame {
    private JLabel title;
    private JButton viewMenu;
    private JButton back;
    private JLabel search;
    private JLabel createOrder;
    private JLabel productName;
    private static int orderId=0;
    private static int clientid=0;
    private JTextField key;
    private JTextField rating;
    private JTextField calories;
    private JTextField proteins;
    private JTextField fats;
    private JTextField sodium;
    private JTextField price;
    private JTextField product;
    private JTextArea result;
    private JScrollPane scroll;

    private JButton keyword;
    private JButton rat;
    private JButton cal;
    private JButton prot;
    private JButton fat;
    private JButton so;
    private JButton pr;
    private JButton add;
    private JButton ok;
    private DeliveryService service;
    private ArrayList<MenuItem> list;
    public ClientGUI(DeliveryService service){
        list = new ArrayList<>();
        this.service =service;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500,150,800,800);
        this.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(217,148,95));

        Font hugeFont= new Font("Serif",Font.BOLD,40);
        Font bigFont= new Font("Serif",Font.PLAIN,22);

        title = new JLabel("Client interface");
        title.setFont(hugeFont);
        title.setBounds(270,20,700,50);
        getContentPane().add(title);

        viewMenu = new JButton("View menu");
        viewMenu.setFont(bigFont);
        viewMenu.setBounds(130,450,150,50);
        getContentPane().add(viewMenu);
        viewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText(service.viewMenu());
            }
        });

        search = new JLabel("Search products by:");
        search.setFont(bigFont);
        search.setBounds(20,40,400,50);
        getContentPane().add(search);


        keyword = new JButton("Keyword");
        keyword.setFont(bigFont);
        keyword.setBounds(100,100,120,30);
        getContentPane().add(keyword);
        keyword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText(service.searchByName(key.getText()));
            }
        });

        key  =new JTextField();
        key.setFont(bigFont);
        key.setBounds(250, 100,100,30);
        getContentPane().add(key);

        rat = new JButton("Rating");
        rat.setFont(bigFont);
        rat.setBounds(100,150,120,30);
        getContentPane().add(rat);
        rat.addActionListener(e -> result.setText(service.searchByRating(Float.parseFloat(rating.getText()))));

        rating  =new JTextField();
        rating.setFont(bigFont);
        rating.setBounds(250, 150,100,30);
        getContentPane().add(rating);


        prot = new JButton("Proteins");
        prot.setFont(bigFont);
        prot.setBounds(100,200,120,30);
        getContentPane().add(prot);
        prot.addActionListener(e -> result.setText(service.searchByProteins(Integer.parseInt(proteins.getText()))));

        proteins  =new JTextField();
        proteins.setFont(bigFont);
        proteins.setBounds(250, 200,100,30);
        getContentPane().add(proteins);

        cal = new JButton("Calories");
        cal.setFont(bigFont);
        cal.setBounds(100,250,120,30);
        getContentPane().add(cal);
        cal.addActionListener(e -> result.setText(service.searchByCalories(Integer.parseInt(calories.getText()))));

        calories  =new JTextField();
        calories.setFont(bigFont);
        calories.setBounds(250, 250,100,30);
        getContentPane().add(calories);

        fat = new JButton("Fats");
        fat.setFont(bigFont);
        fat.setBounds(100,300,120,30);
        getContentPane().add(fat);
        fat.addActionListener(e->result.setText(service.searchByFats(Integer.parseInt(fats.getText()))));

        fats  =new JTextField();
        fats.setFont(bigFont);
        fats.setBounds(250, 300,100,30);
        getContentPane().add(fats);

        so = new JButton("Sodium");
        so.setFont(bigFont);
        so.setBounds(100,350,120,30);
        getContentPane().add(so);
        so.addActionListener(e->result.setText(service.searchBySodium(Integer.parseInt(sodium.getText()))));

        sodium =new JTextField();
        sodium.setFont(bigFont);
        sodium.setBounds(250, 350,100,30);
        getContentPane().add(sodium);

        pr = new JButton("Price");
        pr.setFont(bigFont);
        pr.setBounds(100,400,120,30);
        getContentPane().add(pr);
        pr.addActionListener(e->result.setText(service.searchByPrice(Integer.parseInt(price.getText()))));

        price  =new JTextField();
        price.setFont(bigFont);
        price.setBounds(250, 400,100,30);
        getContentPane().add(price);

        result = new JTextArea();
        result.setEditable(false);
        scroll = new JScrollPane(result);
        scroll.setBounds(400,100,300,400);
        scroll.setViewportView(result);
        getContentPane().add(scroll);

        back = new JButton("Back");
        back.setFont(bigFont);
        back.setBounds(20,710,100,50);
        getContentPane().add(back);

        createOrder = new JLabel("New order:");
        createOrder.setFont(bigFont);
        createOrder.setBounds(100,550,100,30);
        getContentPane().add(createOrder);

        productName = new JLabel("Product name:");
        productName.setFont(bigFont);
        productName.setBounds(100,600,150,30);
        getContentPane().add(productName);

        product = new JTextField();
        product.setBounds(250,600,200,30);
        product.setFont(bigFont);
        getContentPane().add(product);

        add=new JButton("Add");
        add.setFont(bigFont);
        add.setBounds(480,600,100,30);
        getContentPane().add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(service.searchProduct(product.getText())!=null){
                    list.add(service.searchProduct((product.getText())));
                }
            }
        });
        ok = new JButton("OK");
        ok.setFont(bigFont);
        ok.setBounds(450,670,80,30);
        getContentPane().add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.createOrder(++orderId,MainGUI.getClId(), new Date(),list);
                Serializator.serialize(service);
                list.clear();
            }
        });
    }
    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }
}
