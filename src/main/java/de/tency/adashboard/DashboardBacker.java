/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tency.adashboard;

import static de.tency.adashboard.DatabaseHandler.result4;
import de.tency.adashboard.ItemBean.Item;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
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
    
    private DashboardModel model;
    
    private Dashboard dashboard;
    
    GetItemsFromDB iNames = new GetItemsFromDB();
//    final int itemLength = ItemBean.itemCount();
    final int itemLength = 1;
    
    DatabaseHandler dbHandler = new DatabaseHandler();
    DatabaseHandler dbColumnStatusChanger = new DatabaseHandler();
    DatabaseHandler dbGetCurrentItemName = new DatabaseHandler();
    DatabaseHandler dbGetCurrentItemBearbeiter = new DatabaseHandler();
    DatabaseHandler dbChangeCurrentItemBearbeiter = new DatabaseHandler();
    
    
    
    
    String[][] iNB;
    int[][] statusiNB;
    
    MethodExpression listener;
    
    public DashboardBacker() {
        
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
         
        column1.addWidget("Vorrat");
        column2.addWidget("Bearbeitung");
        column3.addWidget("Fertig");
 
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        
        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");
 
//        DashboardModel model = new DefaultDashboardModel();
        for( int i = 0, n = getColumnCount(); i < n; i++ ) {
            DashboardColumn column = new DefaultDashboardColumn();
            model.addColumn(column);
        }
        dashboard.setModel(model);
        
        iNB = ItemBean.getAllItems(dbHandler.getItemCount());
        
        for( int i = 0; i < dbHandler.getItemCount(); i++ ) {
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("_" + iNB[i][0]);
            panel.setHeader("Item: " + iNB[i][1]);
            panel.setFooter("Bearbeiter: " + iNB[i][6]);
            panel.setInView(true);
            panel.setClosable(true);
            panel.setToggleable(true);
//            panel.setStyleClass("items");
            
//            Panel tp = new Panel();
//            FacesContext context = FacesContext.getCurrentInstance();
//            final ELContext elContext = context.getELContext();
//            
//            
//            ExpressionFactory ef = application.getExpressionFactory();
//            MethodExpression me = ef.createMethodExpression(fc.getELContext(), "#{dashboardBacker.handleClose}", null, new Class<?>[]{BehaviorEvent.class});
//            AjaxBehavior ajaxBehavior = (AjaxBehavior) application.createBehavior(AjaxBehavior.BEHAVIOR_ID);
//            ajaxBehavior.setProcess("@this");
//            ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(me, me));
//            panel.addClientBehavior("close", ajaxBehavior);
            
            
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
//        System.out.println(iNB[0][6]);
        System.out.println(dbGetCurrentItemBearbeiter.getCurrentItemBearbeiter(itemID));
        System.out.println(dbGetCurrentItemBearbeiter.getUsername());
        if(dbGetCurrentItemBearbeiter.getCurrentItemBearbeiter(itemID).equals(dbGetCurrentItemBearbeiter.getUsername()) || event.getSenderColumnIndex() >= 1) {
            System.out.println("Aktueller User und Bearbeiter, der Item angelegt hat, sind gleich");
        } else {
            dbChangeCurrentItemBearbeiter.changeItemBearbeiter(itemID, dbGetCurrentItemBearbeiter.getUsername());
        }
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
     public void handleClose(CloseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");  
          
        addMessage(message);  
    }  
}
