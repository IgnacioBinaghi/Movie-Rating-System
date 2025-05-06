package guis;

import javax.swing.*;
import java.util.List;
import models.*;
import managers.ReviewManager;

public class viewRatingsGUI extends JFrame {
    private User user;
    private ReviewManager reviewManager;
    private JLabel titleLabel;
    private JTextArea ratingsArea;
    private JScrollPane scrollPane;
    private DefaultComboBoxModel<String> reviewedTitlesModel;
    private JComboBox<String> reviewDropdown;
    private JButton deleteReviewBtn;

    public viewRatingsGUI(User user) {
        this.user = user;
        this.reviewManager = new ReviewManager();
        initComponents();
    }

    private void initComponents() {
        setTitle("Your Ratings - " + user.getUsername());
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Your Ratings");
        titleLabel.setBounds(20, 20, 200, 25);
        add(titleLabel);

        ratingsArea = new JTextArea();
        ratingsArea.setEditable(false);
        scrollPane = new JScrollPane(ratingsArea);
        scrollPane.setBounds(20, 50, 350, 200);
        add(scrollPane);

        List<Review> ratings = reviewManager.getUserReviews(user.getUsername());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ratings.size(); i++){
            String title = ratings.get(i).getMovieTitle();
            String rating = String.valueOf(ratings.get(i).getRating());
            sb.append("Movie: ").append(title).append(", Rating: ").append(rating).append("\n");
        }
        ratingsArea.setText(sb.toString());

        reviewedTitlesModel  = new DefaultComboBoxModel<>();
        for (Review r: ratings){
            reviewedTitlesModel.addElement(r.getMovieTitle());

        }
        reviewDropdown = new JComboBox<>(reviewedTitlesModel);
        reviewDropdown.setBounds(20, 270, 200, 25);
        add(reviewDropdown);

        deleteReviewBtn = new JButton("Delete selected review.");
        deleteReviewBtn.setBounds(230, 270, 150, 25);
        add(deleteReviewBtn);
        
        // delete review w confirm (same logic as in MovieDetailGUI)
        deleteReviewBtn.addActionListener(e-> {
            String selectedTitle = (String) reviewDropdown.getSelectedItem();
            if (selectedTitle!= null){
                int confirm = JOptionPane.showConfirmDialog(this, "Delete review?","Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    boolean deleted = reviewManager.deleteReview(user.getUsername(), selectedTitle);
                    if (deleted){
                        JOptionPane.showMessageDialog(this, "Review has been deleted.");
                        dispose();
                        new viewRatingsGUI(user).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete review.");
                    }
                }
            }
        });

        setVisible(true);
    }
}
