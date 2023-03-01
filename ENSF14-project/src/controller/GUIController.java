package controller;

import entity.Movie;
import entity.RegisteredUser;
import entity.Showing;
import entity.Ticket;
import jdk.jfr.Registered;
import view.*;
//import entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUIController {

    DBController dbController;

    MovieController movieController;

    TheatreController theatreController;

    UserController userController;

    ShowingController showingController;

    PaymentController paymentController;

    ViewMyTicketsForm viewMyTicketsForm;

    public void setViewMyTicketsForm(ViewMyTicketsForm viewMyTicketsForm) {
        this.viewMyTicketsForm = viewMyTicketsForm;
    }

    private loginForm loginForm;
    private mainMenu mainMenu;
    private SelectSeatsForm selectSeatsForm;
    private ArrayList<Integer> seatsToBePurchased;
    private int selectedShowtimeId;

    public void setSelectMovieForm(SelectMovieForm selectMovieForm) {
        this.selectMovieForm = selectMovieForm;
    }

    private SelectMovieForm selectMovieForm;

    private SelectTheatreForm selectTheatreForm;

    private RegistrationForm registrationForm;

    private PaymentForm paymentForm;

    public void setRegistrationForm(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    int moviefilterSelectionId;

    public view.mainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(view.mainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public GUIController(){

        dbController = DBController.getInstance();

        // create all forms/views
        setLoginForm(new loginForm());
        setMainMenu(new mainMenu());
        setSelectMovieForm(new SelectMovieForm());
        setSelectTheatreForm(new SelectTheatreForm());
        setSelectSeatsForm(new SelectSeatsForm());
        setRegistrationForm(new RegistrationForm());
        setPaymentForm(new PaymentForm());
        setViewMyTicketsForm(new ViewMyTicketsForm());


        // add action listeners for login form
        loginForm.signUp(new SignUpButton());
        loginForm.login(new LoginButton());
        loginForm.continueAsGuest(new ContinueAsGuestButton());
        loginForm.setVisible(true);

        // add main menu action listeners
        mainMenu.selectByMovie(new SelectByMovieButton());
        mainMenu.selectByTheatre(new SelectByTheatreButton());
        mainMenu.enterSelectSeats(new EnterSelectSeatsButton());
        mainMenu.viewMyTickets(new ViewMyTicketsButton());
        mainMenu.logout(new Logout());


        // add register form action listener
        registrationForm.confirmRegistration(new ConfirmRegistrationButton());
        registrationForm.cancelRegistration(new CancelRegistrationButton());

        // add movie form action listener for filtering
        selectMovieForm.selectMovieConfirmation(new SelectMovieConfirmation());

        // add theatre form action listener for filtering
        selectTheatreForm.selectTheatreConfirmation(new SelectByTheatreConfirmation());

        // action listener for seat selection -> payment form
        selectSeatsForm.proceedToPayment(new ProceedToPaymentButton());
        selectSeatsForm.backToMainMenu(new BackToMainMenuButton());

        // payment form action listeners
        paymentForm.editSeatSelection(new EditSeatSelectionButton());
        paymentForm.purchaseTickets(new PurchaseTicketsButton());

        // view my tickets action listeners
        viewMyTicketsForm.returnToMainMenu(new ReturnToMainMenu());

        // instantiate controllers
        movieController = new MovieController(dbController);
        theatreController = new TheatreController(dbController);
        showingController = new ShowingController(dbController);
        userController = new UserController(dbController);
        paymentController = new PaymentController(dbController);
    }

    private class Logout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // set logged in user to null and go back to login screen
            userController.setLoggedInUser(null);
            mainMenu.setVisible(false);
            loginForm.setVisible(true);
            loginForm.clearAllTextFields();

        }
    }

    private class ReturnToMainMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewMyTicketsForm.setVisible(false);
            mainMenu.setVisible(true);
        }
    }

    private class ViewMyTicketsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            viewMyTicketsForm.setVisible(true);
            mainMenu.setVisible(false);

