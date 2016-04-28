/**
 * Created by arash, gray and christopher on 23/04/2016
 */

import java.sql.SQLException;

public class Main {

        public static void main(String[] args) throws SQLException {
            // create our actual client and test the database connection
            UniDbClient uniDB = new UniDbClient();

            if (uniDB.openConnection()) {
                System.out.println(uniDB.listAllAirlines());



                uniDB.closeConnection();
            }



        }


}
