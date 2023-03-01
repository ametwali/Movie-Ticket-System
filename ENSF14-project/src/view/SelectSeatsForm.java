package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectSeatsForm extends JFrame {

    private JPanel mainPanel;
    private JButton proceedToPaymentButton;
    private JToggleButton seat1;
    private JToggleButton seat2;
    private JToggleButton seat3;
    private JToggleButton seat4;
    private JToggleButton seat5;
    private JToggleButton seat6;
    private JToggleButton seat7;
    private JToggleButton seat8;
    private JToggleButton seat9;
    private JToggleButton seat10;
    private JToggleButton seat11;
    private JToggleButton seat12;
    private JToggleButton seat13;
    private JToggleButton seat14;
    private JToggleButton seat15;
    private JToggleButton seat16;
    private JToggleButton seat17;
    private JToggleButton seat18;
    private JToggleButton seat19;
    private ArrayList<JToggleButton> seatButtons = new ArrayList<>();
    private JToggleButton seat20;
    private JButton backToMainMenuButton;

    public SelectSeatsForm (){
        setContentPane(mainPanel);
        setSize(750, 300);
        setTitle("Select Seats Form");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add seats
        seatButtons.add(seat1);
        seatButtons.add(seat2);
        seatButtons.add(seat3);
        seatButtons.add(seat4);
        seatButtons.add(seat5);
        seatButtons.add(seat6);
        seatButtons.add(seat7);
        seatButtons.add(seat8);
        seatButtons.add(seat9);
        seatButtons.add(seat10);
        seatButtons.add(seat11);
        seatButtons.add(seat12);
        seatButtons.add(seat13);
        seatButtons.add(seat14);
        seatButtons.add(seat15);
        seatButtons.add(seat16);
        seatButtons.add(seat17);
        seatButtons.add(seat18);
        seatButtons.add(seat19);
        seatButtons.add(seat20);


    }

    public void clearAllSeats(){
        for(JToggleButton j:seatButtons){
            j.setEnabled(true);
            j.setSelected(false);
        }
    }

    public void proceedToPayment(ActionListener actionListener) {
        proceedToPaymentButton.addActionListener(actionListener);
    }

    public void backToMainMenu(ActionListener actionListener) {
        backToMainMenuButton.addActionListener(actionListener);
    }

    public ArrayList<JToggleButton> getSeatButtons() {
        return seatButtons;
    }


}
