package entity;

public class Seat {

    private int seatId;
    private int row;
    private int seatNumber;
//    private boolean purchaseStatus;
    

    public Seat(int seatId, int row, int seatNumber) {
        this.seatId = seatId;
        this.row = row;
        this.seatNumber = seatNumber;
//        this.purchaseStatus = purchaseStatus;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", row=" + row +
                ", seatNumber=" + seatNumber +
                '}';
    }
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

//    public boolean isPurchaseStatus() {
//        return purchaseStatus;
//    }
//
//    public void setPurchaseStatus(boolean purchaseStatus) {
//        this.purchaseStatus = purchaseStatus;
//    }

}
