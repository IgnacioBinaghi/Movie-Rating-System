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

    private JLabel lblTitle, lblGenre, lblYear, lblAvgRating;
    private JComboBox<Integer> ratingComboBox;
    private JTextArea noteArea;
    private JButton btnSubmit, btnAddToWatchlist, btnBack;

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
        setSize(450, 420);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("Title: " + movie.getTitle());
        lblTitle.setBounds(20, 20, 400, 25);
        add(lblTitle);

        lblGenre = new JLabel("Genre: " + movie.getGenre());
        lblGenre.setBounds(20, 50, 300, 25);
        add(lblGenre);

        lblYear = new JLabel("Year: " + movie.getYear());
        lblYear.setBounds(20, 80, 300, 25);
        add(lblYear);

        double avg = reviewManager.getAverageRating(movie.getTitle());
        lblAvgRating = new JLabel("Average Rating: " + (avg > 0 ? String.format("%.2f", avg) : "No ratings yet"));
        lblAvgRating.setBounds(20, 110, 300, 25);
        add(lblAvgRating);
        
        JLabel ratingLabel = new JLabel("Your Rating:");
        ratingLabel.setBounds(20, 150, 100, 25);
        add(ratingLabel);

        ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingComboBox.setBounds(130,150,90,25);
        add(ratingComboBox);

        JLabel noteLabel = new JLabel("Your Note:");
        noteLabel.setBounds(20, 190, 100, 25);
        add(noteLabel);

        noteArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(noteArea);
        scroll.setBounds(20, 220, 380, 60);
        add(scroll);
        
        btnSubmit = new JButton("Add Rating");
        btnSubmit.setBounds(85, 300, 130, 30);
        btnSubmit.addActionListener(e -> btnSubmit_click()); 
        add(btnSubmit);

        btnAddToWatchlist = new JButton("Add to Watchlist");
        btnAddToWatchlist.setBounds(85 + 130 + 20, 300, 130, 30);
        btnAddToWatchlist.addActionListener(e -> btnAddToWatchlist_Click());
        add(btnAddToWatchlist);

        btnBack = new JButton("Back");
        btnBack.setBounds(175, 340, 100, 30);
        btnBack.addActionListener(e -> btnBack_click());
        add(btnBack);
        //already added
        if (watchlistManager.isInWatchlist(user.getUsername(), movie.getTitle())) {
            btnAddToWatchlist.setEnabled(false);
            btnAddToWatchlist.setText("Already in Watchlist");
        }
    }
    private void btnAddToWatchlist_Click() {
        boolean added = watchlistManager.addToWatchlist(user.getUsername(), movie.getTitle());
        if (added) {
            JOptionPane.showMessageDialog(this, "Movie added to your watchlist.");
            btnAddToWatchlist.setEnabled(false);
            btnAddToWatchlist.setText("Already in Watchlist");
        } else {
            JOptionPane.showMessageDialog(this, "Movie is already in your watchlist.");
        }
    }
    private void btnSubmit_click() {
        int rating = (Integer) ratingComboBox.getSelectedItem();
        String note = noteArea.getText().trim();
        boolean success = reviewManager.addReview(user.getUsername(), movie.getTitle(), rating, note);
        if (success) {
            JOptionPane.showMessageDialog(this, "Review submitted sucessfully!");
            double avg = reviewManager.getAverageRating(movie.getTitle());
            lblAvgRating.setText("Average Rating: " + String.format("%.2f", avg));
        } else {
            JOptionPane.showMessageDialog(this, "Failed to submit review.");
        }
    }
    private void btnBack_click() {
        new UserDashboardGUI(user).setVisible(true);
        dispose();
    }
}
