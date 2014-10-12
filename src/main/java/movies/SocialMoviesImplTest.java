package movies;

import org.junit.Test;

public class SocialMoviesImplTest {

	@Test
	public void test() {
		SocialMoviesImpl sm = new SocialMoviesImpl();
		sm.addCustomer(1, "boris");
		sm.addCustomer(2, "pablo");
		sm.addCustomer(3, "jose");
		sm.addFriend(1, 2);
		sm.addFriend(2, 3);
		sm.addMovie(1, "matrix");
		sm.addMovie(2, "blade runner");
		sm.addMovie(3, "el hobbit");
		sm.addWatchedMovie(1, 1);
		sm.addWatchedMovie(2, 2);
		sm.addLikedMovie(2, 2);
		System.out.println(sm.getRecommendationsFromFriends(1));
		System.out.println(sm.getFriendRecommendations(1, 1));
	}

}
