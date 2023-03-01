package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PaymentForm extends JFrame {
    private JPanel mainPanel;

    private JTextField addresstxt;
    private JTextField emailtxt;
    private JTextField fullNametxt;
    private JTextField ccNumbertxt;
    private JTextField cvvtxt;
    private JTextField ccYeartxt;
    private JTextField ccMonthtxt;
    private JButton purchaseTicketsButton;
    private JButton editSeatSelectionButton;
    private JLabel totalPricetxt;



    private JLabel orderDetailsTxt;


    private JLabel showTimetxt;


    public PaymentForm() {
        setContentPane(mainPanel);
        setSize(700, 500);
        setTitle("Payment Form");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void purchaseTickets(ActionListener actionListener) {
        purchaseTicketsButton.addActionListener(actionListener);
    }

    public void editSeatSelection(ActionListener actionListener) {
        editSeatSelectionButton.addActionListener(actionListener);
    }
    public JLabel getOrderDetailsTxt() {
        return orderDetailsTxt;
    }
    public JLabel getTotalPricetxt() {
        return totalPricetxt;
    }
    public JTextField getAddresstxt() {
        return addresstxt;
    }

    public JTextField getEmailtxt() {
        return emailtxt;
    }

    public JTextField getFullNametxt() {
        return fullNametxt;
    }

    public JTextField getCcNumbertxt() {
        return ccNumbertxt;
    }

    public JTextField getCvvtxt() {
        return cvvtxt;
    }

    public JTextField getCcYeartxt() {
        return ccYeartxt;
    }

    public JTextField getCcMonthtxt() {
        return ccMonthtxt;
    }
    public JLabel getShowTimetxt() {
        return showTimetxt;
    }


}
