package de.tency.adashboard;

import de.tency.adashboard.DatabaseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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

    static DatabaseHandler dbh = new DatabaseHandler();

    static GenerateItem gI = new GenerateItem();

    public static int id;
    public static String name;
    public static String beschreibung;
    public static String prioritaet;
    public static String aufwand;
    public static String datum;
    public static String bearbeiter;
    public static int status;

    public static List<Item> allItems = new ArrayList<Item>();

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getBeschreibung() {
        return beschreibung;
    }

    public static String getPrioritaet() {
        return prioritaet;
    }

    public static String getAufwand() {
        return aufwand;
    }

    public static String getDatum() {
        return datum;
    }

    public static String getBearbeiter() {
        return bearbeiter;
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

    public void setBearbeiter(String bearbeiter) {
        this.bearbeiter = bearbeiter;
    }

    public void set_status(int status) {
        this.status = status;
    }

    public int get_status() {
        return status;
    }

    public ItemBean() {
    }

    public static int itemCount() {
        return dbh.getItemCount();
    }

    public String sendItemUpdate(String name, String description, String prioritaet, String aufwand, String datum) {

        String bearbeiter = dbh.getUsername();
        dbh.sendItemUpdate(name, description, prioritaet, aufwand, datum, bearbeiter);
        return "dashboard.jsf";
//        logger.info("Item succefully sent to DashboardDB!");

    }

    /**
     * Listen
     *
     * @param allItems
     */
    public void setAllItems(List<Item> allItems) {

        this.allItems = allItems;
    }

    public static String[][] getAllItems(int count) {
        return dbh.listAllItems(count);
    }

    public void addToAllItems(Item i) {
        allItems.add(i);
    }

    public void emptyAllItems() {
        allItems.clear();
    }

    public static class Item implements Serializable {

        private static final long serialVersionUID = 1L;

        DatabaseHandler dbh = new DatabaseHandler();

        public static int _id;
        public static String _datum;
        public static String _name;
        public static String _beschreibung;
        public static String _aufwand;
        public static String _bearbeiter;
        public static String _prioritaet;
        public static int _status;

        public Item(
                int id,
                String datum,
                String name,
                String beschreibung,
                String prioritaet,
                String aufwand,
                String bearbeiter,
                int status) {
//            System.out.println("Item-list Constructor");
            _id = id;
            _datum = datum;
            _name = name;
            _beschreibung = beschreibung;
            _prioritaet = prioritaet;
            _aufwand = aufwand;
            _bearbeiter = bearbeiter;
            _status = status;
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

        public void set_bearbeiter(String _bearbeiter) {
            this._bearbeiter = _bearbeiter;
        }

        public String get_bearbeiter() {
            return _bearbeiter;
        }

        public void set_status(int _status) {
            this._status = _status;
        }

        public int get_status() {
            return _status;
        }
    }
}
