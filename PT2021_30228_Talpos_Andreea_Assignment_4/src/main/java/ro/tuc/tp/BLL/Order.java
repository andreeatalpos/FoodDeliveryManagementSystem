package ro.tuc.tp.BLL;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private Date date;
    public Order(int orderID, int clientID, Date date){
        this.clientID=clientID;
        this.orderID=orderID;
        this.date=date;
    }
    public int hashCode(){
        return date.hashCode()+orderID+clientID;
    }
    @Override
    public String toString() {
        return "OrderID: " + orderID + "\n"
                +"Date: " + date + "\n"+
                "ClientID: " + clientID+"\n";
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
