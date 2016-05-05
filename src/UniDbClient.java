/*
 * Example JDBC client for University Registration DB
 * Skeleton Java program for COMP9120 Database Application Programming lecture/tutorial
 * Make sure you have added an appropriate Oracle JDBC driver library
 */

import java.sql.*;
import java.util.ArrayList;

public class UniDbClient {
    // connection parameters
    private final String userid = "apar2844";
    private final String passwd = "apar2844";
    private final String database = "oracle12.it.usyd.edu.au:1521:COMP5138";

    // instance variable for the database connection
    private Connection conn = null;

    /**
     * class constructor
     * which loads the Oracle JDBC driver
     */
    UniDbClient() {
        try {
           /* load Oracle's JDBC driver */
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException no_class_ex) {
           /* error handling when no JDBC class is found */
            System.out.println(no_class_ex);
        }
    }

    /**
     * Establishes a connection to the Oracle database.
     * The connection parameters are read from the instance variables above
     * (userid, passwd, and database).
     *
     * @returns true   on success and then the instance variable 'conn'
     * holds an open connection to the database.
     * false  otherwise
     */
    public boolean connectToDatabase() {
        try {
           /* connect to the database */
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + database, userid, passwd);
           /* If you want to connect to your own database you should remove this line: */
            // conn.createStatement().execute("ALTER SESSION SET current_schema=COMP5138_DEMO");
            return true;
        } catch (SQLException sql_ex) {
           /* error handling */
            System.out.println(sql_ex);
            return false;
        }
    }

    /**
     * open ONE single database connection
     */
    public boolean openConnection() {
        boolean retval = true;

        if (conn != null)
            System.err.println("You are already connected to Oracle; no second connection is needed!");
        else {
            if (connectToDatabase())
                System.out.println("You successfully connected to Oracle.");
            else {
                System.out.println("Oops - something went wrong.");
                retval = false;
            }
        }

        return retval;
    }

    /**
     * close the database connection again
     */
    public void closeConnection() {
        if (conn == null)
            System.err.println("You are not connected to Oracle!");
        else try {
            conn.close(); // close the connection again after usage!
            conn = null;
        } catch (SQLException sql_ex) {  /* error handling */
            System.out.println(sql_ex);
        }
    }


    public void listAircraftsOwenedbyAirline(String airlineName) throws SQLException {
        ResultSet rset = null;
        PreparedStatement stmt = null;
        String query = "Select * "+
                "FROM airline JOIN aircraft ON  airline.code = aircraft.owner "+
                "WHERE airline.name = ? ";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, airlineName);
            rset = stmt.executeQuery();
            while (rset.next()) {
                System.out.println(
                        rset.getString("name") + " - " +
                        rset.getString("country") + " - "  +
                        rset.getString("phone") + " - " +
                        rset.getString("fax")  + " - " +
                                rset.getString("website")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            if (stmt != null) stmt.close();
        }
    }


    public void listViewTechie(String name) throws SQLException {
        ResultSet rset = null;
        PreparedStatement stmt = null;

        String query = "Select * "+
                "FROM techie "+
                "WHERE givenname = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rset = stmt.executeQuery();
            while (rset.next()) {
                System.out.println(
                        rset.getString("empid") + " - " +
                                rset.getString("givenname") + " - "  +
                                rset.getString("familyname") + " - " +
                                rset.getString("address")  + " - " +
                                rset.getString("phone")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            if (stmt != null) stmt.close();
        }
    }

}