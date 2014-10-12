package movies;

import java.util.*;

public class SocialMoviesImpl implements SocialMovies {
    private Map<Integer, Movie> movies;
    private Map<Integer, Customer> customers;

    public SocialMoviesImpl() {
        movies = new HashMap<Integer, Movie>();
        customers = new HashMap<Integer, Customer>();
    }

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
        return customers.get(customerId) != null ? customers.get(customerId).getName() : null;
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
    public void addWatchedMovie(int customerId, int movieId) {
        Customer customer = customers.get(customerId);
        Movie movie = movies.get(movieId);
        if (customer != null && movie != null) {
            customer.watchedMovies.add(movie);
        }
    }

    @Override
    public void addFriend(int customerId1, int customerId2) {
        Customer customer1 = customers.get(customerId1);
        Customer customer2 = customers.get(customerId2);
        if (customer1 != null && customer2 != null) {
            customer1.getFriends().add(customer2);
            customer2.getFriends().add(customer1);
        }
    }

    @Override
    public Collection<Integer> getRecommendationsFromFriends(int customerId) {
        Customer customer = customers.get(customerId);
        Set<Movie> friendsLikedMovies = new HashSet<Movie>();
        for (Customer friend : customer.friends) {
            friendsLikedMovies.addAll(friend.likedMovies);
        }
        friendsLikedMovies.removeAll(customer.likedMovies);
        Collection<Integer> moviesId = new HashSet<Integer>();
        for (Movie movie : friendsLikedMovies) {
            moviesId.add(movie.getMovieId());
        }
        return moviesId;
    }

    @Override
    public Collection<Integer> getFriendRecommendations(int customerId, int minimumCommonFriends) {
        Customer customer = customers.get(customerId);
        Collection<Integer> friendIds = new HashSet<Integer>();
        for (Customer friendCandidate : customers.values()) {
            if (friendCandidate != customer) {
                friendCandidate.getFriends().retainAll(customer.getFriends());
                if (friendCandidate.getFriends().size() >= minimumCommonFriends) {
                    friendIds.add(friendCandidate.getCustomerId());
                }
            }
        }
        return friendIds;
    }
}
