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
    private JLabel welcomeLabel, usernameLabel, createUserLabel, newUsernameLabel, newPasswordLabel;
    private JTextField usernameField, newUsernameField, newPasswordField;
    private JButton deleteUserBtn, createUserBtn;


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

        welcomeLabel = new JLabel("Welcome to User Management, " + admin.getUsername() + "!");
        welcomeLabel.setBounds(20,20,300,25);
        add(welcomeLabel);

        usernameLabel = new JLabel("Enter Username to delete: ");
        usernameLabel.setBounds(20,60,200,25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200,60,150,25);
        add(usernameField);


        deleteUserBtn = new JButton("Delete User");
        deleteUserBtn.setBounds(100,100,150,30);
        add(deleteUserBtn);
        
        createUserLabel = new JLabel("Or create new Film Reviewer:");
        createUserLabel.setBounds(20, 140, 200, 25);
        add(createUserLabel);

        newUsernameLabel = new JLabel("Username:");
        newUsernameLabel.setBounds(20, 170, 100, 25);
        add(newUsernameLabel);

        newUsernameField = new JTextField();
        newUsernameField.setBounds(100, 170, 250, 25);
        add(newUsernameField);

        newPasswordLabel = new JLabel("Password:");
        newPasswordLabel.setBounds(20, 200, 100, 25);
        add(newPasswordLabel);

        newPasswordField = new JTextField();
        newPasswordField.setBounds(100, 200, 250, 25);
        add(newPasswordField);

        createUserBtn = new JButton("Create User");
        createUserBtn.setBounds(100, 240, 150, 30);
        add(createUserBtn);

        deleteUserBtn.addActionListener(e -> {
            String usernameToDelete = usernameField.getText();
            if (userManager.deleteUser(usernameToDelete)) {
                JOptionPane.showMessageDialog(this, "User deleted");
            } else {
                JOptionPane.showMessageDialog(this, "User not found");
            }
        });
        createUserBtn.addActionListener(e -> {
            String newUsername = newUsernameField.getText().trim();
            String newPassword = newPasswordField.getText().trim();
            if (userManager.registerUser(newUsername, newPassword, false)) {
                JOptionPane.showMessageDialog(this, "User created successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            }
        });
    }
}
