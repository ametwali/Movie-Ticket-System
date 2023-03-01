package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class ViewMyTicketsForm extends JFrame {
    private JTable ticketInfoTable;
    private JPanel mainPanel;
    private JButton returnToMainMenuButton;
    private JScrollPane ticketInfoScrollPane;
    private JPanel ticketInfoPanel;

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    private DefaultTableModel tableModel;

    public ViewMyTicketsForm(){
        setContentPane(mainPanel);
        setSize(800, 300);
        setTitle("My Ticket Information");
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
    }

    public void returnToMainMenu(ActionListener actionListener) {
        returnToMainMenuButton.addActionListener(actionListener);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        tableModel = new DefaultTableModel(
                new String[] { "ticketId", "title", "showtime", "seatRow",
                        "seatNum", "theatreName" }, 0);

        ticketInfoTable = new JTable(tableModel);
        ticketInfoPanel = new JPanel();
        ticketInfoScrollPane = new JScrollPane(ticketInfoPanel);

    }




}
