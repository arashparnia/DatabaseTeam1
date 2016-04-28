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
                uniDB.listAircraftsOwenedbyAirline("Qantas");
                System.out.println();
                uniDB.listAircraftsOwenedbyAirline("Emirates");




//                MainFrame mainFrame	= new MainFrame({1,2},{{1,2}{1,2}});
//                mainFrame.setVisible( true );


                uniDB.closeConnection();
            }



        }


}
