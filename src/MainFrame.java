import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Created by arash on 28/04/2016.
 */
public class MainFrame extends JFrame  {

    private	JPanel	    storedprocedurePanel;
    private JTextField storedprocedureTextField1;
    private JTextField storedprocedureTextField2;
    private JTextField storedprocedureTextField3;
    private JTextField storedprocedureTextField4;
    private JTextField storedprocedureTextField5;
    private JTextField storedprocedureTextField6;
    private JButton storedprocedureButton;

    private	JPanel		techiePanel;
    private JTextField  techieTextField;
    private JButton     techieQueryButton;

    private	JPanel		queryPanel;
    private JTextField  queryTextField;
    private JButton     queryButton;

    private	JPanel		triggerPanel;
    private JTextField  triggerTextField;
    private JButton     triggerButton;

    private	JPanel		panel;

    private  String[] columnNames = {"DATA"};
    private Object[][] data ={{"DATA"}};
    private	JTable		table;
    private	JScrollPane scrollPane;
    public MainFrame(){

        super("Database Application");
        setSize( 800, 600 );
        setBackground( Color.gray );

        getContentPane().setLayout( new GridLayout(2,0));


        panel = new JPanel();
        panel.setLayout(new GridLayout(4,0));




        storedprocedurePanel = new JPanel(new GridLayout(3,4));
        storedprocedurePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "Stored Proccedure"));
        storedprocedureTextField1 = new JTextField("VH-OJA");
        storedprocedureTextField2 = new JTextField("123456789");
        storedprocedureTextField3 = new JTextField("1001");
        storedprocedureTextField4 = new JTextField("2001-01-01");
        storedprocedureTextField5 = new JTextField("1");
        storedprocedureTextField6 = new JTextField("1");
        storedprocedureButton = new JButton("STORED PROCEDURE EXCECUTE");
        storedprocedurePanel.add(storedprocedureTextField1);
        storedprocedurePanel.add(storedprocedureTextField2);
        storedprocedurePanel.add(storedprocedureTextField3);
        storedprocedurePanel.add(storedprocedureTextField4);
        storedprocedurePanel.add(storedprocedureTextField5);
        storedprocedurePanel.add(storedprocedureTextField6);
        storedprocedurePanel.add(storedprocedureButton);


        techiePanel = new JPanel(new GridLayout(0,5));
        techiePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "Techie View Query"));
        techieTextField = new JTextField("Peter");
        techieQueryButton = new JButton("Query Techie");
        techiePanel.add(techieTextField);
        techiePanel.add(techieQueryButton);


        queryPanel = new JPanel(new GridLayout(0,5));
        queryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "Query"));
        queryTextField = new JTextField("Boeing");
        queryButton = new JButton("Query");
        queryPanel.add(queryTextField);
        queryPanel.add(queryButton);


        triggerPanel = new JPanel(new GridLayout(0,5));
        triggerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "trigger"));
        triggerTextField = new JTextField("trigger  ?");
        triggerButton = new JButton("Query");
        triggerPanel.add(triggerTextField);
        triggerPanel.add(triggerButton);



        panel.add(storedprocedurePanel);
        panel.add(techiePanel);
        panel.add(queryPanel);
        panel.add(triggerPanel);


        DefaultTableModel tableModel = new DefaultTableModel(data,columnNames);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane( table );
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "Table View"));



        //getContentPane().add( panel );
        add(panel);
        add(scrollPane);




        UniDbClient uniDB = new UniDbClient();
        uniDB.openConnection();


        techieQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TableData t = new TableData(uniDB.listViewTechie(techieTextField.getText()));
                    tableModel.setDataVector(t.getData(),t.getColumns());
                    tableModel.fireTableDataChanged();
                }catch (SQLException err) {System.out.println("SQLException : " + err);}
            }

        });
        storedprocedureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int r = uniDB.addTestEvent(
                            storedprocedureTextField1.getText(),
                            Integer.parseInt(storedprocedureTextField2.getText()),
                            Integer.parseInt(storedprocedureTextField3.getText()),
                            storedprocedureTextField4.getText(),
                            Integer.parseInt(storedprocedureTextField5.getText()),
                            Integer.parseInt(storedprocedureTextField6.getText())
                            );
                    tableModel.setDataVector(new String[][]{{String.valueOf(r)}},new String[]{"TEST ID"});
                    tableModel.fireTableDataChanged();
                }catch (SQLException err) {System.out.println("SQLException : " + err);}

            }
        });
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TableData t = new TableData(uniDB.testEventsFor(queryTextField.getText()));
                    tableModel.setDataVector(t.getData(),t.getColumns());
                    tableModel.fireTableDataChanged();
                }catch (SQLException err) {System.out.println("SQLException : " + err);}
            }
        });



    }

}
