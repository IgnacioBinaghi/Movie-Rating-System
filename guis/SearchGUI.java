package guis;

import javax.swing.*;
import java.awt.event.*;
import managers.MovieManager;
import managers.WatchlistManager;
import models.Movie;
import models.User;

public class SearchGUI extends JFrame {
    private User user;
    private MovieManager movieManager;

    // intializing ui elements
    private JTextField searchField;
    private JButton searchBtn;
    private DefaultListModel<String> resultsModel;
    private JList<String> resultsList;
    private JLabel statusLabel;

    //constructor, initializing GUI
    public SearchGUI(User user){
        this.user = user;
        this.movieManager =new MovieManager();
        initComponents();
    }

    private void initComponents(){
        setTitle("Search Movies");
        setSize(500,400);
        setLayout(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // search field label
        JLabel searchLabel = new JLabel("Search by title: ");
        searchLabel.setBounds(20,20, 120, 25);
        add(searchLabel);

        // text field 
        searchField=new JTextField();
        searchField.setBounds(140,20,200,25);
        add(searchField);

        // button (click to submit text field)
        searchBtn = new JButton("Search");
        searchBtn.setBounds(350,20,100,25);
        searchBtn.addActionListener(e -> performSearch());
        add(searchBtn);

        // list and ui to display search results (w/ scroll pane)
        resultsModel = new DefaultListModel<>();
        resultsList = new JList<>(resultsModel);
        JScrollPane scrollPane = new JScrollPane(resultsList);
        scrollPane.setBounds(20,60,430,220);
        add(scrollPane);

        statusLabel = new JLabel(" "); // status label (might not keep)
        statusLabel.setBounds(20,290,300,25);
        add(statusLabel);

        // select movie
        resultsList.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                if (event.getClickCount() == 2){
                    String selectedTitle = resultsList.getSelectedValue();
                    if (selectedTitle != null){
                        new MovieDetailGUI(user, selectedTitle).setVisible(true);
                    }
                }
            }
        });
    }

// searching for movie
    private void performSearch(){
        String title = searchField.getText().trim();
        resultsModel.clear();
        if (title.isEmpty()){
            statusLabel.setText("Enter a movie title.");
            return;
        }
        Movie movie = movieManager.searchByTitle(title);
        if (movie != null){
            resultsModel.addElement(movie.getTitle());
            statusLabel.setText("Movie found: "+movie.getTitle());
        }
        else {
            statusLabel.setText("No movie found with that title.");
        }
    }
}