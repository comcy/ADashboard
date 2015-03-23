package de.tency.adashboard;


import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
 
@ManagedBean
@SessionScoped
public class DuHure extends ItemBean {
 
    public static final int DEFAULT_COLUMN_COUNT = 3;
    private int columnCount = DEFAULT_COLUMN_COUNT;
     
    private Dashboard dashboard;
 
    GetItemsFromDB iNames = new GetItemsFromDB();
    
    public DuHure() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
 
        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");
 
        DashboardModel model = new DefaultDashboardModel();
        for( int i = 0, n = getColumnCount(); i < n; i++ ) {
            DashboardColumn column = new DefaultDashboardColumn();
            model.addColumn(column);
        }
        dashboard.setModel(model);
 
        int items = 5;
         
        for( int i = 0, n = items; i < n; i++ ) {
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("measure_" + ItemBean.allItems.get(i));
            panel.setHeader("Item " + GetItemsFromDB.itemNames.get(i));
            panel.setClosable(true);
            panel.setToggleable(true);
            
            System.out.println("Sauf mi voll");
            System.out.println(ItemBean.allItems.get(i));
//            for(String ausgabe : iNames){
//                System.out.println(ausgabe);
//            }
            
            getDashboard().getChildren().add(panel);
            DashboardColumn column = model.getColumn(i%getColumnCount());
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue("Beschreibung des Items" );
 
            panel.getChildren().add(text);
        }
    }
 
    public Dashboard getDashboard() {
        return dashboard;
    }
 
    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
 
    public int getColumnCount() {
        return columnCount;
    }
 
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
