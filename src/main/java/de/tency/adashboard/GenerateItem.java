
package de.tency.adashboard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GenerateItem {
    
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void check(){
        if(name.equals("")){
            description = "enter something";
        }else if(name.equals("Japp")){
            description = "sets reit";
        }else{
            description = "wrong stuff";
        }
    }
}
