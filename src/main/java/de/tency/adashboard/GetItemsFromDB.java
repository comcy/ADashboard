package de.tency.adashboard;
 
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
 
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
 
@ManagedBean
@SessionScoped
public class GetItemsFromDB {  
   
    public static ArrayList<String> itemNames = new ArrayList<String>();
    
    public GetItemsFromDB() {
     
    }  
    public ArrayList<String> getItemNames(){
        
        itemNames.add("Datenbank anlegen");
        itemNames.add("Button erstellen");
        itemNames.add("Einen saufen gehn");
        itemNames.add("A");
        itemNames.add("B");
        itemNames.add("C");
        
        return itemNames;
    }
    
}  