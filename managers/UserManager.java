package managers;

import models.User; // import user models
import models.Admin;

import java.io.*;

public class UserManager {
    private List<User> users;
    private final String userFile = "users.txt";
    
    public UserManager(){
        users = new ArrayList<>();
        readUsers();
    }


    public boolean registerUser(String username, String password){
        // insert later
    }

    public boolean deleteUser(String username){
        // insert later
    }

    public int getNumUsers(){ // "View User Statistics"
        return users.size();
    }

    private User readUsers(){ // read data from users.txt

    }

    private User saveUsers(){ // save new data to users.txt

    }
}