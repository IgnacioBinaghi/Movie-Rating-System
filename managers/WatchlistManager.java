package managers;

import models.WatchlistEntry;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WatchlistManager {
    private ArrayList<WatchlistEntry> watchlist;
    
    public WatchlistManager(){
        this.watchlist = new ArrayList<>();
        readWatchlist();
    }

    private void readWatchlist(){
        try (BufferedReader reader = new BufferedReader(new FileReader("./watchlists.txt"))){
            String line;
            while((line=reader.readLine())!=null){
                String[] parts = line.split(",");
                if (parts.length==2){
                    watchlist.add(new WatchlistEntry(parts[0], parts[1]));

                }
            }
        } catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
    
    private void saveWatchlist(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./watchlists.txt"))){
            for (WatchlistEntry entry:watchlist){
                writer.write(entry.getUsername()+","+entry.getMovieTitle());
                writer.newLine();
            }
        }
        catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public boolean addToWatchlist(String username, String movieTitle){
        for (WatchlistEntry entry: watchlist){
            if (entry.getUsername().equalsIgnoreCase(username) && entry.getMovieTitle().equalsIgnoreCase(movieTitle)){
                return false;
            }
        }
        watchlist.add(new WatchlistEntry(username, movieTitle));
        saveWatchlist();
        return true;
    }
    public boolean removeFromWatchlist(String username, String movieTitle){
        for (int i=0; i<watchlist.size(); i++){
            WatchlistEntry entry = watchlist.get(i);
            if (entry.getUsername().equalsIgnoreCase(username) && entry.getMovieTitle().equalsIgnoreCase(movieTitle)){
                watchlist.remove(i);
                saveWatchlist();
                return true;
            }
        }
        return false;
    }

    public List<String> getWatchlist(String username){
        List<String> userWatchlist = new ArrayList<>();
        for (WatchlistEntry entry:watchlist){
            if (entry.getUsername().equalsIgnoreCase(username)){
                userWatchlist.add(entry.getMovieTitle());
            }
        }
        return userWatchlist;
    }

    public boolean isInWatchlist(String username, String movieTitle){
        for (WatchlistEntry entry:watchlist){
            if(entry.getUsername().equalsIgnoreCase(username) && entry.getMovieTitle().equalsIgnoreCase(movieTitle)){
                return true;
            }
        }
        return false;
    }
}