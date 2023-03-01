package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Showing {

    private int showtimeId;
    private int movieId;
    private int theatreId;
    private LocalDateTime showtime;
    private ArrayList<Seat> seatList = new ArrayList<>();

    public Showing(int showtimeId, int movieID, int theatreId, LocalDateTime showtime) {
        this.showtimeId = showtimeId;
        this.movieId = movieID;
        this.theatreId = theatreId;
        this.showtime = showtime;
    }
    public void addSeat(Seat s)
    {
        this.seatList.add(s);
    }


//    public void purchaseSeat(int rowNum, int seatNum)
//    {
//        for(int i = 0; i < seatList.size(); i++)
//        {
//            Seat s = seatList.get(i);
//            if(s.getRow() == rowNum && s.getSeatNumber() == seatNum)
//            {
//                s.setPurchaseStatus(true);
//                break;
//            }
//        }
//    }

//    public void cancelSeat(int rowNum, int seatNum)
//    {
//        for(int i = 0; i < seatList.size(); i++)
//        {
//            Seat s = seatList.get(i);
//            if(s.getRow() == rowNum && s.getSeatNumber() == seatNum)
//            {
//                s.setPurchaseStatus(false);
//                break;
//            }
//        }
//
//    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public ArrayList<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(ArrayList<Seat> seatList) {
        this.seatList = seatList;
    }

    public LocalDateTime getShowtime() {
        return showtime;
    }

    public void setShowtime(LocalDateTime showtime) {
        this.showtime = showtime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }
}
