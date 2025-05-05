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
    private JButton submitBtn, addToWatchlistBtn;

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
        /* 
        JLabel ratingLabel = new JLabel("Your Rating:");
        ratingLabel.setBounds(20, 150, 100, 25);
        add(ratingLabel);

        ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingComboBox.setBounds(130, 150, 50, 25);
        add(ratingComboBox);

        JLabel noteLabel = new JLabel("Your Note:");
        noteLabel.setBounds(20, 190, 100, 25);
        add(noteLabel);

        noteArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(noteArea);
        scroll.setBounds(20, 220, 380, 60);
        add(scroll);
        */
        submitBtn = new JButton("Add Rating");
        submitBtn.setBounds(85, 300, 130, 30);
        //submitBtn.addActionListener(e -> SubmitBtn_Click()); need to implement
        add(submitBtn);

        addToWatchlistBtn = new JButton("Add to Watchlist");
        addToWatchlistBtn.setBounds(85 + 130 + 20, 300, 130, 30);
        addToWatchlistBtn.addActionListener(e -> AddToWatchlistBttn_Click());
        add(addToWatchlistBtn);

        //already added
        if (watchlistManager.isInWatchlist(user.getUsername(), movie.getTitle())) {
            addToWatchlistBtn.setEnabled(false);
            addToWatchlistBtn.setText("Already in Watchlist");
        }
    }
    private void AddToWatchlistBttn_Click() {
        boolean added = watchlistManager.addToWatchlist(user.getUsername(), movie.getTitle());
        if (added) {
            JOptionPane.showMessageDialog(this, "Movie added to your watchlist.");
            addToWatchlistBtn.setEnabled(false);
            addToWatchlistBtn.setText("Already in Watchlist");
        } else {
            JOptionPane.showMessageDialog(this, "Movie is already in your watchlist.");
        }
    }
}
