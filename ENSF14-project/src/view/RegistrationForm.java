package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    private JTextField userNametxt;
    private JPasswordField passwordtxt;
    private JTextField addresstxt;
    private JTextField emailtxt;
    private JTextField fullNametxt;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JTextField ccNumbertxt;
    private JTextField ccExpirytxt;
    private JTextField ccYeartxt;
    private JTextField cvvtxt;

    public RegistrationForm() {
        setContentPane(mainPanel);
        setSize(700, 450);
        setTitle("Sign-Up Form");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void clearAllTextFields(){
        getUserNametxt().setText("");
        getPasswordtxt().setText("");
        getAddresstxt().setText("");
        getEmailtxt().setText("");
        getFullNametxt().setText("");
        getCcNumbertxt().setText("");
        getCcExpirytxt().setText("");
        getCcYeartxt().setText("");
        getCvvtxt().setText("");
    }

    public void confirmRegistration(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }

    public void cancelRegistration(ActionListener actionListener){
        cancelButton.addActionListener(actionListener);
    }

    public JTextField getUserNametxt() {
        return userNametxt;
    }

    public JPasswordField getPasswordtxt() {
        return passwordtxt;
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

    public JTextField getCcExpirytxt() {
        return ccExpirytxt;
    }

    public JTextField getCcYeartxt() {
        return ccYeartxt;
    }

    public JTextField getCvvtxt() {
        return cvvtxt;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
