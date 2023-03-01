package entity;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Movie {

    private int movieID;
    private String title;
    private String rating;
    private LocalDateTime releasedDate;

    public LocalDateTime getReleasedDate() {
        return this.releasedDate;
    }

    public void setReleasedDate(LocalDateTime releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Movie(int movieID, String title, String rating, LocalDateTime releasedDate){
        this.movieID = movieID;
        this.title = title;
        this.rating = rating;
        this.releasedDate = releasedDate;
    }

    public String toString() {
        return "Movie [movieID=" + this.movieID + ", title=" + this.title + ", rating=" + this.rating + "releaseDate = " + this.releasedDate + "]";
    }

    public int getMovieID() {
        return this.movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String gettitle() {
        return this.title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    //TODO: addShowtime(Showing showtime)
    //TODO: removeShowtime(Showing showtime)



//    public ArrayList<Showing> getShowingList() {
//        return showingList;
//    }
//
//    public void setShowingList(ArrayList<Showing> showingList) {
//        this.showingList = showingList;
//    }
//
//    public void addShowing(Showing showing){
//        showingList.add(showing);
//    }
}
