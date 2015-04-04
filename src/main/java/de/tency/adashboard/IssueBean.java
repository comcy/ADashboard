/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tency.adashboard;

/**
 *
 * @author 10tacle
 */

import de.tency.adashboard.DatabaseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "issueBean")
@RequestScoped
public class IssueBean implements Serializable {

    FacesContext context = FacesContext.getCurrentInstance();
    private static Logger logger = Logger.getLogger(ItemBean.class
            .getCanonicalName());
    private static final long serialVersionUID = 2L;

    static DatabaseHandler dbh = new DatabaseHandler();

    static GenerateItem gI = new GenerateItem();

    public static int id;
    public static String name;
    public static String beschreibung;
    public static String datum;
    public static String ersteller;

    public static List<Issue> allItems = new ArrayList<Issue>();

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getBeschreibung() {
        return beschreibung;
    }


    public static String getDatum() {
        return datum;
    }

    public static String getBearbeiter() {
        return ersteller;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setBearbeiter(String ersteller) {
        this.ersteller = ersteller;
    }

    public IssueBean() {
    }

    public static int itemCount() {
        return dbh.getItemCount();
    }

    public String sendIssueUpdate(String name, String description, String datum) {

        String ersteller = dbh.getUsername();
        dbh.sendIssueUpdate(name, description, datum, ersteller);
        return "dashboard.jsf";
//        logger.info("Item succefully sent to DashboardDB!");

    }
    
    public static void changeColumnStatus(int id, int newStatus){
        dbh.changeColumnStatus(id, newStatus);
    }
    
    public static String getCurrentItemBearbeiter(int id){
       String s = dbh.getCurrentItemBearbeiter(id);
       return s;
    }
    
    public static void changeItemBearbeiter(int id, String newBearbeiter){
        dbh.changeItemBearbeiter(id, newBearbeiter);
    }
    
    /**
     * Listen
     *
     * @param allIssues
     */
    public void setAllIssues(List<Issue> allIssues) {
        this.allItems = allIssues;
    }

    public static String[][] getAllIssues(int count) {
        return dbh.listAllIssues(count);
    }

    public void addToAllIssues(Issue i) {
        allItems.add(i);
    }

    public void emptyAllIssues() {
        allItems.clear();
    }

    public static class Issue implements Serializable {

        private static final long serialVersionUID = 1L;

        DatabaseHandler dbh = new DatabaseHandler();

        public static int _id;
        public static String _datum;
        public static String _name;
        public static String _beschreibung;
        public static String _ersteller;

        public Issue(
                int id,
                String datum,
                String name,
                String beschreibung,
                String ersteller) {
//            System.out.println("Item-list Constructor");
            _id = id;
            _datum = datum;
            _name = name;
            _beschreibung = beschreibung;
            _ersteller = ersteller;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public int get_id() {
            return _id;
        }

        public void set_datum(String _datum) {
            this._datum = _datum;
        }

        public String get_datum() {
            return _datum;
        }

        public void set_name(String _name) {
            this._name = _name;
        }

        public static String get_name() {
            return _name;
        }

        public void set_beschreibung(String _beschreibung) {
            this._beschreibung = _beschreibung;
        }

        public String get_beschreibung() {
            return _beschreibung;
        }

        public void set_ersteller(String _ersteller) {
            this._ersteller = _ersteller;
        }

        public String get_ersteller() {
            return _ersteller;
        }

    }
}

