import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Created by arash on 28/04/2016.
 */
public class MainFrame extends JFrame {
    private	JPanel		topPanel;
    private	JTable		table;
    private	JScrollPane scrollPane;
    public MainFrame(String[][] data, String[] columnNames){
        super("Database Application");
        setSize( 800, 600 );
        setBackground( Color.gray );
        topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel );
        table = new JTable( data, columnNames );
        scrollPane = new JScrollPane( table );
        topPanel.add( scrollPane, BorderLayout.CENTER );
    }

}
