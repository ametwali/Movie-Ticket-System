package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginForm extends JFrame {
    private JLabel title;

    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    public JPasswordField getPasswordTxt() {
        return passwordTxt;
    }

    private JTextField userNameTxt;
    private JPasswordField passwordTxt;
    private JButton loginButton;
    private JButton continueAsGuestButton;
    private JButton signUpButton;
    private JPanel mainPanel;
    private JLabel userNameLabel;

    private JLabel passwordLabel;

    public loginForm() {
        setContentPane(mainPanel);
        setSize(450, 300);
        setTitle("Welcome");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void clearAllTextFields(){
       getUserNameTxt().setText("");
       getPasswordTxt().setText("");

    }

    public void login(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

    public void signUp(ActionListener actionListener) {
        signUpButton.addActionListener(actionListener);
    }

    public void continueAsGuest(ActionListener actionListener) {
        continueAsGuestButton.addActionListener(actionListener);
    }

    public void displayUserMessage(String userMessage) {
        JOptionPane.showMessageDialog(this, userMessage);
    }

}
