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
                String[] col;
                System.out.println();
                System.out.println("Selecting all from techie view where name is Steve");
                uniDB.listViewTechie("Steve");
                System.out.println();
                System.out.println("Selecting all from techie view where name is Peter");
                uniDB.listViewTechie("Peter");



//                String[][] data = {{"1","2"},{"1","2"},{"1","2"}};
//
//                MainFrame mainFrame	= new MainFrame();
//                mainFrame.setVisible( true );


                uniDB.closeConnection();
            }



        }


}
