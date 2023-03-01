package entity;

import java.util.ArrayList;

public class Theatre {

    private int theatreId;
    private String theatreName;



    public String toString() {
        return "Theatre [theatreId=" + this.theatreId + ", theatreName=" + this.theatreName + "]";
    }

    public Theatre(int theatreId, String theatreName) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
    }

    public int getTheatreId() {
        return this.theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return this.theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
}
