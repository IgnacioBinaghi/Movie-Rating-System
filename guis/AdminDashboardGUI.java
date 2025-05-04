package guis;

import javax.swing.*;
import java.awt.event.*;
import models.User;

public class AdminDashboardGUI extends JFrame {
    private User admin;

    private JButton searchBtn, browseBtn;

    public AdminDashboardGUI(User admin){
        this.admin = admin;
        initComponents();
    }

    private void initComponents(){
        setTitle("Welcome to the Admin Dashboard, "+ admin.getUsername()+"!");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Choose an action: ");
        welcomeLabel.setBounds(20,20,200,25);
        add(welcomeLabel);

        /*
        searchBtn = new JButton("Search Movies");
        searchBtn.setBounds(100,60,100,30);
        searchBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SearchGUI(admin).setVisible(true);
            }
        });
        add(searchBtn);


        browseBtn = new JButton("Browse Movies");
        browseBtn.setBounds(100,110,180,30);
        browseBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new BrowseGUI(admin).setVisible(true);
            }
        });
        add(browseBtn);
        */

        JButton manageMovieBtn = new JButton("Manage Movies");
        manageMovieBtn.setBounds(100,60,180,30);
        manageMovieBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new MovieManagementGUI(admin).setVisible(true);
            }
        });
        add(manageMovieBtn);

        JButton manageUserBtn = new JButton("Manage Users");
        manageUserBtn.setBounds(100,110,180,30);
        manageUserBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new UserManagementGUI(admin).setVisible(true);
            }
        });
        add(manageUserBtn);

        JButton viewStatisticsBtn = new JButton("View Statistics");
        viewStatisticsBtn.setBounds(100, 160, 180, 30);
        viewStatisticsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new StatisticsGUI(admin).setVisible(true);
            }
        });
        add(viewStatisticsBtn);
    }
    
}
