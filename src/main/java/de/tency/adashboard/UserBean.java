package de.tency.adashboard;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String role;
    
    DatabaseHandler dbh = new DatabaseHandler();

    public UserBean() {

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String showUsername(){
        
        String username = "";
        username = dbh.getUsername();
        
        return username;
    }
    
}
