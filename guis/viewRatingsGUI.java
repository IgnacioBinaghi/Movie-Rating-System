package guis;

import javax.swing.*;
import java.util.List;
import models.*;
import managers.ReviewManager;

public class viewRatingsGUI extends JFrame {
    private User user;
    private ReviewManager reviewManager;

    public viewRatingsGUI(User user) {
        this.user = user;
        this.reviewManager = new ReviewManager();
        initComponents();
    }

    private void initComponents() {
        setTitle("Your Ratings - " + user.getUsername());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Your Ratings");
        titleLabel.setBounds(20, 20, 200, 25);
        add(titleLabel);

        JTextArea ratingsArea = new JTextArea();
        ratingsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ratingsArea);
        scrollPane.setBounds(20, 50, 350, 200);
        add(scrollPane);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(150, 260, 100, 30);
        backBtn.addActionListener(e -> {
            dispose();
            new UserDashboardGUI(user).setVisible(true);
        });
        add(backBtn);

        List<Review> ratings = reviewManager.getUserReviews(user.getUsername());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ratings.size(); i++){
            sb.append(ratings.get(i));
            if (i != ratings.size() - 1){
                sb.append("\n");
            }
        }
        ratingsArea.setText(sb.toString());

        setVisible(true);
    }
}
