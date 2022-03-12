package ro.tuc.tp.DataLayer;

import ro.tuc.tp.BLL.DeliveryService;

import java.io.*;

public class Serializator {
    public static void serialize(DeliveryService service) {
        try {
            FileOutputStream fileOut = new FileOutputStream("DeliveryService.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(service);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Error at serialize");
            i.printStackTrace();
        }
    }

    public static DeliveryService Deserialize() {
        DeliveryService service;
        try {
            FileInputStream fileIn = new FileInputStream("DeliveryService.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            service = (DeliveryService) in.readObject();
            System.out.println(service.findByID(1));
            in.close();
            fileIn.close();
            return service;
        } catch (IOException i) {
            service = new DeliveryService();
            serialize(service);
            return service;
        } catch (ClassNotFoundException c) {
            System.out.println("Error: class not found");
            c.printStackTrace();
            return new DeliveryService();
        }
    }

}
