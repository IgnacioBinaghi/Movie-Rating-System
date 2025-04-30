package managers;


public class test {
    public static void main(String[] args) {
        // Test UserManager
        UserManager userManager = new UserManager();
        System.out.println("Number of users: " + userManager.getNumUsers());
        System.out.println("Registering user: " + userManager.registerUser("testUser", "password123", false));
        System.out.println("Number of users: " + userManager.getNumUsers());
        System.out.println("Deleting user: " + userManager.deleteUser("testUser"));
        System.out.println("Number of users: " + userManager.getNumUsers());

        // Test MovieManager
        MovieManager movieManager = new MovieManager();
        System.out.println("Editing movie: " + movieManager.editMovie("Inception", "2010", "Thriller"));
        System.out.println("All movies: " + movieManager.getAllMovies());
        System.out.println("Removing movie: " + movieManager.removeMovie("Inception"));
        System.out.println("All movies: " + movieManager.getAllMovies());
    }
}


// javac managers/*.java && java managers/test.java