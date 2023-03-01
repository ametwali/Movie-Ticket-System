package controller;

import entity.Movie;
import entity.Theatre;

import java.util.ArrayList;

public class TheatreController {

    private ArrayList<Theatre> theatreList;
    DBController dbController;

    public TheatreController(DBController dbController) {

        this.dbController = dbController;
        theatreList = this.dbController.getTheatres();

    }

    public ArrayList<Theatre> getTheatreList() {
        return theatreList;
    }

    public Theatre getTheatreByTheatreID(int theatreId) {
        Theatre theatre = null;
        for (Theatre t : theatreList) {
            if (t.getTheatreId() == theatreId) {
                theatre = t;
            }
        }

        return theatre;
    }


//    public ArrayList<Theatre> getTheatres(){
//        return theatreList;
//
//    }



}