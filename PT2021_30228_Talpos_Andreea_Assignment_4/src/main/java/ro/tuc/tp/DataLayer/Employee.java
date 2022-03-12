package ro.tuc.tp.DataLayer;


import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    public String password;
    public Employee(String name, String password){
        this.name=name;
        this.password=password;
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
