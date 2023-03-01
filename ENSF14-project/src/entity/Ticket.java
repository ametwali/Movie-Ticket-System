package entity;

public class Ticket {
    int ticketId;
    int showtimeId;

    double price;
    int seatId;

    public Ticket(int ticketId, int showtimeId, double price, int seatId) {
        this.ticketId = ticketId;
        this.showtimeId = showtimeId;
        this.price = price;
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", showtimeId=" + showtimeId +
                ", price=" + price +
                ", seatId=" + seatId +
                '}';
    }
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }


}
