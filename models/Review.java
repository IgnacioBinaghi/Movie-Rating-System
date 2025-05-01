package models;

public class Review {
    private String username;
    private String movieTitle;
    private String rating;
    private String note;

    public Review(String username, String movieTitle, String rating, String note) {
        this.username = username;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.note = note;
    }

    public String getUsername() {
        return username;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getRating() {
        return rating;
    }

    public String getNote() {
        return note;
    }
}
