package entity;

public class Payment {

    int paymentId;
    int saleId;
    int ccId;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCcId() {
        return ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }



}
