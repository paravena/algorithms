package movies;

import basic.Queue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SocialMovies2Impl implements SocialMovies2 {
    protected Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
    protected Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

    @Override
    public void addMovie(int movieId, String title) {
        movies.put(movieId, new Movie(movieId, title));
    }

    @Override
    public String lookupMovie(int movieId) {
        return movies.get(movieId) != null ? movies.get(movieId).getTitle() : null;
    }

    @Override
    public void addCustomer(int customerId, String name) {
        customers.put(customerId, new Customer(customerId, name));
    }

    @Override
    public String lookupCustomer(int customerId) {
        return customers.get(customerId) != null ? customers.get(customers).getName() : null;
    }

    @Override
    public void addLikedMovie(int customerId, int movieId) {
        Customer customer = customers.get(customerId);
        Movie movie = movies.get(movieId);
        if (customer != null && movie != null) {
            customer.likedMovies.add(movie);
        }
    }

    @Override
    public void addFriend(int customerId1, int customerId2) {
        Customer customer1 = customers.get(customerId1);
        Customer customer2 = customers.get(customerId2);
        if (customer1 != null && customer2 != null) {
            customer1.friends.add(customer2);
            customer2.friends.add(customer1);
        }
    }

    public void bfs(int customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) return;
        Queue<Customer> queue = new Queue<Customer>();
        customer.setDistance(0);
        customer.setFriendOf(null);
        queue.enqueue(customer);
        while (!queue.isEmpty()) {
            Customer currentCustomer = queue.dequeue();
            for (Customer friend : currentCustomer.getFriends()) {
                if (!friend.isVisited()) {
                    friend.setDistance(currentCustomer.getDistance() + 1);
                    friend.setFriendOf(currentCustomer);
                    queue.enqueue(friend);
                }
                friend.setVisited(true);
            }
        }
    }
    // Returns the ID of the customer who has the most "likes" in common with the given
    // customer.  The returned customer must be reachable by at most <maxDistance> friend links.
    @Override
    public int getSimilarCustomerInNetwork(int customerId, int maxDistance) {
        return 0;
    }

    // Returns the IDs of the <maximumResults> movies with the highest total number of "likes".  All movies
    // returned must have a "like" count >= the like count of every non-returned movie.
    @Override
    public Collection<Integer> getMostLikedMovies(int maximumResults) {
        return null;
    }
}
