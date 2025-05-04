package guis;

import managers.WatchlistManager;
import models.User;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class WatchlistGUI extends JFrame{
    private User user;
    private MovieManager movieManager;
    private WatchlistManager watchlistManager;

    // initializing gui components
    private DefaultListModel<String> watchlistModel;
    private JList<String> watchlistList; 

    // constructor -- takes username and initializes GUI
    public WatchlistGUI(User user){
        this.user = user;
        this.watchlistManager = new WatchlistManager();
        initComponents();
    }

    private void initComponents(){
        setTitle(user.getUsername()+"'s Watchlist");
        setSize(500,400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // initializing list and UI (JList) to display watchlist
        watchlistModel = new DefaultListModel<>();
        watchlistList = new JList<>(watchlistModel);
        JScrollPane scrollPane = new JScrollPane(watchlistList);
        scrollPane.setBounds(20,20,440,300);
        add(scrollPane);
        
        // populating the watchlist
        List<String> movies = watchlistManager.getWatchlist(user.getUsername());
        for (String title: movies){
            watchlistModel.addElement(title);

        }

        // user double clicks on a movie to open MovieDetailGUI
        watchlistList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                if (evt.getClickCount() == 2){
                    String selectedTitle = watchlistList.getSelectedValue();
                    if (selectedTitle != null){
                        new MovieDetailGUI(user, selectedTitle).setVisible(true); // refer to MovieDetailGUI next
                    }
                }
            }
        });
    }
}