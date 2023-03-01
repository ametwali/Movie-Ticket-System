package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class SelectMovieForm extends JFrame {

    public JList getMovieList() {
        return movieList;
    }

    public void setMovieList(JList movieList) {
        this.movieList = movieList;
    }

    private JList movieList;
    private JPanel mainPanel;
    private JButton selectButton;
    private JScrollPane movieScrollPane;
    private JPanel moviePanel;


    public JTable getMovieTable() {return movieTable;
    }

    private JTable movieTable;
    private DefaultTableModel tableModel;
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public SelectMovieForm() {
        setContentPane(mainPanel);
        setSize(500, 300);
        setTitle("Select Movie");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setVisible(true);
    }

    public void selectMovieConfirmation(ActionListener actionListener) {
        selectButton.addActionListener(actionListener);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        String[] columnNames = {"Movie ID", "Title", "Rating", "Release Date"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // STEP 3B
        movieTable = new JTable(tableModel);
        movieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moviePanel = new JPanel();
        movieScrollPane = new JScrollPane(moviePanel);


    }

    //TODO SET-UP JTABLE (SEE mainMenu.java, method setupUIComponents())


}
