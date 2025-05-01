package managers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Review;

public class ReviewManager {
    ArrayList<Review> reviews;


    public ReviewManager() {
        this.reviews = new ArrayList<>();
        getAllReviews();
    }

    public void getAllReviews() {
        try {
            FileReader reviewsFile = new FileReader("./reviews.txt");
            BufferedReader br = new BufferedReader(reviewsFile);
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                String username = userData[0];
                String movieTitle = userData[1];
                String rating = userData[2];
                String note = userData[3];
                
                Review review;
                review = new Review(username, movieTitle, rating, note);
                this.reviews.add(review);
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Review> getUserReviews(String username) {
        ArrayList<Review> userReviews = new ArrayList<>();

        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUsername().equalsIgnoreCase(username)) {
                userReviews.add(reviews.get(i));
            }
        }

        return userReviews;
    }


    public ArrayList<String> getMostRatedMovies(){
        HashMap<String, Integer> movieRatingsCount = new HashMap<>();

        for (int i = 0; i < reviews.size(); i++) {
            String movieTitle = reviews.get(i).getMovieTitle();
            movieRatingsCount.put(movieTitle, movieRatingsCount.getOrDefault(movieTitle, 0) + 1);
        }

        ArrayList<String> mostRatedMovies = new ArrayList<>(movieRatingsCount.keySet());
        mostRatedMovies.sort((a, b) -> Integer.compare(movieRatingsCount.get(b), movieRatingsCount.get(a)));

        ArrayList<String> topTenMovies = new ArrayList<>();
        for (int i = 0; i < Math.min(10, mostRatedMovies.size()); i++) {
            topTenMovies.add(mostRatedMovies.get(i));
        }

        return topTenMovies;
    }



    public ArrayList<Review> getMovieReviews(String movieTitle) {
        ArrayList<Review> movieReviews = new ArrayList<>();

        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getMovieTitle().equalsIgnoreCase(movieTitle)) {
                movieReviews.add(reviews.get(i));
            }
        }

        return movieReviews;
    }
    public boolean addReview(String username, String movieTitle, int rating, String note) {
        Review review = new Review(username, movieTitle, String.valueOf(rating), note);
        reviews.add(review);
        if (saveReviews()) {
            return true;
        } else {
            return false;
        }
    }

    public int getAverageRating(String movieTitle) {
        int totalRating = 0;
        int count = 0;

        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getMovieTitle().equalsIgnoreCase(movieTitle)) {
                totalRating += Integer.parseInt(reviews.get(i).getRating());
                count++;
            }
        }

        if (count == 0) {
            return 0;
        } else {
            return totalRating / count;
        }
    }

    public boolean deleteReview(String username, String movieTitle) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUsername().equalsIgnoreCase(username) && reviews.get(i).getMovieTitle().equalsIgnoreCase(movieTitle)) {
                reviews.remove(i);
                saveReviews();
                return true;
            }
        }
        return false;
    }

    public boolean saveReviews() {
        try {
            FileWriter reviewsFile = new FileWriter("./reviews.txt");
            BufferedWriter bw = new BufferedWriter(reviewsFile);
            for (int i = 0; i < reviews.size(); i++) {
                bw.write(reviews.get(i).getUsername() + "," + reviews.get(i).getMovieTitle() + "," + reviews.get(i).getRating() + "," + reviews.get(i).getNote());
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
