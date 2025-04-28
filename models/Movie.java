package models;
import java.util.ArrayList;

public class Movie{
    private String title;
    private String year;
    private String genre;
    private ArrayList<Integer> ratings;
    private ArrayList<String> notes;



    // Constructor
    public Movie(String title, String year, String genre){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.ratings = new ArrayList<Integer>();
        this.notes = new ArrayList<String>();
    }

    public void addRating(int rating, String note){
        this.ratings.add(rating);
        this.notes.add(note);
    }

    public double getAverageRating(){
        if (this.ratings.size() == 0){
            return 0.0;
        }

        double sum = 0.0;
        for (int i = 0; i < this.ratings.size(); i++){
            sum += this.ratings.get(i);
        }
        return sum / this.ratings.size();
    }


    public String getTitle(){
        return this.title;
    }
    public String getYear(){
        return this.year;
    }
    public String getGenre(){
        return this.genre;
    }

    public String toString(){
        return "Title: " + this.title + ", Year: " + this.year + ", Genre: " + this.genre;
    }

}