package de.tency.adashboard;

import de.tency.adashboard.ItemBean.Item;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class DatabaseHandler implements Serializable {

    private static final long serialVersionUID = 3L;

    public static Connection connection;
    static Connection connection2;
    static Connection connection3;
    static Connection connection4;

    static Statement statement;
    static Statement statement2;
    static Statement statement3;
    static Statement statement4;

    static Statement changeColumnStatus;

    static ResultSet result;
    static ResultSet result2;
    static ResultSet result3;
    static ResultSet result4;
    static PreparedStatement ps;

    /**
     * SQL connections initializing
     */
    public void SQLConnection() {
        try {

            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection = ds.getConnection();
//            System.out.println("DB open");
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT VERSION()");
            if (result.next()) {
                // System.out.println(result.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
//            System.out.println("Error during DB connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
//            System.out.println("Error during DB connection " + ex);
            ex.printStackTrace();
        }
    }

    public void SQLConnection2() {
        try {

            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection2 = ds.getConnection();
//            System.out.println("   DB2 open");
            statement2 = connection2.createStatement();
            result2 = statement2.executeQuery("SELECT VERSION()");
            if (result2.next()) {
                // System.out.println(result2.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
//            System.out.println("Error during DB2 connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
//            System.out.println("Error during DB2 connection " + ex);
            ex.printStackTrace();
        }
    }

    public void SQLConnection3() {
        try {

            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection3 = ds.getConnection();
//            System.out.println("      DB3 open");
            statement3 = connection3.createStatement();
            result3 = statement3.executeQuery("SELECT VERSION()");
            if (result3.next()) {
                // System.out.println(result3.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
//            System.out.println("Error during DB3 connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
//            System.out.println("Error during DB3 connection " + ex);
            ex.printStackTrace();
        }
    }

    public static void SQLConnection4() {
        try {

            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection4 = ds.getConnection();
//            System.out.println("DB4 open");
            statement4 = connection4.createStatement();
            result4 = statement4.executeQuery("SELECT VERSION()");
            if (result4.next()) {
                // System.out.println(result3.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
//            System.out.println("Error during DB4 connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
//            System.out.println("Error during DB4 connection " + ex);
            ex.printStackTrace();
        }
    }

    /*
     * SQL connection closing
     */
    public void SQLConnectionClose() {
        try {
            connection.close();
//            System.out.println("DB close");
        } catch (SQLException ex) {
//            System.out.println("Error during DB connection " + ex);
            ex.printStackTrace();
        }
    }

    public void SQLConnectionClose2() {
        try {
            connection2.close();
//            System.out.println("   DB2 close");
        } catch (SQLException ex) {
//            System.out.println("Error during DB2 connection " + ex);
            ex.printStackTrace();
        }
    }

    public void SQLConnectionClose3() {
        try {
            connection3.close();
//            System.out.println("      DB3 close");
        } catch (SQLException ex) {
//            System.out.println("Error during DB3 connection " + ex);
            ex.printStackTrace();
        }
    }

    public static void SQLConnectionClose4() {
        try {
            connection4.close();
//            System.out.println("        DB4 close");
        } catch (SQLException ex) {
//            System.out.println("Error during DB4 connection " + ex);
            ex.printStackTrace();
        }
    }

    // USER DATA //
    /**
     * Get the username
     *
     * @return
     */
    public String getUsername() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
  
        String username = ec.getUserPrincipal().getName();
//        System.out.println("username");
        
        return username;
    }

    /**
     * Get the Full-Username
     * @return 
     */
    public String getFullname() {
        String fullname = "";
        return fullname;
    }

    /**
     * Shows the first- and lastname of the user based on his username.
     *
     * @return first- and lastname
     */
    public int getAgendaId() {
//        System.out.println("schowUserFullName");
        SQLConnection();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();

        ResultSet rs = null;
        PreparedStatement pst = null;

        String stm;
        stm = "Select id from agenda;";
        int id = 0;

        try {
            pst = connection2.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose();
        return id;
    }

    int getItemCount() {
        int count = 0;
        SQLConnection4();
        try {
            statement4 = connection4.createStatement();
            result4 = statement4.executeQuery(
                    "SELECT * FROM items ORDER BY id;");
            while (result4.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();
        return count;
    }

    String sendItemUpdate(String name, String beschreibung, String prioritaet, String aufwand, String datum, String bearbeiter) {
        String str = "dashboard.jsf";
        SQLConnection4();
        try {
            String newItem = "insert into items (name, beschreibung, prioritaet, aufwand, datum, bearbeiter, status) values ( ?, ?, ?, ?, ?, ?, ? )";
            ps = connection4.prepareStatement(newItem);
            ps.setString(1, name);
            ps.setString(2, beschreibung);
            ps.setString(3, prioritaet);
            ps.setString(4, aufwand);
            ps.setString(5, datum);
            ps.setString(6, bearbeiter);
            ps.setInt(7, 0);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();
        return str;
    }

    /**
     * List all items
     *
     * @return List<Item> allItems
     */
    public String[][] listAllItems(int i) {
//        System.out.println("Item"); // DEBUG
        List<Item> allItems = new ArrayList<Item>();
        String[][] itemNameBeschreibung = new String[i][8];
        int c = 0;
        SQLConnection4();
        try {
            statement4 = connection4.createStatement();
            result4 = statement4.executeQuery(
                    "SELECT * FROM items ORDER BY id;");
//            System.out.println(result4);
            while (result4.next()) {
                allItems.add(new ItemBean.Item(
                        result4.getInt("id"),
                        result4.getString("name"),
                        result4.getString("beschreibung"),
                        result4.getString("prioritaet"),
                        result4.getString("aufwand"),
                        result4.getString("datum"),
                        result4.getString("bearbeiter"),
                        result4.getInt("status")));

                String sID = Integer.toString(result4.getInt("id"));
                itemNameBeschreibung[c][0] = sID;
                itemNameBeschreibung[c][1] = result4.getString("name");
                itemNameBeschreibung[c][2] = result4.getString("beschreibung");
                itemNameBeschreibung[c][3] = result4.getString("prioritaet");
                itemNameBeschreibung[c][4] = result4.getString("aufwand");
                itemNameBeschreibung[c][5] = result4.getString("datum");
                itemNameBeschreibung[c][6] = result4.getString("bearbeiter");
                String sStatus = Integer.toString(result4.getInt("status"));
                itemNameBeschreibung[c][7] = sStatus;
//                System.out.println(itemNameBeschreibung[c][c]);
//                System.out.println(itemNameBeschreibung[c][c+1]);
                c++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();

        return itemNameBeschreibung;
    }

    int changeColumnStatus(int id, int newStatus) {
        String str = "UPDATE items SET status = " + newStatus + " WHERE id = " + id;
        SQLConnection4();
        try {
            changeColumnStatus = connection4.createStatement();
            changeColumnStatus.execute(str);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();
        return id;
    }

    String getCurrentName(int id) {
        String str = "SELECT name FROM items where id = " + id;
        String sName = null;
        SQLConnection4();
        try {
            statement4 = connection4.createStatement();
            result4 = statement4.executeQuery(str);

            result4.next();
            sName = result4.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();
        return sName;
    }
    
    String getCurrentItemBearbeiter(int id) {
        String str = "SELECT bearbeiter FROM items where id = " + id;
        String sBearbeiter = null;
        SQLConnection4();
        try {
            statement4 = connection4.createStatement();
            result4 = statement4.executeQuery(str);

            result4.next();
            sBearbeiter = result4.getString("bearbeiter");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();
        return sBearbeiter;
    }
    
    int changeItemBearbeiter(int id, String newBearbeiter) {
        String str = "UPDATE items SET bearbeiter = '" + newBearbeiter + "' WHERE id = " + id;
        SQLConnection4();
        try {
            changeColumnStatus = connection4.createStatement();
            changeColumnStatus.execute(str);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLConnectionClose4();
        return id;
    }
}
