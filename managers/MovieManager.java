package managers;

import models.Movie;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private ArrayList<Movie> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
        readMovies();
    }

    public boolean addMovie(String title, String year, String genre) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equalsIgnoreCase(title)) {
                return false;
            }
        }
        movies.add(new Movie(title, year, genre));

        if (saveMovies()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeMovie(String title) {
        boolean removed = false;
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

    public boolean editMovie(String title, String newTitle, String year, String genre) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equalsIgnoreCase(title)) {
                if (newTitle != null && !newTitle.isEmpty()) {
                    movies.get(i).setTitle(title);
                }
                if (year != null) {
                    movies.get(i).setYear(year);
                }
                if (genre != null) {
                    movies.get(i).setGenre(genre);
                }
                saveMovies();
                return true;
            }
        }
        return false;
    }
    
    private void readMovies() {
        try {
            FileReader moviesFile = new FileReader("./movies.txt");
            BufferedReader br = new BufferedReader(moviesFile);
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                String title = userData[0];
                String year = userData[1];
                String genre = userData[2];
                
                Movie movie;
                movie = new Movie(title, year, genre);
                
                this.movies.add(movie);
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean saveMovies() {
        try {
            FileWriter moviesFile = new FileWriter("./movies.txt");
            BufferedWriter bw = new BufferedWriter(moviesFile);
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                bw.write(movie.getTitle() + "," + movie.getYear() + "," + movie.getGenre());
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}
