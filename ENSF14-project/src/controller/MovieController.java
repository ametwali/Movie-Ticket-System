package controller;

import entity.Movie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class MovieController {


    private ArrayList<Movie> movieList;
    DBController dbController;

    public MovieController(DBController dbController) {
        this.dbController = dbController;

        ArrayList<Movie> movieList = dbController.getMovies();

        this.movieList = movieList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Movie> getMovies(){
        return movieList;
    }


    public Movie getMovieByMovieID(int movieId){
        Movie movie = null;
        for(Movie m: movieList){
            if (m.getMovieID() == movieId) {
                movie = m;
            }
        }

        return movie;

    }

//    public static void main(String[] args){
//        MovieController movieController = new MovieController(new DBController());
//
//        System.out.println(movieController.getMovieByMovieID(2).toString());
//
//    }

}