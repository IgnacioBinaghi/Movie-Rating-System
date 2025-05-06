package guis;

public class MainGUI extends javax.swing.JFrame {
    private javax.swing.JButton adminBtn;
    private javax.swing.JButton userBtn;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel roleLabel;
    
    public MainGUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        adminBtn = new javax.swing.JButton();
        userBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        welcomeLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome to the Movie Rating System!");

        roleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roleLabel.setText("Proceed as:");

        adminBtn.setText("Admin");
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin_click(evt);
            }
        });

        userBtn.setText("User");
        userBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUser_click(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(adminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(welcomeLabel)
                .addGap(70, 70, 70)
                .addComponent(roleLabel)
                .addGap(26, 26, 26)
                .addComponent(userBtn)
                .addGap(18, 18, 18)
                .addComponent(adminBtn)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btnAdmin_click(java.awt.event.ActionEvent evt) {                                         
        new LoginGUI(true).setVisible(true);  //admin 
        this.dispose();
    }                                        

    private void btnUser_click(java.awt.event.ActionEvent evt) {                                        
        new LoginGUI(false).setVisible(true);  //user 
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
