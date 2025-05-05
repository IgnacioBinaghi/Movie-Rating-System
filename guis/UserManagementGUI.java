package guis;

import javax.swing.*;
import models.User;
import managers.UserManager;
import managers.ReviewManager;
import managers.WatchlistManager;

public class UserManagementGUI extends JFrame {
    private User admin;
    private UserManager userManager;
    private ReviewManager reviewManager;
    private WatchlistManager watchlistManager;

    public UserManagementGUI(User admin) {
        this.admin = admin;
        this.userManager = new UserManager();
        this.reviewManager = new ReviewManager();
        this.watchlistManager = new WatchlistManager();
        initComponents();
    }

    private void initComponents() {
        setTitle("User Management");
        setSize(400,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to User Management, " + admin.getUsername() + "!");
        welcomeLabel.setBounds(20,20,300,25);
        add(welcomeLabel);

        JLabel usernameLabel = new JLabel("Enter Username to delete: ");
        usernameLabel.setBounds(20,60,200,25);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(200,60,150,25);
        add(usernameField);


        JButton deleteUserBtn = new JButton("Delete User");
        deleteUserBtn.setBounds(100,100,150,30);
        add(deleteUserBtn);

        deleteUserBtn.addActionListener(e -> {
            String usernameToDelete = usernameField.getText();
            if (userManager.deleteUser(usernameToDelete)) {
                JOptionPane.showMessageDialog(this, "User deleted");
            } else {
                JOptionPane.showMessageDialog(this, "User not found");
            }
        });
    }
}
