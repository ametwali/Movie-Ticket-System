package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class SelectTheatreForm extends JFrame {
    public JTable getTheatreTable() {
        return theatreTable;
    }

    public void setTheatreTable(JTable theatreTable) {
        this.theatreTable = theatreTable;
    }

    private JTable theatreTable;
    private JPanel theatrePanel;
    private JScrollPane theatreScrollPane;
    private JButton selectTheatreButton;
    private JPanel mainPanel;
    DefaultTableModel tableModel;

    public SelectTheatreForm(){
        setContentPane(mainPanel);
        setSize(300, 300);
        setTitle("Select Theatre");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
    }

    public void selectTheatreConfirmation(ActionListener actionListener) {
        selectTheatreButton.addActionListener(actionListener);
    }

    private void createUIComponents() {
        // TODO: display theatre list

        // STEP 3A
        String[] columnNames = {"Theatre ID", "Name"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // STEP 3B
        theatreTable = new JTable(tableModel);
        theatreTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        theatrePanel = new JPanel();
        theatreScrollPane = new JScrollPane(theatrePanel);


    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
