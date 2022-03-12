package ro.tuc.tp.DataLayer;


import java.io.Serializable;

public class Admin implements Serializable {
    private String name;
    public String password;
    public Admin(String name, String password){
        this.password=password;
        this.name=name;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
