package ro.tuc.tp.BLL;


import ro.tuc.tp.DataLayer.Admin;
import ro.tuc.tp.DataLayer.Client;
import ro.tuc.tp.DataLayer.Employee;
import ro.tuc.tp.DataLayer.FileWriter;

import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 * The class which implements the methods which solves the requirements of the clients and administrator
 * @invariant wellFormed();
 */

public class DeliveryService  extends Observable implements IDeliveryServiceProcessing, Serializable {
    private Map<Order, ArrayList<MenuItem>> orders;
    private ArrayList<MenuItem> menu;
    private ArrayList<Order> ord;
    private ArrayList<Client> clients;
    private ArrayList<Employee> employees;
    private ro.tuc.tp.DataLayer.FileWriter writer;
    private Admin administrator;
    private int price=0;
    private String products="";

    /**
     * the constructor
     */
    public DeliveryService() {
        orders = new HashMap<Order, ArrayList<MenuItem>>();
        ord = new ArrayList<>();
        clients = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public MenuItem searchProduct(String name){
        for(MenuItem itm: menu){
            if(itm.getTitle().equals(name)){
                return itm;
            }
        }
        return null;
    }
    @Override
    public void addMenuItem(BaseProduct product) {
        assert product !=null;
        assert wellFormed();
        menu.add(product);
        assert wellFormed();
    }

    public void createMenuItem(ArrayList<MenuItem> products, String name) {
        assert wellFormed();
        assert products!=null;
        MenuItem newItem = new CompositeProduct(name, products);
        newItem.setName(name);
        newItem.computePrice();
        newItem.computeCalories();
        newItem.computeFats();
        newItem.computeProteins();
        newItem.computeRating();
        newItem.computeSodium();
        menu.add(newItem);
        assert wellFormed();
    }

    @Override
    public void deleteMenuItem(String product) {
        assert product!=null;
        assert wellFormed();
        menu.removeIf(m -> m.getTitle().equals(product));
        assert wellFormed();
    }
    public String getAdminName(){
        if(administrator==null) return null;
        return administrator.getName();
    }
    public void setAdministratorName(String name){
        administrator.setName(name);
    }
    @Override
    public void editMenuItem(BaseProduct product) {
        assert product!=null;
        assert wellFormed();
        int size=menu.size();
        for (MenuItem m : menu) {
            if (m.getTitle().equals(product.getTitle())) {
                menu.remove(m);
                menu.add(product);
            }
        }
        assert menu.size()==size;
        assert wellFormed();
    }

    public Order findByID(int id) {
        assert id>=1;
        for (Order o : ord) {
            if (o.getOrderID() == id)
                return o;
        }
        return null;
    }
    public String searchClient(String name){
        assert name!=null;
        for(Client c: clients){
            if(c.getName().equals(name))
                return c.getPassword();
        }
        return null;
    }
    public String searchEmployeeByName(String name){
        assert name!=null;
        for(Employee e: employees){
            if(e.getName().equals(name)){
                return e.getPassword();
            }
        }
        return null;
    }
    @Override
    public void importProducts() {
        CsvReader reader = new CsvReader();
        menu=new ArrayList<>();
        menu.addAll(reader.processInputFile("products.csv"));
        assert wellFormed();
    }

    @Override
    public String generateReportTimeInterval(int startHour, int endHour) {
        assert startHour>=0 && startHour<=24;
        assert endHour>=0 && endHour<=24;
        assert startHour < endHour;
        String result = "";
        List<Order> orders = ord.stream().filter(o -> o.getDate().getHours() >= startHour && o.getDate().getHours() <= endHour).collect(Collectors.toList());
        for (Order o : orders) {
            result += o.toString();
        }
        result+="\n";
        return result;
    }

    @Override
    public String generateReportPopularProducts(int number) {
        assert number>=0;
        long freq=0;
        String result = "";
        for (MenuItem item : menu) {
            freq = orders.entrySet().stream().filter(p -> p.getValue().contains(item)).count();
            if (freq >= number)
                result += item.getTitle() + "\n";
        }
        return result;
    }

    @Override
    public String generateReportLoyalClients(int times, int value) {
        assert times>=0 && value >=0;
        String result = "";
       for (Client c : clients) {
            long nb = orders.entrySet().stream().filter(p -> p.getKey().getClientID() == c.getClientID()).count();
            System.out.println("times:" + nb);
            if (nb >= times) {
                int val = 0;
                List<Order> filtered = ord.stream().filter(p -> p.getClientID() == c.getClientID()).collect(Collectors.toList());
                for (Order o : filtered) {
                    List<MenuItem> list = orders.get(o);
                    for(MenuItem item:list){
                        val+=item.computePrice();
                    }
                }
                if (val >= value) {
                    result += c.getName() + "\n";
                }
            }
        }
        return result;
    }

    @Override
    public String generateReportDaysProducts(int day) {
        assert day>=0 &&day<=6;
        String result = "";
        List<MenuItem> products = orders.entrySet().stream().filter(p -> p.getKey().getDate().getDay() == day).flatMap(p -> p.getValue().stream()).collect(Collectors.toList());
        for (MenuItem itm : products) {
            long times = orders.entrySet().stream().filter(p -> p.getValue().contains(itm)).count();
            result += itm.getTitle() + " ,times: " + times + "\n";
        }
        return result;
    }

    public void newClient(int clientID, String name, String password) {
        assert clientID>0 && name!=null && password !=null;
        clients.add(new Client(clientID, name, password));
    }
    public void newEmployee(String name, String password) {
        assert name!=null && password !=null;
        employees.add(new Employee( name, password));
    }
    public void newAdmin(String name, String password){
        assert name!=null && password !=null;
        administrator = new Admin(name, password);
    }
    public Admin getAdministrator(){
        return administrator;
    }

    @Override
    public void createOrder(int orderId, int clientId, Date date, ArrayList<MenuItem> items) {
        assert orderId>0 && clientId>0 && items.size()!=0;
        Order newOrder = new Order(orderId, clientId, date);
        ord.add(newOrder);
        orders.put(newOrder, items);
        for(MenuItem it : items){
            price+=it.computePrice();
            products+=it.getTitle()+"\n";
        }
        setChanged();
        notifyObservers(newOrder);
        FileWriter.generateBill(newOrder, orders.get(newOrder));
        assert price>0 && products!=null;
    }

    @Override
    public String searchByName(String str) {
        assert str!=null;
        String result = "";
        List<MenuItem> it = menu.stream().filter(p -> p.getTitle().toUpperCase().contains(str)).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }

    public String searchByRating(float rating) {
        assert rating>=0;
        String result = "";
        List<MenuItem> it = menu.stream().filter(p -> p.computeRating() == rating).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }

    public String searchByCalories(int calories) {
        assert calories>=0;
        String result = "";
        List<MenuItem> it = menu.stream().filter(p -> ( p).computeCalories() == calories).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }

    public String searchByProteins(int proteins) {
        assert proteins>=0;
        String result=null;
        List<MenuItem> it = menu.stream().filter(p -> p.computeProteins() == proteins).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }

    public String searchByFats(int fats) {
        assert fats>=0;
        String result = "";
        List<MenuItem> it = menu.stream().filter(p -> p.computeFats() == fats).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }

    public String searchBySodium(int sodium) {
        assert sodium>=0;
        String result = "";
        List<MenuItem> it = menu.stream().filter(p ->  p.computeSodium() == sodium).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }

    public String searchByPrice(int price) {
        assert price>=0;
        String result = "";
        List<MenuItem> it = menu.stream().filter(p -> p.computePrice() == price).collect(Collectors.toList());
        for(MenuItem m: it){
            result+=m.toString();
        }
        return result;
    }
    public Map<Order, ArrayList<MenuItem>> getOrdersInfo(){
        return orders;
    }
    public String viewMenu(){
        String result = "";
        for(MenuItem m: menu){
            result+=m.toString();
        }
        return result;
    }
    public boolean wellFormed(){
        ArrayList<MenuItem> auxList=new ArrayList<>();
        for(MenuItem item: menu){
            if(!auxList.contains(item)){
                auxList.add(item);
            }
        }
        if(menu.size()==auxList.size())
            return true;
        return false;
    }
}
