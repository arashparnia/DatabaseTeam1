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


    public boolean sql(String select, String from) throws SQLException {
        boolean success = false;
        PreparedStatement stmt = null;
        String query = "SELECT ? " + "FROM ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, select);
            stmt.setString(2, from);
            ResultSet rset = stmt.executeQuery();
            int nr = 0;
            while (rset.next()) {
                success = true;
                nr++;
                System.out.println(rset.getString("airline_name"));
            }
            if (nr == 0) System.out.println("No entries found.");
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            if (stmt != null) stmt.close();
        }
        return success;
    }
    public boolean listAllAirlines() throws SQLException {
        String columnNames[] = { "Name", "Phone", "Fax", "Website", "Country" };
        ArrayList<String[]> list = new ArrayList<String[]>();
        boolean success = false;
        PreparedStatement stmt = null;
        String query = "SELECT * " + "FROM airline";
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rset = stmt.executeQuery();
            int nr = 0;
            System.out.printf("%4s  %15s   %15s   %20s   %-6s%n", "Name", "Phone", "Fax", "Website", "Country");
            while (rset.next()) {
                success = true;
                nr++;
                String[] row = {rset.getString("airline_name"),
                        rset.getString("phone"),
                        rset.getString("fax"),
                        rset.getString("Website"),
                        rset.getString("country")};
                list.add(row);

                System.out.printf("%4s  %15s   %15s   %20s   %-6s%n",
                        rset.getString("airline_name") ,
                        rset.getString("phone"),
                        rset.getString("fax"),
                        rset.getString("Website"),
                        rset.getString("country")
                );
            }
            if (nr == 0) System.out.println("No entries found.");
        } catch (SQLException e) {
            System.out.println("SQLException : " + e);
        } finally {
            if (stmt != null) stmt.close();
        }


        String[][] data = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) data[i] = list.get(i);


        MainFrame mainFrame	= new MainFrame(data,columnNames);
        mainFrame.setVisible( true );
        return success;
    }
}