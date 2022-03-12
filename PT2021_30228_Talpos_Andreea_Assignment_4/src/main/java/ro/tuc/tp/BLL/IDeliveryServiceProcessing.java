package ro.tuc.tp.BLL;

import java.util.ArrayList;
import java.util.Date;

public interface IDeliveryServiceProcessing {
    /**
     * Adds a new menu item
     * @pre product !=null
     * @param product
     */
    void addMenuItem(BaseProduct product);

    /**
     *  Creates a composite menu item
     * @param products
     * @pre products!=null
     * @pre name!=null
     * @post menu.size = menu.size@pre +1
     * @param name
     */
     void createMenuItem(ArrayList<MenuItem> products, String name);

    /**
     * Deletes an existent menu item
     *@param product
     * @pre product!=null
     * @post menu.size = menu.size@pre -1
     */
    void deleteMenuItem(String product);

    /**
     * Edits an existent menu item
     * @param product
     * @pre product!=null
     */
    void editMenuItem(BaseProduct product);

    /**
     * Imports the initial set of products
     * @post menu.size!=0;
     */
    void importProducts();

    /**
     * Generates a report ordered between a given start and end hour
     * @param startHour
     * @param endHour
     * @return
     * @pre startHour>=0 && startHour<=23
     * @pre endHour>=0 && endHour<=23
     * @pre startHour < endHour
     */
    String generateReportTimeInterval(int startHour, int endHour);

    /**
     *  Generates a report with products which have beed ordered more than a given number of times
     * @param number
     * @return
     * @pre number>=0
     */
    String generateReportPopularProducts(int number);

    /**
     *  Generates a report with clients details who ordered more than a given times and a given value
     * @param times
     * @pre times>=0
     * @param value
     * @pre value>=0
     * @return
     */
    String generateReportLoyalClients(int times, int value);

    /**
     * Generates a report with products ordered in a given day
     * @param day
     * @pre day>=-1 && day<7
     * @return
     */
    String generateReportDaysProducts(int day);

    /**
     * Creates a new order order
     * @param orderId
     * @pre orderId>=1
     * @param clientId
     * @pre clientId>=1
     * @param date
     * @param items
     * @pre items!=null
     */
    void createOrder(int orderId, int clientId, Date date,ArrayList<MenuItem> items);

    /**
     * Searches and returns the products with the given name
     * @pre str!=null
     * @param str
     * @return
     */
    String searchByName(String str);

    /**
     * Searches and returns the products with the given value of rating
     * @pre rating>=0
     * @param rating
     * @return
     */
    String searchByRating(float rating);

    /**
     * Searches and returns the products with the given value of calories
     * @pre calories>=0
     * @param calories
     * @return
     */
    String searchByCalories(int calories);

    /**
     * Searches and returns the products with the given value of proteins
     * @pre proteins>=0
     * @param proteins
     * @return
     */
    String searchByProteins(int proteins);

    /**
     * @pre fats>=0
     * @param fats
     * @return
     */
    String searchByFats(int fats);

    /**
     * Searches and returns the products with the given value of sodium
     * @pre sodium>=0
     * @param sodium
     * @return
     */
    String searchBySodium(int sodium);

    /**
     * Searches and returns the products with the given price
     * @pre price>0
     * @param price
     * @return
     */
   String searchByPrice(int price);

   /* /**
     * Generates a bill with the details of the order
     * @pre id>0
     * @param id
     */
   // void generateBill(int id);*/

    /**
     * Lists the menu items
     * @return
     */
   String viewMenu();
}
