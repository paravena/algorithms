package movies;

import java.util.Collection;

// Maintains a network of movies and customers
// All methods should return an empty collection, -1, or null on failure, as appropriate
public interface SocialMovies2 {

    // Defines a movie ID to title mapping in the system
    void addMovie(int movieId, String title);

    // Returns the title of the given movie
    String lookupMovie(int movieId);

    // Defines a customer ID to name mapping in the system
    void addCustomer(int customerId, String name);

    // Returns the name of the given customer
    String lookupCustomer(int customerId);

    // Record that a movie was "liked" by the given customer
    void addLikedMovie(int customerId, int movieId);

    // Associate two customers as being friends
    void addFriend(int customerId1, int customerId2);

    // Returns the ID of the customer who has the most "likes" in common with the given
    // customer.  The returned customer must be reachable by at most <maxDistance> friend links.
    int getSimilarCustomerInNetwork(int customerId, int maxDistance);

    // Returns the IDs of the <maximumResults> movies with the highest total number of "likes".  All movies
    // returned must have a "like" count >= the like count of every non-returned movie.
    Collection<Integer> getMostLikedMovies(int maximumResults);
}