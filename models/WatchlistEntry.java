package models;

// todo add more later? 

public class WatchlistEntry{
    private String username;
    private String movieTitle;

    public WatchlistEntry(String username, String movieTitle){
        this.username = username;
        this.movieTitle = movieTitle;

    }

    public String getUsername(){
        return username;
    }

    public String getMovieTitle(){
        return movieTitle;
    }
}