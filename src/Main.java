/**
 * Created by arash, gray and christopher on 23/04/2016
 */

import javax.swing.*;
import java.sql.*;

public class Main {

        public static void main(String[] args) throws SQLException {
            // create our actual client and test the database connection

            UniDbClient uniDB = new UniDbClient();

            if (uniDB.openConnection()) {
                MainFrame mainFrame	= new MainFrame();
                mainFrame.setVisible( true );


                uniDB.closeConnection();
            }



        }


}
