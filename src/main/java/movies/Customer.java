package movies;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    protected int customerId;
    protected String name;
    protected Set<Customer> friends = new HashSet<Customer>();
    protected Set<Movie> watchedMovies = new HashSet<Movie>();
    protected Set<Movie> likedMovies = new HashSet<Movie>();

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Customer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Customer> friends) {
        this.friends = friends;
    }

    public Set<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(Set<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public Set<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(Set<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }
}
