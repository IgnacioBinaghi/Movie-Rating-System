package models;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private ArrayList<String> watchlist;
    private ArrayList<String> ratedMovies;



    // Constructor
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.watchlist = new ArrayList<String>();
        this.ratedMovies = new ArrayList<String>();
        this.isAdmin = isAdmin;
    }

    public void addToWatchlist(String movieTitle) {
        if (this.watchlist.contains(movieTitle)) {
            return;
        }
        this.watchlist.add(movieTitle);
    }
    public void removeFromWatchlist(String movieTitle) {
        this.watchlist.remove(movieTitle);
    }

    public void addRatedMovie(String movieTitle) {
        if (this.ratedMovies.contains(movieTitle)) {
            return;
        }
        this.ratedMovies.add(movieTitle);
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public ArrayList<String> getWatchlist() {
        return this.watchlist;
    }
    public ArrayList<String> getRatedMovies() {
        return this.ratedMovies;
    }
}
