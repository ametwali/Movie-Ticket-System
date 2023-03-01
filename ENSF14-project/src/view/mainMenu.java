package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class mainMenu extends JFrame {
    private JPanel mainPanel;
    private JButton theatreButton;
    private JButton movieButton;

    public JLabel getSelectionTxtArea() {
        return selectionTxtArea;
    }

    public void setSelectionTxtArea(JLabel selectionTxtArea) {
        this.selectionTxtArea = selectionTxtArea;
    }

    private JLabel selectionTxtArea;
    private JButton purchaseSeatsButton;

    public DefaultTableModel getTableModeltest() {
        return tableModeltest;
    }

    DefaultTableModel tableModeltest;

    public JTable getMovieTable() {
        return movieTable;
    }

    private JTable movieTable;
    private JPanel movieTablePanel;
    private JScrollPane movieScrollPane;
    private JPanel moviePanel;
    private JButton viewMyTicketsButton;
    private JButton logoutButton;
    private JButton cancelTicketsButton;

    public mainMenu() {
//
//        getTable1().add(new JScrollPane())

        setContentPane(mainPanel);
        setSize(700, 500);
        setTitle("Select Seats");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
    }



    public void cancelTickets(ActionListener actionListener) { cancelTicketsButton.addActionListener(actionListener);}

    public void selectByMovie(ActionListener actionListener) {
        movieButton.addActionListener(actionListener);
    }

    public void selectByTheatre(ActionListener actionListener) {
        theatreButton.addActionListener(actionListener);
    }

    public void viewMyTickets(ActionListener actionListener) {
        viewMyTicketsButton.addActionListener(actionListener);
    }

    public void logout(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }

    public void enterSelectSeats(ActionListener actionListener){
        purchaseSeatsButton.addActionListener(actionListener);
    }

//    public static void main(String[] args){
//        mainMenu mymainMenu = new mainMenu();
//
//    }

    public void ChangeTableData() {

        //TODO query new data based on filter value



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        /* IMPORTANT NOTE: TO GET CUSTOM COLUMN HEADERS TO DISPLAY:

        STEP 1 - CREATE A JTABLE WITHIN A JSCROLLPANE, WITHIN A JPANEL IN INTELLIJ FORM DESIGNER
        STEP 2- SELECT EACH COMPONENT, AND CHECK "CUSTOM CREATE OPTION" IN OPTIONS PANE
        STEP 3 - POPULATE createUIComponents() IN CORRESPONDING JAVA CLASS WITH BELOW METHODOLOGY
        STEP 3A - POPULATE tableModel WITH COLUMNS YOU WANT
        STEP 3B - CONSTRUCT ALL OBJECTS AS SEEN BELOW
         */

        // STEP 3A
        String[] columnNames = {"Showing ID", "Movie Name", "Theatre", "Showtime"};
        tableModeltest = new DefaultTableModel(columnNames, 0);


        // STEP 3B
        movieTable = new JTable(tableModeltest);
        movieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moviePanel = new JPanel();
        movieScrollPane = new JScrollPane(movieTable);

        // BELOW CAUSES A COMPILATION ERROR, DON'T ADD ANYTHING JUST CONSTRUCT AS ABOVE
//         moviePanel.add(movieScrollPane);

    }

}