//            System.out.println(userController.getLoggedInUser());
//            System.out.println(userController.getLoggedInUser().getCcId());

            try {
                populateTicketInfoTable(userController.getLoggedInUser().getCcId());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }

    }

    private class CancelRegistrationButton implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // cancel registration, go back to login screen from registration form
            loginForm.setVisible(true);
            registrationForm.setVisible(false);
            registrationForm.clearAllTextFields();
        }
    }

    private class BackToMainMenuButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // clear all seats
            selectSeatsForm.clearAllSeats();

            // go back to main menu from seat selection form
            selectSeatsForm.setVisible(false);
            mainMenu.setVisible(true);
        }
    }

    private class EditSeatSelectionButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //TODO if no seats selected, throw error

            // proceed to payment form
            paymentForm.setVisible(false);
            selectSeatsForm.setVisible(true);

        }
    }

    private class PurchaseTicketsButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String fullName = paymentForm.getFullNametxt().getText();
            if(fullName.length() == 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Full Name field must not be empty!");
                return;
            }

            String address = paymentForm.getAddresstxt().getText();
            if(address.length() == 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Address field must not be empty!");
                return;
            }

            String email = paymentForm.getEmailtxt().getText();
            if(email.length() == 0)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Email field must not be empty!");
                return;
            }

            String CCNum = paymentForm.getCcNumbertxt().getText();
            if(CCNum.length() != 16)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Credit Card Number must be 16-digits!");
                return;
            }

            String CCExpiryMonth = paymentForm.getCcMonthtxt().getText();
            if(CCExpiryMonth.length() != 2)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Invalid CC Expiry Month!");
                return;
            }

            String CCExpiryYear = paymentForm.getCcYeartxt().getText();
            if(CCExpiryYear.length() != 4)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Invalid CC Expiry Year!");
                return;
            }

            int cvv = Integer.parseInt(paymentForm.getCvvtxt().getText());
            if(Integer.toString(cvv).length() != 3)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Invalid Credit Card CVV!");
                return;
            }

            int CCId = -1;

            //if not logged in, it's a guest, and we need to create new CCInfo entry
            if(!userController.checkIfLoggedIn())
            {
                CCId = dbController.addCCInfo(fullName, CCNum, CCExpiryMonth, CCExpiryYear, cvv);
            }
            else
            {
                CCId = userController.getUserCCId();
            }

            if(CCId < 0)
            {
                JOptionPane.showMessageDialog(null, "DATABASE ERROR: Credit Card ID");
                return;
            }

            //All movies 10$
            double FIXED_PRICE = 10.0;

            //create ticket (and sale and payment) in database
            ArrayList<Integer> ticketsGenerated = paymentController.generateTicket(selectedShowtimeId, FIXED_PRICE, seatsToBePurchased, CCId);

            //Failsafe: Should never be empty
            if(ticketsGenerated.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ERROR: No tickets generated!");
                return;
            }

            String purchaseSuccessMessage = "Purchase successful! Please keep the following Ticket IDs for your reference:\n";
            for(int i = 0; i < ticketsGenerated.size(); i++)
            {
                purchaseSuccessMessage += "TicketID #" + Integer.toString(i + 1) + ": ";
                purchaseSuccessMessage += ticketsGenerated.get(i) + "\n";
            }

            //Show ticket IDs to user
            JOptionPane.showMessageDialog(null, purchaseSuccessMessage);

            //Go back to main menu
            mainMenu.setVisible(true);
            paymentForm.setVisible(false);


        }
    }

    private class ProceedToPaymentButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            seatsToBePurchased = new ArrayList<Integer>();
            // get selected seats
            for(JToggleButton j: selectSeatsForm.getSeatButtons()){
                if(j.isSelected()){

                    seatsToBePurchased.add(selectSeatsForm.getSeatButtons().indexOf(j)+1);
                }
            }

            if(seatsToBePurchased.size() > 0)
            {
                // go to payment form
                selectSeatsForm.setVisible(false);
                paymentForm.setVisible(true);

                // show final cost to user
                paymentForm.getTotalPricetxt().setText("Total Due: $"+seatsToBePurchased.size()*10+".00");

                // get movie name to show
                int movieId = showingController.getShowtimeByShowtimesId(selectedShowtimeId).getMovieId();
                String movieName = movieController.getMovieByMovieID(movieId).gettitle();

                // get theatre name to show
                int theatreId = showingController.getShowtimeByShowtimesId(selectedShowtimeId).getTheatreId();
                String theatreName = theatreController.getTheatreByTheatreID(theatreId).getTheatreName();

                LocalDateTime showtime = showingController.getShowtimeByShowtimesId(selectedShowtimeId).getShowtime();

                //display showing details for user
                paymentForm.getOrderDetailsTxt().setText(seatsToBePurchased.size()+" seat(s) for "+
                        movieName + " @ " + theatreName
                );

                // display showtime
                paymentForm.getShowTimetxt().setText(showtime.toString());

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please select seats to buy tickets!");
                return;
            }

            //Autofill if logged in
            if(userController.checkIfLoggedIn())
            {
                RegisteredUser ruTemp = userController.getLoggedInUser();
                paymentForm.getFullNametxt().setText(userController.getFullName(ruTemp.getCcId()));
                paymentForm.getAddresstxt().setText(ruTemp.getAddress());
                paymentForm.getEmailtxt().setText(ruTemp.getEmail());
                paymentForm.getCcNumbertxt().setText(dbController.getCCNumber(ruTemp.getCcId()));
                paymentForm.getCvvtxt().setText(Integer.toString(dbController.getCVV(ruTemp.getCcId())));

                //parse LocalDate
                LocalDate expiry = dbController.getCCExpiryDate(ruTemp.getCcId());
                String expiryMonth = String.valueOf(expiry.getMonthValue());
                // if January to September, add a leading zero (e.g. convert 1 to 01)
                if(expiryMonth.length() == 1)
                    expiryMonth = "0" + expiryMonth;

                String expiryYear = String.valueOf(expiry.getYear());

                paymentForm.getCcMonthtxt().setText(expiryMonth);
                paymentForm.getCcYeartxt().setText(expiryYear);
            }


        }
    }

    private class ConfirmRegistrationButton implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                // register new user
                boolean userRegistered = userController.registerUser(
                        registrationForm.getFullNametxt().getText(),
                        registrationForm.getUserNametxt().getText(),
                        registrationForm.getPasswordtxt().getText(),
                        registrationForm.getAddresstxt().getText(),
                        registrationForm.getEmailtxt().getText(),
                        registrationForm.getCcNumbertxt().getText(),
                        registrationForm.getCcExpirytxt().getText(),
                        registrationForm.getCcYeartxt().getText(),
                        Integer.parseInt(registrationForm.getCvvtxt().getText())
                );

                if(userRegistered)
                {
                    registrationForm.setVisible(false);
                    mainMenu.setVisible(true);
                    populateEntireShowingTable();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Registration unsuccessful");

                }

            } catch (NumberFormatException n){
                JOptionPane.showMessageDialog(null, "Registration error. Please try again");
            }

        }
    }

    private class SelectByTheatreButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            selectTheatreForm.setVisible(true);
            populateTheatreSelectionTable();

        }
    }

    private class SelectByTheatreConfirmation implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            try to change table columns dynamically
            mainMenu.ChangeTableData();

            // get id of selected movie
            int index = selectTheatreForm.getTheatreTable().getSelectedRow();

            // display user selection
            mainMenu.getSelectionTxtArea().setText(theatreController.getTheatreList().get(moviefilterSelectionId).getTheatreName());

            // close window
            selectTheatreForm.setVisible(false);

