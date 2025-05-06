package guis;

import javax.swing.*;
import java.awt.event.*;
import models.Movie;
import models.User;
import models.Review;
import managers.MovieManager;
import managers.ReviewManager;
import managers.WatchlistManager;

public class MovieDetailGUI extends JFrame {
    private User user;
    private Movie movie;
    private ReviewManager reviewManager;
    private WatchlistManager watchlistManager;

    private JLabel titleLabel, genreLabel, yearLabel, avgRatingLabel;
    private JComboBox<Integer> ratingComboBox;
    private JTextArea noteArea;
    private JButton submitBtn, addToWatchListBtn, backBtn, removeFromWatchListBtn;

    public MovieDetailGUI(User user, String movieTitle) {
        this.user = user;
        this.reviewManager = new ReviewManager();
        this.watchlistManager = new WatchlistManager();

        MovieManager movieManager = new MovieManager();
        this.movie = movieManager.searchByTitle(movieTitle);
        initComponents();
    }

    private void initComponents() {
        setTitle("Movie Details - " + movie.getTitle());
        setSize(540, 560);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Title: " + movie.getTitle());
        titleLabel.setBounds(20, 20, 400, 25);
        add(titleLabel);

        genreLabel = new JLabel("Genre: " + movie.getGenre());
        genreLabel.setBounds(20, 50, 300, 25);
        add(genreLabel);

        yearLabel = new JLabel("Year: " + movie.getYear());
        yearLabel.setBounds(20, 80, 300, 25);
        add(yearLabel);

        double avg = reviewManager.getAverageRating(movie.getTitle());
        avgRatingLabel = new JLabel("Average Rating: " + (avg > 0 ? String.format("%.2f", avg) : "No ratings yet"));
        avgRatingLabel.setBounds(20, 110, 300, 25);
        add(avgRatingLabel);

        // displaying reviews
        JTextArea reviewsArea = new JTextArea();
        reviewsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reviewsArea);
        scrollPane.setBounds(20, 140, 400, 100);
        add(scrollPane);
        StringBuilder reviewsText = new StringBuilder();
        for (Review review : reviewManager.getMovieReviews(movie.getTitle())) {
            reviewsText.append(review.getUsername()).append(": ").append(review.getRating()).append(" - ").append(review.getNote()).append("\n");
        }
        reviewsArea.setText(reviewsText.toString());
        
        JLabel ratingLabel = new JLabel("Your Rating:");
        ratingLabel.setBounds(20, 250, 100, 25);
        add(ratingLabel);

        ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingComboBox.setBounds(130,250,90,25);
        add(ratingComboBox);

        JLabel noteLabel = new JLabel("Your Note:");
        noteLabel.setBounds(20, 290, 100, 25);
        add(noteLabel);

        noteArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(noteArea);
        scroll.setBounds(20, 320, 380, 60);
        add(scroll);
        
        submitBtn = new JButton("Add Rating");
        submitBtn.setBounds(30, 400, 120, 30);
        submitBtn.addActionListener(e -> btnSubmit_click()); 
        add(submitBtn);

        // if review already exists, then show option to delete current review
        boolean hasReview = false;
        for (Review review: reviewManager.getUserReviews(user.getUsername())){
            if (review.getMovieTitle().equalsIgnoreCase(movie.getTitle())){
                hasReview = true;
                break;
            }
        }
        if (hasReview){
            JButton btnDeleteReview = new JButton("Delete Current Review");
            btnDeleteReview.setBounds(30,480,200,30);
            btnDeleteReview.addActionListener(e->deleteReview());
            add(btnDeleteReview);
        }

        addToWatchListBtn = new JButton("Add to Watchlist");
        addToWatchListBtn.setBounds(160, 400, 150, 30);
        addToWatchListBtn.addActionListener(e -> btnAddToWatchlist_Click());
        add(addToWatchListBtn);

        removeFromWatchListBtn = new JButton("Remove from Watchlist");
        removeFromWatchListBtn.setBounds(320, 400, 180, 30);
        removeFromWatchListBtn.addActionListener(e -> btnRemoveFromWatchList_Click());
        add(removeFromWatchListBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(250, 480, 100, 30);
        backBtn.addActionListener(e -> btnBack_click(user.isAdmin()));
        add(backBtn);
        //already added
        if (watchlistManager.isInWatchlist(user.getUsername(), movie.getTitle())) {
            addToWatchListBtn.setEnabled(false);
            addToWatchListBtn.setText("Already in Watchlist");
        }
    }
    private void btnAddToWatchlist_Click() {
        boolean added = watchlistManager.addToWatchlist(user.getUsername(), movie.getTitle());
        if (added) {
            JOptionPane.showMessageDialog(this, "Movie added to your watchlist.");
            addToWatchListBtn.setEnabled(false);
            addToWatchListBtn.setText("Already in Watchlist");
            removeFromWatchListBtn.setEnabled(true);
            removeFromWatchListBtn.setText("Remove from Watchlist");
        } else {
            JOptionPane.showMessageDialog(this, "Movie is already in your watchlist.");
        }
    }
    private void btnSubmit_click() {
        int rating = (Integer) ratingComboBox.getSelectedItem();
        String note = noteArea.getText().trim();
        if (note.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a note before submitting your review.");
            return;
        }
        boolean success = reviewManager.addReview(user.getUsername(), movie.getTitle(), rating, note);
        if (success) {
            JOptionPane.showMessageDialog(this, "Review submitted successfully!");
            double avg = reviewManager.getAverageRating(movie.getTitle());
            avgRatingLabel.setText("Average Rating: " + String.format("%.2f", avg));

            dispose(); // refresh page
            new MovieDetailGUI(user, movie.getTitle()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to submit review.");
        }
    }

    private void deleteReview(){
        int confirm = JOptionPane.showConfirmDialog(this, "Delete review?","Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            boolean deleted = reviewManager.deleteReview(user.getUsername(), movie.getTitle());
            if (deleted){
                JOptionPane.showMessageDialog(this, "Review has been deleted.");
                dispose();
                new MovieDetailGUI(user, movie.getTitle()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete review.");
            }
        }
    }
    private void btnBack_click(boolean isAdmin) {
        dispose();
    }

    private void btnRemoveFromWatchList_Click() {
        boolean removed = watchlistManager.removeFromWatchlist(user.getUsername(), movie.getTitle());
        if (removed) {
            JOptionPane.showMessageDialog(this, "Movie removed from your watchlist.");
            removeFromWatchListBtn.setEnabled(false);
            removeFromWatchListBtn.setText("Already Removed from Watchlist");
            addToWatchListBtn.setEnabled(true);
            addToWatchListBtn.setText("Add to Watchlist");
        } else {
            JOptionPane.showMessageDialog(this, "Movie is not in your watchlist.");
        }
    }
}
