package guis;

import javax.swing.*;
import managers.MovieManager;
import models.User;
import models.Movie;

public class MovieManagementGUI extends JFrame {
    private User admin;
    private MovieManager movieManager;
    private JButton addMovieBtn, removeMovieBtn, editMovieBtn;


    public MovieManagementGUI(User admin){
        this.admin = admin;
        this.movieManager = new MovieManager();
        initComponents();
    }

    private void initComponents(){
        setTitle("Welcome to the Movie Management Dashboard, "+ admin.getUsername()+"!");
        setSize(400,300);
        setLayout(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addMovieBtn = new JButton("Add Movie");
        addMovieBtn.setBounds(100,60,180,30);
        addMovieBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter movie title:");
            String year = JOptionPane.showInputDialog("Enter movie release year:");
            String genre = JOptionPane.showInputDialog("Enter movie genre:");

            movieManager.addMovie(title, year, genre);
            JOptionPane.showMessageDialog(this, "Movie added");
        });
        add(addMovieBtn);

        removeMovieBtn = new JButton("Remove Movie");
        removeMovieBtn.setBounds(100,110,180,30);
        removeMovieBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter movie title to remove:");
            if (movieManager.removeMovie(title)) {
                JOptionPane.showMessageDialog(this, "Movie removed");
            } else {
                JOptionPane.showMessageDialog(this, "Movie not found");
            }
        });
        add(removeMovieBtn);

        editMovieBtn = new JButton("Edit Movie");
        editMovieBtn.setBounds(100,160,180,30);
        editMovieBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter movie title to edit:");
            Movie movie = movieManager.searchByTitle(title);

            String newYear = JOptionPane.showInputDialog("Enter new movie release year:", movie.getYear());
            String newGenre = JOptionPane.showInputDialog("Enter new movie genre:", movie.getGenre());
            String newTitle = JOptionPane.showInputDialog("Enter new movie title:", movie.getTitle());

            if (movieManager.editMovie(title, newTitle, newYear, newGenre)) {
                JOptionPane.showMessageDialog(this, "Movie edited");
            } else {
                JOptionPane.showMessageDialog(this, "Movie not found");
            }
        });
        add(editMovieBtn);
    }
}