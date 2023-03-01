package controller;
import entity.*;
import service.Constants;

import java.sql.*;
import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.ArrayList;

public class DBController implements Constants {

    private Connection conn;
    private ResultSet rs;
    private Statement statement;

    private static DBController instance;

    private DBController(){}

//    public DBController(){
//        getConnection();
//    }

    // singleton pattern enforcing one DB connection
    public static DBController getInstance(){
        if(instance == null){
            instance = new DBController();
            instance.getConnection();
        }

        return instance;
    }

    public void getConnection() {
        try {
            this.conn = DriverManager.getConnection(DB_NAME, DB_USER, DB_PW);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public void close(){
        try{
            this.conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectAll(String tableName){
        try{
            String query = "SELECT * FROM ENSF614PROJECT." + tableName;
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getTicketDetails(int ccId) throws SQLException {

        String query = "SELECT TICKET.ticketId, MOVIE.title, SHOWTIME.showtime, SEAT.seatRow, SEAT.seatNum, THEATRE.theatreName FROM CCINFO " +
                "LEFT JOIN PAYMENT ON PAYMENT.ccId = CCINFO.ccId " +
                "LEFT JOIN SALE ON SALE.saleId = PAYMENT.saleId " +
                "LEFT JOIN TICKET ON TICKET.ticketId = SALE.ticketId " +
                "LEFT JOIN SHOWTIME ON SHOWTIME.showtimeId = TICKET.showtimeId " +
                "LEFT JOIN MOVIE ON MOVIE.movieId = SHOWTIME.movieId " +
                "LEFT JOIN SEAT ON SEAT.seatId = TICKET.seatId " +
                "LEFT JOIN THEATRE ON THEATRE.theatreId = SHOWTIME.theatreId " +
                "WHERE CCINFO.ccID = " + ccId;

        Statement myStmt = conn.createStatement();
        ResultSet rs = myStmt.executeQuery(query);

        return rs;
    }

    public ArrayList<Movie> getMovies(){
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM MOVIE");

            while(rs.next()){

                Movie m = new Movie(rs.getInt("movieId"),
                        rs.getString("title"),
                        rs.getString("rating"),
                        rs.getTimestamp("releasedate").toLocalDateTime());

                movies.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;

    }

    public ArrayList<Theatre> getTheatres() {
        ArrayList<Theatre> theatres = new ArrayList<>();

        try {
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM THEATRE");

            while (rs.next()) {

                Theatre m = new Theatre(rs.getInt("theatreId"),
                        rs.getString("theatreName"));

                theatres.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return theatres;

    }


    public ArrayList<RegisteredUser> getUsers(){
        ArrayList<RegisteredUser> users = new ArrayList<>();

        try{
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM REGISTERED_USERS");

            while(rs.next()){

                RegisteredUser ru = new RegisteredUser(rs.getString("userName"),
                        rs.getString("pwrd"), rs.getString("address"), rs.getString("email"), rs.getInt("ccId"));

                users.add(ru);
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return users;

    }

    //to do getShowtimes
    public ArrayList<Showing> getShowing(){
        ArrayList<Showing> showing = new ArrayList<>();

        try{
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM SHOWTIME");

            while(rs.next()){

                Showing sh = new Showing(rs.getInt("showtimeId"), rs.getInt("movieId"), rs.getInt("theatreId"), rs.getTimestamp("showtime").toLocalDateTime()); //array list????

                showing.add(sh);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return showing;
    }

    public ArrayList<Seat> getSeats(){
        ArrayList<Seat> seats = new ArrayList<>();

        try{
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM SEAT");

            while(rs.next()){

                Seat s = new Seat(rs.getInt("seatId"),rs.getInt("seatRow"),rs.getInt("seatNum"));
                seats.add(s);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return seats;
    }

    public String getCCHolderName(int ccId)
    {
        String result;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT holderName FROM CCINFO WHERE ccId = " + Integer.toString(ccId);
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getString("holderName");

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }



    public String getCCNumber(int ccId)
    {
        String result = "";
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT cardNumber FROM CCINFO WHERE ccId = " + Integer.toString(ccId);
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result += rs.getString("cardNumber");

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    public int getCVV(int ccId)
    {
        int result = 0;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT cvv FROM CCINFO WHERE ccId = " + Integer.toString(ccId);
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getInt("cvv");

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    public LocalDate getCCExpiryDate(int ccId)
    {
        LocalDate result;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT expiry FROM CCINFO WHERE ccId = " + Integer.toString(ccId);
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getDate("expiry").toLocalDate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }

    public boolean authenticateUser(String userName, String pword)
    {
        String result = "";
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT userName FROM registered_user WHERE userName = '" + userName + "' and pwrd = '" + pword + "'";
            ResultSet rs = myStmt.executeQuery(query);

            if(rs.next()){
                return true;
            }else{
                return false;
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUserExists(String userName)
    {
        String result = "";
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT userName FROM registered_user WHERE userName = '" + userName + "'";
            ResultSet rs = myStmt.executeQuery(query);

            if(rs.next()){
                return true;
            }else{
                return false;
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public RegisteredUser retrieveUser(String userName, String pword)
    {
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT * FROM registered_user WHERE userName = '" + userName + "' and pwrd = '" + pword + "'";
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            RegisteredUser result = new RegisteredUser(rs.getString("userName"), rs.getString("pwrd"), rs.getString("address"), rs.getString("email"), rs.getInt("ccId"));
            return result;


        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void registerNewUser(String fullName, String userName, String pword, String address,
                                          String email, String CCNum, String CCexpiryMonth, String CCexpiryYear, int cvv)
    {
        int ccId = addCCInfo(fullName, CCNum, CCexpiryMonth, CCexpiryYear, cvv);
        String query = "INSERT INTO REGISTERED_USER (userName, pwrd, fullName, address, email, ccId) VALUES (";
        query += "'" + userName + "', ";
        query += "'" + pword + "', ";
        query += "'" + fullName + "', ";
        query += "'" + address + "', ";
        query += "'" + email + "', ";
        query += ccId + ")";

        try{
            PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public int addCCInfo(String fullName, String CCNum, String CCexpiryMonth, String CCexpiryYear, int cvv)
    {

        String expiry = CCexpiryYear + "-01-" + CCexpiryMonth;
        int ccId = generateNewCCId();

        String query = "INSERT INTO CCINFO (ccId,holderName,cardNumber,expiry,cvv) VALUES (";
        query += "'" + ccId + "', ";
        query += "'" + fullName + "', ";
        query += "'" + CCNum + "', ";
        query += "'" + expiry + "', ";
        query += "'" + Integer.toString(cvv) + "') ";


        try{
            PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return ccId;
    }

    private int generateNewCCId()
    {
        int result = 0;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT MAX(ccId) FROM CCINFO";
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getInt("MAX(ccId)");
            result++;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }

    public int addTicketDB(int showtimeId, double price, int seatId)
    {
        int ticketId = generateNewTicketId();
        String query = "INSERT INTO TICKET (ticketId,showtimeId,price,seatId) VALUES (";
        query += ticketId + ", ";
        query += showtimeId + ", ";
        query += price + ", ";
        query += seatId + ")";

        try{
            PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return ticketId;
    }

    private int generateNewTicketId()
    {
        int result = 0;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT MAX(ticketId) FROM TICKET";
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getInt("MAX(ticketId)");
            result++;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }

    public int addSaleDB(ArrayList<Integer> ticketIds)
    {
        int saleId = generateNewSaleId();
        for (int i = 0; i < ticketIds.size(); i++)
        {
            String query = "INSERT INTO SALE (saleId, ticketId) VALUES (";
            query += saleId + ", ";
            query += ticketIds.get(i) + ")";

            try{
                PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();


            }catch(SQLException e){
                throw new RuntimeException(e);
            }

        }

        return saleId;

    }

    private int generateNewSaleId()
    {
        int result = 0;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT MAX(saleId) FROM SALE";
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getInt("MAX(saleId)");
            result++;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }

    public void addPaymentDB(int saleId, int ccId)
    {
        int paymentId = generateNewPaymentId();
        String query = "INSERT INTO PAYMENT  (paymentId, saleId, ccId) VALUES (";
        query += paymentId + ", ";
        query += saleId + ", ";
        query += ccId + ")";

        try{
            PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    private int generateNewPaymentId()
    {
        int result = 0;
        try{
            Statement myStmt = conn.createStatement();
            String query = "SELECT MAX(paymentId) FROM PAYMENT";
            ResultSet rs = myStmt.executeQuery(query);

            rs.next();
            result = rs.getInt("MAX(paymentId)");
            result++;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }



    public ArrayList<Ticket> getTicketByShowtimeID(int showtimeid){

        ArrayList<Ticket> tickets = new ArrayList<>();

        try{
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT * FROM TICKET WHERE showtimeId = "+showtimeid);

            while(rs.next()){

                Ticket t = new Ticket(rs.getInt("ticketId"),rs.getInt("showtimeid"),rs.getDouble("price"),rs.getInt("seatId"));
                tickets.add(t);
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tickets;




    }

//    public static void main(String[] args) throws SQLException {
//
//        DBController db = new DBController();
//        ResultSet r = db.getTicketDetails(2);
//        while(r.next()){
//            System.out.println(r.getString("ticketId"));
//        }
//
//    }




}
