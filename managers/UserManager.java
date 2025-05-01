package managers;

import models.User; // import user models
import java.util.ArrayList;

import java.io.*;

public class UserManager {
    private ArrayList<User> users;
    
    public UserManager(){
        this.users = new ArrayList<>();
        readUsers();
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) &&
                user.getPassword().equals(password)) {
                return user; 
            }
        }
        return null;
    }


    public boolean registerUser(String username, String password, boolean isAdmin){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        User newUser = new User(username, password, isAdmin);
        users.add(newUser);
        saveUsers();
        return true;
    }

    public boolean deleteUser(String username){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                users.remove(i);
                saveUsers();
                return true;
            }
        }


        return false;
    }

    public int getNumUsers(){ // "View User Statistics"
        return users.size();
    }

    public User getUser(String username){
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        
        return null;
    }

    private void readUsers(){ // read data from users.txt
        try {
            FileReader usersFile = new FileReader("./users.txt");
            BufferedReader br = new BufferedReader(usersFile);
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                String username = userData[0];
                String password = userData[1];
                boolean isAdmin = Boolean.parseBoolean(userData[2]);
                
                User user;
                user = new User(username, password, isAdmin);
                
                this.users.add(user);
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean saveUsers(){ // save new data to users.txt
        try {
            FileWriter moviesFile = new FileWriter("./users.txt");
            BufferedWriter bw = new BufferedWriter(moviesFile);
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                String username = user.getUsername();
                String password = user.getPassword();
                boolean isAdmin = user.isAdmin();

                bw.write(username + "," + password + "," + Boolean.toString(isAdmin));
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return false;

    }
}