package ro.tuc.tp.PresentationLayer;

import ro.tuc.tp.BLL.BaseProduct;
import ro.tuc.tp.BLL.DeliveryService;
import ro.tuc.tp.BLL.MenuItem;
import ro.tuc.tp.DataLayer.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdministratorGUI extends JFrame {
    private JButton importProducts;
    private JButton add;
    private JButton delete;
    private JButton edit;
    private JButton create;
    private JButton ok;
    private JButton timeInterval;
    private JButton favorites;
    private JButton loyalClients;
    private JButton specificDay;
    private JButton back;

    private JLabel title;
    private JLabel manageProducts;
    private JLabel name;
    private JLabel rating;
    private JLabel calories;
    private JLabel proteins;
    private JLabel fats;
    private JLabel sodium;
    private JLabel price;
    private JLabel generateReports;
    private JLabel sHour;
    private JLabel eHour;
    private JLabel times;
    private JLabel val;
    private JLabel rapDay;
    private JLabel menuTitle;

    private JTextField key;
    private JTextField rat;
    private JTextField cal;
    private JTextField prot;
    private JTextField fat;
    private JTextField so;
    private JTextField pr;
    private JTextField startHour;
    private JTextField endHour;
    private JTextField nbTimes;
    private JTextField value;
    private JTextField day;
    private JTextField menu;

    private JTextArea report;
    private JScrollPane scroll;
    private DeliveryService service;
    private ArrayList<MenuItem> list;
    public AdministratorGUI(DeliveryService service) {
        this.service=service;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 50, 800, 1000);
        this.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(217, 148, 95));
        list=new ArrayList<>();
        Font hugeFont = new Font("Serif", Font.BOLD, 40);
        Font bigFont = new Font("Serif", Font.PLAIN, 22);

        title = new JLabel("Administrator interface");
        title.setFont(hugeFont);
        title.setBounds(200, 20, 700, 50);
        getContentPane().add(title);

        manageProducts = new JLabel("Manage products");
        manageProducts.setFont(bigFont);
        manageProducts.setBounds(50, 150, 200, 30);
        getContentPane().add(manageProducts);

        name = new JLabel("Name:");
        name.setFont(bigFont);
        name.setBounds(100, 200, 120, 30);
        getContentPane().add(name);

        key = new JTextField();
        key.setFont(bigFont);
        key.setBounds(200, 200, 120, 30);
        getContentPane().add(key);

        rating = new JLabel("Rating:");
        rating.setFont(bigFont);
        rating.setBounds(100, 250, 120, 30);
        getContentPane().add(rating);

        rat = new JTextField();
        rat.setFont(bigFont);
        rat.setBounds(200, 250, 120, 30);
        getContentPane().add(rat);

        calories = new JLabel("Calories:");
        calories.setFont(bigFont);
        calories.setBounds(100, 300, 120, 30);
        getContentPane().add(calories);

        cal = new JTextField();
        cal.setFont(bigFont);
        cal.setBounds(200, 300, 120, 30);
        getContentPane().add(cal);

        proteins = new JLabel("Proteins:");
        proteins.setFont(bigFont);
        proteins.setBounds(100, 350, 120, 30);
        getContentPane().add(proteins);

        prot = new JTextField();
        prot.setFont(bigFont);
        prot.setBounds(200, 350, 120, 30);
        getContentPane().add(prot);

        fats = new JLabel("Fats:");
        fats.setFont(bigFont);
        fats.setBounds(100, 400, 120, 30);
        getContentPane().add(fats);

        fat = new JTextField();
        fat.setFont(bigFont);
        fat.setBounds(200, 400, 120, 30);
        getContentPane().add(fat);

        sodium = new JLabel("Sodium:");
        sodium.setFont(bigFont);
        sodium.setBounds(100, 450, 120, 30);
        getContentPane().add(sodium);

        so = new JTextField();
        so.setFont(bigFont);
        so.setBounds(200, 450, 120, 30);
        getContentPane().add(so);

        price = new JLabel("Price:");
        price.setFont(bigFont);
        price.setBounds(100, 500, 120, 30);
        getContentPane().add(price);

        pr = new JTextField();
        pr.setFont(bigFont);
        pr.setBounds(200, 500, 120, 30);
        getContentPane().add(pr);

        menuTitle = new JLabel("Menu title:");
        menuTitle.setFont(bigFont);
        menuTitle.setBounds(400, 400, 120, 30);
        getContentPane().add(menuTitle);

        menu = new JTextField();
        menu.setFont(bigFont);
        menu.setBounds(500, 400, 120, 30);
        getContentPane().add(menu);

        add = new JButton("Add");
        add.setBounds(400, 250, 100, 30);
        add.setFont(bigFont);
        getContentPane().add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.addMenuItem(new BaseProduct(key.getText(),Float.valueOf(rat.getText()),Integer.valueOf(cal.getText()),Integer.valueOf(prot.getText()),Integer.valueOf(fat.getText()),Integer.valueOf(so.getText()),Integer.parseInt(pr.getText())));
                Serializator.serialize(service);
            }
        });

        delete = new JButton("Delete");
        delete.setFont(bigFont);
        delete.setBounds(400, 300, 100, 30);
        getContentPane().add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.deleteMenuItem(key.getText());
                Serializator.serialize(service);
            }
        });

        edit = new JButton("Edit");
        edit.setBounds(400, 350, 100, 30);
        edit.setFont(bigFont);
        getContentPane().add(edit);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.editMenuItem(new BaseProduct(key.getText(),Float.valueOf(rat.getText()),Integer.valueOf(cal.getText()),Integer.valueOf(prot.getText()),Integer.valueOf(fat.getText()),Integer.valueOf(so.getText()),Integer.valueOf(pr.getText())) );
                Serializator.serialize(service);
            }
        });

        create = new JButton("Create");
        create.setFont(bigFont);
        create.setBounds(400,450,100,30);
        getContentPane().add(create);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem p = service.searchProduct(key.getText());
                list.add(p);
            }
        });

        ok=new JButton("OK");
        ok.setFont(bigFont);
        ok.setBounds(550,450,80,30);
        getContentPane().add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               service.createMenuItem(list,menu.getText());
                Serializator.serialize(service);
            }
        });

        importProducts = new JButton("Import");
        importProducts.setFont(bigFont);
        importProducts.setBounds(400,500,100,30);
        getContentPane().add(importProducts);
        importProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.importProducts();
                Serializator.serialize(service);
            }

        });

        generateReports = new JLabel("Generate reports based on:");
        generateReports.setFont(bigFont);
        generateReports.setBounds(50,600,500,30);
        getContentPane().add(generateReports);

        sHour = new JLabel("Start hour:");
        sHour.setFont(bigFont);
        sHour.setBounds(20,650,150,30);
        getContentPane().add(sHour);

        startHour = new JTextField();
        startHour.setFont(bigFont);
        startHour.setBounds(120,650,60,30);
        getContentPane().add(startHour);

        eHour = new JLabel("End hour:");
        eHour.setFont(bigFont);
        eHour.setBounds(20,700,150,30);
        getContentPane().add(eHour);

        endHour = new JTextField();
        endHour.setFont(bigFont);
        endHour.setBounds(120,700,60,30);
        getContentPane().add(endHour);

        times = new JLabel("Times:");
        times.setFont(bigFont);
        times.setBounds(20,750,150,30);
        getContentPane().add(times);

        nbTimes = new JTextField();
        nbTimes.setFont(bigFont);
        nbTimes.setBounds(120,750,60,30);
        getContentPane().add(nbTimes);

        val = new JLabel("Value:");
        val.setFont(bigFont);
        val.setBounds(20,800,150,30);
        getContentPane().add(val);

        value = new JTextField();
        value.setFont(bigFont);
        value.setBounds(120,800,60,30);
        getContentPane().add(value);

        rapDay = new JLabel("Day:");
        rapDay.setFont(bigFont);
        rapDay.setBounds(20,850,150,30);
        getContentPane().add(rapDay);

        day = new JTextField();
        day.setFont(bigFont);
        day.setBounds(120,850,60,30);
        getContentPane().add(day);

        timeInterval = new JButton("Time interval");
        timeInterval.setFont(bigFont);
        timeInterval.setBounds(200,680,170,40);
        getContentPane().add(timeInterval);
        timeInterval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportTimeInterval(Integer.valueOf(startHour.getText()),Integer.valueOf(endHour.getText())));
            }
        });

        favorites = new JButton("Favorites");
        favorites.setFont(bigFont);
        favorites.setBounds(200,730,170,40);
        getContentPane().add(favorites);
        favorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportPopularProducts(Integer.valueOf(nbTimes.getText())));
            }
        });

        loyalClients = new JButton("Loyal clients");
        loyalClients.setFont(bigFont);
        loyalClients.setBounds(200,780,170,40);
        getContentPane().add(loyalClients);
        loyalClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportLoyalClients(Integer.valueOf(nbTimes.getText()),Integer.valueOf(value.getText())));
            }
        });

        specificDay = new JButton("Specific day");
        specificDay.setFont(bigFont);
        specificDay.setBounds(200,830,170,40);
        getContentPane().add(specificDay);
        specificDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportDaysProducts(Integer.valueOf(day.getText())));
            }
        });

        report = new JTextArea();
        report.setEditable(false);
        scroll = new JScrollPane(report);
        scroll.setBounds(430,600,300,400);
        scroll.setViewportView(report);
        getContentPane().add(scroll);

        back=new JButton("Back");
        back.setFont(bigFont);
        back.setBounds(20,900,100,30);
        getContentPane().add(back);

    }
    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }

}
