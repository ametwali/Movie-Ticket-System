package controller;

import java.util.ArrayList;

import entity.Seat;
import entity.Showing;

public class ShowingController {

    private ArrayList<Showing> showingList;
    DBController dbController;

    public ShowingController(DBController dbController){
        this.dbController = dbController;
        this.showingList = dbController.getShowing();
        populateShowingWithSeats();
    }

    public Showing getShowtimeByShowtimesId(int showtimeId){

        for(Showing i: showingList){
            if(i.getShowtimeId() == showtimeId){
                return i;
            }
        }
        return null;

    }

    public void populateShowingWithSeats(){

        ArrayList<Seat> tmpSeats = new ArrayList<>();

        tmpSeats = dbController.getSeats();

        for(Showing s: showingList){
            s.setSeatList(tmpSeats);
        }

    }


    //want this here? or want it in
    public ArrayList<Showing> filterShowtimeByTheatreId(int filterTheatreId){
        ArrayList<Showing> temp = new ArrayList<Showing>();


        for(Showing i: showingList){
            if(i.getTheatreId() == filterTheatreId){
                temp.add(i);
            }
        }
        return temp;
    }


    public ArrayList<Showing> filterMovieByTheatreId(int filterMovieId){
        ArrayList<Showing> temp = new ArrayList<Showing>();


        for(Showing i: showingList){
            if(i.getTheatreId() == filterMovieId){
                temp.add(i);
            }
        }
        return temp;
    }

    public ArrayList<Showing> getShowing(){
        ArrayList<Showing> showings = new ArrayList<Showing>();

        for(Showing sh: showingList){
            showings.add(sh);
        }

        return showings;
    }

    public ArrayList<Showing> getShowingList() {
        return showingList;
    }

    public void setShowingList(ArrayList<Showing> showingList) {
        this.showingList = showingList;
    }

//    public static void main(String[] args){
//
//        ShowingController showingController = new ShowingController(new DBController());
//        System.out.println(showingController.getShowingList().get(1).getSeatList().toString());
//
//    }
}