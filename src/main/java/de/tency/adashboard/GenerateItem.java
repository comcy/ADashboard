
package de.tency.adashboard;

import static de.tency.adashboard.ItemBean.Item._status;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GenerateItem implements Serializable {
    
    private String name;
    private String description;
    private String prioritaet;
    private String aufwandseinschaetzung;
    private String startdatum;
    private String bearbeiter;
    private int status;
    
    
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
    
    public String getPrioritaet() {
        return prioritaet;
    }
    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }
    
    public String getAufwandseinschaetzung() {
        return aufwandseinschaetzung;
    }
    public void setAufwandseinschaetzung(String aufwandseinschaetzung) {
        this.aufwandseinschaetzung = aufwandseinschaetzung;
    }
    
    public String getStartdatum() {
        return startdatum;
    }
    public void setStartdatum(String startdatum) {
        this.startdatum = startdatum;
    }
    
    public String getBearbeiter() {
        return bearbeiter;
    }
    public void setBearbeiter(String bearbeiter) {
        this.bearbeiter = bearbeiter;
    }
    
    public int get_status() {
        return status;
    }
    public void set_status(int status) {
        this.status = status;
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
