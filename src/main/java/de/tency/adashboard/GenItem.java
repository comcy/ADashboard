package de.tency.adashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 

@ManagedBean
@ViewScoped
public class GenItem {
 
    private String buttonValue = "YIEHHHH";

    public void setButtonValue(String buttonValue) {
    this.buttonValue = buttonValue;
    }

    public String getButtonValue() {
    return buttonValue;
    }
}