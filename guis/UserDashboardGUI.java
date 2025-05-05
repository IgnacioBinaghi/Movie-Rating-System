package guis;

import javax.swing.*;
import java.awt.event.*;
import models.User; 

public class UserDashboardGUI extends JFrame {
    private User user;

    // initializing gui elements 
    private JButton searchBtn, browseBtn, watchlistBtn;

    // constructor (initializing gui here)
    public UserDashboardGUI(User user){
        this.user = user;
        initComponents();
    }

    private void initComponents(){
        setTitle("Welcome to the User Dashboard, "+ user.getUsername()+"!");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // greeting user to dashboard
        JLabel welcomeLabel = new JLabel("Choose an action: ");
        welcomeLabel.setBounds(20,20,200,25);
        add(welcomeLabel);

        // selecting search 
        searchBtn = new JButton("Search Movies");
        searchBtn.setBounds(100,60,180,30);
        searchBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SearchGUI(user).setVisible(true);
            }
        });
        add(searchBtn);

        // selecting browse
        
        browseBtn = new JButton("Browse Movies");
        browseBtn.setBounds(100,110,180,30);
        browseBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new BrowseGUI(user).setVisible(true);
            }
        });
        add(browseBtn);

        // selecting watchlist
        watchlistBtn = new JButton("View Watchlist");
        watchlistBtn.setBounds(100, 160, 180, 30);
        watchlistBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new WatchlistGUI(user).setVisible(true);
            }
        });
        add(watchlistBtn);

        // selecting view ratings
        JButton viewRatingsBtn = new JButton("View Your Ratings");
        viewRatingsBtn.setBounds(100, 210, 180, 30);
        viewRatingsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new viewRatingsGUI(user).setVisible(true);
            }
        });
        add(viewRatingsBtn);
    }
}