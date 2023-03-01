package controller;

import entity.Payment;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentController {

    DBController dbController;
    Payment paymentUsed;
    int cvv;

    public PaymentController(DBController dbController) {
        this.dbController = dbController;
    }


    public boolean checkPayment()
    {
        int ccId = this.paymentUsed.getCcId();

        //Check for CC expiry
        if(CCExpiryCheck(ccId))
            return false;

        //Check that our credit card number is 16 digits
        //ccNum is a String in the DB
        String ccNum = dbController.getCCNumber(ccId);
        int ccNumLength = ccNum.length();

        if(ccNumLength != 16 )
            return false;

        //Check that our CVV is 3 digits in length
        int cvvLength = String.valueOf(this.cvv).length();
        if(cvvLength != 3)
            return false;


        return true;
    }

    private boolean CCExpiryCheck(int ccId)
    {
        //return true if CC is expired
        LocalDate expiry = dbController.getCCExpiryDate(ccId);
        if(expiry.isBefore(LocalDate.now()))
            return true;

        return false;
    }

    //Ticket stuff to the DB
    public ArrayList<Integer> generateTicket(int showtimeId, double price, ArrayList<Integer> seatId, int CCId)
    {
        //INSERT TO DATABASE
        ArrayList<Integer> ticketIdsGenerated = new ArrayList<Integer>();
        int temp;
        for(int i = 0; i < seatId.size(); i++)
        {
            temp = dbController.addTicketDB(showtimeId, price, seatId.get(i));
            ticketIdsGenerated.add(temp);
        }

        //we generated a bunch of ticketId used to generate sale
        generateSale(ticketIdsGenerated, CCId);
        return ticketIdsGenerated;
    }

    private void generateSale(ArrayList<Integer> ticketIds, int CCId)
    {
        //INSERT TO DATABASE
        int saleId = dbController.addSaleDB(ticketIds);

        //we generated a saleId used to generate payment
        generatePayment(saleId, CCId);
    }

    private void generatePayment(int saleId, int ccId)
    {
        dbController.addPaymentDB(saleId, ccId);

    }



    public void setPaymentUsed(Payment paymentUsed) {
        this.paymentUsed = paymentUsed;
    }




}
