import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by arash on 12/05/2016.
 */
public class TableData {
    private String[][] data;
    private String[] columns;

    public TableData(String[][] data, String[] columns) {
        this.data = data;
        this.columns = columns;
    }

    public TableData(ResultSet rset) throws SQLException {
        boolean isEmpty = true;
        ArrayList <String[]> result = new ArrayList<String[]>();
        ArrayList <String> fields = new ArrayList<String>();
        int columnCount = rset.getMetaData().getColumnCount();

        while(rset.next())
        {
            isEmpty = false;
            String[] row = new String[columnCount];
            for (int i=0; i <columnCount ; i++)
            {
                row[i] = rset.getString(i + 1);
            }
            result.add(row);
        }

        for (int i = 1; i < columnCount; i++){
            fields.add(rset.getMetaData().getColumnName(i));
        }
        if (isEmpty){
            this.columns = new String[]{"NO RESULTS"};
            this.data = new String[][]{{"NO RESULTS"}};
        }else {
            this.columns = fields.toArray(new String[0]);
            this.data = result.toArray(new String[0][0]);
        }

    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
}
