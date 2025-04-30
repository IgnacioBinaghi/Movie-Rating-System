package managers;

import models.Movie;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies;
    private final String movieFile = "movies.txt";

    public MovieManager() {
        movies = new ArrayList<>();
        readMovies(); 
    }

    public void addMovie(String title, String year, String genre) {
        movies.add(new Movie(title, year, genre));
        saveMovies(); 
    }

    public boolean removeMovie(String title) {
        boolean removed; 
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equalsIgnoreCase(title)) {
                movies.remove(i);  
                removed = true; 
                break;
            }
        }
        if (removed) {
            saveMovies();
        }
        return removed; 
        
    }

    public Movie searchByTitle(String title) {
        for (Movie m : movies) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public boolean editMovie () {
        //to do
    }
    
    private void readMovies() {
        //to do
    }

    private void saveMovies() {
        //to do
    }

}
