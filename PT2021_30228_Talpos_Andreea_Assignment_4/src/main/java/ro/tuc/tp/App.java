package ro.tuc.tp;

import ro.tuc.tp.BLL.DeliveryService;
import ro.tuc.tp.DataLayer.Serializator;
import ro.tuc.tp.PresentationLayer.AdministratorGUI;
import ro.tuc.tp.PresentationLayer.ClientGUI;
import ro.tuc.tp.PresentationLayer.EmployeeGUI;
import ro.tuc.tp.PresentationLayer.MainGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        DeliveryService ds = Serializator.Deserialize();
        EmployeeGUI emp = new EmployeeGUI(ds);
        AdministratorGUI admin = new AdministratorGUI(ds);
        ClientGUI client = new ClientGUI(ds);
        MainGUI main = new MainGUI(ds, admin,emp,client);
        main.setVisible(true);
        client.addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.setVisible(false);
                main.setVisible(true);
            }
        });
        admin.addBackActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin.setVisible(false);
                main.setVisible(true);
            }
        });
        ds.addObserver(emp);
    }
}
