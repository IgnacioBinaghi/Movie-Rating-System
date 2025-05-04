package guis;

import managers.MovieManager;
import models.Movie;
import models.User;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class BrowseGUI extends JFrame{
    private User user;
    private MovieManager movieManager;

    private DefaultListModel<String> movieListModel; // store movie titles for JList
    private JList<String> movieList; // Jlist to display the movie titles ^

    // constructor -- takes username and initializes GUI
    public BrowseGUI(User user){
        this.user = user;
        this.movieManager=new MovieManager();
        initComponents();
    }

    private void initComponents(){
        setTitle("Browse Movie Catalogue");
        setSize(500,400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // initializing list and UI (JList) to display movie titles (includes scroll pane)
        movieListModel = new DefaultListModel<>();
        movieList = new JList<>(movieListModel);
        JScrollPane scrollPane = new JScrollPane(movieList);
        scrollPane.setBounds(20,20,440,300);
        add(scrollPane);
        
        // populating the list w/ movie catalogue
        List<Movie> movies = movieManager.getAllMovies();
        for (Movie movie: movies){
            movieListModel.addElement(movie.getTitle());

        }

        // user double clicks on a movie to open MovieDetailGUI
        movieList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                if (evt.getClickCount() == 2){
                    String selectedTitle = movieList.getSelectedValue();
                    if (selectedTitle != null){
                        new MovieDetailGUI(user, selectedTitle).setVisible(true); // refer to MovieDetailGUI next
                    }
                }
            }
        });
    }
}