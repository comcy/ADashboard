/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tency.adashboard;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.myfaces.component.html.ext.HtmlOutputText;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;

@ManagedBean
@RequestScoped
public class AgendaBacker extends IssueBean {
    
    private TabView tabView;
    DatabaseHandler dbHandler = new DatabaseHandler();
    String[][] allIssues;
    
    public AgendaBacker(){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        
        tabView = (TabView) application.createComponent(fc, "org.primefaces.component.TabView", "org.primefaces.component.TabViewRenderer");
        tabView.setId("accordionpanel");
        
        allIssues = IssueBean.getAllIssues(dbHandler.getIssueCount());
        
        for( int i = 0; i < dbHandler.getIssueCount(); i++ ) {
            Tab tab = (Tab) application.createComponent("org.primefaces.component.Tab");
            tab.setId("Issue" + allIssues[i][0] );
            tab.setTitle("Issue: " + allIssues[i][1]);
            tab.setInView(true);
            tab.setClosable(true);
            
            HtmlOutputText text = new HtmlOutputText();
            text.setValue(allIssues[i][2]);
            tab.getChildren().add(text);
            
            getAccordionPanel().getChildren().add(tab);
        }
    }
    
    public TabView getAccordionPanel() {
        return tabView;
    }
 
    public void setAccordionPanel(TabView tabView) {
        this.tabView = tabView;
    }
}