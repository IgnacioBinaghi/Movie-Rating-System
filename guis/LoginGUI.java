package guis;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import managers.UserManager;
import models.User;

public class LoginGUI extends javax.swing.JFrame {

    private boolean admin;
    UserManager userManager = new UserManager();
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField fieldUsername;
    private javax.swing.JTextField fieldPassword;

    public LoginGUI(boolean admin) {
        this.admin = admin;
        initComponents();
        setTitle(admin ? "Admin Login" : "User Login");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(450, 420);
        
        lblTitle = new javax.swing.JLabel("Movie Rating System – Login / Register", javax.swing.SwingConstants.CENTER);
        lblTitle.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.BOLD, 13));
    
        lblUsername = new javax.swing.JLabel("Username:");
        lblPassword = new javax.swing.JLabel("Password:");
        fieldUsername = new javax.swing.JTextField(20);
        fieldPassword = new javax.swing.JTextField(20);
    
        btnLogin = new javax.swing.JButton("Login");
        btnRegister = new javax.swing.JButton("Register");
        btnBack = new javax.swing.JButton("Back");

        java.awt.Dimension btnSize = new java.awt.Dimension(120, 30);
        btnLogin.setPreferredSize(btnSize);
        btnRegister.setPreferredSize(btnSize);
        btnLogin.setMaximumSize(btnSize);
        btnRegister.setMaximumSize(btnSize);
    
        btnLogin.addActionListener(this::btnLogin_click);
        btnRegister.addActionListener(this::btnRegister_click);
        btnBack.addActionListener(this::btnBack_click);
    
        java.awt.Container pane = getContentPane();
        pane.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 10, 5, 10); // uniform padding
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pane.add(lblTitle, gbc);
    
        gbc.gridy = 1;
        gbc.weighty = 0;
        pane.add(javax.swing.Box.createVerticalStrut(80), gbc);
    
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        pane.add(lblUsername, gbc);
    
        gbc.gridx = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pane.add(fieldUsername, gbc);
    
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        pane.add(lblPassword, gbc);
    
        gbc.gridx = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pane.add(fieldPassword, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        pane.add(javax.swing.Box.createVerticalStrut(30), gbc);
    
        JPanel buttonsPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnRegister);
    
        gbc.gridy = 5;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        pane.add(buttonsPanel, gbc);
    
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        pane.add(btnBack, gbc);
    }
    
    private void btnRegister_click(java.awt.event.ActionEvent evt) {   
        String username = fieldUsername.getText().trim();
        String password = fieldPassword.getText().trim();
        boolean success = userManager.registerUser(username, password, this.admin);
        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            User user = userManager.getUser(username);
            if (this.admin) {
                new AdminDashboardGUI(user).setVisible(true); 
                this.dispose();
            }
            else {
                new UserDashboardGUI(user).setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists.");
        }

    }                                           

    private void btnLogin_click(java.awt.event.ActionEvent evt) {                                         
        String username = fieldUsername.getText().trim();
        String password = fieldPassword.getText().trim();

        User user = userManager.authenticate(username, password);
        if (this.admin) { //attempting to log in as admin
            if (user == null || !user.isAdmin()) { 
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }     
            else {
                new AdminDashboardGUI(user).setVisible(true);
                this.dispose();
            }
        }
        else { //attempting to log in as user
            if (user == null || user.isAdmin()) {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            } else {
                new UserDashboardGUI(user).setVisible(true);
                this.dispose();
            }
        }
    }      
    private void btnBack_click(java.awt.event.ActionEvent evt) {
        new MainGUI().setVisible(true); 
        this.dispose();
    }                  

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }                 
}
