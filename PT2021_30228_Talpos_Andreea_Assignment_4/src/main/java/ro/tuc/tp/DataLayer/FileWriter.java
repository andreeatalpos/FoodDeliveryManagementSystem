package ro.tuc.tp.DataLayer;
import ro.tuc.tp.BLL.MenuItem;
import ro.tuc.tp.BLL.Order;

import java.io.IOException;
import java.util.List;

public class FileWriter {
    public static void generateBill(Order order,List<MenuItem> products) {
        int price=0;
        for(MenuItem item:products){
            price+=item.computePrice();
        }
        try {
            java.io.FileWriter writer = new java.io.FileWriter("bill.txt");
            writer.write(order.toString());
            writer.write("\n"+"Price:" + price+"\n");
            writer.write("Products: "+ products+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error at generating bill");
        }
    }
}
