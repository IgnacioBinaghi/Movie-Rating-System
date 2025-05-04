package guis;

import java.util.ArrayList;

import javax.swing.*;
import models.User;
import managers.ReviewManager;
import managers.UserManager;

public class StatisticsGUI extends JFrame {
    private User admin;

    private ReviewManager reviewManager;
    private UserManager userManager;

    public StatisticsGUI(User admin){
        this.admin = admin;
        this.reviewManager = new ReviewManager();
        this.userManager = new UserManager();
        initComponents();
    }

    private void initComponents(){
        setTitle("Welcome to the Statistics Dashboard, "+ admin.getUsername()+"!");
        setSize(400,400);
        setLayout(null);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel statsLabel = new JLabel("Most Rated Movies / Total Users:");
        statsLabel.setBounds(20,20,300,25);
        add(statsLabel);

        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statsArea);
        scrollPane.setBounds(20, 50, 350, 250);
        add(scrollPane);

        StringBuilder stats = new StringBuilder();
        int totalUsers = userManager.getNumUsers();
        stats.append("Total Users: ").append(totalUsers).append("\n\n");


        stats.append("Most Rated Movies:\n");
        ArrayList<String> mostRatedMovies = reviewManager.getMostRatedMovies();
        for (int i = 0; i < mostRatedMovies.size(); i++) {
            stats.append(i + 1).append(". ").append(mostRatedMovies.get(i)).append("\n");
        }
        statsArea.setText(stats.toString());

        JButton backbtn = new JButton("Back");
        backbtn.setBounds(100,310,180,30);
        backbtn.addActionListener(e -> {
            this.dispose();
            new AdminDashboardGUI(admin).setVisible(true);
        });
        add(backbtn);
    }


}
