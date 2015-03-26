/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tency.adashboard;

import static de.tency.adashboard.DatabaseHandler.result4;
import de.tency.adashboard.ItemBean.Item;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
 
@ManagedBean
@RequestScoped
public class DashboardBacker extends Dashboard {
 
    public static final int DEFAULT_COLUMN_COUNT = 3;
    private int columnCount = DEFAULT_COLUMN_COUNT;
    
    private Dashboard dashboard;
    
    GetItemsFromDB iNames = new GetItemsFromDB();
//    final int itemLength = ItemBean.itemCount();
    final int itemLength = 1;
    
    DatabaseHandler dbHandler = new DatabaseHandler();
    DatabaseHandler dbColumnStatusChanger = new DatabaseHandler();
    DatabaseHandler dbGetCurrentItemName = new DatabaseHandler();
    
    String[][] iNB;
    int[][] statusiNB;
    
    public DashboardBacker() {
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
        
//        System.out.println("YIEHH");
//        dbHandler.sendItemUpdate();
//        System.out.println("II WORKS");
        
        iNB = ItemBean.getAllItems(dbHandler.getItemCount());
        
        for( int i = 0; i < dbHandler.getItemCount(); i++ ) {
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            
            
            panel.setId("_" + iNB[i][0]);
            panel.setHeader("Item: " + iNB[i][1]);
            panel.setFooter("Prioritaet: " + iNB[i][3]);
            panel.setInView(true);
            panel.setClosable(true);
            panel.setToggleable(true);
            
            System.out.println("Sauf mi voll");
            
            getDashboard().getChildren().add(panel);
            
            Integer columnChooser = Integer.parseInt(iNB[i][7]);
            DashboardColumn column = model.getColumn(columnChooser%getColumnCount());
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue( iNB[i][2] );
 
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
    
    public void handleReorder(DashboardReorderEvent event) {
        String s = event.getWidgetId();
        Integer itemID = Integer.parseInt(s.substring(1));
        String currentItemName = dbGetCurrentItemName.getCurrentName(itemID);
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Neuordnung: Item " + "'" + currentItemName + "'");
        
        message.setDetail("von Spalte " + event.getSenderColumnIndex()
                + ", zu Spalte " + event.getColumnIndex());
 
        addMessage(message);
        dbColumnStatusChanger.changeColumnStatus(itemID, event.getColumnIndex());
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
