package controller;

import entity.RegisteredUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    DBController dbController;

    public RegisteredUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(RegisteredUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private RegisteredUser loggedInUser;

    public UserController(DBController dbController) {
        this.dbController = dbController;
    }

    public boolean authenticateUser(String userName, String pword)
    {
        //if user exists, log him in
        if(dbController.authenticateUser(userName,pword))
        {
            logInUser(userName, pword);
            return true;
        }

        return false;
    }

    public void logInUser(String userName, String pword)
    {
        this.loggedInUser = dbController.retrieveUser(userName, pword);

    }

    public boolean registerUser(String fullName, String userName, String pword, String address,
                             String email, String CCNum, String CCexpiryMonth, String CCexpiryYear, int cvv)
    {
        //TODO: Don't have CCExpiry checks
        if(dbController.checkIfUserExists(userName) || CCNum.length() != 16 || Integer.toString(cvv).length() != 3)
        {
            //TODO: Error message that username is already taken
            return false;
        }
        else
        {
            //User doesn't exist yet, let's register
            dbController.registerNewUser(fullName, userName, pword, address, email, CCNum, CCexpiryMonth, CCexpiryYear, cvv);
            logInUser(userName, pword);
            return true;
        }

    }

    public boolean checkIfLoggedIn()
    {
        if(this.loggedInUser != null)
            return true;

        return false;
    }

    public int getUserCCId()
    {
        if(!checkIfLoggedIn())
            return -1;

        return this.loggedInUser.getCcId();
    }

    public String getFullName(int ccId)
    {
        return this.dbController.getCCHolderName(ccId);
    }



}
