package entity;

import java.util.ArrayList;

public class Auditorium {

    private int auditoriumID;
    private ArrayList<Showing> showingList;

    public Auditorium(int auditoriumID, ArrayList<Showing> showingList) {
        this.auditoriumID = auditoriumID;
        this.showingList = showingList;
    }

    public int getAuditoriumID() {
        return auditoriumID;
    }

    public void setAuditoriumID(int auditoriumID) {
        this.auditoriumID = auditoriumID;
    }

    public ArrayList<Showing> getShowingList() {
        return showingList;
    }

    public void setShowingList(ArrayList<Showing> showingList) {
        this.showingList = showingList;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "auditoriumID=" + auditoriumID +
                ", seatList=" +
                '}';
    }
}
