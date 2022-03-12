package ro.tuc.tp.PresentationLayer;

import ro.tuc.tp.DataLayer.Admin;
import ro.tuc.tp.BLL.DeliveryService;
import ro.tuc.tp.DataLayer.Employee;
import ro.tuc.tp.DataLayer.Client;
import ro.tuc.tp.DataLayer.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JLabel title;
    private JLabel identity;
    private JButton client;
    private JButton administrator;
    private JButton employee;

    private JLabel name;
    private JLabel id;
    private JLabel registered;
    private JTextField clientName;
    private JTextField clientId;

    private JButton logIn;
    private JButton register;
    private static int clId;
    private Client c;
    private Employee e;
    private Admin a;
    private int code=0;
    private int ok=0;
    private int ok2=0;
    private String passCl;
    private String passEm;


    public MainGUI(DeliveryService service, AdministratorGUI administratorGUI, EmployeeGUI employeeGUI, ClientGUI clientGUI){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500,150,800,600);
        this.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(217,148,95));

        Font hugeFont= new Font("Serif",Font.BOLD,40);
        Font bigFont= new Font("Serif",Font.BOLD,22);


        title = new JLabel("Food Delivery Management System");
        title.setFont(hugeFont);
        title.setBounds(100,50,700,50);
        getContentPane().add(title);

        identity = new JLabel("Choose your role:");
        identity.setFont(bigFont);
        identity.setBounds(100,350,700,50);
        getContentPane().add(identity);

        client  = new JButton("Client");
        client.setBounds(100,420,100,70);
        client.setFont(bigFont);
        getContentPane().add(client);
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                code=1;
                if(ok==1) {
                    service.newClient(++clId,clientName.getText(),id.getText());
                    setVisible(false);
                    clientGUI.setVisible(true);
                    c=new Client(clId,clientName.getText(),id.getText());
                    ok=0;
                }
                else if(ok2==1){
                    if(passCl.equals(id.getText()))
                    {setVisible(false);
                    clientGUI.setVisible(true);
                    ok2=0;}
                    else JOptionPane.showMessageDialog(null,"Wrong password");
                }

            }
        });
        administrator  = new JButton("Admin");
        administrator.setBounds(250,420,100,70);
        administrator.setFont(bigFont);
        getContentPane().add(administrator);
        administrator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                code=3;
                if(ok==1) {
                    service.newAdmin(clientName.getText(), id.getText());
                    setVisible(false);
                    administratorGUI.setVisible(true);
                    ok=0;
                }
                else if(ok2==1){
                    setVisible(false);
                    administratorGUI.setVisible(true);
                    ok2=0;
                }

            }
        });

        employee  = new JButton("Employee");
        employee.setBounds(400,420,150,70);
        employee.setFont(bigFont);
        getContentPane().add(employee);
        employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                code=2;
                if(ok==1) {
                    if(passEm.equals(id.getText()))
                    service.newEmployee(name.getText(), id.getText());
                    else JOptionPane.showMessageDialog(null,"Wrong password");
                }
                ok=0;
            }
        });

        name = new JLabel("Name:");
        name.setFont(bigFont);
        name.setBounds(50,150,100,30);
        getContentPane().add(name);

        clientName = new JTextField();
        clientName.setFont(bigFont);
        clientName.setBounds(150,150,200,30);
        getContentPane().add(clientName);

        id = new JLabel("Password:");
        id.setFont(bigFont);
        id.setBounds(50,200,100,30);
        getContentPane().add(id);

        clientId = new JTextField();
        clientId.setFont(bigFont);
        clientId.setBounds(150,200,200,30);
        getContentPane().add(clientId);

        logIn = new JButton("Login");
        logIn.setFont(bigFont);
        logIn.setBounds(70,250,100,50);
        getContentPane().add(logIn);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(code==1){
                    if(service.searchClient(clientName.getText())!=null){
                        ok2=1;
                        passCl=service.searchClient(clientName.getText());
                    }
                    else JOptionPane.showMessageDialog(null,"Can't find this username");
                }
                if(code==2){
                    if(service.searchEmployeeByName(clientName.getText())!=null){
                        ok2=1;
                        passEm=service.searchEmployeeByName(clientName.getText());
                    }
                    else JOptionPane.showMessageDialog(null,"Can't find this username");
                }
                if(code==3){
                    if(service.getAdminName()!=null){
                        ok2=1;
                    }
                    else JOptionPane.showMessageDialog(null,"Can't find this username");
                }
            }
        });

        register = new JButton("Register");
        register.setFont(bigFont);
        register.setBounds(230,250,150,50);
        getContentPane().add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(code==1){
                    if(service.searchClient(clientName.getText())==null){
                        ok=1;
                    }
                    else JOptionPane.showMessageDialog(null,"This username is already used!!");
                }
                if(code==2){
                    if(service.searchEmployeeByName(clientName.getText())==null){
                        ok=1;
                    }
                    else JOptionPane.showMessageDialog(null,"This username is already used!!");
                }
                if(code==3){
                    if(service.getAdminName()==null){
                        ok=1;
                    }
                    else JOptionPane.showMessageDialog(null,"You can't be administrator!!!");
                }
            }
        });
        Serializator.serialize(service);
    }
    public static int getClId(){
        return clId;
    }
}