//            System.out.println(index);

            populateShowingTableByTheatre(index+1);

        }
    }

    private class SelectMovieConfirmation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // get id of selected movie
            int index = selectMovieForm.getMovieTable().getSelectedRow();

            // display user selection
            mainMenu.getSelectionTxtArea().setText(movieController.getMovies().get(index).gettitle());

            // close window
            selectMovieForm.setVisible(false);

            // filter showtimes by movie_id
            populateShowingTableByMovie(index+1);

        }
    }

    private class SignUpButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginForm.setVisible(false);

            //open register form
            registrationForm.setVisible(true);

        }
    }

    private class SelectByMovieButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            loginForm.setVisible(false);
//            loginForm.displayUserMessage(movieController.getMovieTitles().toString());
            selectMovieForm.setVisible(true);

            populateMovieSelectionTable();

//            DefaultListModel<String> listModel = new DefaultListModel<>();
//
//
//
//            for(Movie s: movieController.getMovies()){
//                listModel.addElement(s.toString());
//            }
//
//            selectMovieForm.getMovieList().setModel(listModel);

            //open register form
        }
    }

    private class EnterSelectSeatsButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            selectedShowtimeId = -1;
            // get selected row
            try{
                int row = mainMenu.getMovieTable().getSelectedRow();
                selectedShowtimeId = (int) mainMenu.getMovieTable().getValueAt(row,0);
//                System.out.println(selectedShowtimeId);

                mainMenu.setVisible(false);
                selectSeatsForm.setVisible(true);

                ArrayList<Ticket> tmp = new ArrayList<>();
                tmp = dbController.getTicketByShowtimeID(selectedShowtimeId);

//                System.out.println(tmp);


                for(Ticket t: tmp){
                    selectSeatsForm.getSeatButtons().get(t.getSeatId()-1).setEnabled(false);
                }

            } catch (IndexOutOfBoundsException x) {
                JOptionPane.showMessageDialog(null, "Please select a showtime to buy tickets");
            }

        }


    }


    private class LoginButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //check that credentials are valid

            String userName = loginForm.getUserNameTxt().getText();
            String pword = loginForm.getPasswordTxt().getText();

            if(userController.authenticateUser(userName,pword)){

                // hide login form, show main menu
                loginForm.setVisible(false);
                mainMenu.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Login Credentials Error. Try again!");
            }


            // populate main table
            populateEntireShowingTable();

        }
    }

    private class ContinueAsGuestButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginForm.setVisible(false);
            mainMenu.setVisible(true);

            // populate main table
            populateEntireShowingTable();
        }
    }

    public view.loginForm getLoginForm() {
        return loginForm;
    }

    public void populateShowingTableByMovie(int movieId){

        mainMenu.getTableModeltest().setRowCount(0);

        for(int i = 0; i < showingController.getShowingList().size(); i++){

            if (showingController.getShowingList().get(i).getMovieId() == movieId) {
                // showing id
                int ShowingId = showingController.getShowingList().get(i).getShowtimeId();

                // movie name
                int movieID = showingController.getShowingList().get(i).getMovieId();
                String MovieName = movieController.getMovieByMovieID(movieID).gettitle();

                // theatre name
                int theatreId = showingController.getShowingList().get(i).getTheatreId();
                String theatreName = theatreController.getTheatreByTheatreID(theatreId).getTheatreName();

                // showing date
                LocalDate showingTime = showingController.getShowingList().get(i).getShowtime().toLocalDate();

                // add row
                mainMenu.getTableModeltest().addRow(new Object[]{ShowingId, MovieName, theatreName, showingTime});
            }
        }
    }

    public void populateShowingTableByTheatre(int tid){

        mainMenu.getTableModeltest().setRowCount(0);


        for(int i = 0; i < showingController.getShowingList().size(); i++){

            if (showingController.getShowingList().get(i).getTheatreId() == tid){
                // showing id
                int ShowingId = showingController.getShowingList().get(i).getShowtimeId();

                // movie name
                int movieID = showingController.getShowingList().get(i).getMovieId();
                String MovieName = movieController.getMovieByMovieID(movieID).gettitle();

                // theatre name
                int theatreId = showingController.getShowingList().get(i).getTheatreId();
                String theatreName = theatreController.getTheatreByTheatreID(theatreId).getTheatreName();

                // showing date
                LocalDate showingTime = showingController.getShowingList().get(i).getShowtime().toLocalDate();

                // add row
                mainMenu.getTableModeltest().addRow(new Object[]{ShowingId,MovieName,theatreName,showingTime});
            } else {


            }

        }
    }

    public void populateEntireShowingTable(){

        mainMenu.getTableModeltest().setRowCount(0);

        for(int i = 0; i < showingController.getShowingList().size(); i++){

            // showing id
            int ShowingId = showingController.getShowingList().get(i).getShowtimeId();

            // movie name
            int movieID = showingController.getShowingList().get(i).getMovieId();
            String MovieName = movieController.getMovieByMovieID(movieID).gettitle();

            // theatre name
            int theatreId = showingController.getShowingList().get(i).getTheatreId();
            String theatreName = theatreController.getTheatreByTheatreID(theatreId).getTheatreName();

            // showing date
            LocalDate showingTime = showingController.getShowingList().get(i).getShowtime().toLocalDate();

            // add row
            mainMenu.getTableModeltest().addRow(new Object[]{ShowingId,MovieName,theatreName,showingTime});


        }
    }

    public void populateMovieSelectionTable(){

        selectMovieForm.getTableModel().setRowCount(0);

        for(int i = 0; i < movieController.getMovieList().size(); i++){

            // showing id
            int movieId = movieController.getMovieList().get(i).getMovieID();

            // movie name
            String MovieName = movieController.getMovieList().get(i).gettitle();

            // rating
            String rating = movieController.getMovieList().get(i).getRating();

            // showing date
            LocalDate showingTime = movieController.getMovieList().get(i).getReleasedDate().toLocalDate();

            // add row
            selectMovieForm.getTableModel().addRow(new Object[]{movieId,MovieName,rating,showingTime});

        }
    }

    public void populateTheatreSelectionTable(){

        selectTheatreForm.getTableModel().setRowCount(0);

        for(int i = 0; i < theatreController.getTheatreList().size(); i++){

            // theatre id
            int theatreId = theatreController.getTheatreList().get(i).getTheatreId();

            // theatre name
            String theatreName = theatreController.getTheatreList().get(i).getTheatreName();


            // add row
            selectTheatreForm.getTableModel().addRow(new Object[]{theatreId,theatreName});

        }
    }

    public void populateTicketInfoTable(int ccId) throws SQLException {

        viewMyTicketsForm.getTableModel().setRowCount(0);

        ResultSet rs = dbController.getTicketDetails(ccId);

        while(rs.next()){
            // add row
            viewMyTicketsForm.getTableModel().addRow(new Object[]{rs.getInt("ticketId"),
                    rs.getString("title"),
                    rs.getTimestamp("showtime"),
                    rs.getString("seatRow"),
                    rs.getInt("seatNum"),
                    rs.getString("theatreName")});
        }

    }


    public void setLoginForm(loginForm loginForm) {
        this.loginForm = loginForm;
    }
    public SelectTheatreForm getSelectTheatreForm() {
        return selectTheatreForm;
    }

    public void setSelectTheatreForm(SelectTheatreForm selectTheatreForm) {
        this.selectTheatreForm = selectTheatreForm;
    }

    public void setSelectSeatsForm(SelectSeatsForm selectSeatsForm) {
        this.selectSeatsForm = selectSeatsForm;
    }
    public void setPaymentForm(PaymentForm paymentForm) {
        this.paymentForm = paymentForm;
    }

//    public static void main(String[] args){
//
//        GUIController guiController = new GUIController();
//
//    }


}