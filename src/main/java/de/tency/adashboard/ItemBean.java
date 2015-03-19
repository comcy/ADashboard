package de.tency.adashboard;

import de.tency.adashboard.DatabaseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import java.sql.Date;
import java.sql.Timestamp;
import javax.faces.event.ActionEvent;
import org.apache.myfaces.custom.datascroller.ScrollerActionEvent;

/**
 *
 * @author Christian Silfang
 */
@ManagedBean(name = "itemBean")
@RequestScoped
public class ItemBean implements Serializable {

    FacesContext context = FacesContext.getCurrentInstance();
    private static Logger logger = Logger.getLogger(ItemBean.class
            .getCanonicalName());
    private static final long serialVersionUID = 2L;

    DatabaseHandler dbh = new DatabaseHandler();

    private int id;
    private String name;
    private String beschreibung;
    private String prioritaet;
    private String aufwand;
    private String datum;
    private String ersteller;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public String getAufwand() {
        return aufwand;
    }

    public String getDatum() {
        return datum;
    }

    public String getErsteller() {
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

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public void setAufwand(String aufwand) {
        this.aufwand = aufwand;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setErsteller(String ersteller) {
        this.ersteller = ersteller;
    }

    private List<Item> allItems = new ArrayList<Item>();

    public ItemBean() {
    }

    public int itemCount() {
        return dbh.getItemCount();
    }
    
    public String sendItemUpdate(String name, String beschreibung, String aufwand, String prioritaet) {

        String username = "";// --> Abfrage für aktuellen User einbauen

        dbh.sendItemUpdate(name, beschreibung, aufwand, prioritaet, username);

        logger.info("Item succefully sent to DashboardDB!");
        return "items";
    }
    
    public void setAllItems( List<Item> allItems ) {
        this.allItems = allItems;
    }

    public List<Item> getAllItems() {
        return dbh.listAllItems();
    }

    public void addToAllItemsList(Item i) {
        allItems.add(i);
    }

    public void emptyAllItemsList() {
        allItems.clear();
    }

    public static class Item implements Serializable {

        private static final long serialVersionUID = 1L;
        DatabaseHandler dbh = new DatabaseHandler();

        private int _id;
        private String _datum;
        private String _name;
        private String _beschreibung;
        private String _aufwand;
        private String _ersteller;
        private String _prioritaet;

        public Item(
                int id,
                String datum,
                String name,
                String beschreibung,
                String prioritaet,
                String aufwand,
                String ersteller) {
            System.out.println("Item-list Constructor");
            _id = id;
            _datum = datum;
            _name = name;
            _beschreibung = beschreibung;
            _prioritaet = prioritaet;
            _aufwand = aufwand;
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

        public String get_name() {
            return _name;
        }

        public void set_beschreibung(String _beschreibung) {
            this._beschreibung = _beschreibung;
        }

        public String get_beschreibung() {
            return _beschreibung;
        }

        public void set_prioritaet(String _prioritaet) {
            this._prioritaet = _prioritaet;
        }

        public String get_prioritaet() {
            return _prioritaet;
        }

        public void set_aufwand(String _aufwand) {
            this._aufwand = _aufwand;
        }

        public String get_aufwand() {
            return _aufwand;
        }

        public void set_ersteller(String _ersteller) {
            this._ersteller = _ersteller;
        }

        public String get_ersteller() {
            return _ersteller;
        }
    }
}
