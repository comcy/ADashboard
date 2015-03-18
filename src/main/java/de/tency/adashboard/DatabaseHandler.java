package de.tency.adashboard;
 
import java.io.Serializable;
 
import javax.sql.*;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
 
public class DatabaseHandler implements Serializable {
 
    private static final long serialVersionUID = 3L;
    Connection connection;
    Connection connection2;
    Connection connection3;
    Connection connection4;
    Statement statement;
    Statement statement2;
    Statement statement3;
    Statement statement4;
    ResultSet result;
    ResultSet result2;
    ResultSet result3;
    ResultSet result4;
 
    /**
     * SQL connections initializing
     */
    public void SQLConnection() {
        try {
 
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection = ds.getConnection();
            System.out.println("DB open");
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT VERSION()");
            if (result.next()) {
                // System.out.println(result.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
            System.out.println("Error during DB connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
            System.out.println("Error during DB connection " + ex);
            ex.printStackTrace();
        }
    }
 
    public void SQLConnection2() {
        try {
 
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection2 = ds.getConnection();
            System.out.println("   DB2 open");
            statement2 = connection2.createStatement();
            result2 = statement2.executeQuery("SELECT VERSION()");
            if (result2.next()) {
                // System.out.println(result2.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
            System.out.println("Error during DB2 connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
            System.out.println("Error during DB2 connection " + ex);
            ex.printStackTrace();
        }
    }
 
    public void SQLConnection3() {
        try {
 
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection3 = ds.getConnection();
            System.out.println("      DB3 open");
            statement3 = connection3.createStatement();
            result3 = statement3.executeQuery("SELECT VERSION()");
            if (result3.next()) {
                // System.out.println(result3.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
            System.out.println("Error during DB3 connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
            System.out.println("Error during DB3 connection " + ex);
            ex.printStackTrace();
        }
    }
 
    public void SQLConnection4() {
        try {
 
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt
                    .lookup("java:/comp/env/jdbc/postgres");
            connection4 = ds.getConnection();
            System.out.println("        DB4 open");
            statement4 = connection4.createStatement();
            result4 = statement4.executeQuery("SELECT VERSION()");
            if (result4.next()) {
                // System.out.println(result3.getString(1)); // DEBUG -
                // Connection
            }
        } catch (SQLException ex) {
            System.out.println("Error during DB4 connection " + ex);
            ex.printStackTrace();
        } catch (NamingException ex) {
            System.out.println("Error during DB4 connection " + ex);
            ex.printStackTrace();
        }
    }
 
    /*
     * SQL connection closing
     */
    public void SQLConnectionClose() {
        try {
            connection.close();
            System.out.println("DB close");
        } catch (SQLException ex) {
            System.out.println("Error during DB connection " + ex);
            ex.printStackTrace();
        }
    }
 
    public void SQLConnectionClose2() {
        try {
            connection2.close();
            System.out.println("   DB2 close");
        } catch (SQLException ex) {
            System.out.println("Error during DB2 connection " + ex);
            ex.printStackTrace();
        }
    }
 
    public void SQLConnectionClose3() {
        try {
            connection3.close();
            System.out.println("      DB3 close");
        } catch (SQLException ex) {
            System.out.println("Error during DB3 connection " + ex);
            ex.printStackTrace();
        }
    }
 
    public void SQLConnectionClose4() {
        try {
            connection4.close();
            System.out.println("        DB4 close");
        } catch (SQLException ex) {
            System.out.println("Error during DB4 connection " + ex);
            ex.printStackTrace();
        }
    }
 
    // USER DATA //
    /**
     * Shows the first- and lastname of the user based on his username.
     *
     * @return first- and lastname
     */
    public int getAgendaId() {
        System.out.println("schowUserFullName");
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
}