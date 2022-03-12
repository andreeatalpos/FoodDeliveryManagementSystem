package ro.tuc.tp.DataLayer;

import java.io.Serializable;

public class Client implements Serializable {
    private int clientID;
    private String name;
    public String password;

    public Client(int id, String name, String password) {
        this.clientID = id;
        this.name = name;
        this.password = password;
    }

    public Client findById(int id) {
        if (this.clientID == id)
            return this;
        return null;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
}
