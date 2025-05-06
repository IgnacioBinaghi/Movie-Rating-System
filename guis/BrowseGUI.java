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

    private int currentPage =0;
    private final int moviesPerPage = 20;
    private List<Movie> allMovies;
    private JButton prevBtn, nextBtn;

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
        scrollPane.setBounds(20,20,440,280);
        add(scrollPane);
        
        allMovies = movieManager.getAllMovies();

        prevBtn = new JButton("Previous");
        prevBtn.setBounds(100, 330, 100, 30);
        prevBtn.addActionListener(e->{
            if(currentPage>0){
                currentPage--;
                updateMovieList();
            }
        });
        add(prevBtn);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(300, 330, 100, 30);
        nextBtn.addActionListener(e->{
            if ((currentPage +1)*moviesPerPage<allMovies.size()){
                currentPage++;
                updateMovieList();
            }
        });
        add(nextBtn);

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

        updateMovieList();
    }

    private void updateMovieList(){
        movieListModel.clear();
        int start = currentPage*moviesPerPage;
        int end=Math.min(start+moviesPerPage, allMovies.size());
        for (int i=start; i<end; i++){
            movieListModel.addElement(allMovies.get(i).getTitle());
        }

        prevBtn.setEnabled(currentPage>0);
        int totalPages = (int)Math.ceil((double) allMovies.size()/moviesPerPage);
        nextBtn.setEnabled(currentPage<totalPages-1);
    }

}